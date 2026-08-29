import java.util.ArrayList;
import java.util.Iterator;

/**
 計算ゲームのバウンダリクラス。UI担当
*/
public class KeisanGame{
    private static final int NUM_QUESTION = 10;                                           //問題数
    private ArrayList<GameType>  games;                                                   //出題とランキングを持つクラス
    private GameType             current_game;                                            //実行中のゲーム
    
    //-----コンストラクタ：各メンバの初期化とゲーム進行管理メソッドの呼び出し-------------
    public KeisanGame(){
        games = new ArrayList<GameType>();
        games.add(new AddGame());                                                         //ゲームの種類が増えたときは、ここだけ追加すれば良い
        games.add(new SubGame());
        games.add(new MultiGame());
        games.add(new DivGame());
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
            int i = 1;
            for(Iterator<GameType> it = games.iterator(); it.hasNext(); i++){
                GameType g = it.next();
                System.out.println(i + "：" + g.getName());
            }
            System.out.println(i + "：終了");
            System.out.println("どれをやりますか？（1～" + i + "）");
            
            n = new Keybord().inputNum();
            System.out.println();
            if(n == i){
                current_game = null;
                System.out.println("ゲームを終了します。お疲れさま。");
                throw new EndGameException();
            }else if(0 < n && n < i){
                current_game = games.get(n - 1);
                System.out.println("これから" + current_game.getName() + "の問題を" + NUM_QUESTION + "問出します。");
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
