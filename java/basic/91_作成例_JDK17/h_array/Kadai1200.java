package h_array;
/******************************************************************************
 * 配列の内容をシャッフルする
 *****************************************************************************/
public class Kadai1200{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        //配列の各要素に乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf(" %3d", array[i]);
        }
        System.out.println(); //配列表示後の改行
        System.out.println(); //1行あけるための改行
        
        //要素の入れ替えを20回繰り返す
        for(int i = 0; i < 20; i++){
            //2つの入れ替え要素を乱数で決定
            int a = (int)(Math.random() * (double)array.length);
            int b = (int)(Math.random() * (double)array.length);
            //入れ替え処理
            int w = array[a];
            array[a] = array[b];
            array[b] = w;
        }
        
        //処理後の配列の内容を表示
        for(int i = 0; i < array.length; i++){
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();
    }
}
