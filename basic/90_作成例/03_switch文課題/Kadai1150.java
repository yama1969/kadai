/******************************************************************************
 * 2つのサイコロ
 *****************************************************************************/
public class Kadai1150{
    public static void main(String[] args){
        //2つのサイコロの目を乱数で決定する
        int a = (int)(Math.random() * 6.0) + 1;
        int b = (int)(Math.random() * 6.0) + 1;
        System.out.print(a + " , " + b + " の ");
        
        //2の剰余計算で奇数・偶数を求める
        int cho = (a + b) % 2;
        
        //奇数・偶数により丁半の表示
        switch(cho){
        case 0:
            System.out.println("丁");
            break;
        case 1:
            System.out.println("半");
            break;
        default:
            System.out.println("プログラムエラー。");
        }
    }
}
