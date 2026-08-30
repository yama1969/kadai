package i_algorithm;
/******************************************************************************
 * 素因数分解に向けて
 * 整数が素数かどうかを調べる
 *****************************************************************************/
public class Kadai1010{
    public static void main(String[] args){
        //素数か否かを調べる整数の入力
        int num = Keyboard.readInt("2以上の整数");
        if(num < 2){
            System.out.println("入力値が不正です。");
            return;
        }
        
        //調べる整数が他の整数で割り切れるか否かを調べる
        boolean prime = true;            //true:素数, false:非素数
        if(num != 2 && num % 2 == 0){
            //整数が2以外で、かつ2で割り切れるなら非素数
            prime = false;
        }else{
            //3～整数の平方根までの奇数で割り切れるなら非素数
            int max = (int)(Math.sqrt((double)num)) + 1;
            for(int i = 3; i < max && prime; i += 2){
                if(num % i == 0){
                    prime = false;
                }
            }
        }
        
        //結果表示
        //最終的に上記のどの場合でも割り切れなかった場合は素数
        if(prime){
            System.out.println("素数");
        }else{
            System.out.println("非素数");
        }
    }
}
