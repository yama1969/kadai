/*
  書籍販売システムの注文データを作成するプログラム
  商品情報はデータベースから読み込み、bookusers.csvのユーザと適当に合わせてに注文データを作成する。
  作成した注文データは早速insertすると共に、csvファイルにも保存する。
  
  これで一石二鳥！・・・と思いきや、Javaからのinsertには時間がかかるようで、
  あとでcsvから一括インポートした方がよっぽど早い。
  
  例えば5年分の44,000件データの場合、
  このプログラム → 70分ほどかかる
  後からインポート → 5分もあれば終わる
  
  ネタにはなるかもなので取っといている
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class InsertSales {
    private record User(
        String sei,
        String mei,
        String pref,
        String add,
        String tel,
        String mail
    ){};

    public static void main(String[] args) {
        Random r = new Random();
        Scanner in = new Scanner(System.in, "Shift-JIS");

        //作成条件の設定 ----------------------------------------------------------------------
        System.out.print("元期 (yyyy-MM-ddThh:mm) => ");
        String sdate = in.nextLine();
        
        LocalDateTime date = LocalDateTime.now();
        if(!sdate.equals("")){
            try{
                date = LocalDateTime.parse(sdate);
            }catch(DateTimeParseException e){
                System.out.println("元期年月日の形式が不正です。");
                in.close();
                return;
            }
        }

        System.out.print("注文件数 (約5年 44000件) => ");
        String smax = in.nextLine();
        in.close();

        int max = r.nextInt(8001) + 2000;
        if(!smax.equals("")){
            try{
                max = Integer.parseInt(smax);
            }catch(NumberFormatException e){
                System.out.println("作成データ数が不正です。");
                return;
            }
        }
        
        //購入者情報の読み込み ----------------------------------------------------------
        ArrayList<User> users = new ArrayList<>();
        try(
            FileReader fr = new FileReader("bookusers.csv");
            BufferedReader br = new BufferedReader(fr);
        ){
            String line =  null;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                String mail = "";
                if(values.length > 5){
                    mail = values[5];
                }
                users.add(new User(values[0],values[1],values[2],values[3],values[4],mail));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try(
            Connection con = connect();
        ){
            //商品コードの読み込み --------------------------------------------------------------
            ArrayList<String> items = new ArrayList<>();
            String sql = "SELECT code FROM items";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                items.add(rs.getString("code"));
            }
            rs.close();
            ps.close();
            
            sql = "DELETE FROM orders";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();

            //注文情報の登録 -------------------------------------------------------------------
            try(
                FileWriter fw = new FileWriter("bookorders.csv");
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
            ){
                sql = "INSERT INTO orders(no, orderdate, itemCode, quantity, sei, mei, pref, address, tel, mail) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                for(int no = 1; no <= max; no++){
                    String item = items.get(r.nextInt(items.size()));
                    User user = users.get(r.nextInt(users.size()));
                    date = date.plusMinutes((long)(r.nextInt(101) + 10));
                    int quantity = r.nextInt(5) + 1;

                    ps.setInt(1, no);
                    ps.setString(2, date.toString().replace("T", " "));
                    ps.setString(3, item);
                    ps.setInt(4, quantity);
                    ps.setString(5, user.sei);
                    ps.setString(6, user.mei);
                    ps.setString(7, user.pref);
                    ps.setString(8, user.add);
                    ps.setString(9, user.tel);
                    ps.setString(10, user.mail);

                    ps.executeUpdate();
                    //pw.println(ps.toString().substring(43) + ";");
                    pw.println(
                          no + ","
                        + date.toString().replace("T", " ") + ","
                        + item + ","
                        + quantity + ","
                        + user.sei + ","
                        + user.mei + ","
                        + user.pref + ","
                        + user.add + ","
                        + user.tel + ","
                        + user.mail
                    );
                    System.out.println(no + "/" + max);
                }
                pw.flush();
                ps.close();
            }catch(IOException e){
                e.printStackTrace();
                return;
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //データベースへの接続
    private static Connection connect() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql:///BookOrder";
        String user = "root";
        String pass = "pass";
        return DriverManager.getConnection(url, user, pass);
    }
}
