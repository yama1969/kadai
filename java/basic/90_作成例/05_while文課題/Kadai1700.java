/******************************************************************************
 * 0を入力するまで繰り返す
 *****************************************************************************/
public class Kadai1700{
    public static void main(String[] args){
        int sum = 0;                                //合計値
        int num = Keyboard.readInt("整数[0:終了]"); //入力値
        while(num != 0){                            //入力値が0でない間繰り返す
            sum += num;
            System.out.println("累計 = " + sum);
            System.out.println();
            num = Keyboard.readInt("整数[0:終了]"); //次のための入力
        }
    }
}
