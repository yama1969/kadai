/**
 * つぶあんたい焼きをシミュレートするクラスです。
 * たい焼きクラスを継承しています。
 *
 * @author 山田　洋 2008/5/17
 */
public class SorceTaiyaki extends Taiyaki{
    /**
     * 価格を引数に取るコンストラクタです。
     */
    public SorceTaiyaki(int price){
        super(price);
    }
    
    /**
     * このたい焼きを全部食べます。
     *
     * @param  message 味メッセージ文字列を入れるStringBuilderインスタンス
     * @return 食べた後の状態のたい焼き
     */
    @Override
    public Taiyaki eat(StringBuilder message){
        message.append("ドロッとしたソースがまったりとしていて、それでいて、とてもしつこいです。");
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
    public Taiyaki eat(StringBuilder message, int n){
        eat(message);
        message.append("\nもっと食べたいな。");
        return this;          //1/nだけ食べた場合は、まだ残っているのでthisをリターン
    }
    
    /**
     * インスタンスを説明する文字列を返します。
     *
     * @return インスタンスの説明文字列
     */
    @Override
    public String toString(){
        return "ソースたい焼き(" + getPrice() +"円)";
    }
}
