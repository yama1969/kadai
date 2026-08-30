/******************************************************************************
 * リスト構造の実現に向けて
 * リストから削除する
 *****************************************************************************/
public class Kadai5040{
    public static void main(String[] args){
        //駅名配列の初期化
        String[] name = {
            null, "五橋", "仙台", "勾当台公園", "ごみ", "愛宕橋"
        };
        //次ポインタ配列の初期化
        int[] next = {
            3, 5, 1, 2, 32, -1
        };
        //要素使用状況配列の初期化
        boolean[] use = {
            true, true, true, true, false, true
        };
        
        //配列の内容を添字順に表示
        for(int i = 0; i < name.length; i++){
            System.out.printf("%2d %6b %3d  %s%n", i, use[i], next[i], name[i]);
        }
        System.out.println();
        
        //駅名配列を次ポインタに従い表示
        int pos = next[0];
        while(pos != -1){
            System.out.println(name[pos]);
            pos = next[pos];
        }
        System.out.println();
        
        //削除要素の前要素の添字を探す
        int no = 3;              //3番目が削除要素
        int before = 0;
        for(int i = 0; i < no - 1 && before != -1; i++){
            before = next[before];
        }
        if(before == -1){
            System.out.println("削除位置が不正です。");
            return;
        }
        //削除要素の添字を探す
        int del = next[before];
        if(del == -1){
            System.out.println("削除位置が不正です。");
            return;
        }
        
        //削除処理
        use[del] = false;         //削除要素の使用状況をfalseにする
        next[before] = next[del]; //削除要素の次が、削除要素の前要素の次になる
        
        //各配列を添字順に表示
        for(int i = 0; i < name.length; i++){
            System.out.printf("%2d %6b %3d  %s%n", i, use[i], next[i], name[i]);
        }
        System.out.println();
        
        //駅名配列をポインタに従い表示
        pos = next[0];
        while(pos != -1){
            System.out.println(name[pos]);
            pos = next[pos];
        }
        System.out.println();
    }
}
