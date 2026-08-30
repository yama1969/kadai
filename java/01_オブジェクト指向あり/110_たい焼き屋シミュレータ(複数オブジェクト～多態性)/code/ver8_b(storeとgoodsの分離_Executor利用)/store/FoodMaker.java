package store;

import goods.Eatable;

/**
 * 食料品を作るクラスです。
 *
 * @author 山田　洋 2008/10/14
 */
public class FoodMaker implements Runnable{
    private FoodStockable stocker;
    private Makable maker;
    
    /**
     * 作った食料品を入れるFoodStocable実装インスタンスを指定するコンストラクタ。
     *
     * @param stocker 作った食料品を入れるFoodStockable実装インスタンス
     * @param maker   実際に食料品を作るインスタンス
     */
    public FoodMaker(FoodStockable stocker, Makable maker){
        this.stocker = stocker;
        this.maker = maker;
        new Thread(this).start();
    }
    
    /**
     * 食料品を作り、指定されたFoodStockableへ格納します。
     */
    public void run(){
        stocker.putStock(maker.make());
    }
}
