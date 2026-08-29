package dao;

import beans.Order;
import beans.OrderCondition;
import beans.Item;
import exceptions.CannotAddOrderException;
import exceptions.CannotSearchOrdersException;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDataAccessor{
    
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /***************************************************************************
     データベースへの接続
    ***************************************************************************/
    private Connection connect() throws NamingException, SQLException{
        Context context = new InitialContext();
        String name = "java:comp/env/jdbc/bookorder";
        DataSource ds = (DataSource) context.lookup(name);
        return ds.getConnection();
    }
    
    /***************************************************************************
     注文の登録
     ***************************************************************************/
    public int insert(Order newOrder) throws CannotAddOrderException{
        
        try(Connection conn = connect()){
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            
            //最新注文番号の読込み
            int order_no = 0;
            String sql = "SELECT MAX(no) AS max_no FROM orders FOR UPDATE";
            try(
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
            ){
                while(rs.next()){
                    order_no = rs.getInt("max_no");
                }
            }catch(SQLException e){
                e.printStackTrace();
                conn.rollback();
                throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
            }

            //注文情報の登録
            order_no++;
        
            sql = "INSERT INTO orders(no, orderdate, itemCode, quantity, sei, mei, pref, address, tel, mail)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
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
                newOrder.setNo(order_no);
                return order_no;
            }catch(SQLException e){
                e.printStackTrace();
                conn.rollback();
                throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
            }
        }catch(NamingException e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }catch(SQLException e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }
    }
    
    /***************************************************************************
     注文の検索
     ***************************************************************************/
    public ArrayList<Order> select(OrderCondition key) throws CannotSearchOrdersException {
        try(Connection conn = connect()){
            String sql = "SELECT no, orderdate, items.code AS itemCode, items.name AS itemName, items.price AS itemPrice, quantity, sei, mei, pref, address, tel, mail FROM orders INNER JOIN items ON orders.itemCode = items.code"
                       + " WHERE no LIKE ? AND orderdate BETWEEN ? AND ? AND CONCAT(sei, mei) LIKE ? AND tel LIKE ? AND items.name LIKE ? ORDER BY no";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
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
                try(ResultSet rs = ps.executeQuery()){
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
                }
            }
        }catch(NamingException e){
            e.printStackTrace();
            throw new CannotSearchOrdersException("検索できませんでした。やり直してください。");
        }catch(SQLException e){
            e.printStackTrace();
            throw new CannotSearchOrdersException("検索できませんでした。やり直してください。");
        }
    }
}
