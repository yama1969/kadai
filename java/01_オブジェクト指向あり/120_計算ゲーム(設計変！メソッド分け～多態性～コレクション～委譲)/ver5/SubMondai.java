/**
 ひき算問題クラス
*/
public class SubMondai implements Mondai{
    //-----問題表示と回答チェック---------------------------------------------------------
    public int showQuestion(){
        int a = (int)(Math.random() * 10);                                                //数値は0～9の整数
        int b = (int)(Math.random() * 10);
        
        if(a < b){                                                                        //解答は正のみとする
            int swap = a;
            a = b;
            b = swap;
        }
        
        System.out.printf("%2d - %2d = ? ",a,b);
        int ans = new Keybord().inputNum();
        
        if(ans == a - b){
            System.out.println("正解！");
            return 1;
        }
        System.out.println("まちがい・・・");
        return 0;
    }
}
