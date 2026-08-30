import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 食料品屋シミュレーションにおける、ユーザインターフェースを提供します。
 *
 * @author 山田　洋 2008/5/17
 */
public class Main{
    /**
     * 食料品屋シミュレーションのメインメニューの表示、操作入力、結果出力をします。
     */
    public void showMenu(){
        FoodShop shop = new FoodShop();
        Ningen man = new Ningen(shop);
        System.out.println("食料品屋シミュレーションへようこそ！");
        System.out.println("（今のところ、たい焼きしか扱ってないですが。。。）");
        
        int cmd = 0;
        while(cmd != 4){
            System.out.println();
            System.out.println("メニュー番号を入力して下さい。");
            System.out.println("1:食料品を注文する");
            System.out.println("2:食料品を買う");
            System.out.println("3:食料品を食べる");
            System.out.println("4:終了する");
            cmd = inputNum("番号を入力してください。＞",4);
            
            switch(cmd){
                case 1:
                    order(man);
                    break;
                case 2:
                    buy(man, shop);
                    break;
                case 3:
                    eat(man);
                    break;
                case 4:
                    System.exit(0);     //追加 ver6 残スレッドがあっても終了。
                    break;
                default:
                    System.out.println("予期しないエラーが発生しました(at Main.showMenu())。終了します。");
                    cmd = 4;
            }
        }
    }
    
    /**
     * メニュー番号入力処理をします。
     *
     * @param  mess 入力表示メッセージ
     * @param  max  メニュー最大値
     * @return 入力されたメニュー番号
     */
    private int inputNum(String mess, int max){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try{
                System.out.println();
                System.out.print(mess);
                int n = Integer.parseInt(reader.readLine());
                if(n > 0 && n <= max){
                    return n;
                }
            }catch(IOException e){
                System.out.println("入力エラーが発生しました。終了します。");
                System.exit(1);
            }catch(NumberFormatException e){
            }
            System.out.println("1～" + max + "までの数を入力してください。");
        }
    }
    
    /**
     * 注文メニューの実行と、結果表示をします。
     */
    private void order(Ningen man){
        try{
            man.orderFood();
            System.out.println("食料品を１つ注文しました。");
        }catch(NotMakeException e){
            System.out.println("もう作れないそうです。");
        }
    }
    
    /**
     * 買うメニューの実行と、結果表示をします。
     */
    private void buy(Ningen man,FoodShop shop){
        try{
            man.buyFood();
            System.out.println("食料品を１つ買いました。");
            System.out.println("食料品屋の売り上げは" + shop.getUriage() + "円になりました。");
        }catch(HaveAlreadyException e){
            System.out.println("もう買ってあります。");
        }catch(NotSellException e){
            System.out.println("売り切れだそうです。");
        }
    }
    
    /**
     * 食べるメニューの実行と、結果表示をします。
     */
    private void eat(Ningen man){
        try{
            String mess = man.eatFood();
            System.out.println("食料品を食べました。");
            System.out.println(mess);
        }catch(NotEatException e){
            System.out.println("まだ、食料品を買っていません。");
        }
    }
    
    /**
     * 初期インスタンスの生成と起動メソッド呼び出しをします。
     */
    public static void main(String[] args){
        new Main().showMenu();
    }
}
