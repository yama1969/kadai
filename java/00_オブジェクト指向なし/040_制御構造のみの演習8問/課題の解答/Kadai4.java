/*
[課題4]
1～10の整数乱数を生成し、それを表示する。これを乱数が5を出すまで繰り返す
*/
public class Kadai4{
    public static void main(String[] args){
        int num = 0;
        do{
            num = num = (int)(Math.random() * 10.0) + 1;
            System.out.println(num);
        }while(num != 5);
    }
}
