package carmove.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class PanelScreen extends JPanel{
	Image    offi;
	Graphics offg;
	
	public void paint(Graphics g){
		g.drawImage(offi, 0, 0, this);
	}
	
	public void setOffImage(Image img){
		offi = img;
		offg = offi.getGraphics();
	}
}
