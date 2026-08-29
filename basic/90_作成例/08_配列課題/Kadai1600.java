/******************************************************************************
 * 配列の内容を値をずらしながら横並びに表示する
 *****************************************************************************/
public class Kadai1600{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        //配列の各要素に乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf("%3d", array[i]);
        }
        System.out.println(); //配列表示後の改行
        
        //配列の各要素を1つ後ろへずらす処理を1周分繰り返す
        for(int loop = 0; loop < array.length; loop++){
            int w = array[array.length - 1];           //最後尾の値を退避
            for(int i = array.length - 1; i > 0; i--){ //要素を1つずつ後ろへ
                array[i] = array[i - 1];
            }
            array[0] = w;                              //最後尾の値を先頭へ
            
            //ずらし処理の結果を表示
            for(int i = 0; i < array.length; i++){
                System.out.printf("%3d", array[i]);
            }
            System.out.println(); //配列表示後の改行
        }
    }
}
