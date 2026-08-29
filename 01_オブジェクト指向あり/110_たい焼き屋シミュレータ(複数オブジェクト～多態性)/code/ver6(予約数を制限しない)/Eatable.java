/**
 * 食べられる物を表すインターフェースです。
 *
 * @author 山田　洋 2008/5/17
 */
public interface Eatable{
    /**
     * このたい焼きを全部食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @return 食べた後の状態のたい焼き
     */
    public Eatable eat(StringBuilder message);
    
    /**
     * このたい焼きを1/nだけ食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @param  n       1/nのn。
     * @return 食べた後の状態のたい焼き
     */
    public Eatable eat(StringBuilder message, int n);
    
    /**
     * このたい焼きの価格を設定します。
     *
     * @param price このたい焼きの価格
     */
    public void setPrice(int price);
    
    /**
     * このたい焼きの価格を返します。
     *
     * @return このたい焼きの価格
     */
    public int getPrice();
}
