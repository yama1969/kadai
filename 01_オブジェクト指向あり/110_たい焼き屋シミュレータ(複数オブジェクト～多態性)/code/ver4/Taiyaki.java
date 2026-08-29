/**
 * たい焼きの動作をシミュレートするクラスです。
 *
 * @author 山田　洋 2008/5/17
 */
public class Taiyaki{
    /** たい焼きの製造総数 */
    private static int count = 0;
    /** たい焼きの価格 */
    private int price;
    
    /**
     * 価格を引数に取るコンストラクタ。価格に負が指定された場合は0円にします。
     */
    public Taiyaki(int price){
        count++;
        setPrice(price);
    }
    
    /**
     * このたい焼きを全部食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @return 食べた後の状態のたい焼き
     */
    public Taiyaki eat(StringBuilder message){
        message.append("ほかほかで、素朴な味わいです。");
        return null;          //食べてしまうと、このたい焼きは消滅するのでnullをリターン
    }
    
    /**
     * このたい焼きを1/nだけ食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @param  n       1/nのn。
     * @return 食べた後の状態のたい焼き
     */
    public Taiyaki eat(StringBuilder message, int n){
        message.append("ほかほかで、素朴な味わいです。\nもっと食べたいな。");
        return this;          //1/nだけ食べた場合は、まだ残っているのでthisをリターン
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
    
    /**
     * たい焼きの製造総数を返します。
     *
     * @return たい焼きの製造総数
     */
    public static int getCount(){
        return count;
    }
    
    /**
     * インスタンスを説明する文字列を返します。
     *
     * @return インスタンスの説明文字列
     */
    @Override
    public String toString(){
        return "たい焼き(" + price +"円)";
    }
}
