package b_if;

/******************************************************************************
 * 絶対にコンピュータが負けるじゃんけん
 *****************************************************************************/
public class Kadai2225{
    public static void main(String[] args){
        //じゃんけんの手の入力
        int hand = Keyboard.readInt("じゃんけんの手[0:グー, 1:チョキ, その他:パー]");
        
        //入力された手に従い、コンピュータの手を決定
        if(hand == 0){
            System.out.println("あなたはグー。");
            System.out.println("私はチョキです。");
        }else if(hand == 1){
            System.out.println("あなたはチョキ。");
            System.out.println("私はパーです。");
        }else{
            System.out.println("あなたはパー。");
            System.out.println("私はグーです。");
        }
        System.out.println();
        System.out.println("私の負けですね。");
    }
}
