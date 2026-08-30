package a_hensu;

/******************************************************************************
 * 割引表による決定
 *****************************************************************************/
public class Kadai4200{
    public static void main(String[] args){
        //入力補助のため 割引表の表示
        System.out.println("+---------------------------+----------+");
        System.out.println("| 15,000円以上 20,000円未満 | 21% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("| 10,000円以上 15,000円未満 | 14% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|  5,000円以上 10,000円未満 |  7% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|      0円以上  5,000円未満 | 割引なし |");
        System.out.println("+---------------------------+----------+");
        System.out.println();
        
        //購入金額の入力
        int amount = Keyboard.readInt("購入金額");
        System.out.println();
        
        //割引率の計算と表示
        int rate = amount / 5000 * 7;
        System.out.println("割引率 = " + rate + "%");
        
        //割引後金額の計算と表示
        amount = (int)((double)amount * ((double)(100 - rate) / 100.0));
        System.out.println("割引後金額 = " + amount);
    }
}
