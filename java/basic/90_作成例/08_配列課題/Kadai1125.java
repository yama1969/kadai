/******************************************************************************
 *  配列の内容から特定の値を探す(線形探索)
 *****************************************************************************/
public class Kadai1125{
    public static void main(String[] args){
        //値格納用配列
        int[] array = new int[10];
        
        //配列の各要素に乱数を代入し、表示
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 90.0) + 10;
            System.out.printf(" %3d", array[i]);
        }
        System.out.println();  //配列表示後の改行
        System.out.println();  //1行あけるための改行
        
        //配列内から探し出す値を入力
        int num = Keyboard.readInt("値");
        
        //探索処理
        int i = 0;    //値が発見位置の添字(ループ外で利用するため、宣言はここ)
        for(i = 0; i < array.length && array[i] != num; i++){
        }
        if(i == array.length){
            //添字が配列外なら、発見できず
            System.out.println("その値はありません。");
        }else{
            //添字が配列内なら、発見できた
            System.out.println("要素 " + i + " にあります。");
        }
    }
}
