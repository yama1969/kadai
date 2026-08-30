/**
 わり算問題クラス
*/
public class DivMondai implements Mondai{
    //-----問題表示と回答チェック---------------------------------------------------------
    public int showQuestion(){
        int a = (int)(Math.random() * 10);                                                //解答は0～9の整数とする
        int b = (int)(Math.random() * 9) + 1;
        int c = a * b;
        
        System.out.printf("%2d ÷ %2d ＝ ? ",c,b);
        int ans = new Keybord().inputNum();
        
        if(ans == a){
            System.out.println("正解！");
            return 1;
        }
        System.out.println("まちがい・・・");
        return 0;
    }
}
