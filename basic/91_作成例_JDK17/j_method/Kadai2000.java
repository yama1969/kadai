package j_method;
/******************************************************************************
 * クラス・インスタンスの機能としてのメソッド ～ 自作Mathクラス
 * 自作MathクラスであるMathExpクラスのテスト
 *****************************************************************************/
public class Kadai2000{
    
    /**************************************************************************
     * メインメソッド
     *************************************************************************/
    public static void main(String[] args){
        System.out.println("MathExpクラスのデモ");
        
        //gcd()メソッドのテスト-------------------------------------------
        System.out.println("最大公約数を求める");
        int num1 = Keyboard.readInt("自然数1");
        int num2 = Keyboard.readInt("自然数2");
        System.out.println("最大公約数 = " + MathExp.gcd(num1, num2));
        System.out.println();
        
        //isPrime()メソッドのテスト---------------------------------------
        System.out.println("素数の判定");
        num1 = Keyboard.readInt("2以上の整数");
        if(MathExp.isPrime(num1)){
            System.out.println("素数です");
        }else{
            System.out.println("素数でない");
        }
        System.out.println();
        
        //nextPrime()メソッドのテスト-------------------------------------
        System.out.println("次の素数を見つける");
        num1 = Keyboard.readInt("整数");
        System.out.println("次の素数は" + MathExp.nextPrime(num1));
        System.out.println();
        
        //decomposeIntoPrime()メソッドのテスト----------------------------
        System.out.println("素因数分解する");
        num1 = Keyboard.readInt("整数");
        int[] result = MathExp.decomposeIntoPrime(num1);
        //  結果表示
        System.out.print(num1 + " = ");
        for(int i = 0; i < result.length; i += 2){
            System.out.print(result[i] + " ^ " + result[i + 1]);
            if(i < result.length - 2){
                System.out.print(" × ");
            }
        }
        System.out.println();
    }
}
