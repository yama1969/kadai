/**
 計算ゲームのランキング担当クラス
*/
public class Ranking implements ScoreManager{
    private static final int NUM_RANK = 10;                                               //最大記録数
    private String   GameName;                                                            //ゲーム名
    private double[] score;                                                               //得点ランキング。常に昇順
    private String[] scorer;                                                              //得点者名。scoreと共にソート済み
    private long     start_time;                                                          //開始時刻
    private long     end_time;                                                            //終了時刻
    
    //-----コンストラクタ：得点と得点者名の配列初期化-------------------------------------
    public Ranking(String name){
        GameName = name;
        score = new double[NUM_RANK + 1];
        scorer = new String[NUM_RANK + 1];
        for(int i = 0; i < score.length; i++){
            score[i] = Double.POSITIVE_INFINITY;
            scorer[i] = null;
        }
        start_time = 0L;
        end_time = 0L;
    }
    
    //-----開始時刻登録-------------------------------------------------------------------
    public long setStartTime(){
        if(start_time == 0L){                                                             //0でない時は既に登録済みとみなす
            start_time = System.currentTimeMillis();
        }
        return start_time;
    }
    
    //-----終了時刻登録-------------------------------------------------------------------
    public long setEndTime(){
        if(end_time == 0L){                                                               //0でない時は既に登録済みとみなす
            end_time = System.currentTimeMillis();
        }
        return end_time;
    }
    
    //-----得点計算-----------------------------------------------------------------------
    public void calcScore(int num_question, int goodAns){
        if(end_time == 0L || start_time == 0L){                                           //時刻が未登録のときは得点計算不能
            return;
        }
        
        double s_time = (double)(end_time- start_time) / 1000.0;
        s_time = floorDecimal2(s_time);                                                   //時間を小数第2位の秒に変換
        
        double score = (s_time + (double)((num_question - goodAns) * 2)) / (double)goodAns;
        score = floorDecimal2(score);
        
        System.out.println("正答数：" + goodAns + "問／" + num_question + "問");
        System.out.println("時間　：" + s_time + "秒");
        System.out.println("スコア：" + score);
        System.out.println();
        insertScore(score);
        start_time = 0L;
        end_time = 0L;
    }
    
    //-----ランキング表示-----------------------------------------------------------------
    public void showRank(){
        System.out.println(GameName + "のランキング");
        for(int i = 0; i < score.length -1; i++){
            if(score[i] != Double.POSITIVE_INFINITY){
                System.out.println("第" + (i + 1) + "位：" + scorer[i] + "\t" + score[i]);
            }
        }
    }
    
    //-----ゲーム名取得-------------------------------------------------------------------
    public String getName(){
        return GameName;
    }
    
    //-----得点ランクイン処理-------------------------------------------------------------
    private void insertScore(double new_score){
        int i;
        for(i = score.length - 2; i >= 0 && score[i] > new_score; i--){                   //挿入ソートのような感じ
            score[i + 1] = score[i];                                                      //ランク落ちするデータを入れるために、ランク配列は要素を１つ多くしてある
            scorer[i + 1] = scorer[i];
        }
        i++;                                                                              //ループから出たとき、iは格納位置のひとつ前を指している
        if(i < score.length - 1){
            score[i] = new_score;
            System.out.println("第" + (i + 1) + "位にランクインしました。");
            System.out.println("名前を入力してください。");
            scorer[i] = new Keybord().inputString();
            System.out.println();
        }
    }
    
    //-----小数点第2位で切り下げる処理----------------------------------------------------
    private double floorDecimal2(double num){
        return Math.floor(num * 100.0) / 100.0;
    }
}
