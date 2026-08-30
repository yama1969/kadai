/******************************************************************************
 * 配列の内容を横並びに2行表示する
 *****************************************************************************/
public class Kadai1020{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        
        //配列の各要素に乱数を入れる
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
        }
        
        //配列の各要素の表示を2回繰り返す
        for(int n = 0; n < 2; n++){
            //配列の各要素を表示する
            for(int i = 0; i < array.length; i++){
                System.out.printf(" %3d", array[i]);
            }
            System.out.println();
        }
    }
}
