package b_if;

/******************************************************************************
 * 数くらべ
 *****************************************************************************/
public class Kadai1600{
    public static void main(String[] args){
        //整数の入力
        int a = Keyboard.readInt("値");
        
        //値の範囲を判断し、結果を表示
        if(a >= 0 && a <= 10){
            System.out.println("0～10です");
        }else{
            System.out.println("0～10ではありません");
        }
    }
}
