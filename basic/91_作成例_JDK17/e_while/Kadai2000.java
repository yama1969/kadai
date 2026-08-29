package e_while;

/******************************************************************************
 * 最初の1回は必ず累計を表示
 *****************************************************************************/
public class Kadai2000{
    public static void main(String[] args){
        int sum = 0;                                //合計値
        int num = Keyboard.readInt("整数");         //入力整数
        do{                                         //初回は必ず累計表示
            sum += num;
            System.out.println("累計 = " + sum);
            System.out.println();
            num = Keyboard.readInt("整数[0:終了]"); //次の整数入力
        }while(num != 0);
    }
}
