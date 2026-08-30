/**
 ランキング担当クラスインターフェース
*/
public interface ScoreManager{
    long setStartTime();                            //開始時刻登録
    long setEndTime();                              //終了時刻登録
    void calcScore(int num_question, int goodAns);  //得点計算＆ランクイン処理
    void showRank();                                //ランキング表示
    String getName();                               //ゲーム名取得
}
