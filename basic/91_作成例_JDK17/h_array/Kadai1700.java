package h_array;
/******************************************************************************
 * 入力データから、上位データを収集する
 *****************************************************************************/
public class Kadai1700{
    public static void main(String[] args){
        //上位値格納用配列
        int[] rank = new int[6];
        //配列をint型最小値で初期化
        for(int i = 0; i < rank.length; i++){
            rank[i] = Integer.MIN_VALUE;
        }
        
        //0が入力されるまで繰り返す
        int w = 0;
        while((w = Keyboard.readInt("整数(0は終了)")) != 0){
            //入力値を上位値格納用配列に挿入
            int i = 0;
            for(i = rank.length - 2; i > -1 && w > rank[i]; i--){
                rank[i + 1] = rank[i];
            }
            rank[i + 1] = w;
        }
        
        //処理結果表示
        System.out.println();
        for(int i = 0; i < rank.length - 1; i++){
            System.out.println(rank[i]);
        }
    }
}
