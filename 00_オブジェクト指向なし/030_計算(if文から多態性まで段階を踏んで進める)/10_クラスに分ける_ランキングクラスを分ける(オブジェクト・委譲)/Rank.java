import java.io.*;

/*******************************************************************************
 * ランキングを扱うクラス
 ******************************************************************************/
public class Rank{
    private static final int RANK_NUM = 10;               //ランクイン数
    
    private int[] rank = new int[RANK_NUM];        //ランキング用配列(得点)
    private String[] name = new String[RANK_NUM];  //ランキング用配列(氏名)
    
    /***************************************************************************
     * ランクイン処理をする
     *
     * 引　数：score 得点
     * 戻り値：なし
     * その他：ランキング配列を更新する
     **************************************************************************/
    public void ranking(int score){
        System.out.println();
        int r = RANK_NUM + 1;         //今回の順位
        boolean rank_end = false;     //順位探索終了フラグ
        for(int i = RANK_NUM - 1; i >= 0 && !rank_end; i--){
            if(score > rank[i]){
                if(i != RANK_NUM - 1){
                    rank[i + 1] = rank[i];
                    name[i + 1] = name[i];
                }
                rank[i] = score;
                name[i] = "";
                r--;
            }else{
                rank_end = true;
            }
        }
        if(r < RANK_NUM + 1){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //ランクインなので名前入力
            System.out.println(r + "位にランクインしました！");
            System.out.print("名前を入力してください。=> ");
            try{
                name[r - 1] = reader.readLine();
            }catch(IOException e){
                System.out.println("キーボードエラーのため、プログラムを中断します。");
                System.out.println("at ranking()");
                System.exit(0);
            }
        }else{
            System.out.println("残念ながら、今回はランクインしませんでした。");
        }
    }
    
    /***************************************************************************
     * ランキングを表示する
     *
     * 引　数：なし
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public void showRanking(){
        System.out.println("上位" + RANK_NUM + "名");
        for(int i = 0; i < RANK_NUM; i++){
            System.out.println((i + 1) + "位\t" + name[i] + "\t" + rank[i]);
        }
    }
}
