/******************************************************************************
 * Š„ˆø•\‚É‚æ‚éŒˆ’è
 *****************************************************************************/
public class Kadai4200{
    public static void main(String[] args){
        //“ü—Í•â•‚Ì‚½‚ß Š„ˆø•\‚Ì•\¦
        System.out.println("+---------------------------+----------+");
        System.out.println("| 15,000‰~ˆÈã 20,000‰~–¢– | 21% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("| 10,000‰~ˆÈã 15,000‰~–¢– | 14% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|  5,000‰~ˆÈã 10,000‰~–¢– |  7% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|      0‰~ˆÈã  5,000‰~–¢– | Š„ˆø‚È‚µ |");
        System.out.println("+---------------------------+----------+");
        System.out.println();
        
        //w“ü‹àŠz‚Ì“ü—Í
        int amount = Keyboard.readInt("w“ü‹àŠz");
        System.out.println();
        
        //Š„ˆø—¦‚ÌŒvZ‚Æ•\¦
        int rate = amount / 5000 * 7;
        System.out.println("Š„ˆø—¦ = " + rate + "%");
        
        //Š„ˆøŒã‹àŠz‚ÌŒvZ‚Æ•\¦
        amount = (int)((double)amount * ((double)(100 - rate) / 100.0));
        System.out.println("Š„ˆøŒã‹àŠz = " + amount);
    }
}
