package i_algorithm;
/******************************************************************************
 * リスト構造の実現に向けて
 * リストを辿る ～ 歯抜けの配列
 *****************************************************************************/
public class Kadai5010{
    public static void main(String[] args){
        //駅名配列の初期化
        String[] name = {
            null, "ごみ", "仙台", "勾当台公園", "ごみ", "愛宕橋"
        };
        //次ポインタ配列の初期化
        int[] next = {
            3, 23, 5, 2, 32, -1
        };
        //要素使用状況配列の初期化
        boolean[] use = {
            true, false, true, true, false, true
        };
        
        //配列の内容を添字順に表示
        for(int i = 0; i < name.length; i++){
            System.out.printf("%2d %6b %3d  %s%n",i, use[i], next[i], name[i]);
        }
        System.out.println();
        
        //駅名配列をポインタに従い表示
        int pos = next[0];
        while(pos != -1){
            System.out.println(name[pos]);
            pos = next[pos];
        }
        System.out.println();
        
        //空き要素の検索と表示
        int i = 0;
        for(i = 0; i < use.length && use[i]; i++){
        }
        if(i == use.length){
            System.out.println("空きがありません。");
        }else{
            System.out.println("空き添字 : " + i);
        }
    }
}
