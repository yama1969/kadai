/******************************************************************************
 * 配列内の最大値を最後尾に移動して、横並びに表示する
 *****************************************************************************/
public class Kadai1620{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        //配列の各要素に乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf("%3d", array[i]);
        }
        System.out.println(); //配列表示後の改行
        
        //配列内の最大値を最後尾へ移動する処理
        for(int i = 0; i < array.length - 1; i++){
            //次の要素と比較し、前の方が大きければ要素を交換
            if(array[i] > array[i + 1]){
                int w = array[i];
                array[i] = array[i + 1];
                array[i + 1] = w;
            }
        }
        
        //処理後の配列の表示
        for(int i = 0; i < array.length; i++){
            System.out.printf("%3d", array[i]);
        }
    }
}
