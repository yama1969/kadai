/******************************************************************************
 * 「動きをランダムに指示 を変更」をGUI画面で見える化
 *****************************************************************************/
import javax.swing.*;
import java.awt.*;


/******************************************************************************
 * 起動と動作を行うクラス
 *****************************************************************************/
class Kadai1550gui{
    public static void main(String[] args){
        //GUI画面の生成と表示
        Kadai1550guiFrame fr = new Kadai1550guiFrame();
        
        //動き指示と再表示をひたすら繰り返す
        while(true){
            fr.move();                         //動き指示
            fr.repaint();                      //再表示要求
            try{
                Thread.sleep(10);              //0.01秒休み
            }catch(InterruptedException e){
            }
        }
    }
}

/******************************************************************************
 * GUI画面クラス
 *****************************************************************************/
class Kadai1550guiFrame extends JFrame{
    private int x = 320;   //動点の初期x座標
    private int y = 240;   //動点の初期y座標
    private int dirc = 0;  //動点の進行方向(0:上, 1:右, 2:下, 3:左)
    private int xs = x;    //動点の直前x座標(動点の軌跡描画に使用)
    private int ys = y;    //動点の直前y座標
    
    Image image = null;    //描画用イメージオブジェクト
    Graphics graph = null; //描画用グラフィクスオブジェクト
    
    /**************************************************************************
     * コンストラクタ。
     * 閉じるボタン動作と窓サイズの設定を行い、窓表示する。
     *************************************************************************/
    public Kadai1550guiFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640,480);
        setVisible(true);
    }
    
    /**************************************************************************
     * 描画処理
     *************************************************************************/
    @Override
    public void paint(Graphics g){
        //描画用イメージオブジェクトが無ければ、生成
        if(image == null){
            image = createImage(getWidth(), getHeight());
            graph = image.getGraphics();
        }
        
        //動点の直前座標に黒点を打ち、軌跡を残す
        graph.setColor(Color.BLACK);
        graph.drawRect(xs,ys,1,1);
        //動点の現在座標に赤点を打つ
        graph.setColor(Color.RED);
        graph.drawRect(x,y,1,1);
        //次の移動に備え、現在座標を直前座標にする
        xs = x;
        ys = y;
        
        //描画用イメージを画面に描き出す
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
    /**************************************************************************
     * 動点を移動する
     *************************************************************************/
    public void move(){
        int mode = (int)(Math.random() * 6.0) + 1;
        switch(mode){
        case 1:
        case 2:
            switch(dirc){          //前へ進む(向きによって方向が変わる)
            case 0:
                y -= 1;            //  上へ移動
                break;
            case 1:
                x += 1;            //  右へ移動
                break;
            case 2:
                y += 1;            //  下へ移動
                break;
            case 3:
                x -= 1;            //  左へ移動
            }
            break;
        case 3:
        case 4:
            dirc = (dirc + 1) % 4; //右を向く(時計回り)
            break;
        case 5:
        case 6:
            dirc = (dirc + 3) % 4; //左を向く(反時計回り)
        }
    }
}
