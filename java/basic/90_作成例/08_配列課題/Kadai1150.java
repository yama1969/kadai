/******************************************************************************
 * 配列の内容を逆順にする
 *****************************************************************************/
public class Kadai1150{
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
        
        //配列の内容を逆順にする
        for(int i = 0; i < array.length / 2; i++){
            //範囲を縮めながら、範囲の先端と最後尾を入れ替え
            int w = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = w;
        }
        
        //逆順処理後の配列の表示
        for(int i = 0; i < array.length; i++){
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();
    }
}
