package man;

import store.FoodShop;
import store.NotSellException;
import store.NotMakeException;
import store.Makable;
import goods.Eatable;

/**
 * 人間の動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 */
public class Ningen{
    /** 人間が利用する食料品屋 */
    private FoodShop omise;
    /** 人間が持つ食料品*/
    private Eatable food;
    
    /**
     * 利用する食料品屋インスタンスの参照を取るコンストラクタです。
     *
     * @param shop Ningenが利用するFoodShopインスタンス
     */
    public Ningen(FoodShop shop){
        this.omise = shop;
        food = null;
    }
    
    /**
     * 食料品を注文します。
     *
     * @param  maker            注文内容を表すMakableインスタンス
     * @throws NotMakeException 在庫いっぱいなどで、注文を受け付けられないときにスローされます。
     */
    public void orderFood(Makable maker) throws NotMakeException{
        omise.makeFood(maker);
    }
    
    /**
     * 食料品を買います。
     *
     * @throws NotSellException 在庫切れなどで、売ることが出来ないときにスローされます。
     */
    public void buyFood() throws HaveAlreadyException, NotSellException{
        if(food != null){
            throw new HaveAlreadyException();
        }
        food = omise.selFood();
    }
    
    /**
     * 食料品を食べます。
     *
     * @return 食料品の味メッセージ文字列。
     * @throws NotEatException 食料品を持っていないなどで、食べることが出来ないときにスローされます。
     */
    public String eatFood() throws NotEatException{
        if(food == null){
            throw new NotEatException();
        }
        
        StringBuilder strb = new StringBuilder();
        int n = (int)(Math.random() * 2.0) + 1;
        if(n == 1){
            food = food.eat(strb);
        }else{
            food = food.eat(strb, n);
        }
        return strb.toString();
    }
}
