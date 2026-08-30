package dao;

import beans.Order;
import beans.OrderCondition;
import beans.Item;
import exceptions.CannotAddOrderException;
import exceptions.CannotSearchOrdersException;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDataAccessor{
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /***************************************************************************
     データベースへの接続
    ***************************************************************************/
    private boolean connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql:///BookOrder?allowPublicKeyRetrieval=true&useSSL=false";
            String user = "root";
            String pass = "pass";
            conn = DriverManager.getConnection(url, user, pass);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /***************************************************************************
     データベースからの切断
    ***************************************************************************/
    private boolean close(){
        boolean result = true;
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
                result = false;
            }
        }
        if(ps != null){
            try{
                ps.close();
            }catch(SQLException e){
                e.printStackTrace();
                result = false;
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }
    
    /***************************************************************************
     注文の登録
     ***************************************************************************/
    public int insert(Order newOrder) throws CannotAddOrderException{
        if(!connect()){
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }
        
        //最新注文番号の読込み
        int order_no = 0;
        
        try{
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            String sql = "SELECT MAX(no) AS max_no FROM orders FOR UPDATE";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                order_no = rs.getInt("max_no");
            }
        }catch(SQLException e){
            e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException er){
                er.printStackTrace();
            }
            close();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }

        //注文情報の登録
        order_no++;
        
        try{
            String sql = "INSERT INTO orders(no, orderdate, itemCode, quantity, sei, mei, pref, address, tel, mail)";
            sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, order_no);
            ps.setString(2, newOrder.getDatetime().format(dateFormat));
            ps.setString(3, newOrder.getItem().getCode());
            ps.setInt(4, newOrder.getQuantity());
            ps.setString(5, newOrder.getSei());
            ps.setString(6, newOrder.getMei());
            ps.setString(7, newOrder.getPref());
            ps.setString(8, newOrder.getAdd());
            ps.setString(9, newOrder.getTel());
            ps.setString(10, newOrder.getMail());

            int num = ps.executeUpdate();
            if(num == 0){
                conn.rollback();
                throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
            }
            conn.commit();
        }catch(SQLException e){
            e.printStackTrace();
            try{
                conn.rollback();
            }catch(SQLException er){
                er.printStackTrace();
            }
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }finally{
            close();
        }
        
        newOrder.setNo(order_no);
        return order_no;
    }
    
    /***************************************************************************
     注文の検索
     ***************************************************************************/
    public ArrayList<Order> select(OrderCondition key) throws CannotSearchOrdersException {
        if(!connect()){
            throw new CannotSearchOrdersException("検索できませんでした。やり直してください。");
        }
        
        try{
            String sql = "SELECT no, orderdate, items.code AS itemCode, items.name AS itemName, items.price AS itemPrice, quantity, sei, mei, pref, address, tel, mail FROM orders INNER JOIN items ON orders.itemCode = items.code ";
            sql += "WHERE no LIKE ? AND orderdate BETWEEN ? AND ? AND CONCAT(sei, mei) LIKE ? AND tel LIKE ? AND items.name LIKE ? ORDER BY no";
            ps = conn.prepareStatement(sql);
            
            //条件設定
            //  注文番号
            int ikey_no = key.getNo();
            String key_no = "%";
            if(ikey_no > 0){
                key_no = "" + ikey_no;
            }
            ps.setString(1, key_no);
            
            //  注文日時
            LocalDateTime[] dates = {key.getDatetime(), key.getEnddate()};
            for(int i = 0; i < dates.length; i++){
                String date = null;
                if(dates[i] != null){
                    date = dates[i].format(dateFormat);
                }else{
                    if(i == 0){
                        date = "0-0-0 0:0:0";
                    }else{
                        date = "9999-12-31 23:59:59";
                    }
                }
                ps.setString((i + 2), date);
            }
            
            //  氏名
            String key_name = "%";
            String key_sei = key.getSei();
            if(key_sei != null){
                key_name += key_sei;
            }
            String key_mei = key.getMei();
            if(key_mei != null){
                key_name += key_mei;
            }
            key_name += "%";
            ps.setString(4, key_name);
            
            //  電話番号
            String key_tel = key.getTel();
            if(key_tel == null || key_tel.equals("")){
                key_tel = "%";
            }
            ps.setString(5, key_tel);
            
            //  商品名
            Item key_item = key.getItem();
            String key_itemname = "%";
            if(key_item != null){
                key_itemname += key_item.getName() + "%";
            }
            ps.setString(6, key_itemname);
            
            //検索実行
            rs = ps.executeQuery();
            ArrayList<Order> list = new ArrayList<Order>();
            while(rs.next()){
                Order order = new Order();
                order.setNo(rs.getInt("no"));
                
                order.setDatetime(LocalDateTime.parse(rs.getString("orderdate"), dateFormat));
                
                Item item = new Item(rs.getString("itemCode"), rs.getString("itemName"), rs.getInt("itemPrice"));
                order.setItem(item);
                
                order.setQuantity(rs.getInt("quantity"));
                order.setSei(rs.getString("sei"));
                order.setMei(rs.getString("mei"));
                order.setPref(rs.getString("pref"));
                order.setAdd(rs.getString("address"));
                order.setTel(rs.getString("tel"));
                order.setMail(rs.getString("mail"));
                list.add(order);
            }
            return list;
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new CannotSearchOrdersException("検索できませんでした。やり直してください。");
        }finally{
            close();
        }
    }
}
