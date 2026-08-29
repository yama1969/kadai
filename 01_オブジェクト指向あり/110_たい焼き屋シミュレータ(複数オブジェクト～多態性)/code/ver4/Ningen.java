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
     */
    public void orderTaiyaki() throws NotMakeException{
        omise.makeTaiyaki();
    }
    
    /**
     * たい焼きを買います。
     */
    public void buyTaiyaki() throws HaveAlreadyException, NotSellException{
        if(taiyaki != null){
            throw new HaveAlreadyException();
        }
        taiyaki = omise.selTaiyaki();
    }
    
    /**
     * たい焼きを食べます。
     *
     * @return たい焼きの味メッセージ文字列。
     */
    public String eatTaiyaki() throws NotEatException{
        if(taiyaki == null){
            throw new NotEatException();
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
