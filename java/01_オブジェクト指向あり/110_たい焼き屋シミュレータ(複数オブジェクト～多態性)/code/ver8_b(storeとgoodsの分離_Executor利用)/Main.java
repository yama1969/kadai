import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

import man.Ningen;
import man.HaveAlreadyException;
import man.NotEatException;
import store.FoodShop;
import store.NotSellException;
import store.NotMakeException;
import store.Makable;
import goods.Eatable;
import goods.TsubuanTaiyaki;
import goods.SorceTaiyaki;
import goods.CreamTaiyaki;

/**
 * 食料品屋シミュレーションにおける、ユーザインターフェースを提供します。
 *
 * @author 山田　洋 2008/5/17
 */
public class Main{
    private final int MENU_MAX = 4;                               //メニュー番号最大値
    private final int    MENU_ORDER     = 1;                      //メニュー:注文
    private final String MENU_ORDER_STR = "食料品を注文する";
    private final int    MENU_BUY       = 2;                      //メニュー:購入
    private final String MENU_BUY_STR   = "食料品を買う";
    private final int    MENU_EAT       = 3;                      //メニュー:食べる
    private final String MENU_EAT_STR   = "食料品を食べる";
    private final int    MENU_EXIT      = 4;                      //メニュー:終了
    private final String MENU_EXIT_STR  = "終了する";
    private HashMap<Integer,String> menuSet;                      //メニュー表示文字列
    
    private final int GOODS_MAX = 3;                              //商品番号最大値
    private final int     GOODS_TSUBUAN       = 1;                //つぶあんたい焼
    private final String  GOODS_TSUBUAN_STR   = "つぶあんたい焼";
    private final Makable MAKER_TSUBUAN = new Makable(){
        public Eatable make(){
            return new TsubuanTaiyaki(0);
        }
    };
    private final int     GOODS_SORCE         = 2;                //ソースたい焼
    private final String  GOODS_SORCE_STR     = "ソースたい焼";
    private final Makable MAKER_SORCE = new Makable(){
        public Eatable make(){
            return new SorceTaiyaki(0);
        }
    };
    private final int     GOODS_CREAM         = 3;                //クリームたい焼
    private final String  GOODS_CREAM_STR     = "クリームたい焼";
    private final Makable MAKER_CREAM = new Makable(){
        public Eatable make(){
            return new CreamTaiyaki(0);
        }
    };
    private HashMap<Integer,String> goodsSet;                     //商品一覧表示文字列
    private HashMap<Integer,Makable> makerSet;                    //商品作成インスタンス
    
    public Main(){
        menuSet = new HashMap<Integer,String>();
        menuSet.put(MENU_ORDER, MENU_ORDER_STR);
        menuSet.put(MENU_BUY,   MENU_BUY_STR  );
        menuSet.put(MENU_EAT,   MENU_EAT_STR  );
        menuSet.put(MENU_EXIT,  MENU_EXIT_STR );
        
        goodsSet = new HashMap<Integer,String>();
        makerSet = new HashMap<Integer,Makable>();
        goodsSet.put(GOODS_TSUBUAN, GOODS_TSUBUAN_STR);
        makerSet.put(GOODS_TSUBUAN, MAKER_TSUBUAN    );
        goodsSet.put(GOODS_SORCE,   GOODS_SORCE_STR  );
        makerSet.put(GOODS_SORCE,   MAKER_SORCE      );
        goodsSet.put(GOODS_CREAM,   GOODS_CREAM_STR  );
        makerSet.put(GOODS_CREAM,   MAKER_CREAM      );
    }
    
    /**
     * 食料品屋シミュレーションのメインメニューの表示、操作入力、結果出力をします。
     */
    public void showMenu(){
        FoodShop shop = new FoodShop();
        Ningen man = new Ningen(shop);
        System.out.println("食料品屋シミュレーションへようこそ！");
        System.out.println("（今のところ、たい焼きしか扱ってないですが。。。）");
        
        int cmd = 0;
        while(true){
            System.out.println();
            System.out.println("メニュー番号を入力して下さい。");
            int no = 1;
            String menu = null;
            while((menu = menuSet.get(no)) != null){
                System.out.println(no + ":" + menu);
                no++;
            }
            cmd = inputNum("番号を入力してください。＞", MENU_MAX);
            
            switch(cmd){
                case MENU_ORDER:
                    order(man);
                    break;
                case MENU_BUY:
                    buy(man, shop);
                    break;
                case MENU_EAT:
                    eat(man);
                    break;
                case MENU_EXIT:
                    System.exit(0);
                    break;
                default:
                    System.out.println("予期しないエラーが発生しました(at Main.showMenu())。終了します。");
                    cmd = MENU_EXIT;
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
     *
     * @param man 注文を実行するNingenインスタンス
     */
    private void order(Ningen man){
        System.out.println();
        System.out.println("商品メニュー");
        int no = 1;
        String goods = null;
        while((goods = goodsSet.get(no)) != null){
            System.out.println(no + ":" + goods);
            no++;
        }
        int cmd = inputNum("番号を入力してください。＞", GOODS_MAX);
        
        Makable maker = makerSet.get(cmd);
        try{
            man.orderFood(maker);
            System.out.println(goodsSet.get(cmd) + "を１つ注文しました。");
        }catch(NotMakeException e){
            System.out.println("もう作れないそうです。");
        }
    }
    
    /**
     * 買うメニューの実行と、結果表示をします。
     *
     * @param man  購入を実行するNingenインスタンス
     * @param shop 購入先FoodShopインスタンス
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
     *
     * @param man 食べるを実行するNingenインスタンス
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
