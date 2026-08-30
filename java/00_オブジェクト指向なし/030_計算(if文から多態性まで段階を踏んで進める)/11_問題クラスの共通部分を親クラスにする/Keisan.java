import java.io.*;

/*******************************************************************************
 * 計算問題アプリケーションの進行役となるクラス
 ******************************************************************************/
public class Keisan{
    private static final int KIND_NUM = 4;                            //計算種類の数
    private static final int ADD = 0;                                 //加算
    private static final int SUB = 1;                                 //減算
    private static final int MUL = 2;                                 //乗算
    private static final int DIV = 3;                                 //除算
    private static final int END = 4;                                 //終了
    
    private static final int QUES_NUM = 10;                           //出題数
    
    private Question[] ques;
    
    private boolean malinput = false;                                 //不正入力の有無 true:不正入力あり
    
    /***************************************************************************
     * 計算問題アプリケーションを開始する
     *
     * 引　数：使用しない
     **************************************************************************/
    public static void main(String[] args){
        new Keisan().execKeisan();
    }
    
    /***************************************************************************
     * コンストラクタ
     *
     * 引　数：なし
     **************************************************************************/
    public Keisan(){
        ques = new Question[KIND_NUM];
        ques[ADD] = new Add();
        ques[SUB] = new Sub();
        ques[MUL] = new Mul();
        ques[DIV] = new Div();
    }
    
    /***************************************************************************
     * 計算問題アプリケーションを実行する
     *
     * 引　数：なし
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public void execKeisan(){
        int kind = 0;                                            //ユーザが選択した種類(ADD,SUB,MUL,DIV,END)
        do{
            kind = selectKind();                                 //計算の種類を選択する
            if(kind != END){
                System.out.println();
                int correct = 0;                                 //正答数
                long stime = System.currentTimeMillis();         //開始時刻を得る
                
                //問題を繰り返し出す
                for(int i = 0; i < QUES_NUM; i++){
                    ques[kind].makeQuestion();                   //問題を作る
                    int ans = 0;
                    do{
                        ques[kind].showQuestion(i);              //問題を表示する
                        ans = inputAns();                        //回答を入力する
                    }while(malinput);
                    correct += ques[kind].judge(ans);            //正誤を判定する
                }
                
                long etime = System.currentTimeMillis();         //終了時刻を得る
                double time = (double)(etime - stime) / 1000.0;  //経過時間を計算する
                
                int score = showResult(correct, time);           //結果を表示する
                
                //ランクイン処理とランキング表示
                ques[kind].ranking(score);
                ques[kind].showRanking();
                
                System.out.println();
                System.out.println();
            }
        }while(kind != END);
    }
    
    /***************************************************************************
     * 計算の種類を選択する
     *
     * 引　数：なし
     * 戻り値：選択した種類(ADD,SUB,MUL,DIV,ENDのいずれか)
     * その他：malinputを更新する
     **************************************************************************/
    public int selectKind(){
        int kind = 0;              //ユーザが選択した種類
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //計算の種類を選択する
        System.out.println("これから計算問題を" + QUES_NUM + "問だします。");
        do{
            System.out.println();
            System.out.println("計算の種類を選択してください。");
            System.out.print(ADD + ":加算 " + SUB + ":減算 " + MUL + ":乗算 " + DIV + ":除算 " + END + ":終了 => ");
            try{
                malinput = false;
                String line = reader.readLine();
                kind = Integer.parseInt(line);
                if(kind < ADD || kind > END){
                    malinput = true;
                }
            }catch(NumberFormatException e){
                malinput = true;
            }catch(IOException e){
                System.out.println("キーボードエラーのため、プログラムを中断します。");
                System.out.println("at selectKind()");
                System.exit(0);
            }
        }while(malinput);
        
        return kind;
    }
    
    /***************************************************************************
     * 回答を入力する
     *
     * 引　数：なし
     * 戻り値：入力された回答値
     * その他：malinputを更新する
     **************************************************************************/
    public int inputAns(){
        int ans = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            malinput = false;
            String line = reader.readLine();
            ans = Integer.parseInt(line);
        }catch(NumberFormatException e){
            malinput = true;
        }catch(IOException e){
            System.out.println("キーボードエラーのため、プログラムを中断します。");
            System.out.println("at inputAns()");
            System.exit(0);
        }
        return ans;
    }
    
    /***************************************************************************
     * 得点を計算し、成績を表示する
     *
     * 引　数：correct 正答数, time 経過時間[秒]
     * 戻り値：得点
     * その他：なし
     **************************************************************************/
    public int showResult(int correct, double time){
        double rate = (double)correct * 100.0/ (double)QUES_NUM;
        int score = (int)(rate * 10.0 / time);
        
        System.out.println();
        System.out.println(QUES_NUM + "問中、" + correct + "問正解。");
        System.out.println("正答率は" + (int)rate + "%でした。");
        System.out.println("経過時間は" + time + "秒");
        System.out.println("スコアは" + score + "点");
        
        return score;
    }
}
