import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TransCardinal{
    public static void main(String[] args){
        int num = 0;                                                            //変換前10進数
        String bin = "";                                                        //変換後2進数文字列
        
        System.out.print("10進数整数を入力して下さい。(-21億～+21億)：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            num = Integer.parseInt(reader.readLine());
            int sign = 1;
            if(num < 0){
                sign = -1;
                num = -num;
            }
            if(num == 0){
                bin = "0";
            }
            while(num != 0){
                bin = (num % 2) + bin;
                num /= 2;
            }
            if(sign < 0){
                bin = "-" + bin;
            }
            System.out.println(bin);
        }catch(IOException e){
            System.out.println("キーボードエラーです。プログラムを停止します。");
        }catch(NumberFormatException e){
            System.out.println("数値ではありません。変換は不能です。");
        }
    }
}
