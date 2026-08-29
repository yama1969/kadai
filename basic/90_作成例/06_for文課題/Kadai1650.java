/******************************************************************************
 * 入力された数値の合計を計算
 *****************************************************************************/
public class Kadai1650{
    public static void main(String[] args){
        //変数の準備
        int sum = 0;     //入力値の合計
        
        //数値入力と加算を10回繰り返す
        for(int i = 0; i < 10; i++){
            int num = Keyboard.readInt("整数[あと" + (10 - i) + "個]");
            sum += num;
            System.out.println("累計 = " + sum);
            System.out.println();
        }
    }
}
