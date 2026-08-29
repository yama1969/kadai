package b_if;

/******************************************************************************
 * 割引表による判定
 *****************************************************************************/
public class Kadai1900{
    public static void main(String[] args){
        //割引表の表示
        System.out.println("+----------------------------+----------+");
        System.out.println("| 100,000円以上              | 30% OFF  |");
        System.out.println("+----------------------------+----------+");
        System.out.println("|  50,000円以上100,000円未満 | 20% OFF  |");
        System.out.println("+----------------------------+----------+");
        System.out.println("|  20,000円以上 50,000円未満 | 10% OFF  |");
        System.out.println("+----------------------------+----------+");
        System.out.println("|               20,000円未満 | 割引なし |");
        System.out.println("+----------------------------+----------+");
        System.out.println();
        
        //購入金額の入力
        int amount = Keyboard.readInt("購入金額");
        
        //購入金額と割引表から、割引率を決定
        double rate = 0.0;
        if(amount < 20000){
            rate = 0.0;
        }else if(amount < 50000){
            rate = 0.1;
        }else if(amount < 100000){
            rate = 0.2;
        }else{
            rate = 0.3;
        }
        
        //決定された割引率と割引後金額の表示
        System.out.println("割引率 " + (int)(rate * 100.0) + " %");
        System.out.println("割引後金額 " + (int)(amount * (1.0 - rate)) + " 円");
    }
}
