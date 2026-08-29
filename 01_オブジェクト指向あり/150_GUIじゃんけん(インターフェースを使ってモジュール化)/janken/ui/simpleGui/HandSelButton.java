package janken.ui.simpleGui;

import janken.frame.JankenHand;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;

/**
 プレイヤーの手の選択ボタンの基底クラスです。アプリケーションの基底フレームへの参照を持ち、
 ボタンをクリックしたときの動作を基底フレームに委譲するメソッドを持っています。
 */
public abstract class HandSelButton extends JButton{
    private JankenFrame fr = null;
    
    /**
     唯一のコンストラクタ。アイコン画像ファイル名と基底フレームを引数に取ります。<br>
     アイコン画像ファイル名がnullもしくは空文字列だった場合は、アイコンの設定をしません。
     基底フレームの参照がnullだった場合、clickButton()メソッドは何も動作しません。
     @param fname ボタンアイコンの画像ファイル名
     @param fr 基底フレームインスタンスの参照
     */
    public HandSelButton(String fname, JankenFrame fr){
        this.fr = fr;
        if(fname != null && !fname.equals("")){
            Image pic = getToolkit().getImage(fname);
            setIcon(new ImageIcon(pic));
        }
    }
    
    /**
     プレイヤーの手の選択ボタンをクリックしたときに呼び出されるメソッドです。<br>
     このクラスを継承したボタンが、クリックされたときにこのメソッドを呼び出すと、
     クリックに応じた処理を基底フレームに委譲します。基底フレームへの参照がnullの場合は何もしません。
     @param hand クリックされたボタンが表すプレイヤーの手
     */
    protected void clickButton(JankenHand hand){
        if(fr == null){
            return;
        }
        fr.selectHand(hand);
    }
}
