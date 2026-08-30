package h_array;
/******************************************************************************
 * 入力された値を降順に並べながら配列に格納する
 *****************************************************************************/
public class Kadai1640{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        //配列をint型最小値で初期化
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.MIN_VALUE;
        }
        
        //値の入力と配列への挿入を配列サイズ分繰り返し
        for(int cnt = 0; cnt < array.length; cnt++){
            //値の入力
            int w = Keyboard.readInt("値");
            //配列への挿入
            int i = 0;
            for(i = array.length - 2; i > -1 && w > array[i]; i--){
                array[i + 1] = array[i]; //入力値の方が大きい場合、後ろへずらす
            }
            array[i + 1] = w;            //比較対象の後ろへ入力値を代入
        }
        System.out.println();
        
        //処理結果を表示
        for(int i = 0; i < array.length; i++){
            System.out.printf(" %3d", array[i]);
        }
    }
}
