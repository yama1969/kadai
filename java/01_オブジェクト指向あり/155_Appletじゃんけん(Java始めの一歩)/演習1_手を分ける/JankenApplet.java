import java.applet.Applet;
import java.awt.Image;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JankenApplet extends Applet
    implements Runnable, ActionListener {
  Image image[] = new Image[3];
  Thread t;
  int index1 = 0;
  int index2 = 0;
  String msg = "";
  Hand playerHand = new Hand();   //追加（プレイヤーの手）
  Hand compHand = new Hand();     //追加（コンピュータの手）

  boolean state = false;
  Button b1 = new Button("ぐー");
  Button b2 = new Button("ちょき");
  Button b3 = new Button("ぱー");

  public void init() {
    for(int i = 0; i <= 2; i++) {
      image[i] = getImage(getDocumentBase(),"image" + (i+1) + ".gif");
    }
    add(b1);
    add(b2);
    add(b3);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
  }

  public void paint(Graphics g) {
    g.drawImage(image[index1],10,30,this);
    g.drawImage(image[index2],500,30,this);
    g.drawString("わたし", 45,180);
    g.drawString("あなた", 535,180);
    g.drawString(msg,50,300);
  }
  

  public void start() {
    t = new Thread(this);
    state = true;
    msg = "　　　　　　　　　　　　";
    t.start();
  }

  public void run() {
    while(state) {
      index1++;
      if(index1 == 3) {
        index1 = 0;
      }
      
      index2++;
      if(index2 == 3) {
        index2 = 0;
      }
      repaint();
      try {
        Thread.sleep(70);
      } catch(InterruptedException e) { }

    }
  }

  public void actionPerformed(ActionEvent e) {
    if(state == false) {
      start();
      return;
    }
    state = false;
    if(e.getSource() == b1) {
      index2 = 0;
    }
    else if(e.getSource() == b2) {
      index2 = 1;
    }
    else if(e.getSource() == b3) {
      index2 = 2;
    }
    index1 = compHand.setKind(index1);   //追加（コンピュータの手を設定する）
    index2 = playerHand.setKind(index2); //追加（プレイヤーの手を設定する）
    check();
    repaint();
  }

  public void check() {
    int r = playerHand.check(compHand);  //差替え（勝敗判定する）
    switch(r) {
    case 0:
      msg="あいこ！";
    break;
    case 1:
      msg="あなたの勝ち！";
    break;
    case 2:
      msg="あなたの負け！";
    break;
    }
  }
} 