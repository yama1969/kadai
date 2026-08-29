/******************************************************************************
 * “ñ•ª–Ø\‘¢‚ğ”z—ñ‚Å•\Œ»‚·‚é
 *****************************************************************************/
public class Kadai3013{
    public static void main(String[] args){
        //”z—ñ‚Ì‰Šú‰»
        int[] dat = new int[15];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //”z—ñ‚ğ“ñ•ª–Ø\‘¢‚Æ‚µ‚Ä•\¦
        System.out.println("                     " + dat[0]);
        System.out.println("                   ^  _");
        System.out.println("                 ^      _");
        System.out.println("               ^          _");
        System.out.println("             ^              _");
        System.out.println("           ^                  _");
        System.out.println("         " + dat[1] + "                      " + dat[2]);
        System.out.println("       ^  _                  ^  _");
        System.out.println("     ^      _              ^      _");
        System.out.println("   " + dat[3] + "          " + dat[4] + "          " + dat[5] + "          " + dat[6]);
        System.out.println(" ^  _      ^  _      ^  _      ^  _");
        System.out.println(dat[7] + "    " + dat[8] + "    " + dat[9] + "    " + dat[10] + "    " + dat[11] + "    " + dat[12] + "    " + dat[13] + "    " + dat[14]);
    }
}
