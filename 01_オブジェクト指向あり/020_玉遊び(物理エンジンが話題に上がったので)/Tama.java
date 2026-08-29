import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************************
 * 玉を表すクラス
 ******************************************************************************/
public class Tama{
    private double locatex;                                //x座標
    private double locatey;                                //y座標
    private double speedx;                                 //速度x成分
    private double speedy;                                 //速度y成分
    private double r;                                      //半径
    private double accx;                                   //加速度x成分
    private double accy;                                   //加速度y成分
    
    private boolean stilly;                                //y方向停止フラグ
    private double nextsx;                                 //次速度x成分
    private double nextsy;                                 //次速度y成分
    
    private double movex;                                  //他玉との重なり部分を強制移動する移動量x成分
    private double movey;                                  //他玉との重なり部分を強制移動する移動量y成分
    
    private Color color;                                   //色
    private static final int COLOR_WIDTH = 100;                 //色範囲
    private static final int COLOR_TOTAL = COLOR_WIDTH / 2 * 3; //3原色合計値
    private static final int COLOR_CENTER = 78;                 //中央明度
    
    private static double repul_floor = 0.8;               //床との反発係数
    private static double repul = 0.9;                     //玉同士の反発係数
    private static double g = 0.1;                         //重力加速度
    
    /***************************************************************************
     * コンストラクタ。玉の位置と大きさは指定のもの。色はランダムに決定。
     * @param x 挿入される位置のx座標
     * @param y 挿入される位置のy座標
     * @param r 半径
     **************************************************************************/
    public Tama(int x, int y, int r){
        if(r < 0){
            r = 10;
        }
        this.locatex = (double)x;
        this.locatey = (double)y;
        this.speedx = 0.0;
        this.speedy = 0.0;
        this.r = (double)r;
        this.stilly = false;
        this.nextsx = 0.0;
        this.nextsy = 0.0;
        this.movex = 0.0;
        this.movey = 0.0;
        
        int red = (int)(Math.random() * COLOR_WIDTH);
        int min = Math.max((COLOR_TOTAL - COLOR_WIDTH) - red, 0);
        int green = (int)(Math.random() * (COLOR_WIDTH - min)) + min;
        int blue = COLOR_TOTAL - red - green;
        color = new Color(red + COLOR_CENTER, green + COLOR_CENTER, blue + COLOR_CENTER);
    }
    
    /*
     * 床衝突→壁衝突→玉同士衝突→移動の順番で処理をする。
     * 各衝突処理で次の速度を決定し、最後にその速度に従って移動する。
     */
    
    /***************************************************************************
     * 玉の移動。衝突処理によって決定された速度によって移動する。
     **************************************************************************/
    public void move(){
        speedx = nextsx;                     //衝突による速度計算結果を今回の速度に更新
        speedy = nextsy;
        accy += g;                           //重力加速度
        
        locatex += speedx + accx / 2.0;      //加速度分も含めたx方向移動
        nextsx += accx;
        
        if(stilly){                          //床に着いているならy方向停止
            speedy = 0.0;
            nextsy = 0.0;
        }else{
            locatey += speedy + accy / 2.0;  //床に着いていないならy方向移動
            nextsy += accy;
        }
        
        locatex += movex;                    //重なりによる強制移動
        locatey += movey;
        movex = 0.0;                         //強制移動をリセット→毎回判定と計算する
        movey = 0.0;
        
        accx = 0.0;                          //加速度リセット→毎回計算する
        accy = 0.0;
    }
    
    /***************************************************************************
     * 床との衝突処理。物が消える場合はfalseを返す。
     * @param  height 移動エリア高さ
     * @return 玉が存在する場合true, 玉が消える場合false
     **************************************************************************/
    public boolean collideFloor(int height){
        double h = (double)height;
        //床に達したかの判定
        if(locatey + r > h){
            locatey = h - r;
            if(speedy > 0.0){
                //床に達していて下方向へ移動中
                accy += -g;       //床による重力の反作用
                if(speedy < g){
                    //重力加速度程度の速度なら停止→でないといつまでもブルブル震えてしまう
                    nextsy = 0.0;
                    stilly = true;
                }else{
                    //十分に速度が大きいなら反発
                    nextsy += -speedy * (1.0 + repul_floor);
                    stilly = false;
                }
            }
        }else{
            //床から離れているなら停止解除
            stilly = false;
        }
        return true;
    }
    
    /***************************************************************************
     * 壁との衝突処理。物が消える場合はfalseを返す。
     * @param  width 移動エリア幅
     * @param  left  左壁ありtrue, 左壁なしfalse
     * @param  right 右壁ありtrue, 右壁なしfalse
     * @return 玉が存在する場合true, 玉が消える場合false
     **************************************************************************/
    public boolean collideWall(int width, boolean left, boolean right){
        double w = (double)width;
        if(left){
            if(locatex - r < 0.0){
                //左壁による反射
                nextsx += - speedx * (1.0 + repul_floor);
                locatex = r;
            }
        }else{
            if(locatex + r < 0.0){
                //左壁なしでエリア外に出たら消える
                return false;
            }
        }
        if(right){
             if(locatex + r > w){
                 //右壁による反射
                 nextsx += - speedx * (1.0 + repul_floor);
                 locatex = w - r;
             }
        }else{
            if(locatex - r > w){
                //右壁なしでエリア外に出たら消える
                return false;
            }
        }
        return true;
    }
    
