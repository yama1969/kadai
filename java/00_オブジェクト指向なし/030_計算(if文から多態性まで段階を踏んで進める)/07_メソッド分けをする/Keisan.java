import java.io.*;

public class Keisan{
    private static final int KIND_NUM = 4;                            //計算種類の数
    private static final int ADD = 0;                                 //加算
    private static final int SUB = 1;                                 //減算
    private static final int MUL = 2;                                 //乗算
    private static final int DIV = 3;                                 //除算
    private static final int END = 4;                                 //終了
    
    private static final int QUES_NUM = 10;                           //出題数
    private static final int MIN = 1;                                 //問題に使う数の最小値
    private static final int MAX = 9;                                 //問題に使う数の最大値
    private static final int RANK_NUM = 10;                           //ランクイン数
    
    private static boolean malinput = false;                          //不正入力の有無 true:不正入力あり
    
    private static int a;                                             //問題の数値a
    private static int b;                                             //問題の数値b
    
    private static int[][] rank = new int[KIND_NUM][RANK_NUM];        //ランキング用配列(得点)
    private static String[][] name = new String[KIND_NUM][RANK_NUM];  //ランキング用配列(氏名)
    
    /***************************************************************************
     * 計算問題アプリケーションを実行する
     *
     * 引　数：使用しない
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public static void main(String[] args){
        int kind = 0;                                            //ユーザが選択した種類(ADD,SUB,MUL,DIV,END)
        do{
            kind = selectKind();                                 //計算の種類を選択する
            if(kind != END){
                System.out.println();
                int correct = 0;                                 //正答数
                long stime = System.currentTimeMillis();         //開始時刻を得る
                
                //問題を繰り返し出す
                for(int i = 0; i < QUES_NUM; i++){
                    makeQuestion(kind);                          //問題を作る
                    int ans = 0;
                    do{
                        showQuestion(i, kind);                   //問題を表示する
                        ans = inputAns();                        //回答を入力する
                    }while(malinput);
                    correct += judge(ans, kind);                 //正誤を判定する
                }
                
                long etime = System.currentTimeMillis();         //終了時刻を得る
                double time = (double)(etime - stime) / 1000.0;  //経過時間を計算する
                
                int score = showResult(correct, time);           //結果を表示する
                
                ranking(score, kind);                            //ランクイン処理
                showRanking(kind);                               //ランキング表示
                
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
    public static int selectKind(){
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
     * 問題を作る
     *
     * 引　数：kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public static void makeQuestion(int kind){
        //問題を作る
        a = 0;
        b = 0;
        int ans = 0;
        switch(kind){
        case ADD: //加算
        case MUL: //乗算
            a = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            break;
        case SUB: //減算
            b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            ans = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            a = ans + b;
            break;
        case DIV: //除算
            b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            ans = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
            a = ans * b;
            break;
        default: //ここにはこないはず
            System.out.println("プログラムが不正に終了しました。");
            System.out.println("at makeQuestion()");
            System.exit(0);
        }
    }
    
    /***************************************************************************
     * 問題を表示する
     *
     * 引　数：i 問題番号(0から開始), kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public static void showQuestion(int i, int kind){
        //問題を表示
        System.out.print("[第" + (i + 1) + "問] ");
        switch(kind){
        case ADD: //加算
            System.out.print(a + " ＋ " + b + " ＝ ");
            break;
        case SUB: //減算
            System.out.print(a + " － " + b + " ＝ ");
            break;
        case MUL: //乗算
            System.out.print(a + " × " + b + " ＝ ");
            break;
        case DIV: //除算
            System.out.print(a + " ÷ " + b + " ＝ ");
            break;
        default: //ここにはこないはず
            System.out.println("プログラムが不正に終了しました。");
            System.out.println("at showQuestion()");
            System.exit(0);
        }
    }
    
    /***************************************************************************
     * 回答を入力する
     *
     * 引　数：なし
     * 戻り値：入力された回答値
     * その他：malinputを更新する
     **************************************************************************/
    public static int inputAns(){
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
     * 正誤を判定する
     *
     * 引　数：ans 回答値, kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：正誤(正:1, 誤:0)
     * その他：
     **************************************************************************/
    public static int judge(int ans, int kind){
        int correct = 0;
        switch(kind){
        case ADD:  //加算
            if(ans == a + b){
                correct = 1;
            }
            break;
        case SUB:  //減算
            if(ans == a - b){
                correct = 1;
            }
            break;
        case MUL:  //乗算
            if(ans == a * b){
                correct = 1;
            }
            break;
        case DIV:  //除算
            if(ans == a / b){
                correct = 1;
            }
            break;
        default: //ここにはこないはず
            System.out.println("プログラムが不正に終了しました。");
            System.out.println("at judge()");
            System.exit(0);
        }
        if(correct == 1){
            System.out.println("正解！");
        }else{
            System.out.println("まちがい。。。");
        }
        return correct;
    }
    
    /***************************************************************************
     * 得点を計算し、成績を表示する
     *
     * 引　数：correct 正答数, time 経過時間[秒]
     * 戻り値：得点
     * その他：なし
     **************************************************************************/
    public static int showResult(int correct, double time){
        double rate = (double)correct * 100.0/ (double)QUES_NUM;
        int score = (int)(rate * 10.0 / time);
        
        System.out.println();
        System.out.println(QUES_NUM + "問中、" + correct + "問正解。");
        System.out.println("正答率は" + (int)rate + "%でした。");
        System.out.println("経過時間は" + time + "秒");
        System.out.println("スコアは" + score + "点");
        
        return score;
    }
    
    /***************************************************************************
     * ランクイン処理をする
     *
     * 引　数：score 得点, kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：なし
     * その他：ランキング配列を更新する
     **************************************************************************/
    public static void ranking(int score, int kind){
        System.out.println();
        int r = RANK_NUM + 1;         //今回の順位
        boolean rank_end = false;     //順位探索終了フラグ
        for(int i = RANK_NUM - 1; i >= 0 && !rank_end; i--){
            if(score > rank[kind][i]){
                if(i != RANK_NUM - 1){
                    rank[kind][i + 1] = rank[kind][i];
                    name[kind][i + 1] = name[kind][i];
                }
                rank[kind][i] = score;
                name[kind][i] = "";
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
                name[kind][r - 1] = reader.readLine();
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
     * 引　数：kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：なし
     * その他：なし
     **************************************************************************/
    public static void showRanking(int kind){
        System.out.println("上位" + RANK_NUM + "名");
        for(int i = 0; i < RANK_NUM; i++){
            System.out.println((i + 1) + "位\t" + name[kind][i] + "\t" + rank[kind][i]);
        }
    }
}
