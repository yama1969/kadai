/******************************************************************************
 * ‘fˆö”•ª‰ğ‚ÉŒü‚¯‚Ä
 * ®”‚ª‘f”‚©‚Ç‚¤‚©‚ğ’²‚×‚é
 *****************************************************************************/
public class Kadai1010{
    public static void main(String[] args){
        //‘f”‚©”Û‚©‚ğ’²‚×‚é®”‚Ì“ü—Í
        int num = Keyboard.readInt("2ˆÈã‚Ì®”");
        if(num < 2){
            System.out.println("“ü—Í’l‚ª•s³‚Å‚·B");
            return;
        }
        
        //’²‚×‚é®”‚ª‘¼‚Ì®”‚ÅŠ„‚èØ‚ê‚é‚©”Û‚©‚ğ’²‚×‚é
        boolean prime = true;            //true:‘f”, false:”ñ‘f”
        if(num != 2 && num % 2 == 0){
            //®”‚ª2ˆÈŠO‚ÅA‚©‚Â2‚ÅŠ„‚èØ‚ê‚é‚È‚ç”ñ‘f”
            prime = false;
        }else{
            //3`®”‚Ì•½•ûª‚Ü‚Å‚ÌŠï”‚ÅŠ„‚èØ‚ê‚é‚È‚ç”ñ‘f”
            int max = (int)(Math.sqrt((double)num)) + 1;
            for(int i = 3; i < max && prime; i += 2){
                if(num % i == 0){
                    prime = false;
                }
            }
        }
        
        //Œ‹‰Ê•\¦
        //ÅI“I‚Éã‹L‚Ì‚Ç‚Ìê‡‚Å‚àŠ„‚èØ‚ê‚È‚©‚Á‚½ê‡‚Í‘f”
        if(prime){
            System.out.println("‘f”");
        }else{
            System.out.println("”ñ‘f”");
        }
    }
}
