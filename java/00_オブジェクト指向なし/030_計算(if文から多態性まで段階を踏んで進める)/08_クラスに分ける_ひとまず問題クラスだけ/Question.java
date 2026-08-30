/*******************************************************************************
 * 計算問題を扱うクラス
 ******************************************************************************/
class Question{
    public static final int ADD = 0;                                  //加算
    public static final int SUB = 1;                                  //減算
    public static final int MUL = 2;                                  //乗算
    public static final int DIV = 3;                                  //除算
    
    private static final int MIN = 1;                                 //問題に使う数の最小値
    private static final int MAX = 9;                                 //問題に使う数の最大値
    
    private int a;                                                    //問題の数値a
    private int b;                                                    //問題の数値b
    
    /***************************************************************************
     * 問題を作る
     *
     * 引　数：kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：なし
     * その他：a,bを更新する
     **************************************************************************/
    public void makeQuestion(int kind){
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
    public void showQuestion(int i, int kind){
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
     * 正誤を判定する
     *
     * 引　数：ans 回答値, kind 計算種類(ADD,SUB,MUL,DIVのいずれか)
     * 戻り値：正誤(正:1, 誤:0)
     * その他：なし
     **************************************************************************/
    public int judge(int ans, int kind){
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
}
