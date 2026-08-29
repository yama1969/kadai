import java.util.TreeSet;

/**
 計算ゲームのランキング担当クラス
*/
public class Ranking implements ScoreManager{
    //-----クラスRankingのフィールド------------------------------------------------------
    private static final int   NUM_RANK = 10;                                             //最大記録数
    private String             GameName;                                                  //ゲーム名
    private TreeSet<ScoreData> scoreDat;                                                  //得点ランキングセット
    private long               start_time;                                                //開始時刻
    private long               end_time;                                                  //終了時刻
    
    //-----コンストラクタ：得点と得点者名の配列初期化-------------------------------------
    public Ranking(String name){
        GameName = name;
        scoreDat = new TreeSet<ScoreData>();
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
        int i = 1;
        for(ScoreData sd : scoreDat){
            System.out.println("第" + i + "位：" + sd.getName() + "\t" + sd.getScore());
            i++;
        }
    }
    
    //-----ゲーム名取得-------------------------------------------------------------------
    public String getName(){
        return GameName;
    }
    
    //-----得点ランクイン処理-------------------------------------------------------------
    private void insertScore(double new_score){
        ScoreData new_sd = new ScoreData("", new_score);
        scoreDat.add(new_sd);
        if(scoreDat.size() > NUM_RANK){
            scoreDat.pollLast();                                                          //JDK1.6からサポート
        }
        int i = 1;
        for(ScoreData sd : scoreDat){
            if(sd.equals(new_sd)){
                break;
            }
            i++;
        }
        if(i <= NUM_RANK){
            System.out.println("第" + i + "位にランクインしました。");
            System.out.println("名前を入力してください。");
            new_sd.setName(new Keybord().inputString());
            System.out.println();
        }
    }
    
    //-----小数点第2位で切り下げる処理----------------------------------------------------
    private double floorDecimal2(double num){
        return Math.floor(num * 100.0) / 100.0;
    }
    
    //************************************************************************************
    //-----Ranking内部で使用する得点情報クラス--------------------------------------------
    public class ScoreData implements Comparable{
        private String name;                                                              //プレーヤー名
        private double score;                                                             //得点
        
        //----ScoreDataコンストラクタ：引数あり-------------------------------------------
        public ScoreData(String name, double score){
            setName(name);
            setScore(score);
        }
        
        //-----ScoreDataコンストラクタ：引数なし------------------------------------------
        public ScoreData(){
            this("", 0.0);
        }
        
        //-----比較メソッド---------------------------------------------------------------
        public int compareTo(Object obj){
            if(obj instanceof ScoreData){
                double objScore = ((ScoreData)obj).getScore();
                if(score < objScore){                                                     //得点は小さい方が上
                    return -1;
                }else if(score > objScore){
                    return 1;                                                             //得点は大きい方が下
                }else{
                    return 0;
                }
            }else{
                throw new ClassCastException();
            }
        }
        
        //----等価メソッド----------------------------------------------------------------
        public boolean equals(Object obj){
            if(obj instanceof ScoreData){
                ScoreData sd = (ScoreData)obj;                                            //プレーヤー名と得点が一致すれば等価オブジェクト
                if(name.equals(sd.getName()) && score == sd.getScore()){
                    return true;
                }
            }
            return false;
        }
        
        //-----以下、セッターおよびゲッター-----------------------------------------------
        public String getName(){
            return name;
        }
        
        public void setName(String name){
            this.name = name;
        }
        
        public double getScore(){
            return score;
        }
        
        public void setScore(double score){
            this.score = score;
        }
    }
}
