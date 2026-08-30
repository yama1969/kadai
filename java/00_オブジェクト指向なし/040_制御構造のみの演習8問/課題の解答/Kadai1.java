/*
[課題1]
1～6の整数乱数を生成し、その数を表示した後、偶数なら「偶数です」、奇数なら「奇数です」と表示する。
*/
public class Kadai1{
    public static void main(String[] args){
        int a = (int)(Math.random() * 6.0) + 1;
        System.out.println(a);
        if(a % 2 == 0){
            System.out.println("偶数です");
        }else{
            System.out.println("奇数です");
        }
    }
}
