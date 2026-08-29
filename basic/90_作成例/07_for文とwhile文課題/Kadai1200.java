/******************************************************************************
 * •bƒJƒEƒ“ƒ^
 *****************************************************************************/
public class Kadai1200{
    public static void main(String[] args){
        //•Ï”‚Ì€”õ
        int s = 0;                                     //•b
        long end = System.currentTimeMillis() + 1000L; //Ÿ‚Ì•b•\¦
        System.out.println(s);                         //Å‰‚Ì•b•\¦
        
        //•b•\¦‚ğ10‰ñŒJ‚è•Ô‚·
        for(int i = 0; i < 10; i++){
            while(System.currentTimeMillis() < end){   //•\¦‚Ü‚Å‹óƒ‹[ƒv
            }
            //Ÿ‚Ì•b•\¦
            end += 1000L;
            
            //•b‰ÁZ‚Æ•\¦
            s++;
            System.out.println(s);
        }
    }
}
