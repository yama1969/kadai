import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/*******************************************************************************
 * ランキングを扱うクラス
 ******************************************************************************/
public class Rank{
    private static final int RANK_NUM = 10;               //ランクイン数
    
    private String rank_name;                             //ランキング名
    
    /***************************************************************************
     * コンストラクタ
     *
     * 引　数：rank_name ランキング名
     **************************************************************************/
    public Rank(String rank_name){
        if(rank_name != null && !rank_name.equals("")){
            this.rank_name = rank_name;
            return;
        }
        this.rank_name = "NoName";
    }
    
    /***************************************************************************
     * ランクイン処理をする
     *
     * 引　数：score 得点
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public void ranking(int score){
        ArrayList<Integer> rank = new ArrayList<Integer>();   //ランキング用配列(得点)
        ArrayList<String> name = new ArrayList<String>();     //ランキング用配列(氏名)
        
        //ランキングファイル読込み
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(rank_name + ".csv"));
            String record = null;
            while((record = reader.readLine()) != null){
                String[] dat = record.split(",");
                name.add(dat[0]);
                try{
                    rank.add(new Integer(dat[1]));
                }catch(NumberFormatException e){
                    rank.add(new Integer(-1));
                }
            }
        }catch(FileNotFoundException e){
            //ファイルを新規作成するので、処理なし
        }catch(IOException e){
            System.out.println("ランキングファイル読込中のエラーです。");
            System.out.println("新規にファイルを作成します。");
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    //ファイルを閉じる時は処置なし
                }
            }
        }
        
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
        if(rank_end || rank.size() < RANK_NUM){
            rank.add(i, new Integer(score));
            //ランクインなので名前入力
            System.out.println((i + 1) + "位にランクインしました！");
            System.out.print("名前を入力してください。=> ");
            reader = new BufferedReader(new InputStreamReader(System.in));
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
            
            //ランキングファイル書込み
            PrintWriter writer = null;
            try{
                writer = new PrintWriter(new FileWriter(this.rank_name + ".csv"));
                for(int j = 0; j < rank.size(); j++){
                    writer.println(name.get(j) + "," + rank.get(j));
                }
            }catch(IOException e){
                System.out.println("ランキングファイルの更新に失敗しました。");
            }finally{
                if(writer != null){
                    writer.close();
                }
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
        
        //ランキングファイル読込み
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(rank_name + ".csv"));
            String record = null;
            int i = 1;
            while((record = reader.readLine()) != null){
                String[] dat = record.split(",");
                System.out.println(i + "位\t" + dat[0] + "\t" + dat[1]);
                i++;
            }
        }catch(FileNotFoundException e){
            System.out.println("ランキングファイルが見つかりませんでした。");
        }catch(IOException e){
            System.out.println("ランキングファイル読込中のエラーです。");
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    //ファイルを閉じる時は処置なし
                }
            }
        }
    }
}
