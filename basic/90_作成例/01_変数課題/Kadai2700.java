/******************************************************************************
 * •Ï”‚ğŒø—¦—Ç‚­g‚¤B3‚Â‚Ì‰~’Œ‚ğŠª‚­‚Ì‚É•K—v‚È•R‚Ì’·‚³‚Í
 *****************************************************************************/
public class Kadai2700{
    public static void main(String[] args){
        //•R‚Ì’·‚³‡Œv
        double sum = 0.0;
        
        //1‚Â‚ß‚Ì‰~’ŒF’¼Œa‚Ì“ü—Í‚Æ’·‚³‚ÌŒvZ‚Æ‡Œv‚Ö‚Ì‰ÁZ
        int d = Keyboard.readInt("1‚Â‚ß‚Ì‰~’Œ‚Ì’¼Œa[cm]");
        double length = (double)d * Math.PI;
        System.out.println("•K—v‚È•R‚Ì’·‚³ = " + length + " [cm]");
        System.out.println();
        sum += length;
        
        //2‚Â‚ß‚Ì‰~’ŒF’¼Œa‚Ì“ü—Í‚Æ’·‚³‚ÌŒvZ‚Æ‡Œv‚Ö‚Ì‰ÁZ
        d = Keyboard.readInt("2‚Â‚ß‚Ì‰~’Œ‚Ì’¼Œa[cm]");
        length = (double)d * Math.PI;
        System.out.println("•K—v‚È•R‚Ì’·‚³ = " + length + " [cm]");
        System.out.println();
        sum += length;
        
        //3‚Â‚ß‚Ì‰~’ŒF’¼Œa‚Ì“ü—Í‚Æ’·‚³‚ÌŒvZ‚Æ‡Œv‚Ö‚Ì‰ÁZ
        d = Keyboard.readInt("3‚Â‚ß‚Ì‰~’Œ‚Ì’¼Œa[cm]");
        length = (double)d * Math.PI;
        System.out.println("•K—v‚È•R‚Ì’·‚³ = " + length + " [cm]");
        System.out.println();
        sum += length;
        
        //‡Œv’·‚³‚Ì•\¦
        System.out.println();
        System.out.println("•K—v‚È•R‚Ì’·‚³‚Ì‡Œv = " + sum + " [cm]");
    }
}
