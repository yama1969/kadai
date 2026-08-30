package h_array;
/******************************************************************************
 * 配列にデータを入れる
 *****************************************************************************/
public class Kadai1100{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        
        //配列の各要素に乱数を代入し、表示する
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();  //配列表示の最後の改行
        System.out.println();  //1行あけるための改行
        
        //配列の各要素を添字の降順に表示する
        for(int i = array.length - 1; i > -1; i--){
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();
    }
}
