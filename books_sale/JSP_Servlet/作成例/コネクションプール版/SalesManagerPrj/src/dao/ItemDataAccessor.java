package dao;

import beans.Item;
import exceptions.CannotSearchItemsException;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDataAccessor{
    
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
     商品検索
    ***************************************************************************/
    public ArrayList<Item> select(Item key) throws CannotSearchItemsException {
        try(Connection conn = connect()){
            String sql = "SELECT code, name, price FROM items WHERE code LIKE ? AND name LIKE ?";
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                //条件設定
                String code = key.getCode();
                if(code == null){
                    code = "";
                }
                code = "%" + code + "%";
                ps.setString(1, code);
                
                String name = key.getName();
                if(name == null){
                    name = "";
                }
                name = "%" + name + "%";
                ps.setString(2, name);
                
                //検索実行
                try(ResultSet rs = ps.executeQuery()){
                    ArrayList<Item> result = new ArrayList<Item>();
                    while(rs.next()){
                        String codedat = rs.getString("code");
                        String namedat = rs.getString("name");
                        int pricedat = rs.getInt("price");
                        result.add(new Item(codedat, namedat, pricedat));
                    }
                    return result;
                }
            }
        }catch(NamingException e){
            e.printStackTrace();
            throw new CannotSearchItemsException("検索できませんでした。やり直してください。");
        }catch(SQLException e){
            e.printStackTrace();
            throw new CannotSearchItemsException("検索できませんでした。やり直してください。");
        }
    }
}
