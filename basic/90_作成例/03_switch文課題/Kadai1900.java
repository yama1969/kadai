/******************************************************************************
 * Š„ˆø•\‚É‚æ‚éŒˆ’è
 *****************************************************************************/
public class Kadai1900{
    public static void main(String[] args){
        //Š„ˆø•\‚Ì•\¦
        System.out.println("+---------------------------+----------+");
        System.out.println("| 15,000‰~ˆÈã 20,000‰~–¢– | 12% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("| 10,000‰~ˆÈã 15,000‰~–¢– |  8% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|  5,000‰~ˆÈã 10,000‰~–¢– |  5% OFF  |");
        System.out.println("+---------------------------+----------+");
        System.out.println("|      0‰~ˆÈã  5,000‰~–¢– | Š„ˆø‚È‚µ |");
        System.out.println("+---------------------------+----------+");
        System.out.println();
        
        //w“ü‹àŠz‚Ì“ü—Í
        int amount = Keyboard.readInt("w“ü‹àŠz");
        
        //Š„ˆøƒ‰ƒ“ƒN‚ÌŒvZ
        int rank = (amount + 5000) / 5000;
        
        //Š„ˆø—¦‚Ì”»’è
        int rate = -1;  //Š„ˆø—¦ƒfƒtƒHƒ‹ƒg’l ¨ ‚±‚Ì‚Ü‚Ü‚È‚çŠ„ˆøƒ‰ƒ“ƒNŠO
        switch(rank){
        case 1:
            rate = 0;
            break;
        case 2:
            rate = 5;
            break;
        case 3:
            rate = 8;
            break;
        case 4:
            rate = 12;
            break;
        default:
        }
        
        //Œ‹‰Ê•\¦
        switch(rate){
        case -1:   //rate‚ªƒfƒtƒHƒ‹ƒg‚Ì‚Ü‚Ü‚È‚çAƒ‰ƒ“ƒN“K—p‚È‚©‚Á‚½Ø‹’
            System.out.println("‹àŠz‚ª”ÍˆÍŠO‚Å‚·B");
            break;
        default:   //rate‚ÉŠ„ˆø—¦‚ª“ü‚Á‚Ä‚¢‚é
            amount = (int)((double)amount * (double)(100 - rate) / 100.0);
            System.out.println("Š„ˆø " + rate + "%");
            System.out.println("Š„ˆøŒã‹àŠz " + amount + "‰~");
        }
    }
}
