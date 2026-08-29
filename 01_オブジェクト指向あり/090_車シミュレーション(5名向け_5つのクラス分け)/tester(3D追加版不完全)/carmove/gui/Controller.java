package carmove.gui;

import carmove.testmove.TestMoveControll;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class Controller extends JFrame implements WindowListener{
    private final int WIDTH = 100;
    private final int HEIGHT = 100;
    private ControllerPanel cp;
    
    public Controller(TestMoveControll tmc, int x){
        super("Controller");
        addWindowListener(this);
        
        cp = new ControllerPanel(tmc);
        
        setLayout(new GridLayout());
        add(cp);
        setBounds(x, 0, WIDTH, HEIGHT);
        setVisible(true);
    }
    
    public void windowClosed(WindowEvent e){
        windowClosing(e);
    }
    
    public void windowClosing(WindowEvent e){
        cp.end();
    }
    
    public void windowActivated(WindowEvent e){}
    
    public void windowDeactivated(WindowEvent e){}
    
    public void windowDeiconified(WindowEvent e){}
    
    public void windowIconified(WindowEvent e){}
    
    public void windowOpened(WindowEvent e){}
}

class ControllerPanel extends JPanel implements MouseMotionListener{
    private TestMoveControll tmc;
    
    public ControllerPanel(TestMoveControll tmc){
        this.tmc = tmc;
        addMouseMotionListener(this);
    }
    
    public void end(){
        tmc.end();
    }
    
    public void paint(Graphics g){
        int w = getWidth();
        int h = getHeight();
        g.drawLine(w / 2, 0, w / 2, h);
        g.drawLine(0, h / 2, w, h / 2);
    }
    
    public void mouseDragged(MouseEvent e){
        mouseMoved(e);
    }
    
    public void mouseMoved(MouseEvent e){
        int w = getWidth();
        int h = getHeight();
        tmc.move((h / 2 - e.getY()) / 2, e.getX() - w / 2);
    }
}
