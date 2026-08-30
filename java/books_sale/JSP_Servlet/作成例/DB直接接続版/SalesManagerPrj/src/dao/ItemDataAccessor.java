package dao;

import beans.Item;
import exceptions.CannotSearchItemsException;

import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDataAccessor{
    
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
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
     商品検索
    ***************************************************************************/
    public ArrayList<Item> select(Item key) throws CannotSearchItemsException {
        if(!connect()){
            throw new CannotSearchItemsException("検索できませんでした。やり直してください。");
        }
        
        try{
            String sql = "SELECT code, name, price FROM items WHERE code LIKE ? AND name LIKE ?";
            ps = conn.prepareStatement(sql);
            
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
            rs = ps.executeQuery();
            ArrayList<Item> result = new ArrayList<Item>();
            while(rs.next()){
                String codedat = rs.getString("code");
                String namedat = rs.getString("name");
                int pricedat = rs.getInt("price");
                result.add(new Item(codedat, namedat, pricedat));
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
            throw new CannotSearchItemsException("検索できませんでした。やり直してください。");
        }finally{
            close();
        }
    }
}
