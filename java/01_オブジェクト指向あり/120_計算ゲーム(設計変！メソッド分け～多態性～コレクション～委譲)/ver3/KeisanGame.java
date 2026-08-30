/**
 計算ゲームのバウンダリクラス。UI担当
*/
public class KeisanGame{
    private static final int NUM_QUESTION = 10;                                           //問題数
    private GameType[] game;                                                              //出題とランキングを持つクラス
    private GameType   current_game;                                                      //実行中のゲーム
    
    //-----コンストラクタ：各メンバの初期化とゲーム進行管理メソッドの呼び出し-------------
    public KeisanGame(){
        game = new GameType[4];                                                           //ゲームの種類を増やすときは、配列サイズとオブジェクトの代入だけ追加すればよい
        game[0] = new AddGame();
        game[1] = new SubGame();
        game[2] = new MultiGame();
        game[3] = new DivGame();
        mainGame();
    }
    
    //-----ゲーム進行管理-----------------------------------------------------------------
    public void mainGame(){
        try{
            while(true){
                startTurn();                                                              //ターン開始処理
                System.out.println();
                
                int goodAns = 0;                                                          //正答数クリア
                current_game.setStartTime();                                              //問題表示処理
                for(int i = 0; i < NUM_QUESTION; i++){
                    goodAns += current_game.showQuestion();
                }
                current_game.setEndTime();
                System.out.println();
                
                current_game.calcScore(NUM_QUESTION, goodAns);                            //ターン終了処理
                current_game.showRank();
                System.out.println();
            }
        }catch(EndGameException ege){
            //ゲーム終了。
        }
    }
    
    //-----ターン開始---------------------------------------------------------------------
    private void startTurn() throws EndGameException{
        int n = 0;
        do{
            System.out.println("計算ゲーム");
            int i;
            for(i = 0; i < game.length; i++){
                System.out.println((i + 1) + "：" + game[i].getName());
            }
            i++;
            System.out.println(i + "：終了");
            System.out.println("どれをやりますか？（1～" + i + "）");
            
            n = new Keybord().inputNum();
            System.out.println();
            if(0 < n && n < i){
                current_game = game[n - 1];
                System.out.println("これから" + current_game.getName() + "の問題を" + NUM_QUESTION + "問出します。");
            }else if(n == i){
                current_game = null;
                System.out.println("ゲームを終了します。お疲れさま。");
                throw new EndGameException();
            }else{
                current_game = null;
                System.out.println("1～" + i + "を入力して下さい。");
                System.out.println();
                n = 0;
            }
        }while(n == 0);
        pause();
    }
    
    //-----ユーザの空入力を待つ-----------------------------------------------------------
    private void pause(){
        System.out.println("Enterキーを押してください！");
        new Keybord().inputNum();
    }
}
