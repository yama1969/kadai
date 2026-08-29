/******************************************************************************
 * 2つの自然数の最大公約数を求める(ユークリッドの互除法)
 *****************************************************************************/
public class Kadai0910{
    public static void main(String[] args){
        //2つの自然数の入力
        int num1 = Keyboard.readInt("自然数その1");
        int num2 = Keyboard.readInt("自然数その2");
        
        //1つめの自然数の方が大きくなるように入れ替え
        if(num1 < num2){
            int w = num1;
            num1 = num2;
            num2 = w;
        }
        
        //入力値チェック(両方が自然数でなければならない)
        if(num2 < 1){
            System.out.println("自然数(1以上の整数)を入力して下さい。");
            return;
        }
        
        //ユークリッドの互除法により、最大公約数を求める
        int c = 0;
        do{
            c = num1 % num2;
            num1 = num2;
            num2 = c;
        }while(c != 0);
        
        //結果表示
        System.out.println("最大公約数 " + num1);
    }
}
