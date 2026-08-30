import java.io.*;

/**
 * コンピュータとじゃんけんするプログラム
 */
public class Janken{
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
            //ここに、playerが1なら"グー"、2なら"チョキ"、3なら"パー"をplayer_handに代入するプログラムを書きましょう
            
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
        //ここに、勝敗判定をして"あいこです。","あなたの勝ちです。","あなたの負けです。"のいずれかをresultに代入するプログラムを書きましょう
        
        
        //--結果表示-----------------------------------------------------------
        System.out.println();
        System.out.println("コンピュータが" + comp_hand + "、");
        System.out.println("あなたが" + player_hand + "で");
        System.out.println(result);
    }
}
