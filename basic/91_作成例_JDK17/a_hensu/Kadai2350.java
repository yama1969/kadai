package a_hensu;

/******************************************************************************
 * int型とdouble型の使い方。消費税の計算
 *****************************************************************************/
public class Kadai2350{
    public static void main(String[] args){
        //消費税率の定義
        final double RATE = 0.05;  //この後、値が変わらないのでfinalにする
        
        //お菓子の価格を入れる変数の宣言と、価格の代入
        int price1 = Keyboard.readInt("1つめのお菓子の価格[円]");
        int price2 = Keyboard.readInt("2つめのお菓子の価格[円]");
        int price3 = Keyboard.readInt("3つめのお菓子の価格[円]");
        System.out.println();
        
        //個別購入時の消費税計算
        int tax = (int)(price1 * RATE) + (int)(price2 * RATE) + (int)(price3 * RATE);
        System.out.println("個別購入時の消費税 = " + tax + " 円");
        
        //一括購入時の消費税計算
        tax = (int)((price1 + price2 + price3) * RATE);
        System.out.println("一括購入時の消費税 = " + tax + " 円");
    }
}
