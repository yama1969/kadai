package h_array;
/******************************************************************************
 *  配列の内容を横並びに表示する
 *****************************************************************************/
public class Kadai1000{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        
        //配列の各要素に乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();
    }
}
