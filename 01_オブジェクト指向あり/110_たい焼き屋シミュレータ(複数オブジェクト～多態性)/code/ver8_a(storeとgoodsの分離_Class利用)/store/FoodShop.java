package store;

import goods.Eatable;

/**
 * 食料品屋の動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/10/14
 */
public class FoodShop implements FoodStockable{
    /** 最大在庫数 */
    private final int MAX_STOCK = 5;
    /** 食料品定価 */
    private final int PRICE = 100;
    
    /** 食料品屋の売り上げ */
    private int uriage;
    /** 食料品在庫配列 */
    private final Eatable[] stock;
    /** 在庫数 */
    private int zaiko;
    /** 予約数 */
    private int yoyaku;
    /** 在庫取出し位置 */
    private int pick;
    
    /**
     * 引数なしコンストラクタ。
     */
    public FoodShop(){
        uriage = 0;
        stock = new Eatable[MAX_STOCK];
        zaiko = 0;
        yoyaku = 0;
        pick = 0;
    }
    
    /**
     * 食料品を作ります。
     */
    public void makeFood(String className) throws NotMakeException{
        if(yoyaku >= stock.length){
            throw new NotMakeException();
        }
        
        try{
            Class<?> make_cl = Class.forName(className);
            Class<?> check_cl = Class.forName("goods.Eatable");
            if(!check_cl.isAssignableFrom(make_cl)){
                //System.out.println("Eatableではありません。");
                throw new NotMakeException();
            }
        }catch(ClassNotFoundException e){
            //System.out.println("クラス名が見つかりません。");
            throw new NotMakeException();
        }
        
        yoyaku++;
        FoodMaker maker = new FoodMaker(this, className, PRICE);
    }
    
    /**
     * 食料品を売ります。
     *
     * @return 売った食料品。
     */
    public Eatable selFood() throws NotSellException{
        if(zaiko <= 0){
            throw new NotSellException();
        }
        zaiko--;
        Eatable e = stock[pick];  //Eatable実装インスタンスへの参照をstock[]から外すことにより、食料品屋の手から離れたことを表現
        stock[pick] = null;
        pick = (pick + 1) % MAX_STOCK;
        uriage += e.getPrice();
        return e;
    }
    
    /**
     * 現在の売り上げ額を返します。
     *
     * @return 現在の売り上げ額
     */
    public int getUriage(){
        return uriage;
    }
    
    /**
     * 在庫へ食料品を入れます。
     *
     * @param food 在庫へ入れる食料品インスタンス(Eatable実装)
     */
    public synchronized void putStock(Eatable food){
        stock[(pick + zaiko) % MAX_STOCK] = food;
        zaiko++;
        yoyaku--;
    }
}
