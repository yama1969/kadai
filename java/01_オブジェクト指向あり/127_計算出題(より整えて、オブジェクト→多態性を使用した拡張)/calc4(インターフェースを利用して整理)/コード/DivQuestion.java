/**
 計算出題プログラムの割り算問題を作成する計算問題クラスです。<br>
 CalcQuestionインターフェースの実装です。
*/
public class DivQuestion implements CalcQuestion{
    private int a;  //計算問題の値1
    private int b;  //計算問題の値2
    
    /**
     新たな割り算の問題を作成し、問題文の文字列を返すメソッドです。<br>
     このメソッドを実行すると、オブジェクトが持つ問題は次の問題に変わります。<br>
     返される問題文の文字列は、例えば「9 ÷ 3」のようなものです。<br>
     <table border="1">
       <caption>各整数乱数の範囲</caption>
       <tr><td>割られる数</td><td>0～81</td></tr>
       <tr><td>割る数</td><td>1～9</td></tr>
       <tr><td>回答</td><td>0～9</td></tr>
     </table>
     
     @return 問題文の文字列
    */
    @Override
    public String next(){
        b = (int)(Math.random() * 9.0) + 1;
        a = (int)(Math.random() * 10.0) * b;
        String q = a + " ÷ " + b;
        return q;
    }
    
    /**
     引数に渡された答案の正誤判定をします。<br>
     現在の問題の正答と引数に渡された答案とを比較し、正誤結果を返します。
     
     @param ans 答案の整数
     @return 正誤判定結果(正解:true, 不正解:false)
    */
    @Override
    public boolean check(int ans){
        if(ans == a / b){
            return true;
        }
        return false;
    }
}
