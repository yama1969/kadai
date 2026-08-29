import javax.swing.JFrame;
import java.awt.BorderLayout;

/*******************************************************************************
 * 物理エンジンを作ってみよう！ということで作成してみたプログラム。<br>
 * とりあえず玉のみ実現。
 ******************************************************************************/
public class TamaFrame extends JFrame{
    private TamaPanel tpanel;      //実際に物を描くパネル
    private WallSelecter wallsel;  //壁選択ボタンパネル
    
    /***************************************************************************
     * コンストラクタ。画面を表示し、シミュレーションを開始する。
     **************************************************************************/
    public TamaFrame(String title){
        //ウィンドウの準備と表示
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //×ボタンで閉じる
        tpanel = new TamaPanel();                 //物を描くパネルを準備
        add(tpanel, BorderLayout.CENTER);
        wallsel = new WallSelecter(this);         //壁選択ボタン
        add(wallsel, BorderLayout.SOUTH);
        setSize(800, 600);                        //サイズ設定とWindow表示
        setVisible(true);
        
        //ウィンドウが表示を待つ(時間は適当)→待たずに描画開始すると描画されない
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            System.out.println("Interrupt.");
        }
        
        //シミュレーション開始。ひたすらパネルのmove()を実行
        while(true){
            tpanel.move();
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                System.out.println("Interrupt.");
            }
        }
    }
    
    /***************************************************************************
     * 左壁オン・オフ
     * @param left オン:true, オフ:false
     **************************************************************************/
    public void setLeftWall(boolean left){
        tpanel.setLeftWall(left);
    }
    
    /***************************************************************************
     * 右壁オン・オフ
     * @param left オン:true, オフ:false
     **************************************************************************/
    public void setRightWall(boolean right){
        tpanel.setRightWall(right);
    }
    
    /***************************************************************************
     * プログラム起動
     **************************************************************************/
    public static void main(String[] args){
        new TamaFrame("玉玉");
    }
}
