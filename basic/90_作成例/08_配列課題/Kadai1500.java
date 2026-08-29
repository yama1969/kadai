/******************************************************************************
 * 配列の内容を横並びに2行、値をずらして表示する
 *****************************************************************************/
public class Kadai1500{
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
        
        //値を1つずつずらす処理
        int w = array[array.length - 1];           //最後尾の値を退避
        for(int i = array.length - 1; i > 0; i--){ //要素を1つずつ後ろへコピー
            array[i] = array[i - 1];
        }
        array[0] = w;                              //元の最後尾を先頭へ代入
        
        //処理結果の表示
        for(int i = 0; i < array.length; i++){
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();
    }
}
