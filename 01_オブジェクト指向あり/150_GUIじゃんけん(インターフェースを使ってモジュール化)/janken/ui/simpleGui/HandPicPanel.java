package janken.ui.simpleGui;

import janken.frame.JankenHand;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 対戦相手の手を表示するパネルのクラスです。手の表示を高速回転する機能を持ちます。
 */
public class HandPicPanel extends JPanel implements Runnable{
    private JankenFrame fr = null;
    
    private Image[] imgs = null;
    private int img_no = 0;
    private boolean roll = false;
    
    /**
     唯一のコンストラクタ。JankenFrameクラスのインスタンスを引数に取ります。<br>
     現在のバージョンではJankenFrameクラスのインスタンスのメソッドは呼び出していませんが、
     将来の拡張性のために参照を保持しています。<br>
     グー、チョキ、パーのそれぞれの画像をロードします。
     @param fr アプリケーションの基底フレームを表すオブジェクト
     */
    public HandPicPanel(JankenFrame fr){
        this.fr = fr;
        imgs = new Image[3];
        Toolkit tk = getToolkit();
        imgs[0] = tk.getImage("janken/ui/simpleGui/pics/guu.gif");
        imgs[1] = tk.getImage("janken/ui/simpleGui/pics/choki.gif");
        imgs[2] = tk.getImage("janken/ui/simpleGui/pics/paa.gif");
        setSize(420,400);
    }
    
    /**
     パネルを描画します。<br>
     現在の対戦相手の手の画像を表示します。このメソッドはJVM（イベントディスパッチャースレッド）から呼ばれるものなので、
     直接呼び出すことは禁じられています。
     @param g 描画に使用するグラフィクスインスタンス
     */
    @Override
    public void paint(Graphics g){
        g.drawImage(imgs[img_no],0,0,this);
    }
    
    /**
     対戦相手の手の高速回転を開始します。
     */
    public void startRoll(){
        roll = true;
        new Thread(this).start();
    }
    
    /**
     対戦相手の手の高速回転を停止します。
     */
    public void stopRoll(){
        roll = false;
    }
    
    /**
     対戦相手の手の高速回転を実行します。<br>
     このメソッドは別スレッドで実行するためのものです。Threadクラスのインスタンスのstart()メソッドから呼ばれるものなので、
     直接呼び出すことは禁じられています。
     */
    @Override
    public void run(){
        while(roll){
            img_no = (img_no + 1) % 3;
            repaint();
            try{
                Thread.sleep(33);
            }catch(InterruptedException e){
            }
        }
    }
    
    /**
     対戦相手の手を設定・表示します。<br>
     現在の対戦相手の手の種類を、引数で指定したものに変更します。その上でパネルを再描画します。
     @param hand 設定する対戦相手の手
     */
    public void showHand(JankenHand hand){
        switch(hand){
        case GUU:
            img_no = 0;
            break;
        case CHOKI:
            img_no = 1;
            break;
        case PAA:
            img_no = 2;
            break;
        }
        repaint();
    }
}
