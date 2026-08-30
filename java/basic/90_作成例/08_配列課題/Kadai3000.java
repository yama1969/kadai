/******************************************************************************
 * 配列の内容を並び替える(挿入ソート)
 *****************************************************************************/
public class Kadai3000{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        //配列の各要素に乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf("%3d", array[i]);
        }
        System.out.println(); //配列表示後の改行
        
        //挿入ソート(添字ins_pos-1までがソート済み、添字ins_posが挿入値)
        for(int ins_pos = 1; ins_pos < array.length; ins_pos++){
            int ins_dat = array[ins_pos];  //挿入値
            int comp_pos = ins_pos - 1;    //比較添字初期値
            while(comp_pos > -1 && array[comp_pos] < ins_dat){
                array[comp_pos + 1] = array[comp_pos];
                comp_pos--;
            }
            array[comp_pos + 1] = ins_dat;
        }
        
        //処理結果表示
        System.out.println();
        for(int i = 0; i < array.length; i++){
            System.out.printf("%3d", array[i]);
        }
        System.out.println();
    }
}
