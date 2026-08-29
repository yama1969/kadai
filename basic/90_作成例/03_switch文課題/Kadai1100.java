/******************************************************************************
 * 入力された値を表示
 *****************************************************************************/
public class Kadai1100{
    public static void main(String[] args){
        //整数を入力
        int a = Keyboard.readInt("整数");
        
        //入力された整数に従って表示
        switch(a){
        case 1:
            System.out.println("1が入力されました。");
            break;
        case 2:
            System.out.println("2が入力されました。");
            break;
        default:
            System.out.println("1あるいは2以外が入力されました。");
        }
    }
}
