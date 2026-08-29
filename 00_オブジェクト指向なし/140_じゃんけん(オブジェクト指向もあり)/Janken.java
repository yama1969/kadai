import java.io.*;

/**
 * じゃんけんプログラム
 * じゃんけんをコンピュータと10回勝負し、最後に成績を表示します。
 *
 * @author 山田　洋 2008/4/8
 */
public class Janken{
    /**
     * ゲーム全体の流れ処理
     */
    public static void main(String[] args){
        int win = 0;   //勝ち数
        int lose = 0;  //負け数
        int draw = 0;  //あいこ数
        
        for(int i = 0; i < 10; i++){
            System.out.println("［" + (i + 1) + "回目］");
            int player = getPlayerHand();         //プレイヤーの手を得る
            int comp = getCompHand();             //コンピュータの手を得る
            int result = comJanken(player,comp);  //勝敗を得る
            switch(result){
                case 0:
                    System.out.println("あなたの勝ちです。");
                    win++;
                    break;
                case 1:
                    System.out.println("あいこです。");
                    draw++;
                    break;
                case 2:
                    System.out.println("あなたの負けです。");
                    lose++;
                    break;
                default:
                    System.out.println("\t判定処理の結果が異常です。");
            }
            System.out.println();
        }
        System.out.println(win + "勝" + lose + "敗" + draw + "分でした。");
    }
    
    /**
     * じゃんけんのプレイヤーの手を入力し、その結果を返します。
     *
     * @return 0:グー<br>1:チョキ<br>2:パー
     */
    public static int getPlayerHand(){
        while(true){
            System.out.println("1.グー");
            System.out.println("2.チョキ");
            System.out.println("3.パー");
            System.out.print("*** 1～3を入力してください。=> ");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                String line = reader.readLine();
                int hand = Integer.parseInt(line);
                if(hand < 1 || 3 < hand){
                    System.out.println("\t1～3でお願いします！");
                }else{
                    hand--;
                    switch(hand){
                        case 0:
                            System.out.println("グーを出しました。");
                            break;
                        case 1:
                            System.out.println("チョキを出しました。");
                            break;
                        case 2:
                            System.out.println("パーを出しました。");
                            break;
                        default:
                            System.out.println("\tプレイヤーの手を入力中に異常事態発生！");
                    }
                    return hand;
                }
            }catch(IOException e){
                System.out.println(e);
                System.exit(-1);
            }catch(NumberFormatException e){
                System.out.println("\t1～3の数値を入力してください！");
            }
            System.out.println("\tもう一度入力をお願いします。");
            System.out.println();
        }
    }
    
    /**
     * じゃんけんのコンピュータの手をランダムに発生し、その結果を返します。
     *
     * @return 0:グー<br>1:チョキ<br>2:パー
     */
    public static int getCompHand(){
        int hand = (int)(Math.random() * 3);
        System.out.print("コンピュータは");
        switch(hand){
            case 0:
                System.out.println("グーを出しました。");
                break;
            case 1:
                System.out.println("チョキを出しました。");
                break;
            case 2:
                System.out.println("パーを出しました。");
                break;
            default:
                System.out.println("異常事態発生です！");
        }
        return hand;
    }
    
    /**
     * ２つのじゃんけんの手から、勝敗を判定します。
     *
     * @param  hand1 １つ目の手(0:グー、1:チョキ、2:パー)
     * @param  hand2 ２つ目の手(0:グー、1:チョキ、2:パー)
     * @return 0:１つ目の勝ち<br>1:あいこ<br>2:１つ目の負け<br>3:異常
     */
    public static int comJanken(int hand1, int hand2){
        if(hand1 < 0 || 2 < hand1 || hand2 < 0 || 2 < hand2){
            return 3;
        }
        
        /*
         * 基本的には引き算で勝敗を求める。
         * １つ目ー２つ目が-1または2ならば１つ目の勝ち。→-1に揃える
         * １つ目ー２つ目が1または-2ならば１つ目の負け。→1に揃える
         * １つ目ー２つ目が0ならばあいこ。
         */
        int result = hand1 - hand2;
        if(result > 1){
            result = -1;
        }else if(result < -1){
            result = 1;
        }
        return result + 1;  //0～2に直して返す
    }
}
