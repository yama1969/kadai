/**
 計算ゲームのバウンダリクラス。UI担当
*/
public class KeisanGame{
    private static final int NUM_QUESTION = 10;                                           //問題数
    private Mondai quest;                                                                 //出題クラス
    
    //-----コンストラクタ：各メンバの初期化とゲーム進行管理メソッドの呼び出し-------------
    public KeisanGame(){
        mainGame();
    }
    
    //-----ゲーム進行管理-----------------------------------------------------------------
    public void mainGame(){
        try{
            while(true){
                long startTime;                                                           //開始時刻[ms]
                long endTime;                                                             //終了時刻[ms]
                int goodAns = 0;                                                          //正答数
                
                startTurn();                                                              //ターン開始処理
                System.out.println();
                
                startTime = System.currentTimeMillis();                                   //問題表示処理
                for(int i = 0; i < NUM_QUESTION; i++){
                    goodAns += quest.showQuestion();
                }
                endTime = System.currentTimeMillis();
                System.out.println();
                
                endTurn(startTime, endTime, goodAns);                                     //ターン終了処理
            }
        }catch(EndGameException ege){
            //ゲーム終了。
        }
    }
    
    //-----ターン開始---------------------------------------------------------------------
    private void startTurn() throws EndGameException{
        int n = 0;
        do{                                                                               //ユーザが終了を選択しない限り、無限ループ
            System.out.println("計算ゲーム");
            System.out.println("１：足し算");
            System.out.println("２：引き算");
            System.out.println("３：かけ算");
            System.out.println("４：割り算");
            System.out.println("５：終了");
            System.out.println("どれをやりますか？（１～５）");
            
            n = new Keybord().inputNum();
            System.out.println();
            switch(n){                                                                    //ユーザの選択によって、問題を切り替える
                case 1:
                    quest = new AddMondai();
                    System.out.print("これから足し算の問題を");
                    break;
                case 2:
                    quest = new SubMondai();
                    System.out.print("これから引き算の問題を");
                    break;
                case 3:
                    quest = new MultiMondai();
                    System.out.print("これからかけ算の問題を");
                    break;
                case 4:
                    quest = new DivMondai();
                    System.out.print("これから割り算の問題を");
                    break;
                case 5:
                    quest = null;
                    System.out.println("ゲームを終了します。お疲れさま。");
                    throw new EndGameException();                                         //終了は例外で知らせる
                default:
                    quest = null;
                    System.out.println("１～５を入力して下さい。");
                    System.out.println();
                    n = 0;
            }
        }while(n == 0);
        System.out.println(NUM_QUESTION + "問出します。");                                //case1～4の場合の残りの表示
        pause();
    }
    
    //-----ターン終了---------------------------------------------------------------------
    private void endTurn(long startTime, long endTime, int goodAns){
        double s_time = (double)(endTime- startTime) / 1000.0;
        s_time = floorDecimal2(s_time);                                                   //時間を小数第2位の秒に変換
        
        double score = s_time + (double)(NUM_QUESTION - goodAns) * 2.0;                   //所要時間には不正答する度に2秒のペナルティが付く
        score /= (double)goodAns;                                                         //スコア＝所要時間÷正答数
        score = floorDecimal2(score);
        
        System.out.println("正答数：" + goodAns + "問／" + NUM_QUESTION + "問");
        System.out.println("時間　：" + s_time + "秒");
        System.out.println("スコア：" + score);
        System.out.println();
        pause();
    }
    
    //-----ユーザが何かを入力するまで待つ処理---------------------------------------------
    private void pause(){
        System.out.println("Enterキーを押してください！");
        new Keybord().inputNum();
    }
    
    //-----小数を小数点第2位で切り下げる--------------------------------------------------
    private double floorDecimal2(double num){
        return Math.floor(num * 100.0) / 100.0;
    }
}
