package store;

import goods.Eatable;

/**
 * 食料品注文を実行するメソッドを持つインターフェースです。注文内容を表すクラスは、このインターフェースを実装します。
 */
public interface Makable{
    /**
     * 注文を実行するメソッド。
     *
     * @return 注文の結果、生成されたEatableインスタンス
     */
    Eatable make();
}
