/******************************************************************************
 * ”“–‚Ä
 *****************************************************************************/
public class Kadai2200{
    public static void main(String[] args){
        int cnt = 1;                                //‰ñ“š‰ñ”
        int ans = (int)(Math.random() * 5.0) + 1;   //³‰ğ‚Ì’l
        int num = Keyboard.readInt("1`5‚Ì‚Ç‚ê‚©"); //‰‰ñ‰ñ“š“ü—Í
        while(ans != num){                          //•s³‰ğ‚ÌŠÔŒJ‚è•Ô‚µ
            System.out.println("³‚µ‚­‚È‚¢");
            System.out.println();
            cnt++;
            num = Keyboard.readInt("1`5‚Ì‚Ç‚ê‚©"); //Ÿ‚Ì‰ñ“š“ü—Í
        }
        System.out.println("³‚µ‚¢B" + cnt + "‰ñ“ü—Í‚µ‚Ü‚µ‚½B");
    }
}
