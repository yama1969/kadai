/*
[課題7]
1284～1303の20個の整数それぞれについて、整数を表示し、その整数の横に
7の倍数なら「7の倍数」、13の倍数なら「13の倍数」と表示する。
両方の倍数なら両方とも表示する。
*/
public class Kadai7{
    public static void main(String[] args){
        for(int num = 1284; num < 1304; num++){
            System.out.print(num);
            if(num % 7 == 0){
                System.out.print(" 7の倍数");
            }
            if(num % 13 == 0){
                System.out.print(" 13の倍数");
            }
            System.out.println();
        }
    }
}
