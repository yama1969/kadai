/**
 計算出題プログラムの起動用クラスです。main()メソッドを持ちます。
*/
public class Calc{
    
    private Calc(){
    }
    
    /**
     計算出題プログラムの起動をします。<br>
     ユーザI/Fオブジェクト、計算問題オブジェクト、実行オブジェクトをそれぞれ
     生成し、実行オブジェクトのexec()メソッドを呼び出して開始します。
     
     @param args コマンドライン引数(未使用)
     
    */
    public static void main(String[] args){
        CalcUI ui = new CalcUI();                    //ユーザーI/F
        CalcQuestion question = new CalcQuestion();  //計算問題オブジェクト
        
        CalcController con = new CalcController();   //実行オブジェクト
        con.exec(ui, question);
    }
}
