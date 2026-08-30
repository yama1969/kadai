package janken.ui.simpleGui;

import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 プレイヤーの手の選択ボタンを配置するパネルです。
 */
public class HandSelPanel extends JPanel{
    private GuuButton guu = null;
    private ChokiButton choki = null;
    private PaaButton paa = null;
    
    /**
     唯一のコンストラクタ。JankenFrameクラスのインスタンスを引数に取ります。<br>
     現在のバージョンではJankenFrameクラスのインスタンスのメソッドは呼び出していませんが、
     将来の拡張性のために参照を保持しています。
     @param fr アプリケーションの基底フレームを表すオブジェクト
     */
    public HandSelPanel(JankenFrame fr){
        setLayout(new GridLayout(1,3));
        guu = new GuuButton(fr);
        add(guu);
        choki = new ChokiButton(fr);
        add(choki);
        paa = new PaaButton(fr);
        add(paa);
    }
}
