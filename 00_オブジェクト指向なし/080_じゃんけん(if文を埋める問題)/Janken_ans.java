import java.io.*;

/**
 * コンピュータとじゃんけんするプログラム
 */
public class Janken_ans{
    public static void main(String[] args){
        //--初期表示-----------------------------------------------------------
        System.out.println();
        System.out.println("これからコンピュータとじゃんけんをします。");
        System.out.println();
        System.out.println("0：グー");
        System.out.println("1：チョキ");
        System.out.println("2：パー");
        System.out.println("0～2のどれかを入力してください。");
        System.out.println("じゃーんけーん・・・");
        
        //--コンピュータの手の決定---------------------------------------------
        int comp = (int)(Math.random() * 3); //コンピュータの手(符号)
        String comp_hand = "グー";           //コンピュータの手(名称)
        if(comp == 1){
            comp_hand = "チョキ";
        }else if(comp == 2){
            comp_hand = "パー";
        }
        
        //--プレーヤーの手の選択-----------------------------------------------
        int player = -1;                    //プレーヤーの手(符号) -1なら手なし
        String player_hand = "手なし";      //プレーヤーの手(名称)
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String line = reader.readLine();
            player = Integer.parseInt(line);
            if(player == 0){
                player_hand = "グー";
            }else if(player == 1){
                player_hand = "チョキ";
            }else if(player == 2){
                player_hand = "パー";
            }else{
                System.out.println("0～2の整数を入力してください。");
                player = -1;
            }
        }catch(IOException e){
            System.out.println(e);
        }catch(NumberFormatException e){
            System.out.println("0～2の整数を入力してください。");
        }
        
        //--勝敗判定-----------------------------------------------------------
        String result = "勝負なし！";
        if(player != -1){
            if(player == comp){
                result = "あいこです。";
            }else{
                if(player == 0 && comp == 1){
                    result = "あなたの勝ちです！";
                }else if(player == 1 && comp == 2){
                    result = "あなたの勝ちです！";
                }else if(player == 2 && comp == 0){
                    result = "あなたの勝ちです！";
                }else{
                    result = "あなたの負けです。。。";
                }
            }
        }
        
        //--結果表示-----------------------------------------------------------
        System.out.println();
        System.out.println("コンピュータが" + comp_hand + "、");
        System.out.println("あなたが" + player_hand + "で");
        System.out.println(result);
    }
}
