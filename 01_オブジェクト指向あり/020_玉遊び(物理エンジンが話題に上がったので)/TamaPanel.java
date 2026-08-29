import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*******************************************************************************
 * 物理シミュレーションの物を描くパネル
 ******************************************************************************/
public class TamaPanel extends JPanel implements ComponentListener, MouseListener, MouseMotionListener{
    private int width;                  //パネル幅
    private int height;                 //パネル高さ
    private ArrayList<Tama> tamas;      //玉リスト
    
    private boolean left;               //左壁 true:あり, false:なし
    private boolean right;              //右壁 true:あり, false:なし
    
    private int drag_sx;                //ドラッグ開始x座標
    private int drag_sy;                //ドラッグ開始y座標
    private int drag_r;                 //ドラッグ半径
    private boolean drag;               //ドラッグ中フラグ
    
    /***************************************************************************
     * コンストラクタ
     **************************************************************************/
    public TamaPanel(){
        tamas = new ArrayList<Tama>();
        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        drag = false;
    }
    
    /***************************************************************************
     * 物を動かす。衝突処理後、移動すると良いようだ。
     **************************************************************************/
    public void move(){
        //物と床との衝突処理
        for(int i = 0; i < tamas.size(); i++){
            if(!tamas.get(i).collideFloor(height)){
                tamas.remove(i);
            }
        }
        //物と壁との衝突処理
        for(int i = 0; i < tamas.size(); i++){
            if(!tamas.get(i).collideWall(width, left, right)){
                tamas.remove(i);
            }
        }
        
        //物同士の衝突処理
        for(int i = 0; i < tamas.size(); i++){
            for(int j = 0; j < tamas.size(); j++){
                tamas.get(i).collide(tamas.get(j));
            }
        }
        //物の移動
        for(int i = 0; i < tamas.size(); i++){
            tamas.get(i).move();
        }
        repaint();
    }
    
    /***************************************************************************
     * 画面を描く
     **************************************************************************/
    @Override
    public void paint(Graphics g){
        super.paint(g);
        //物を描く
        for(Tama t : tamas){
            t.draw(g);
        }
        //ドラッグ中であれば、玉の挿入円を描く
        if(drag){
            g.setColor(Color.BLACK);
            g.drawOval(drag_sx - drag_r, drag_sy - drag_r, drag_r * 2, drag_r * 2);
        }
    }
    
    /***************************************************************************
     * 左壁オン・オフ
     * @param left オン:true, オフ:false
     **************************************************************************/
    public void setLeftWall(boolean left){
        this.left = left;
    }
    
    /***************************************************************************
     * 右壁オン・オフ
     * @param left オン:true, オフ:false
     **************************************************************************/
    public void setRightWall(boolean right){
        this.right = right;
    }
    
    //以下はイベントリスナ
    
    /***************************************************************************
     * マウスボタンを押下。玉の挿入を開始する。
     **************************************************************************/
    public void mousePressed(MouseEvent e){
        drag_sx = e.getX();  //挿入位置を記憶
        drag_sy = e.getY();
        drag_r = 0;
        drag = true;
    }
    
    /***************************************************************************
     * マウスをドラッグ。挿入する玉の大きさを調整する。
     **************************************************************************/
    public void mouseDragged(MouseEvent e){
        //挿入する玉の半径を計算
        drag_r = (int)(Math.sqrt(Math.pow(e.getX() - drag_sx, 2.0) + Math.pow(e.getY() - drag_sy, 2.0)));
    }
    
    /***************************************************************************
     * マウスボタンを解放。玉の挿入を実行する。
     **************************************************************************/
    public void mouseReleased(MouseEvent e){
        double dx = e.getX() - drag_sx;
        double dy = e.getY() - drag_sy;
        double r = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0));
        tamas.add(new Tama(drag_sx, drag_sy, (int)r));
        drag = false;
    }
    
    /***************************************************************************
     * パネルサイズ変更。サイズを記憶する。
     **************************************************************************/
    public void componentResized(ComponentEvent e){
        componentShown(e);
    }
    
    /***************************************************************************
     * パネル表示。サイズを記憶する。
     **************************************************************************/
    public void componentShown(ComponentEvent e){
        width = getWidth();
        height = getHeight();
    }
    
    //以下のイベントリスナは未使用。特に何もしない。
    
    /**
     * マウスボタンをクリック。特に何もしない。
     */
    public void mouseClicked(MouseEvent e){
    }
    
    /**
     * マウスを入れる。特に何もしない。
     */
    public void mouseEntered(MouseEvent e){
    }
    
    /**
     * マウスを出す。特に何もしない。
     */
    public void mouseExited(MouseEvent e){
    }
    
    /**
     * マウスを移動。特に何もしない。
     */
    public void mouseMoved(MouseEvent e){
    }
    
    /**
     * パネル非表示。特に何もしない。
     */
    public void componentHidden(ComponentEvent e){}
    
    /**
     * パネル移動。特に何もしない。
     */
    public void componentMoved(ComponentEvent e){}
    
}
