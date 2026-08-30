/******************************************************************************
 * あいこも出してそれらしくじゃんけん
 *****************************************************************************/
public class Kadai2275{
    public static void main(String[] args){
        //じゃんけんの手を入力
        int hand = Keyboard.readInt("じゃんけんの手[0:グー, 1:チョキ, その他:パー]");
        //入力された手の表示
        if(hand == 0){
            System.out.println("あなたはグー。");
        }else if(hand == 1){
            System.out.println("あなたはチョキ。");
        }else{
            System.out.println("あなたはパー。");
        }
        
        //乱数によりコンピュータの手を決定
        int rand = (int)(Math.random() * 100.0);
        if(rand < 33){
            //33%はあいこ
            if(hand == 0){
                System.out.println("私もグーです。");
            }else if(hand == 1){
                System.out.println("私もチョキです。");
            }else{
                System.out.println("私もパーです。");
            }
            System.out.println();
            System.out.println("あいこですね。");
        }else if(rand < 70){
            //37%はコンピュータの勝ち
            if(hand == 0){
                System.out.println("私はパーです。");
            }else if(hand == 1){
                System.out.println("私はグーです。");
            }else{
                System.out.println("私はチョキです。");
            }
            System.out.println();
            System.out.println("私の勝ちですね。");
        }else{
            //30%はコンピュータの負け
            if(hand == 0){
                System.out.println("私はチョキです。");
            }else if(hand == 1){
                System.out.println("私はパーです。");
            }else{
                System.out.println("私はグーです。");
            }
            System.out.println();
            System.out.println("私の負けですね。");
        }
    }
}
