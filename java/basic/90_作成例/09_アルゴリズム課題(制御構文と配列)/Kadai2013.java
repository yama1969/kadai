/******************************************************************************
 * Å‘å’l‚Ì’Tõ
 *****************************************************************************/
public class Kadai2013{
    public static void main(String[] args){
        //”z—ñ‚Ì‰Šú‰»
        int[] dat = new int[30];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //Å‘å’l’Tõ
        int max = 0;         //Å‘å’lŒó•â‚Ì“Yš
        for(int i = 1; i < dat.length; i++){
            if(dat[max] < dat[i]){
                max = i;
            }
        }
        System.out.println("Å‘å’l‚Ì“Yš = " + max);
        
        //Œ‹‰Ê•\¦
        for(int i = 0; i < dat.length; i++){
            if(i == max){
                System.out.print("› ");
            }else{
                System.out.print("   ");
            }
        }
        System.out.println();
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
}
