/******************************************************************************
 * 配列の任意の要素に値を挿入する
 *****************************************************************************/
public class Kadai1140{
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
        
        //挿入位置の入力(添字範囲外は終了)
        int pos = Keyboard.readInt("添字[0～9,それ以外は終了]");
        
        //挿入処理(終了入力まで繰り返し)
        while(pos > -1 && pos < array.length){
            //挿入値の入力
            int dat = Keyboard.readInt("値");
            
            //配列の挿入位置を空ける(挿入位置から後ろの値を、後ろにずらす)
            for(int i = array.length - 1; i > pos; i--){
                array[i] = array[i - 1];
            }
            //挿入位置に挿入値を代入
            array[pos] = dat;
            //挿入後の配列表示
            for(int i = 0; i < array.length; i++){
                System.out.printf(" %3d", array[i]);
            }
            System.out.println(); //配列表示後の改行
            System.out.println(); //1行あけるための改行
            
            //次のための挿入位置の入力(添字範囲外は終了)
            pos = Keyboard.readInt("添字[0～9,それ以外は終了]");
        }
    }
}
