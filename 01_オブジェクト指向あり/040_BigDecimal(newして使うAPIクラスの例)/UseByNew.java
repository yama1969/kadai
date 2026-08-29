import java.math.BigDecimal;

/**
 * newして利用するJava APIクラスの例 (java.math.BigDecimal)
 * BigDecimalは云わばBCDのようなもの。任意の精度の計算が可能。
 */
public class UseByNew{
    public static void main(String[] args){
        System.out.println("次の計算は、BigDecimalクラスによる計算です。");
        System.out.println("任意の精度での計算が可能です。");
        
        BigDecimal v1 = new BigDecimal("0.00000000000025435712374853574783456");
        BigDecimal v2 = new BigDecimal("5874738656273748350000000000000000000");
        System.out.print(v1 + " + " + v2 + " = ");
        v1 = v1.add(v2);
        System.out.println(v1);
        
        System.out.println();
        
        System.out.println("同じ計算をdouble型で行うと、次のようになります。");
        System.out.println("精度は15桁なので、丸め誤差及び情報落ちが発生します。");
        
        double d1 = 0.00000000000025435712374853574783456d;
        double d2 = 5874738656273748350000000000000000000d;
        System.out.println(d1 + " + " + d2 + " = " + (d1 + d2));
        
        System.out.println();
        
        System.out.println("double型では丸め誤差がでます。");
        System.out.println("以下は0.1を10万回加算した結果です。");
        
        d1 = 0.0;
        for(int i = 0; i < 100000; i++){
            d1 += 0.1d;
        }
        System.out.println(d1);
        
        System.out.println();
        
        System.out.println("これをBigDecimalクラスで行うと次のようになります。");
        System.out.println("丸め誤差は発生しません。");
        v1 = new BigDecimal("0");
        for(int i = 0; i < 100000; i++){
            v1 = v1.add(new BigDecimal("0.1"));
        }
        System.out.println(v1);
    }
}
