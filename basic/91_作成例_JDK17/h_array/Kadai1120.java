package h_array;
/******************************************************************************
 * 配列の任意のデータを表示する
 *****************************************************************************/
public class Kadai1120{
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
        
        //表示する値の添字を入力
        int i = Keyboard.readInt("添字[0～9,それ以外は終了]");
        //終了が入力されるまで繰り返す
        while(i >= 0 && i < array.length){
            //入力された添字のデータを表示
            System.out.println(array[i]);
            System.out.println();
            //次のための入力
            i = Keyboard.readInt("添字[0～9,それ以外は終了]");
        }
    }
}
