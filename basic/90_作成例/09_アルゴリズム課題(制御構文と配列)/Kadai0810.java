/******************************************************************************
 * ‚È‚é‚×‚­­‚È‚¢”‚Ì†•¼E‰İ•¼‚ğg‚¤
 *****************************************************************************/
public class Kadai0810{
    public static void main(String[] args){
        //‹àŠz‚ğŒˆ’è
        int price = (int)(Math.random() * 99999.0) + 1;
        System.out.println("‹àŠz = " + price);
        System.out.println();
        
        int[][] kind = new int[10][2];
        kind[0][0] = 10000;
        kind[1][0] =  5000;
        kind[2][0] =  2000;
        kind[3][0] =  1000;
        kind[4][0] =   500;
        kind[5][0] =   100;
        kind[6][0] =    50;
        kind[7][0] =    10;
        kind[8][0] =     5;
        kind[9][0] =     1;
        
        //‹àíŒvZ
        for(int i = 0; i < kind.length; i++){
            while(!(kind[i][0] > price)){
                kind[i][1]++;
                price -= kind[i][0];
            }
        }
        
        //Œ‹‰Ê•\¦
        for(int i = 0; i < kind.length; i++){
            System.out.printf("%5d‰~ : %1d –‡\n", kind[i][0], kind[i][1]);
        }
    }
}
