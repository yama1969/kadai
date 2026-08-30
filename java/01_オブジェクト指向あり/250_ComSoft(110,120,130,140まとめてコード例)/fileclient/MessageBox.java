package fileclient;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageBox extends JDialog {
	
	private JTextArea	tarMessage;
	private JButton		btnOk;
	private int			ownerX;
	private int			ownerY;
	
	public MessageBox(Frame owner){
		super(owner, "Information!", false);
		ownerX = owner.getX();
		ownerY = owner.getY();
		
		btnOk = new JButton("OK");
		tarMessage = new JTextArea();
		tarMessage.setEditable(false);
		tarMessage.setFont(btnOk.getFont());
		tarMessage.setBackground(btnOk.getBackground());
		
		add(tarMessage, BorderLayout.CENTER);
		add(btnOk, BorderLayout.SOUTH);
		
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				setVisible(false);
			}
		});
	}
	
	public void disp(String message){
		int x = ownerX + (int)(Math.random() * 100.0);
		int y = ownerY + (int)(Math.random() * 100.0);
		tarMessage.setText("\n" + message);
		setBounds(x, y, 300, 200);
		setVisible(true);
	}
}
