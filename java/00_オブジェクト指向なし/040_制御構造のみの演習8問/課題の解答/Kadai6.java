/*
[課題6]
1～100の整数乱数aとbを生成し、/や%、*を用いずにa÷bの商と余りを求める。
a÷bの商は、aからbを何回引き算出来るかで計算できる。
*/
public class Kadai6{
    public static void main(String[] args){
        int a = (int)(Math.random() * 100.0) + 1;
        int b = (int)(Math.random() * 100.0) + 1;
        int syo = 0;
        
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
        while(a >= b){
            syo++;
            a = a - b;
        }
        
        System.out.println("a÷b = " + syo + " あまり " + a);
    }
}
