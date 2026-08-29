/*
[課題8]
0～255の整数乱数を生成し、その数を2進数に変換して表示する。
変換後の2進数は文字列とし、右端には「B」を付けること。
2進数変換は以下の手順で出来る。

手順1.文字列「B」を作る。
手順2.変換元の数値を2で割り、商と余りを求める。
手順3.余りが1ならば「1」を、余りが0ならば「0」を文字列の左側に付ける。
手順4.手順2の商が0ならば終了。そうでなければ、手順2に戻る。
*/
public class Kadai8{
    public static void main(String[] args){
        int num = (int)(Math.random() * 256.0);
        System.out.println(num);
        
        String bin = "B";
        while(num != 0){
            int rest = num % 2;
            num = num / 2;
            bin = rest + bin;
        }
        System.out.println(bin);
    }
}
