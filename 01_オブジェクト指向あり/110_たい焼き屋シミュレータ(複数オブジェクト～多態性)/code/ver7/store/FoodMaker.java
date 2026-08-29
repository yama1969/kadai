package store;

import goods.Eatable;
import goods.TsubuanTaiyaki;
import goods.SorceTaiyaki;
import goods.CreamTaiyaki;

/**
 * 食料品を作るクラスです。
 *
 * @author 山田　洋 2008/10/11
 */
public class FoodMaker implements Runnable{
    private FoodStockable stocker;
    private int price;
    
    /**
     * 作った食料品を入れるFoodStocable実装インスタンスを指定するコンストラクタ。
     *
     * @param stocker 作った食料品を入れるFoodStockable実装インスタンス
     * @param price   作る食料品の価格
     */
    public FoodMaker(FoodStockable stocker, int price){
        this.stocker = stocker;
        this.price = price;
        new Thread(this).start();
    }
    
    /**
     * 食料品を作り、指定されたFoodStockableへ格納します。
     */
    public void run(){
        Eatable food = null;
        int n = (int)(Math.random() * 3.0);
        switch(n){
            case 0:
                food = new TsubuanTaiyaki(price);
                break;
            case 1:
                food = new CreamTaiyaki(price);
                break;
            case 2:
                food = new SorceTaiyaki(price);
                break;
            default:
                System.out.println("予期せぬエラーが発生しました(FoodMaker.run())。終了します。");
                System.exit(1);
        }
        stocker.putStock(food);
    }
}
