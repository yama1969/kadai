import janken.frame.JankenManager;
import janken.frame.JankenMotion;
import janken.frame.JankenUI;

import janken.motion.normal.NormalMotion;
import janken.ui.simpleGui.JankenFrame;

public class Normal{
    public static void main(String[] args){
        JankenMotion motion = new NormalMotion();
        JankenUI ui = new JankenFrame();
        JankenManager.startJanken(ui, motion);
    }
}
