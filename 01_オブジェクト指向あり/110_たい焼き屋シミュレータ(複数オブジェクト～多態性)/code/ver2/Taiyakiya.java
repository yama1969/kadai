/**
 * たい焼き屋の動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 */
public class Taiyakiya{
    /** 最大在庫数 */
    private final int MAX_STOCK = 5;
    /** たい焼き定価 */
    private final int PRICE = 100;
    
    /** たい焼き屋の売り上げ */
    private int uriage;
    /** たい焼き在庫配列 */
    private final Taiyaki[] stock;
    /** 在庫数 */
    private int zaiko;
    /** 在庫取出し位置 */
    private int pick;
    
    /**
     * 引数なしコンストラクタ。
     */
    public Taiyakiya(){
        uriage = 0;
        stock = new Taiyaki[MAX_STOCK];
        zaiko = 0;
        pick = 0;
    }
    
    /**
     * たい焼きを作ります。
     *
     * @return true:製造完了 false:在庫いっぱいで製造不可
     */
    public boolean makeTaiyaki(){
        if(zaiko >= stock.length){
            return false;
        }
        stock[(pick + zaiko) % MAX_STOCK] = new Taiyaki(PRICE);
        zaiko++;
        return true;
    }
    
    /**
     * たい焼きを売ります。
     *
     * @return 売ったたい焼き。売り切れの場合はnull。
     */
    public Taiyaki selTaiyaki(){
        if(zaiko <= 0){
            return null;
        }
        zaiko--;
        Taiyaki t = stock[pick];  //Taiyakiインスタンスへの参照をstock[]から外すことにより、たい焼き屋の手から離れたことを表現
        stock[pick] = null;
        pick = (pick + 1) % MAX_STOCK;
        uriage += t.getPrice();
        return t;
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
