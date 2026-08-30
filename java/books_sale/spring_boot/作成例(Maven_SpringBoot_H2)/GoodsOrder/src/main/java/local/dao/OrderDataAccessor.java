package local.dao;

import local.beans.Order;
import local.beans.OrderCondition;
import local.beans.Item;
import local.exceptions.CannotAddOrderException;
import local.exceptions.CannotSearchOrdersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderDataAccessor{
    private final JdbcTemplate jdbc;

    @Autowired
    public OrderDataAccessor(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    /***************************************************************************
     注文の登録
     ***************************************************************************/
    @Transactional
    public int insert(Order newOrder) throws CannotAddOrderException{
/*
注文番号をauto_incrementに変更したことにより前半の処理がごっそり不要になったが、
H2データベースに関する調査結果のメモがもったいないので、コメントで残しておく

        //最新注文番号の読込み
        int order_no = 0;
        
        try{
            //H2データベースはFOR UPDATEに集約関数が使えない
            //また、ロックを取得するための特別なコマンドもないようだ
            //仕方ないのでFOR UPDATEのための空SELECTを実行
            //これの実行に時間がかからないようにLIMIT 1を付加
            //H2には行ロックがないそうなので、これで表ロックがかかる
            String sql = "SELECT no FROM orders LIMIT 1 FOR UPDATE NOWAIT";
            jdbc.queryForList(sql);
            sql = "SELECT MAX(no) AS max_no FROM orders";
            order_no = jdbc.queryForObject(sql, Integer.class);
        }catch(DataAccessException e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }catch(NullPointerException e) {
            //order_noは0のまま
        }

        //注文情報の登録
        order_no++;
*/
        try {
            //30万件のレコードがあると、INSERTに10秒くらいかかることがある
            //これがH2の性能なのかもしれない
            String sql = "INSERT INTO orders(orderdate, itemCode, quantity, sei, mei, pref, address, tel, mail)";
            sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int num = jdbc.update(
                    sql,
                    newOrder.getDatetime().toString(),
                    newOrder.getItem().getCode(),
                    newOrder.getQuantity(),
                    newOrder.getSei(),
                    newOrder.getMei(),
                    newOrder.getPref(),
                    newOrder.getAdd(),
                    newOrder.getTel(),
                    newOrder.getMail()
            );

            if (num == 0) {
                System.out.println("注文INSERTの結果行が0");
                throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
            }
            
            sql = "SELECT SCOPE_IDENTITY()";
            int order_no = jdbc.queryForObject(sql, Integer.class);

            newOrder.setNo(order_no);
        }catch(Exception e){
            e.printStackTrace();
            throw new CannotAddOrderException("注文登録できませんでした。やり直してください。");
        }
        return order_no;
    }
    
    /***************************************************************************
     注文の検索
     ***************************************************************************/
    public ArrayList<Order> select(OrderCondition key) throws CannotSearchOrdersException {
        ArrayList<Order> list = new ArrayList<Order>();
        try{
            String sql = "SELECT no, orderdate, items.code AS itemCode, items.name AS itemName, items.price AS itemPrice, quantity, sei, mei, pref, address, tel, mail FROM orders INNER JOIN items ON orders.itemCode = items.code ";
            sql += "WHERE no LIKE ? AND orderdate BETWEEN ? AND ? AND CONCAT(sei, mei) LIKE ? AND tel LIKE ? AND items.name LIKE ? ORDER BY no";

            //条件設定
            //  注文番号
            int ikey_no = key.getNo();
            String key_no = "%";
            if(ikey_no > 0){
                key_no = "" + ikey_no;
            }

            //  注文日時
            LocalDateTime[] dates = {key.getDatetime(), key.getEnddate()};
            String[] strdate = new String[dates.length];
            for(int i = 0; i < dates.length; i++){
                if(dates[i] != null){
                    strdate[i] = dates[i].toString();
                }else{
                    if(i == 0){
                        strdate[i] = "0000-01-01T00:00:00";
                    }else{
                        strdate[i] = "9999-12-31T23:59:59";
                    }
                }
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

            //  電話番号
            String key_tel = key.getTel();
            if(key_tel == null || key_tel.equals("")){
                key_tel = "%";
            }

            //  商品名
            Item key_item = key.getItem();
            String key_itemname = "%";
            if(key_item != null){
                key_itemname += key_item.getName() + "%";
            }

            //検索実行
            List<Map<String, Object>> rs = jdbc.queryForList(
                sql,
                key_no,
                strdate[0],
                strdate[1],
                key_name,
                key_tel,
                key_itemname
            );
            for(Map<String, Object> row : rs){
                Order order = new Order();
                order.setNo(Integer.parseInt(row.get("no").toString()));
                order.setDatetime(LocalDateTime.parse(row.get("orderdate").toString().replace(" ","T")));
                
                Item item = new Item(
                    row.get("itemCode").toString(),
                    row.get("itemName").toString(),
                    Integer.parseInt(row.get("itemPrice").toString())
                );
                order.setItem(item);
                
                order.setQuantity(Integer.parseInt(row.get("quantity").toString()));
                order.setSei(row.get("sei").toString());
                order.setMei(row.get("mei").toString());
                order.setPref(row.get("pref").toString());
                order.setAdd(row.get("address").toString());
                Object dat = row.get("tel");
                if(dat != null){
                    order.setTel(dat.toString());
                }
                dat = row.get("mail");
                if(dat != null) {
                    order.setMail(dat.toString());
                }

                list.add(order);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new CannotSearchOrdersException("検索できませんでした。やり直してください。");
        }
        return list;
    }
}
