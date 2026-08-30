package store;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import goods.Eatable;
import goods.TsubuanTaiyaki;
import goods.SorceTaiyaki;
import goods.CreamTaiyaki;

/**
 * 食料品を作るクラスです。
 *
 * @author 山田　洋 2008/10/14
 */
public class FoodMaker implements Runnable{
    private FoodStockable stocker;
    private String className;
    private int price;
    
    /**
     * 作った食料品を入れるFoodStocable実装インスタンスを指定するコンストラクタ。
     *
     * @param stocker 作った食料品を入れるFoodStockable実装インスタンス
     * @param price   作る食料品の価格
     */
    public FoodMaker(FoodStockable stocker, String className, int price){
        this.stocker = stocker;
        this.className = className;
        this.price = price;
        new Thread(this).start();
    }
    
    /**
     * 食料品を作り、指定されたFoodStockableへ格納します。
     */
    public void run(){
        Eatable food = null;
        try{
            Class<Eatable> cl = (Class<Eatable>)Class.forName(className);  //これはどうすれば警告が出なくなるのか？
            Constructor<Eatable> con = cl.getConstructor(Integer.TYPE);
            food = con.newInstance(price);
            stocker.putStock(food);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(NoSuchMethodException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
