package i_algorithm;
/******************************************************************************
 * 二分木構造を配列で表現する
 *****************************************************************************/
public class Kadai3013{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = new int[15];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
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
