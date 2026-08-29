/**
 計算出題プログラムの実行クラスです。<br>
 今回、このクラスの記述に変更はありませんが、CalcQuestionがインターフェースになったので、
 コンパイルのし直しは必要です。
*/
public class CalcController{
    /**
     計算問題を10問出題し、成績算出を行います。<br>
     問題作成処理は計算問題オブジェクトに、画面表示処理と入力処理はユーザーI/Fオブジェクトに移譲します。
     
     @param ui ユーザーI/Fオブジェクト
     @param question 計算問題オブジェクト
    */
    public void exec(CalcUI ui, CalcQuestion question){
        final int Q_NUM = 10;                    //出題数
        int right = 0;                           //正答数
        for(int i = 0; i < Q_NUM; i++){
            String q = question.next();          //問題の作成
            ui.showQuestion(q);                  //問題の表示
            int ans = ui.inputAnswer();          //回答の入力
            boolean check = question.check(ans); //正誤判定
            ui.showCheck(check);                 //判定の表示
            if(check){
                right++;                         //正答数加算
            }
        }
        ui.showResult(right, Q_NUM);             //最終結果の表示
    }
}
