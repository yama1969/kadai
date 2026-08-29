import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
  書籍販売システムの注文データを作成するプログラム
  おおよそ1時間に1注文
*/
public class MakeSales {
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

        //商品コードの読み込み ------------------------------------------------------------------
        ArrayList<String> items = new ArrayList<>();
        try(
            FileReader fr = new FileReader("bookitems.csv");
            BufferedReader br = new BufferedReader(fr);
        ){
            String line =  null;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                items.add(values[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //注文情報の作成 --------------------------------------------------------------------------
        try(
            FileWriter fw = new FileWriter("bookorders.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
        ){
            for(int no = 1; no <= max; no++){
                String item = items.get(r.nextInt(items.size()));
                User user = users.get(r.nextInt(users.size()));
                date = date.plusMinutes((long)(r.nextInt(101) + 10));
                int quantity = r.nextInt(5) + 1;

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
                System.out.println(no + "/" + max + " " + date);
            }
            pw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
