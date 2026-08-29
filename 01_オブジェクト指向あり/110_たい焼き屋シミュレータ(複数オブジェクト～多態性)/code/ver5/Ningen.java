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
     */
    public Ningen(FoodShop shop){
        this.omise = shop;
        food = null;
    }
    
    /**
     * 食料品を注文します。
     */
    public void orderFood() throws NotMakeException{
        omise.makeFood();
    }
    
    /**
     * 食料品を買います。
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
