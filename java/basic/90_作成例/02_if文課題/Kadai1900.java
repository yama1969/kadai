/******************************************************************************
 * Š„ˆø•\‚É‚æ‚é”»’è
 *****************************************************************************/
public class Kadai1900{
    public static void main(String[] args){
        //Š„ˆø•\‚Ì•\¦
        System.out.println("+----------------------------+----------+");
        System.out.println("| 100,000‰~ˆÈã              | 30% OFF  |");
        System.out.println("+----------------------------+----------+");
        System.out.println("|  50,000‰~ˆÈã100,000‰~–¢– | 20% OFF  |");
        System.out.println("+----------------------------+----------+");
        System.out.println("|  20,000‰~ˆÈã 50,000‰~–¢– | 10% OFF  |");
        System.out.println("+----------------------------+----------+");
        System.out.println("|               20,000‰~–¢– | Š„ˆø‚È‚µ |");
        System.out.println("+----------------------------+----------+");
        System.out.println();
        
        //w“ü‹àŠz‚Ì“ü—Í
        int amount = Keyboard.readInt("w“ü‹àŠz");
        
        //w“ü‹àŠz‚ÆŠ„ˆø•\‚©‚çAŠ„ˆø—¦‚ğŒˆ’è
        double rate = 0.0;
        if(amount < 20000){
            rate = 0.0;
        }else if(amount < 50000){
            rate = 0.1;
        }else if(amount < 100000){
            rate = 0.2;
        }else{
            rate = 0.3;
        }
        
        //Œˆ’è‚³‚ê‚½Š„ˆø—¦‚ÆŠ„ˆøŒã‹àŠz‚Ì•\¦
        System.out.println("Š„ˆø—¦ " + (int)(rate * 100.0) + " %");
        System.out.println("Š„ˆøŒã‹àŠz " + (int)(amount * (1.0 - rate)) + " ‰~");
    }
}
