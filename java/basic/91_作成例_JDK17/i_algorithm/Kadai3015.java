package i_algorithm;
/******************************************************************************
 * 配列を二分探索木として、値を格納する
 *****************************************************************************/
public class Kadai3015{
    public static void main(String[] args){
        final int SIZE = 15;
        final int NODAT = 99;
        
        //配列の初期化
        int[] dat = new int[SIZE];
        for(int i = 0; i < dat.length; i++){
            dat[i] = NODAT;
        }
        
        //整数乱数を生成し、二分探索木へ格納する
        for(int i = 0; i < SIZE * 5; i++){
            int w = (int)(Math.random() * 80) + 10;
            int pos = 0;
            
            while(pos < dat.length && dat[pos] != w){
                if(dat[pos] == NODAT){
                    dat[pos] = w;                 //その節点に代入
                }else{
                    if(w < dat[pos]){
                        pos = (pos + 1) * 2 - 1;  //左の子へ進む
                    }else{
                        pos = (pos + 1) * 2;      //右の子へ進む
                    }
                }
            }
        }
        
        //配列を二分木構造として表示
        System.out.println("                     " + dat[0]);
        System.out.println("                   ／  ＼");
        System.out.println("                 ／      ＼");
        System.out.println("               ／          ＼");
        System.out.println("             ／              ＼");
        System.out.println("           ／                  ＼");
        System.out.println("         " + dat[1] + "                      " + dat[2]);
        System.out.println("       ／  ＼                  ／  ＼");
        System.out.println("     ／      ＼              ／      ＼");
        System.out.println("   " + dat[3] + "          " + dat[4] + "          " + dat[5] + "          " + dat[6]);
        System.out.println(" ／  ＼      ／  ＼      ／  ＼      ／  ＼");
        System.out.println(dat[7] + "    " + dat[8] + "    " + dat[9] + "    " + dat[10] + "    " + dat[11] + "    " + dat[12] + "    " + dat[13] + "    " + dat[14]);
    }
}
