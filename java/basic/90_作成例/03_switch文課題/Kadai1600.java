/******************************************************************************
 * 絶対にコンピュータが勝つじゃんけん
 *****************************************************************************/
public class Kadai1600{
    public static void main(String[] args){
        //じゃんけんの手を入力
        int hand = Keyboard.readInt("手[0:グー 1:チョキ その他:パー]");
        System.out.println();
        
        //入力された手によってコンピュータの手を決める
        switch(hand){
        case 0:
            System.out.println("あなたはグー");
            System.out.println("私はパー");
            break;
        case 1:
            System.out.println("あなたはチョキ");
            System.out.println("私はグー");
            break;
        default:
            System.out.println("あなたはパー");
            System.out.println("私はチョキ");
        }
        System.out.println("私の勝ちですね。");
    }
}
