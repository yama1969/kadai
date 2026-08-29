import java.io.*;

public class Number{
    public static void main(String[] args){
        System.out.println();
        System.out.println("0～15の整数をひとつ思い浮かべてください。");
        System.out.println();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            int n = 0;
            
            System.out.println("この中に思い浮かべた数はありますか？");
            System.out.println("1,3,5,7,9,11,13,15");
            System.out.println("Yes : 1  No : 1以外");
            String line = reader.readLine();
            int ans = Integer.parseInt(line);
            if(ans == 1){
                n = n + 1;
            }
            System.out.println();
            
            System.out.println("この中に思い浮かべた数はありますか？");
            System.out.println("2,3,6,7,10,11,14,15");
            System.out.println("Yes : 1  No : 1以外");
            line = reader.readLine();
            ans = Integer.parseInt(line);
            if(ans == 1){
                n = n + 2;
            }
            System.out.println();
            
            System.out.println("この中に思い浮かべた数はありますか？");
            System.out.println("4,5,6,7,12,13,14,15");
            System.out.println("Yes : 1  No : 1以外");
            line = reader.readLine();
            ans = Integer.parseInt(line);
            if(ans == 1){
                n = n + 4;
            }
            System.out.println();
            
            System.out.println("この中に思い浮かべた数はありますか？");
            System.out.println("8,9,10,11,12,13,14,15");
            System.out.println("Yes : 1  No : 1以外");
            line = reader.readLine();
            ans = Integer.parseInt(line);
            if(ans == 1){
                n = n + 8;
            }
            System.out.println();
            System.out.println("あなたの思い浮かべた数は" + n + "ですね。");
        }catch(IOException e){
            System.out.println(e);
        }catch(NumberFormatException e){
            System.out.println("整数を入力してください。");
        }
    }
}
