//アルゴリズムの最初の問題として利用
//すぐに完成した受講者様には、退避用変数を用いない方法を考えて頂く
public class Algo01_Swap{
    public static void main(String[] args){
        int a = (int)(Math.random() * 10);
        int b = (int)(Math.random() * 10);
        System.out.println("a,b = " + a + "," + b);
        
        //aとbを別の変数に退避する方法を考える方も多い。これはもっと考えて頂く。
        int c = a;
        int d = b;
        a = d;
        b = c;
        System.out.println("a,b = " + a + "," + b + " 2変数に退避する方法");
        
        //一般的な方法。これが出来ればOKとする。
        int w = a;
        a = b;
        b = w;
        System.out.println("a,b = " + a + "," + b + " 1変数に退避する方法。一般的。");
        
        //加算を使う方法。a+bがオーバーフローしても有効であることを解説すべし。
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a,b = " + a + "," + b + " 加算を使う方法。");
        
        //XORを使う方法。bit演算を詳細解説すべし。
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a,b = " + a + "," + b + " XORを使う方法。");
    }
}
