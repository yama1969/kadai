/**
 * 人間の動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 */
public class Ningen{
    /** 人間が利用するたい焼き屋 */
    private Taiyakiya omise;
    /** 人間が持つたい焼き*/
    private Taiyaki taiyaki;
    
    /**
     * 利用するたい焼き屋インスタンスの参照を取るコンストラクタです。
     */
    public Ningen(Taiyakiya omise){
        this.omise = omise;
        taiyaki = null;
    }
    
    /**
     * たい焼きを注文します。
     *
     * @return 0:注文完了 1:注文不可
     */
    public int orderTaiyaki(){
        if(omise.makeTaiyaki()){
            return 0;
        }
        return 1;
    }
    
    /**
     * たい焼きを買います。
     *
     * @return 0:購入完了 1:購入済み 2:売り切れ
     */
    public int buyTaiyaki(){
        if(taiyaki != null){
            return 1;
        }
        taiyaki = omise.selTaiyaki();
        if(taiyaki == null){
            return 2;
        }
        return 0;
    }
    
    /**
     * たい焼きを食べます。
     *
     * @return たい焼きの味メッセージ文字列。食べられないときはnull。
     */
    public String eatTaiyaki(){
        if(taiyaki == null){
            return null;
        }
        
        StringBuilder strb = new StringBuilder();
        int n = (int)(Math.random() * 2.0) + 1;
        if(n == 1){
            taiyaki = taiyaki.eat(strb);
        }else{
            taiyaki = taiyaki.eat(strb, n);
        }
        return strb.toString();
    }
}
