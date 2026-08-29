import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*******************************************************************************
 * ランキングを扱うクラス
 ******************************************************************************/
public class Rank{
    private static final int RANK_NUM = 10;                     //ランクイン数
    
    private ArrayList<Integer> rank = new ArrayList<Integer>(); //ランキング用配列(得点)
    private ArrayList<String> name = new ArrayList<String>();   //ランキング用配列(氏名)
    
    /***************************************************************************
     * ランクイン処理をする
     *
     * 引　数：score 得点
     * 戻り値：なし
     * その他：ランキング配列を更新する
     **************************************************************************/
    public void ranking(int score){
        System.out.println();
        
        //順位を探索する
        int i = 0;
        boolean rank_end = false;     //順位探索終了フラグ
        for(i = 0; i < rank.size() && !rank_end; i++){
            if(score > rank.get(i).intValue()){
                rank_end = true;
                i--;
            }
        }
        
        //ランクイン処理をする
        if(rank_end || i < RANK_NUM){
            rank.add(i, new Integer(score));
            //ランクインなので名前入力
            System.out.println((i + 1) + "位にランクインしました！");
            System.out.print("名前を入力してください。=> ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                name.add(i, reader.readLine());
            }catch(IOException e){
                System.out.println("キーボードエラーのため、プログラムを中断します。");
                System.out.println("at ranking()");
                System.exit(0);
            }
            //RANK_NUM以降の記録を消去
            while(rank.size() > RANK_NUM){
                rank.remove(RANK_NUM);
                name.remove(RANK_NUM);
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
        for(int i = 0; i < rank.size(); i++){
            System.out.println((i + 1) + "位\t" + name.get(i) + "\t" + rank.get(i));
        }
    }
}
