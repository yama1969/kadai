package goods;

/**
 * クリームたい焼きの動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 * @author 山田　洋 2008/10/11
 */
public class CreamTaiyaki implements Eatable{
    /** たい焼きの価格 */
    private int price;
    
    /**
     * 価格を引数に取るコンストラクタ。価格に負が指定された場合は0円にします。
     */
    public CreamTaiyaki(int price){
        setPrice(price);
        int wait = (int)(Math.random() * 15001.0) + 15000;                      //追加:2008/10/11 ver6 山田
        try{                                                                    //
            Thread.sleep(wait);                                                 //
        }catch(InterruptedException e){                                         //
            //割り込まれても特にすることはない                                  //
        }                                                                       //
    }
    
    /**
     * このたい焼きを全部食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @return 食べた後の状態のたい焼き
     */
    @Override
    public Eatable eat(StringBuilder message){
        message.append("クリームたい焼きです。\n思いの外さっぱりした印象の、クリーム味です。");
        return null;          //食べてしまうと、このたい焼きは消滅するのでnullをリターン
    }
    
    /**
     * このたい焼きを1/nだけ食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @param  n       1/nのn。
     * @return 食べた後の状態のたい焼き
     */
    @Override
    public Eatable eat(StringBuilder message, int n){
        eat(message);
        message.append("\nもっと食べたいな。");
        return this;          //1/nだけ食べた場合は、まだ残っているのでthisをリターン
    }
    
    /**
     * このたい焼きの価格を設定します。負が指定された場合は0円にします。
     *
     * @param price このたい焼きの価格
     */
    @Override
    public void setPrice(int price){
        if(price < 0){
            price = 0;
        }
        this.price = price;
    }
    
    /**
     * このたい焼きの価格を返します。
     *
     * @return このたい焼きの価格
     */
    @Override
    public int getPrice(){
        return price;
    }
    
    /**
     * インスタンスを説明する文字列を返します。
     *
     * @return インスタンスの説明文字列
     */
    @Override
    public String toString(){
        return "クリームたい焼き(" + price +"円)";
    }
}
