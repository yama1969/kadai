/******************************************************************************
 * Š„ˆø•\‚É‚æ‚é”»’è
 *****************************************************************************/
public class Kadai2000{
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
        
        //Š„ˆø•\î•ñ‚Ì‘ã“ü
        int border1 = 20000;  //ƒ‰ƒ“ƒN1‹«ŠE‹àŠz
        int border2 = 50000;  //ƒ‰ƒ“ƒN2‹«ŠE‹àŠz
        int border3 = 100000; //ƒ‰ƒ“ƒN3‹«ŠE‹àŠz
        double rate1 = 0.1;   //ƒ‰ƒ“ƒN1Š„ˆø—¦
        double rate2 = 0.2;   //ƒ‰ƒ“ƒN2Š„ˆø—¦
        double rate3 = 0.3;   //ƒ‰ƒ“ƒN3Š„ˆø—¦
        
        //”»’èŒ‹‰Ê—p•Ï”
        double rate = 0.0;    //Œˆ’è‚µ‚½Š„ˆø—¦
        int min = 0;          //Ÿƒ‰ƒ“ƒN‚Ü‚Åw“ü‚µ‚½•û‚ªˆÀ‚­‚È‚é‹«ŠE
        int next = 0;         //Ÿƒ‰ƒ“ƒN‚Ì‹«ŠE‹àŠz
        
        //ƒ‰ƒ“ƒN‚Ì”»’è‚Æ”»’èŒ‹‰Ê—p•Ï”‚Ö‚Ì‘ã“ü
        if(amount < border1){
            rate = 0.0;
            min = (int)((double)border1 * (1.0 - rate1));
            next = border1;
        }else if(amount < border2){
            rate = rate1;
            min = (int)((double)border2 * (1.0 - rate2));
            next = border2;
        }else if(amount < border3){
            rate = rate2;
            min = (int)((double)border3 * (1.0 - rate3));
            next = border3;
        }else{
            rate = rate3;
            min = amount;
        }
        
        //”»’èŒ‹‰Ê•\¦
        //  Š„ˆø—¦
        System.out.println("Š„ˆø—¦ " + (int)(rate * 100.0) + " %");
        //  Š„ˆøŒã‹àŠz
        int pay = (int)((double)amount * (1.0 - rate));
        System.out.println("Š„ˆøŒã‹àŠz " + pay + " ‰~");
        //  Ÿƒ‰ƒ“ƒN‚Ü‚Åw“ü‚µ‚½•û‚ªˆÀ‚¢‚©‚Ì”»’f
        if(pay > min){
            System.out.println(next + "‰~w“ü‚µ‚½•û‚ª‚¨“¾‚Å‚·‚æ");
        }
    }
}
