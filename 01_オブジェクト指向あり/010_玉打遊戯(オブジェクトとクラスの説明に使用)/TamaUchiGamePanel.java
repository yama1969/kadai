import javax.swing.JPanel;                  //GUI部品のパネルを表すクラス
import java.awt.Image;                      //グラフィックを描き込むスクリーンのクラス
import java.awt.Graphics;                   //グラフィック描画機能クラス
import java.awt.event.MouseMotionListener;  //マウス操作イベントを受け取るメソッドを装備するインターフェース
import java.awt.event.MouseEvent;           //マウス操作イベントを表すクラス

/*******************************************************************************
 * 玉打遊戯パネル。玉打遊戯に関する全てのオブジェクトを配置する。
 *
 * @author 山田　洋 2008/5/1
 ******************************************************************************/
public class TamaUchiGamePanel extends JPanel implements MouseMotionListener{
    private final int FPS = 50;  //１秒あたりの描画フレーム数(厳密でない)
    private int num_tama = 3;    //玉オブジェクトの数
    private Tama[] tamas;        //玉オブジェクトの配列
    private Bar    bar;          //バーオブジェクト
    
    /***************************************************************************
     * 遊戯パネルおよび必要な全てのオブジェクトを生成し、時間カウントを開始する。
     * コンストラクタ。
     *
     *  @param width  パネル幅(ドット数)
     *  @param height パネル高さ(ドット数)
     **************************************************************************/
    public TamaUchiGamePanel(int width, int height){
        tamas = new Tama[num_tama];                          //玉オブジェクト配列の確保
        for(int i = 0; i < tamas.length; i++){
            tamas[i] = new Tama(this, width, height, FPS);   //玉オブジェクトの生成
        }
        bar = new Bar();                                     //バーオブジェクトの生成
        
        addMouseMotionListener(this);                        //このパネルに発生したマウスイベントを受け取るオブジェクトは、自分自身であることを設定
        
        Timer t = new Timer(this, FPS);                      //タイマーオブジェクトの生成
        t.start();                                           //時間カウントの開始(別スレッド)
    }
    
    /***************************************************************************
     * パネル描画処理のオーバーライド。
     *
     * @param g パネルのグラフィックオブジェクト
     **************************************************************************/
    public void paint(Graphics g){
        Image offscr = createImage(getWidth(), getHeight()); //オフスクリーンオブジェクト生成
        Graphics offgr = offscr.getGraphics();               //オフスクリーンのグラフィックオブジェクト取得
        
        for(int i = 0; i < tamas.length; i++){
            tamas[i].draw(offgr);                            //玉の描画
        }
        bar.draw(offgr);                                     //バーの描画
        g.drawImage(offscr, 0, 0, this);                     //オフスクリーンをパネルに描き出す
    }
    
    /***************************************************************************
     * 玉などの遊戯要素移動。タイマーオブジェクトが時間をひとつカウントする度に
     * このメソッドが実行される。
     **************************************************************************/
    public void move(){
        for(int i = 0; i < tamas.length; i++){
            tamas[i].move(bar);                              //玉の移動
        }
    }
    
    /***************************************************************************
     * バーの移動。マウスがドラッグされるとこのメソッドが実行される。
     * マウスドラッグイベントリスナの実装。
     *
     * @param e マウスイベントオブジェクト
     **************************************************************************/
    public void mouseDragged(MouseEvent e){
        mouseMoved(e);                                       //マウス移動イベントと同じ処理
    }
    
    /***************************************************************************
     * バーの移動。マウス移動イベントリスナの実装。
     *
     * @param e マウスイベントオブジェクト
     **************************************************************************/
    public void mouseMoved(MouseEvent e){
        bar.move(e.getX(), e.getY());                        //バーの移動
    }
}
