import java.io.*;

public class Keisan{
    public static void main(String[] args){
        final int MIN = 1;       //問題に使う数の最小値
        final int MAX = 9;       //問題に使う数の最大値
        
        //問題を作る
        int a = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
        int b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
        int ans = 0;
        
        //問題を表示する
        System.out.print(a + " + " + b + " = ");
    }
}
