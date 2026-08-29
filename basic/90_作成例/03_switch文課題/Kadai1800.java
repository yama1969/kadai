/******************************************************************************
 * ガチンコでじゃんけん
 *****************************************************************************/
public class Kadai1800{
    public static void main(String[] args){
        //プレイヤーとコンピュータの手を決定
        int p_hand = Keyboard.readInt("手[0:グー 1:チョキ 2:パー]");
        int c_hand = (int)(Math.random() * 2.0);
        System.out.println();
        
        //双方の手から結果を判断
        switch(p_hand){
        case 0:      //プレイヤーはグー
            System.out.println("あなたはグー");
            switch(c_hand){
            case 0:  //  コンピュータもグー
                System.out.println("私もグー");
                System.out.println();
                System.out.println("あいこですね。");
                break;
            case 1:  //  コンピュータはチョキ
                System.out.println("私はチョキ");
                System.out.println();
                System.out.println("私の負けですね。");
                break;
            default: //  コンピュータはパー
                System.out.println("私はパー");
                System.out.println();
                System.out.println("私の勝ちですね。");
            }
            break;
        case 1:      //プレイヤーはチョキ
            System.out.println("あなたはチョキ");
            switch(c_hand){
            case 0:  //  コンピュータはグー
                System.out.println("私はグー");
                System.out.println();
                System.out.println("私の勝ちですね。");
                break;
            case 1:  //  コンピュータもチョキ
                System.out.println("私もチョキ");
                System.out.println();
                System.out.println("あいこですね。");
                break;
            default: //  コンピュータはパー
                System.out.println("私はパー");
                System.out.println();
                System.out.println("私の負けですね。");
            }
            break;
        default:     //プレイヤーはパー
            System.out.println("あなたはパー");
            switch(c_hand){
            case 0:  //  コンピュータはグー
                System.out.println("私はグー");
                System.out.println();
                System.out.println("私の負けですね。");
                break;
            case 1:  //  コンピュータはチョキ
                System.out.println("私はチョキ");
                System.out.println();
                System.out.println("私の勝ちですね。");
                break;
            default: //  コンピュータもパー
                System.out.println("私もパー");
                System.out.println();
                System.out.println("あいこですね。");
            }
        }
    }
}