    /***************************************************************************
     * 玉同士の衝突
     * @param t 衝突相手の玉
     **************************************************************************/
    public void collide(Tama t){
        //自分自身とは衝突しない
        if(t == this){
            return;
        }
        
        //相対位置ベクトル(相手が原点)
        double dx = locatex - t.getX();
        double dy = locatey - t.getY();
        double d = Math.sqrt(dx * dx + dy * dy);
        
        //離れていれば衝突なし
        double inbound = d - (r + t.getR());
        if(inbound > 0.0){
            return;
        }
        
        //相対速度ベクトル(相手が原点＝停止)
        double sx = speedx - t.getSx();
        double sy = speedy - t.getSy();
        
        //相対速度の衝突成分(相対位置ベクトルと相対速度ベクトルの内積÷相対位置ベクトルの大きさ)
        //これが正ならば離れる方向(衝突処理なし)
        //ただし、ここに処理が来たということは、内部に食い込んでいる状態なので強制移動
        double s = (sx * dx + sy * dy) / d;
        if(s > 0.0){
            //重なった部分は強制移動(相手と互いに移動するので半分ずつ)
            movex += - inbound * dx / d / 2.0;
            if(!stilly || dy < 0.0){
                //自分がy方向固定でないか、自分が上ならばy方向移動
                movey += - inbound * dy / d;
            }
            return;
        }
        
        //相対速度は近付く方向なら必ず負だが、x成分y成分に分けるときに符号が付く
        //どの玉も密度は同じ(質量比は半径二乗の比)として、反発係数から反発後の相対速度を計算
        double tr = t.getR();
        nextsx += s * dx / d * ((r * r - tr * tr * repul) / (r * r + tr * tr) - 1);

/*
//数値確認のためのデバッグコード
System.out.println("自分半径=" + r);
System.out.println("相手半径=" + tr);
System.out.println("自速度x成分=" + speedx);
System.out.println("自速度y成分=" + speedy);
System.out.println("相対速度x成分=" + sx);
System.out.println("相対速度y成分=" + sy);
System.out.println("衝突速度=" + s);
System.out.println("x成分比率=" + dx/d);
System.out.println("衝突速度x成分=" + (s * dx / d));
System.out.println("反発速度係数=" + ((r * r - tr * tr * repul) / (r * r + tr * tr)));
System.out.println("反発速度x成分=" + (s * dx / d * (r * r - tr * tr * repul) / (r * r + tr * tr)));
System.out.println("速度増分x成分=" + (s * dx / d * ((r * r - tr * tr * repul) / (r * r + tr * tr) - 1)));
System.out.println("------------------------------------------------------------------------");
*/

        if(!t.getStilly()){
            //相手が床に付いていないときは、y速度を配分
            nextsy += s * dy / d * ((r * r - tr * tr * repul) / (r * r + tr * tr) - 1);
        }else{
            //相手が床についているときは、y速度は全部自分
            nextsy += -(1 + repul) * s * dy / d;
            //相手が床についているときは、重力の反作用としての加速度もあり。
            //必要だと思うが、入れても抜いても挙動はあまり変わらない。
            if(dy < 0.0){
                double a = -g * dy / d;
                accx += a * dx / d;
                accy += -a * dy / d;
            }
        }
    }
    
    /***************************************************************************
     * 物(自分)を描く
     **************************************************************************/
    public void draw(Graphics g){
        int x = (int)this.locatex;
        int y = (int)this.locatey;
        int r = (int)this.r;
        g.setColor(color);
        g.fillOval(x - r, y - r, r * 2, r * 2);
    }
    
    /***************************************************************************
     * 玉のx座標を得る
     * @return 玉のx座標
     **************************************************************************/
    public double getX(){
        return locatex;
    }
    
    /***************************************************************************
     * 玉のy座標を得る
     * @return 玉のy座標
     **************************************************************************/
    public double getY(){
        return locatey;
    }
    
    /***************************************************************************
     * 玉の速度のx成分を得る
     * @return 玉の速度x成分
     **************************************************************************/
    public double getSx(){
        return speedx;
    }
    
    /***************************************************************************
     * 玉の速度のy成分を得る
     * @return 玉の速度y成分
     **************************************************************************/
    public double getSy(){
        return speedy;
    }
    
    /***************************************************************************
     * 玉の半径を得る
     * @return 玉の半径
     **************************************************************************/
    public double getR(){
        return r;
    }
    
    /***************************************************************************
     * 玉のy方向の停止状態を得る
     * @return 玉のy方向の停止状態
     **************************************************************************/
    public boolean getStilly(){
        return stilly;
    }
    
    /***************************************************************************
     * 玉オブジェクトの文字列表現
     **************************************************************************/
    @Override
    public String toString(){
        return "Tama(" + locatex + ", " + locatey + ", " + r + ")";
    }
}
