/******************************************************************************
 * “ñ•ª’Tõ–Ø‚ğg—p‚µ‚½’Tõ
 *****************************************************************************/
public class Kadai3020{
    public static void main(String[] args){
        final int SIZE = 15;
        final int NODAT = 99;
        
        //”z—ñ‚Ì‰Šú‰»
        int[] dat = new int[SIZE];
        for(int i = 0; i < dat.length; i++){
            dat[i] = NODAT;
        }
        
        //®”—”‚ğ¶¬‚µA“ñ•ª’Tõ–Ø‚ÖŠi”[‚·‚é
        for(int i = 0; i < SIZE * 5; i++){
            int w = (int)(Math.random() * 80) + 10;
            int pos = 0;
            
            while(pos < dat.length && dat[pos] != w){
                if(dat[pos] == NODAT){
                    dat[pos] = w;                 //‚»‚Ìß“_‚É‘ã“ü
                }else{
                    if(w < dat[pos]){
                        pos = (pos + 1) * 2 - 1;  //¶‚Ìq‚Öi‚Ş
                    }else{
                        pos = (pos + 1) * 2;      //‰E‚Ìq‚Öi‚Ş
                    }
                }
            }
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
        System.out.println();
        
        //’Tõˆ—
        int val = Keyboard.readInt("’Tõ’l");
        
        int pos = 0;
        while(pos < dat.length && dat[pos] != val){
            if(val < dat[pos]){
                pos = (pos + 1) * 2 - 1;  //¶‚Ìq‚Öi‚Ş
            }else{
                pos = (pos + 1) * 2;      //‰E‚Ìq‚Öi‚Ş
            }
        }
        if(pos < dat.length){
            System.out.println(val + " ‚Í“Yš " + pos);
        }else{
            System.out.println(val + " ‚Í‚ ‚è‚Ü‚¹‚ñB");
        }
    }
}
