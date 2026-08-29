import java.awt.Graphics;  //グラフィックス描画機能クラス
import java.awt.Color;     //色クラス

/*******************************************************************************
 * 玉打遊戯の玉。
 *
 * @author 山田　洋 2008/5/1
 ******************************************************************************/
public class Tama{
    private final double DPS = 150.0; //玉の初期速さ。1秒あたりの移動ドット数(厳密でない)
    private TamaUchiGamePanel panel;  //玉打遊戯パネルオブジェクト参照
    private double x;                 //玉の位置 x座標
    private double y;                 //玉の位置 y座標
    private int r;                    //玉の半径
    private double v;                 //玉の速さ。1描画あたりの移動ドット数[dpf]
    private double vx;                //玉の速度 x成分
    private double vy;                //玉の速度 y成分
    private Color color;              //玉の色
    private boolean bound;            //バーによる反射状態の有無(true:反射済み　false:未反射)
    
    /***************************************************************************
     * 玉の座標および速度のX成分とY成分を決定する。コンストラクタ。
     * なお、玉打遊戯パネルの参照を引数に取っているにも関わらず、その幅と高さをも
     * 引数に入れている理由は、玉打遊戯パネルのコンストラクタ内で玉オブジェクトの
     * 生成をする可能性があるからである。
     *（パネルのコンストラクタ中では、画面における幅と高さが確定していない。）
     *
     * @param panel  玉打遊戯パネル参照
     * @param width  玉打遊戯パネルの幅
     * @param height 玉打遊戯パネルの高さ
     * @param fps    1秒あたりの描画フレーム数
     **************************************************************************/
    public Tama(TamaUchiGamePanel panel, int width, int height, int fps){
        this.panel = panel;
        r = 10;                                               //半径10ドット
        x = Math.random() * width;                            //パネル内で位置をランダムに決定
        y = Math.random() * height;
        v = DPS / (double)fps;                                //fpsとdpsからdpfを計算
        double rad = Math.PI * (1.25 + Math.random() * 0.5);  //玉の移動方向をランダムに決定。上方で左右45°以内にする
        vx = v * Math.cos(rad);                               //移動方向から速度のX,Y成分を計算
        vy = v * Math.sin(rad);
        color = Color.RED;                                    //玉の色は赤
        
        bound = false;                                        //初期状態は、バーによる反射なし
    }
    
    /***************************************************************************
     * 玉を描画する。
     *
     * @param g 描画するグラフィックオブジェクト
     **************************************************************************/
    public void draw(Graphics g){
        g.setColor(color);                                    //描画色の指定
        g.fillOval((int)(x - r), (int)(y - r), 2 * r, 2 * r); //円の描画
    }
    
    /***************************************************************************
     * 玉の移動処理と、壁およびバーとの衝突処理を行う。
     *
     * @param bar バーオブジェクト参照
     **************************************************************************/
    public void move(Bar bar){
        x += vx;                                              //玉の移動
        y += vy;
        
        int barX = bar.getX();                                //バーの位置情報を取得
        int barY = bar.getY();
        int barW2 = bar.getWidth() / 2;
                                                              //玉の内部にバーが入ったかの判定
        if(x >= barX - barW2 && x <= barX + barW2 && y + r >= barY && y - r <= barY){
            if(!bound){                                       //未反射なら反射処理。この判定により、同じ所で何度も反射してしまうのを防ぐ。
/*
                double s = -1.0;                              //バーによる反射時のy座標処理を入れたが、却って動きが不自然なので削除
                if(vy < 0){
                    s = 1.0;
                }
                y = 2 * (barY + s * r) - y;
*/
                vy = -vy;                                     //速度y成分の反転
                bound = true;                                 //反射済みをセット
            }
        }else{
            bound = false;                                    //玉からバーが離れたなら、反射状態をリセット
        }
        
        int w = panel.getWidth();
        if(x < 0){                                            //左壁の反射処理
            x = -x;
            vx = -vx;
        }else if(x > w){                                      //右壁の反射処理
            x = w * 2 - x;
            vx = -vx;
        }
        
        int h = panel.getHeight();
        if(y < 0){                                            //上面の反射処理
            y = -y;
            vy = -vy;
        }else if(y > h){                                      //下面の反射処理
            y = h * 2 - y;
            vy = -vy;
        }
    }
}
