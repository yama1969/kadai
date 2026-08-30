import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 キーボード入力担当クラス
*/
public class Keybord{
    //-----整数値入力（異常時には負の最小値を返す）---------------------------------------
    public int inputNum(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.MIN_VALUE;
        try{
            String line = reader.readLine();
            num = Integer.parseInt(line);
        }catch(IOException e){
            num = Integer.MIN_VALUE;
        }catch(NumberFormatException e){
            num = Integer.MIN_VALUE;
        }
        return num;
    }
    
    //-----文字列入力（異常時にはnullを返す）---------------------------------------------
    public String inputString(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try{
            line = reader.readLine();
        }catch(IOException e){
            line = null;
        }
        return line;
    }
}
