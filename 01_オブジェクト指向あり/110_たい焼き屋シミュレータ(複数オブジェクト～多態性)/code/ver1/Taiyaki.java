/**
 * たい焼きの動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 */
public class Taiyaki{
    /** たい焼きの価格 */
    private int price;
    
    /**
     * 価格を引数に取るコンストラクタ。価格に負が指定された場合は0円にします。
     */
    public Taiyaki(int price){
        setPrice(price);
    }
    
    /**
     * このたい焼きを食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @return 食べた後の状態のたい焼き
     */
    public Taiyaki eat(StringBuilder message){
        message.append("ほかほかで、素朴な味わいです。");
        return null;          //食べてしまうと、このたい焼きは消滅するのでnullをリターン
    }
    
    /**
     * このたい焼きの価格を設定します。負が指定された場合は0円にします。
     *
     * @param price このたい焼きの価格
     */
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
    public int getPrice(){
        return price;
    }
}
