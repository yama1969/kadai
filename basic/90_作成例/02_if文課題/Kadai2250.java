/******************************************************************************
 * ややコンピュータが勝つじゃんけん
 *****************************************************************************/
public class Kadai2250{
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
        if((int)(Math.random() * 100.0) < 55){
            //55%はコンピュータの勝ちとする
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
            //45%はコンピュータの負けとする
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
