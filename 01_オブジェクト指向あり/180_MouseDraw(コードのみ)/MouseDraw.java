import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
<applet code="MouseDraw" width=300 height=200>
</applet>
*/

public class MouseDraw extends Applet{
    MouseDrawJPanel mousePanel;
    
    public void init(){
        mousePanel = new MouseDrawJPanel();
        setLayout(new BorderLayout());
        add(mousePanel, BorderLayout.CENTER);
    }
    
    public void paint(Graphics g){
        mousePanel.repaint();
    }
}

class MouseDrawJPanel extends JPanel{
    private int sx;
    private int sy;
    private int ex;
    private int ey;
    
    public MouseDrawJPanel(){
        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                ex = e.getX();
                ey = e.getY();
                
                repaint();
            }
        });
        
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                sx = e.getX();
                sy = e.getY();
            }
        });
    }
    
    public void paint(Graphics g){
        g.drawLine(sx, sy, ex, ey);
        sx = ex;
        sy = ey;
    }
}
