/******************************************************************************
 * ‚¶‚á‚ñ‚¯‚ñƒQ[ƒ€‚Ìis–ğ
 ******************************************************************************/
public class Master{
  Hand hand1 = new Hand();
  Hand hand2 = new Hand();
  
  boolean state = false;
  
  JankenApplet applet = null;
  
  public void setUserIF(JankenApplet app){
      applet = app;
  }
  
  public void setHand(int kind1, int kind2){
    if(state == false) {
      applet.start();
      return;
    }
    kind1 = hand1.setHand(kind1);
    kind2 = hand2.setHand(kind2);
    applet.setIndex(kind1, kind2);
  }
}
