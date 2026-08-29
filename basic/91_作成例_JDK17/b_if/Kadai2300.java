package b_if;

/******************************************************************************
 * ガチンコでじゃんけん
 *****************************************************************************/
public class Kadai2300{
    public static void main(String[] args){
        //プレイヤー、コンピュータ双方の手の決定
        int p_hand = Keyboard.readInt("じゃんけんの手[0:グー, 1:チョキ, その他:パー]");
        int c_hand = (int)(Math.random() * 3.0);
        
        //プレイヤーの手の表示
        if(p_hand == 0){
            System.out.println("あなたはグー。");
        }else if(p_hand == 1){
            System.out.println("あなたはチョキ。");
        }else{
            p_hand = 2;
            System.out.println("あなたはパー。");
        }
        
        //コンピュータの手の表示
        if(c_hand == 0){
            System.out.println("私はグー。");
        }else if(c_hand == 1){
            System.out.println("私はチョキ。");
        }else{
            System.out.println("私はパー。");
        }
        System.out.println();
        
        //勝敗判定(0:あいこ, 1:コンピュータ負け, 2:コンピュータ勝ち)
        int result = 0;             //判定結果。デフォルトをあいことする。
        if(p_hand != c_hand){       //あいこでない場合
            if(p_hand == 0){        //  プレイヤーがグー
                if(c_hand == 1){    //    コンピュータはチョキ
                    result = 1;
                }else{              //    コンピュータはパー
                    result = 2;
                }
            }else if(p_hand == 1){  //  プレイヤーがチョキ
                if(c_hand == 2){    //    コンピュータはパー
                    result = 1;
                }else{              //    コンピュータはグー
                    result = 2;
                }
            }else{                  //  プレイヤーがパー
                if(c_hand == 0){    //    コンピュータはグー
                    result = 1;
                }else{              //    コンピュータはチョキ
                    result = 2;
                }
            }
        }
        //int result = (c_hand + 3 - p_hand) % 3;  //こういうやり方もある
        
        //判定結果表示
        if(result == 0){
            System.out.println("あいこですね。");
        }else if(result == 1){
            System.out.println("私の負けですね。");
        }else{
            System.out.println("私の勝ちですね。");
        }
    }
}
