/**
 計算出題プログラムのユーザーI/Fを担うクラスです。
*/
public class CalcUI{
    /**
     引数で渡されたメッセージを表示します。<br>
     コンソール表示の場合は、表示後に改行します。
     
     @param message 表示する問題文やメッセージ
    */
    public void showMessage(String message){
        System.out.println(message);
    }
    
    /**
     引数で渡された問題文を表示します。<br>
     コンソール表示の場合、showMessage()メソッドとの違いは、表示した問題文の後ろに「=」が表示されることです。
     また、「=」の後ろでは改行しません。
     
     @param question 問題文の文字列
    */
    public void showQuestion(String question){
        System.out.print(question + " = ");
    }
    
    /**
     回答を入力し、入力された値を返します。
     
     @return 入力された回答値
    */
    public int inputAnswer(){
        int ans = Keyboard.readInt();
        return ans;
    }
    
    /**
     正答/誤答の表示をします。
     
     @param check 正答or誤答 (true:正答, false:誤答)
    */
    public void showCheck(boolean check){
        if(check){
            System.out.println("正解！");
        }else{
            System.out.println("不正解・・・");
        }
    }
    
    /**
     最終結果の表示をします。
     
     @param right 正答数
     @param num 全問題数
    */
    public void showResult(int right, int num){
        System.out.print("全" + num + "問中、");
        System.out.println(right + "問正解でした。");
    }
}
