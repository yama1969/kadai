package h_array;
/******************************************************************************
 * 配列の内容をシャッフルする別方法
 *****************************************************************************/
public class Kadai1250{
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
        
        //要素の入れ替えを配列の先頭から末尾まで順番に行う
        for(int i = 0; i < array.length; i++){
            //要素入れ替えの相手を乱数で決定
            int a = (int)(Math.random() * (double)array.length);
            //入れ替え処理
            int w = array[a];
            array[a] = array[i];
            array[i] = w;
        }
        
        //処理後の配列の内容を表示
        for(int i = 0; i < array.length; i++){
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();
    }
}
