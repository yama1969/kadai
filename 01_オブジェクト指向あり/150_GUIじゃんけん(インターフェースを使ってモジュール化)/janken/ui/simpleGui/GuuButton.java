package janken.ui.simpleGui;

import janken.frame.JankenHand;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 プレイヤーがグーを選択するためのボタンです。
 */
public class GuuButton extends HandSelButton implements ActionListener{
    
    /**
     唯一のコンストラクタ。基底フレームを引数に取ります。<br>
     ボタンのアイコンとしてグーの画像を設定します。
     @param fr 基底フレームインスタンス
     */
    public GuuButton(JankenFrame fr){
        super("janken/ui/simpleGui/pics/guu_icon.gif", fr);
        addActionListener(this);
    }
    
    /**
     プレイヤーがボタンをクリックしたときに呼び出されるメソッドです。<br>
     基底フレームインスタンスへ、プレイヤーがグーを選択したことを通知します。
     このメソッドはJVM(イベントディスパッチスレッド)により実行されるものなので、
     直接呼び出すことは禁じられています。
     @param e イベントを表すインスタンス
     */
    @Override
    public void actionPerformed(ActionEvent e){
        clickButton(JankenHand.GUU);
    }
}
