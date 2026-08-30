/******************************************************************************
 * 配列の任意の要素を削除する
 *****************************************************************************/
public class Kadai1130{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        
        //配列の各要素へ乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();  //配列表示後の改行
        System.out.println();  //1行あけるための改行
        
        //削除する位置の添字を入力(添字範囲外は終了)
        int pos = Keyboard.readInt("添字[0～9,それ以外は終了]");
        
        //削除処理(位置が添字範囲外でなければ繰り返し)
        while(pos > -1 && pos < array.length){
            //削除位置より後ろの値を1つずつ前にずらす
            for(int i = pos; i < array.length - 1; i++){
                array[i] = array[i + 1];
            }
            //最後の要素には0を代入
            array[array.length - 1] = 0;
            
            //処理後の配列を表示
            for(int i = 0; i < array.length; i++){
                System.out.printf(" %3d", array[i]);
            }
            System.out.println();  //配列表示後の改行
            System.out.println();  //1行あけるための改行
            
            //次のための削除位置入力(添字範囲外は終了)
            pos = Keyboard.readInt("添字[0～9,それ以外は終了]");
        }
    }
}
