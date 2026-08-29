package i_algorithm;
/******************************************************************************
 * リスト構造の実現に向けて
 * リストを辿る
 *****************************************************************************/
public class Kadai5000{
    public static void main(String[] args){
        //駅名配列の初期化
        String[] name = {
            null, "愛宕橋", "仙台", "勾当台公園", "五橋", "広瀬通"
        };
        //次ポインタ配列の初期化
        int[] next = {
            3, -1, 4, 5, 1, 2
        };
        
        //2つの配列を添字順に表示
        for(int i = 0; i < name.length; i++){
            System.out.printf("%2d %3d  %s%n",i, next[i], name[i]);
        }
        System.out.println();
        
        //駅名配列をポインタに従い表示
        int pos = next[0];
        while(pos != -1){
            System.out.println(name[pos]);
            pos = next[pos];
        }
    }
}
