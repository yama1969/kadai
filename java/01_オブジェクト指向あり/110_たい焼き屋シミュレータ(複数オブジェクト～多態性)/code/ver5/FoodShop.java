/**
 * 食料品屋の動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 */
public class FoodShop{
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
    /** 在庫取出し位置 */
    private int pick;
    
    /**
     * 引数なしコンストラクタ。
     */
    public FoodShop(){
        uriage = 0;
        stock = new Eatable[MAX_STOCK];
        zaiko = 0;
        pick = 0;
    }
    
    /**
     * 食料品を作ります。
     */
    public void makeFood() throws NotMakeException{
        if(zaiko >= stock.length){
            throw new NotMakeException();
        }
        int n = (int)(Math.random() * 3.0);
        switch(n){
            case 0:
                stock[(pick + zaiko) % MAX_STOCK] = new TsubuanTaiyaki(PRICE);
                break;
            case 1:
                stock[(pick + zaiko) % MAX_STOCK] = new CreamTaiyaki(PRICE);
                break;
            case 2:
                stock[(pick + zaiko) % MAX_STOCK] = new SorceTaiyaki(PRICE);
                break;
            default:
                System.out.println("予期せぬエラーが発生しました(FoodShop.makeFood())。終了します。");
                System.exit(1);
        }
        zaiko++;
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
}
