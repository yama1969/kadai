/**
 計算出題プログラムの計算問題作成インターフェースです。
 計算問題クラスは、このインターフェースを実装してください。
*/
public interface CalcQuestion{
    /**
     新たな計算問題を作成し、問題文の文字列を返すメソッドです。<br>
     
     @return 問題文の文字列
    */
    public String next();
    
    /**
     引数に渡された答案の正誤判定をします。<br>
     現在の問題の正答と引数に渡された答案とを比較し、正誤結果を返します。
     
     @param ans 答案の整数
     @return 正誤判定結果(正解:true, 不正解:false)
    */
    public boolean check(int ans);
}
