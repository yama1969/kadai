package c_switch;

/******************************************************************************
 * 割引表による判定
 *****************************************************************************/
public class Kadai2000{
    public static void main(String[] args){
        //割引表の表示
        System.out.println("+---------------------------+----------+");
        System.out.println("| 10,000円以上 14,000円未満 | 12% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|  7,000円以上 10,000円未満 |  8% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|  5,000円以上  7,000円未満 |  5% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|      0円以上  5,000円未満 | 割引なし |");
        System.out.println("+---------------------------+----------+");
        System.out.println();
        
        //購入金額の入力
        int amount = Keyboard.readInt("購入金額");
        
        //割引ランクの計算(千円単位)
        int rank = (amount + 1000) / 1000;
        
        //割引率の判定
        int rate = -1;  //割引率デフォルト値 → この値のままならランク外
        switch(rank){
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            rate = 0;
            break;
        case 6:
        case 7:
            rate = 5;
            break;
        case 8:
        case 9:
        case 10:
            rate = 8;
            break;
        case 11:
        case 12:
        case 13:
        case 14:
            rate = 12;
            break;
        default:
        }
        
        //結果表示
        switch(rate){
        case -1:   //rateがデフォルトのままなら、ランク適用なかった証拠
            System.out.println("金額が範囲外です。");
            break;
        default:   //rateに割引率が入っている
            amount = (int)((double)amount * (double)(100 - rate) / 100.0);
            System.out.println("割引 " + rate + "%");
            System.out.println("割引後金額 " + amount + "円");
        }
    }
}
