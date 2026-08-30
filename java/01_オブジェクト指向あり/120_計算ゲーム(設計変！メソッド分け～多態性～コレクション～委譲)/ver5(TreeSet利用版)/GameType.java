/**
計算ゲームの「問題」と「スコア」を関連付けるクラス
*/
public class GameType implements Mondai, ScoreManager{
    //-----フィールド-----------------------------------------------------------
    private Mondai mondai;                                                      //関連付ける問題オブジェクト
    private ScoreManager ranking;                                               //関連付けるスコアオブジェクト
    
    //-----コンストラクタ-------------------------------------------------------
    public GameType(Mondai mondai, ScoreManager ranking){                       //関連付ける問題とスコアを引数に取る
        this.mondai = mondai;
        this.ranking = ranking;
    }
    
    //-----以下、MondaiとScoreManagerの実装メソッド。
    //     全ては問題クラスとスコアクラスに委譲する。---------------------------
    public int showQuestion(){
        return mondai.showQuestion();
    }
    
    public long setStartTime(){
        return ranking.setStartTime();
    }
    
    public long setEndTime(){
        return ranking.setEndTime();
    }
    
    public void calcScore(int num_question, int goodAns){
        ranking.calcScore(num_question, goodAns);
    }
    
    public void showRank(){
        ranking.showRank();
    }
    
    public String getName(){
        return ranking.getName();
    }
}
