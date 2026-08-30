import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/*
  東日本アビエーションのために作成したダミー会員を、書籍販売システムの購入者に変換するプログラム
*/
public class MakeUser {
    private record User(
        String sei,
        String mei,
        String pref,
        String add,
        String tel,
        String mail
    ){};

    private static Random r = new Random();
    
    public static void main(String[] args){
        ArrayList<User> list = new ArrayList<>();
        try(
            FileReader fr = new FileReader("users.csv");
            BufferedReader br = new BufferedReader(fr);
        ){
            String line =  null;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                
                
                list.add(new User(
                    values[1], values[2],
                    values[6].substring(0,3),
                    values[6].substring(3),
                    values[7],
                    makeMail()
                ));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }catch(IOException e){
            e.printStackTrace();
            return;
        }

        try(
            FileWriter fw = new FileWriter("bookuser.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
        ){
            for(User u : list){
                pw.println(u.sei + "," + u.mei + "," + u.pref + "," + u.add + "," + u.tel + "," + u.mail);
            }
            pw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //メールアドレス適当作成
    private static String makeMail(){
        if(r.nextInt(10) < 1){
            return "";
        }
        
        String[] kinds = {".ne.jp", ".com", ".jp"};
        int boxlen = r.nextInt(6) + 5;
        int domainlen = r.nextInt(5) + 4;
        int kind = r.nextInt(3);

        StringBuffer box = new StringBuffer();
        for(int i = 0; i < boxlen; i++){
            box.append((char)(r.nextInt(26) + 97));
        }
        StringBuffer domain = new StringBuffer();
        for(int i = 0; i < domainlen; i++){
            domain.append((char)(r.nextInt(26) + 97));
        }

        return box + "@" + domain + kinds[kind];
    }
}
