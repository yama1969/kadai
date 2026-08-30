/*
  マウスで絵を描くアプリ
  
  「プロになるJava」のGUIプログラムがあまりにも中途半端なので、
  せめてこれくらいはやった方が受講者も盛り上がるのではないの？というサンプル。
  出来る限りシンプルに作ることを目指した。
  
  ウィンドウを最小化しても絵が残るようにImageとpaintイベントを使用したけど、複雑過ぎるか。
  p179のサンプルではImageを使っている。
  
  教科書でGUIサンプルが最後に登場するのはp179 (制御構文とデータ構造の後、メソッドの前)
  Swingについて少しだけ触れているのがp339 (Javaのひと通りが終わった後、継承などの後)
*/

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Draw extends JFrame implements MouseMotionListener{
    private BufferedImage image;
    private Graphics graph;
    private int sx;
    private int sy;
    
    public Draw(){
        super("マウスでお絵描き");
        
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        graph = image.getGraphics();
        graph.setColor(Color.WHITE);
        graph.fillRect(0, 0, 800, 600);
        
        addMouseMotionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        int ex = e.getX();
        int ey = e.getY();
        graph.setColor(Color.BLACK);
        graph.drawLine(sx, sy, ex, ey);
        sx = ex;
        sy = ey;
        repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        sx = e.getX();
        sy = e.getY();
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(image, 0, 0, this);
    }
    
    public static void main(String[] args){
        new Draw();
    }
}
