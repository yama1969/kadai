import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;

/*******************************************************************************
 * 機雷ボタン                                                                  *
 *******************************************************************************/
class SweepButton extends JButton{
    //ボタン全体情報
    private SweepButtonInfo info;
    
    //ボタン毎の情報
    private int x;                                                              //自分の位置 X
    private int y;                                                              //自分の位置 Y
    private Painter painter;                                                    //描画処理オブジェクト

    private Status stat;                                                        //ボタン状態
    static enum Status{
        CLOSE,                                                                  //機雷なし・未チェック
        OPEN,                                                                   //機雷なし
        BADCHECK,                                                               //機雷なし・チェック済み
        MYMINE,                                                                 //自機雷あり・未チェック
        MYCHECK,                                                                //自機雷あり・チェック済み
        MINE,                                                                   //機雷あり・未チェック
        CHECKED,                                                                //機雷あり・チェック済み
        EXPLODE                                                                 //機雷爆発
    };
    
    /***************************************************************************
     * ボタン押下アクションリスナ                                              *
     ***************************************************************************/
    private ActionListener pressListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(info == null){
                return;
            }
            switch(info.getMode()){
                case INIT:                                                      //初期状態(何もしない)
                    break;
                case POSTING:                                                   //機雷配置中
                    if(checkStatus(Status.CLOSE)){                              //  未配置なら配置に
                        setStatus(Status.MYMINE);
                        if(info != null){
                            info.addMineNum();
                        }
                    }else{                                                      //  配置なら未配置に
                        setStatus(Status.CLOSE);
                        if(info != null){
                            info.subMineNum();
                        }
                    }
                    break;
                case WAIT:                                                      //相手機雷配置待ち
                    //何もしない。
                    break;
                case GAME_ME:                                                   //ゲーム中 自分の番
                    boolean check = info.setClickInfo(x, y);
                    push(check);
                    break;
                case GAME_MATE:                                                 //ゲーム中 相手の番
                    //何もしない。
                    break;
                default:
                    throw new RuntimeException("機雷ボタン押下時の異常処理が発生。");
            }
        }
    };
    
    /***************************************************************************
     * コンストラクタ
     */
    SweepButton(int x, int y, SweepButtonInfo sbi){
        this.x = x;
        this.y = y;
        this.info = sbi;
        setStatus(Status.CLOSE);
        addActionListener(pressListener);
    }
    
    /***************************************************************************
     * ボタン状態を設定する
     */
    void setStatus(Status newStat){
        stat = newStat;
        switch(stat){
            case CLOSE:                                                         //機雷なし・未チェック
                painter = new ClosePainter();
                setEnabled(true);
                break;
            case OPEN:                                                          //機雷なし
                painter = new OpenPainter();
                setEnabled(false);
                break;
            case BADCHECK:                                                      //機雷なし・チェック済み
                painter = new BadcheckPainter();
                setEnabled(true);
                break;
            case MYMINE:                                                        //自機雷あり・未チェック
                painter = new MyminePainter();
                setEnabled(true);
                break;
            case MYCHECK:                                                       //自機雷あり・チェック済み
                painter = new MycheckPainter();
                setEnabled(true);
                break;
            case MINE:                                                          //機雷あり・未チェック
                painter = new ClosePainter();
                setEnabled(true);
                break;
            case CHECKED:                                                       //機雷あり・チェック済み
                painter = new CheckedPainter();
                setEnabled(true);
                break;
            case EXPLODE:                                                       //機雷爆発
                painter = new ExplodePainter();
                setEnabled(false);
                break;
            default:
                throw new RuntimeException("機雷ボタン状態変更時の異常処理が発生。");
        }
        repaint();
    }
    
    /***************************************************************************
     * ボタン状態を取得する
     */
    Status getStatus(){
        return stat;
    }
    
    /***************************************************************************
     * ボタン状態を比較する
     */
    boolean checkStatus(Status st){
        return stat.equals(st);
    }
    
    /***************************************************************************
     * ボタンを初期状態に戻す
     */
    void reset(){
        setStatus(Status.CLOSE);
        setEnabled(true);
    }
    
    /***************************************************************************
     * ボタンを操作する
     */
    void push(boolean check){
        switch(stat){
            case CLOSE:
                if(check){
                    setStatus(Status.BADCHECK);
                }else{
                    setStatus(Status.OPEN);
                }
                break;
            case OPEN:
                //何もしない。
                break;
            case BADCHECK:
                //BADCHECKが発生した時点で勝負がついているので、
                //ここには来ない。
                //if(check){
                //    setStatus(Status.CLOSE);
                //}else{
                //    setStatus(Status.OPEN);
                //}
                break;
            case MYMINE:
                if(check){
                    setStatus(Status.MYCHECK);
                }else{
                    setStatus(Status.EXPLODE);
                }
                break;
            case MYCHECK:
                if(check){
                    //自機雷のチェックは戻せないようにする。
                    //でないと、相手による自機雷チェックが外せてしまう。
                    //setStatus(Status.MYMINE);
                }else{
                    setStatus(Status.EXPLODE);
                }
                break;
            case MINE:
                if(check){
                    setStatus(Status.CHECKED);
                }else{
                    setStatus(Status.EXPLODE);
                }
                break;
            case CHECKED:
                if(check){
                    setStatus(Status.MINE);
                }else{
                    setStatus(Status.EXPLODE);
                }
                break;
            case EXPLODE:
                //何もしない。
                break;
            default:
                throw new RuntimeException("機雷ボタン操作時の異常処理が発生。");
        }
    }
    
    /***************************************************************************
     * 描画する
     */
    @Override
    public void paint(Graphics g){
        painter.paint(g);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    //--------------------------------------------------------------------------
    
    /***************************************************************************
     * 描画クラス(抽象)                                                        *
     ***************************************************************************/
    private abstract class Painter{
        abstract void paint(Graphics g);
    }
    
    /***************************************************************************
     * CLOSE状態描画                                                           *
     ***************************************************************************/
    private class ClosePainter extends Painter{
        @Override
        void paint(Graphics g){
            SweepButton.super.paint(g);
        }
    }
    
    /***************************************************************************
     * OPEN状態描画                                                            *
     ***************************************************************************/
    private class OpenPainter extends Painter{
        @Override
        void paint(Graphics g){
            int w = SweepButton.this.getWidth();
            int h = SweepButton.this.getHeight();
            g.clearRect(0, 0, w, h);
            
            int count = info.getAroundMineNum(x, y);                            //表示数値文字列を得る
            String str = String.valueOf(count);
            FontMetrics fm = g.getFontMetrics();
            h = h / 2 + fm.getAscent() / 2;                                     //表示数値文字列の表示y座標
            w = w / 2 - fm.stringWidth(str) / 2;                                //表示数値文字列の表示x座標
            g.drawString(String.valueOf(count), w, h);
        }
    }
    
    /***************************************************************************
     * BADCHECK状態描画                                                            *
     ***************************************************************************/
    private class BadcheckPainter extends Painter{
        @Override
        void paint(Graphics g){
            int w = SweepButton.this.getWidth();
            int h = SweepButton.this.getHeight();
            g.setColor(Color.RED);
            g.drawLine(0, 0, w, h);
            g.drawLine(0, h, w, 0);
        }
    }
    
    /***************************************************************************
     * MYMINE状態描画                                                          *
     ***************************************************************************/
    private class MyminePainter extends Painter{
        @Override
        void paint(Graphics g){
            int w = SweepButton.this.getWidth();
            int h = SweepButton.this.getHeight();
            g.clearRect(0, 0, w, h);
            drawMine(g);
        }
    }
    
    /***************************************************************************
     * MYCHECK状態描画                                                         *
     ***************************************************************************/
    private class MycheckPainter extends Painter{
        @Override
        void paint(Graphics g){
            int w = SweepButton.this.getWidth();
            int h = SweepButton.this.getHeight();
            g.clearRect(0, 0, w, h);
            drawMine(g);
            g.setColor(Color.RED);
            g.drawLine(0, 0, w, h);
            g.drawLine(0, h, w, 0);
        }
    }
    
    /***************************************************************************
     * CHECKED状態描画                                                         *
     ***************************************************************************/
    private class CheckedPainter extends Painter{
        @Override
        void paint(Graphics g){
            int w = SweepButton.this.getWidth();
            int h = SweepButton.this.getHeight();
            g.setColor(Color.RED);
            g.drawLine(0, 0, w, h);
            g.drawLine(0, h, w, 0);
        }
    }
    
    /***************************************************************************
     * EXPLODE状態描画                                                         *
     ***************************************************************************/
    private class ExplodePainter extends Painter{
        @Override
        void paint(Graphics g){
            int w = SweepButton.this.getWidth();
            int h = SweepButton.this.getHeight();
            g.setColor(Color.RED);
            g.fillRect(0, 0, w, h);
            drawMine(g);
        }
    }
    
    /***************************************************************************
     * 機雷描画
     */
    private void drawMine(Graphics g){
/*
        Image img = Toolkit.getDefaultToolkit().getImage("mine.gif");
        g.drawImage(img, 0, 0, SweepButton.this);
*/
        int w = SweepButton.this.getWidth();
        int h = SweepButton.this.getHeight();
        int dif = w / 12;
        g.setColor(Color.BLACK);
        g.fillOval(dif, dif, w - dif * 2, h - dif * 2);
        g.drawLine(w / 2, 0, w / 2, h);
        g.drawLine(0, h / 2, w, h / 2);
        g.setColor(Color.WHITE);
        g.fillOval(dif * 2, dif * 2, w / 4, h / 4);
    }
}
