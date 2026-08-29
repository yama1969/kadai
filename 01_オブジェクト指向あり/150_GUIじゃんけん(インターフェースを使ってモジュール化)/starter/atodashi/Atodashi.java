import janken.motion.atodashi.AtodashiMotion;
import janken.ui.simpleGui.JankenFrame;
import janken.frame.JankenManager;

public class Atodashi{
    public static void main(String[] args){
        AtodashiMotion motion = new AtodashiMotion();
        JankenFrame fr = new JankenFrame();
        JankenManager.startJanken(fr, motion);
    }
}
