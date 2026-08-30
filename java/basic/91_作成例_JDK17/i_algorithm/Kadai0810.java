package i_algorithm;
/******************************************************************************
 * なるべく少ない数の紙幣・貨幣を使う
 *****************************************************************************/
public class Kadai0810{
    public static void main(String[] args){
        //金額を決定
        int price = (int)(Math.random() * 99999.0) + 1;
        System.out.println("金額 = " + price);
        System.out.println();
        
        int[][] kind = new int[10][2];
        kind[0][0] = 10000;
        kind[1][0] =  5000;
        kind[2][0] =  2000;
        kind[3][0] =  1000;
        kind[4][0] =   500;
        kind[5][0] =   100;
        kind[6][0] =    50;
        kind[7][0] =    10;
        kind[8][0] =     5;
        kind[9][0] =     1;
        
        //金種計算
        for(int i = 0; i < kind.length; i++){
            while(!(kind[i][0] > price)){
                kind[i][1]++;
                price -= kind[i][0];
            }
        }
        
        //結果表示
        for(int i = 0; i < kind.length; i++){
            System.out.printf("%5d円 : %1d 枚\n", kind[i][0], kind[i][1]);
        }
    }
}
