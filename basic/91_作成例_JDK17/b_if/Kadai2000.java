package b_if;

/******************************************************************************
 * 割引表による判定
 *****************************************************************************/
public class Kadai2000{
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
        
        //割引表情報の代入
        int border1 = 20000;  //ランク1境界金額
        int border2 = 50000;  //ランク2境界金額
        int border3 = 100000; //ランク3境界金額
        double rate1 = 0.1;   //ランク1割引率
        double rate2 = 0.2;   //ランク2割引率
        double rate3 = 0.3;   //ランク3割引率
        
        //判定結果用変数
        double rate = 0.0;    //決定した割引率
        int min = 0;          //次ランクまで購入した方が安くなる境界
        int next = 0;         //次ランクの境界金額
        
        //ランクの判定と判定結果用変数への代入
        if(amount < border1){
            rate = 0.0;
            min = (int)((double)border1 * (1.0 - rate1));
            next = border1;
        }else if(amount < border2){
            rate = rate1;
            min = (int)((double)border2 * (1.0 - rate2));
            next = border2;
        }else if(amount < border3){
            rate = rate2;
            min = (int)((double)border3 * (1.0 - rate3));
            next = border3;
        }else{
            rate = rate3;
            min = amount;
        }
        
        //判定結果表示
        //  割引率
        System.out.println("割引率 " + (int)(rate * 100.0) + " %");
        //  割引後金額
        int pay = (int)((double)amount * (1.0 - rate));
        System.out.println("割引後金額 " + pay + " 円");
        //  次ランクまで購入した方が安いかの判断
        if(pay > min){
            System.out.println(next + "円購入した方がお得ですよ");
        }
    }
}
