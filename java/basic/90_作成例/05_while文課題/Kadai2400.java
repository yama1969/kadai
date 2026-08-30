/******************************************************************************
 * 10•bˆÈ“à‚É‰½–â‚Ì‘«‚µZ‚ğ³“š‚Å‚«‚é‚©
 *****************************************************************************/
public class Kadai2400{
    public static void main(String[] args){
        //•Ï”€”õ
        int cnt = -1;  //³“š‰ñ”
        int a = 0;     //‘«‚µZ–â‘è‚Ì’la
        int b = 0;     //              b
        int ans = 0;   //‘«‚µZ‰ñ“š
        
        //Œ»İ‚©‚ç10•bŒã‚Ì‚ğ“¾‚é
        long time = System.currentTimeMillis() + 10000L;
        
        //‘«‚µZ‰ñ“šŠJn
        while( !(time < System.currentTimeMillis()) && ans == a + b ){
            //I—¹‘O‚©‚Â‰ñ“š‚ª³‚µ‚¢ê‡AŒJ‚è•Ô‚µ
            cnt++;
            a = (int)(Math.random() * 80.0) + 10;             //‘«‚µZ–â‘èì¬
            b = (int)(Math.random() * (double)(90 - a)) + 10;
            ans = Keyboard.readInt(a + " + " + b);            //‰ñ“š“ü—Í
        }
        System.out.println();
        
        //Œ‹‰Ê•\¦
        System.out.println(cnt + "‰ñ³“š‚µ‚Ü‚µ‚½B");
    }
}
