package store;

import goods.Eatable;

/**
 * 食料品在庫を扱うクラスが実装するインターフェースです。
 *
 * @author 山田　洋 2008/10/11
 */
public interface FoodStockable{
    /**
     * 食料品を在庫に入れます。
     *
     * @param food 在庫に入れる食料品インスタンス(Eatable実装インスタンス)
     */
    public void putStock(Eatable food);
}
