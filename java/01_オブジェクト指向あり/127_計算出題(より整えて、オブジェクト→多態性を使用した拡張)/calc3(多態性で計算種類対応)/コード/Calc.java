/**
 計算出題プログラムの起動用クラスです。main()メソッドを持ちます。
*/
public class Calc{
    
    private Calc(){
    }
    
    /**
     計算出題プログラムの起動をします。<br>
     ユーザI/Fオブジェクトを生成後、問題選択オブジェクトを使用して計算問題オブジェクトを生成し、
     それから実行オブジェクトを生成してexec()メソッドを呼び出します。
     
     @param args コマンドライン引数(未使用)
    */
    public static void main(String[] args){
        CalcUI ui = new CalcUI();                        //ユーザーI/F
        
        Selecter s = new Selecter();                     //計算問題の選択
        CalcQuestion question = s.select(ui);
        s = null;
        
        if(question != null){
            CalcController con = new CalcController();   //実行オブジェクト
            con.exec(ui, question);
        }
    }
}
