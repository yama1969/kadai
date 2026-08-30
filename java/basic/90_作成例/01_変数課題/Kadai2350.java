/******************************************************************************
 * intŒ^‚ÆdoubleŒ^‚Ìg‚¢•ûBÁ”ïÅ‚ÌŒvZ
 *****************************************************************************/
public class Kadai2350{
    public static void main(String[] args){
        //Á”ïÅ—¦‚Ì’è‹`
        final double RATE = 0.05;  //‚±‚ÌŒãA’l‚ª•Ï‚í‚ç‚È‚¢‚Ì‚Åfinal‚É‚·‚é
        
        //‚¨‰Ùq‚Ì‰¿Ši‚ğ“ü‚ê‚é•Ï”‚ÌéŒ¾‚ÆA‰¿Ši‚Ì‘ã“ü
        int price1 = Keyboard.readInt("1‚Â‚ß‚Ì‚¨‰Ùq‚Ì‰¿Ši[‰~]");
        int price2 = Keyboard.readInt("2‚Â‚ß‚Ì‚¨‰Ùq‚Ì‰¿Ši[‰~]");
        int price3 = Keyboard.readInt("3‚Â‚ß‚Ì‚¨‰Ùq‚Ì‰¿Ši[‰~]");
        System.out.println();
        
        //ŒÂ•Êw“ü‚ÌÁ”ïÅŒvZ
        int tax = (int)(price1 * RATE) + (int)(price2 * RATE) + (int)(price3 * RATE);
        System.out.println("ŒÂ•Êw“ü‚ÌÁ”ïÅ = " + tax + " ‰~");
        
        //ˆêŠ‡w“ü‚ÌÁ”ïÅŒvZ
        tax = (int)((price1 + price2 + price3) * RATE);
        System.out.println("ˆêŠ‡w“ü‚ÌÁ”ïÅ = " + tax + " ‰~");
    }
}
