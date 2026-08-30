package j_method;
/******************************************************************************
 * クラス・インスタンスの機能としてのメソッド ～ スタック
 * int型データを保持するスタックのクラスIntStackのテスト
 *****************************************************************************/
public class Kadai2010{
    
    /**************************************************************************
     * メインメソッド
     *************************************************************************/
    public static void main(String[] args){
        //スタックオブジェクトの生成
        IntStack is = new IntStack();
        
        //コマンド処理
        String prompt = "[e:終了, i:積む, o:取出す, g:最上データ, c:データ数, "
                      + "a:全クリア]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){ //eなら終了
            switch(cmd){
            case 'i': //スタックにpush
                int dat = Keyboard.readInt("pushする値");
                int num = is.push(dat);
                if(num == -1){
                    System.out.println("すでに満杯です。");
                }else{
                    System.out.println(num + "個");
                }
                break;
            case 'o': //スタックからpop
                dat = is.pop();
                if(dat == Integer.MIN_VALUE){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(dat);
                    num = is.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'g': //次にpopされる値を得る
                dat = is.get();
                if(dat == Integer.MIN_VALUE){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(dat);
                    num = is.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'c': //スタックに積まれているデータ数を得る
                System.out.println(is.getCount() + "個");
                break;
            case 'a': //スタックを全クリアする
                is.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
