package i_algorithm;
/******************************************************************************
 * リスト構造の実現に向けて
 * リストに追加する
 *****************************************************************************/
public class Kadai5020{
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
        
        //挿入要素の添字を割り当てる
        int ins = 0;             //添字の若い方から順にuseがfalseの要素を探す
        for(ins = 0; ins < use.length && use[ins]; ins++){
        }
        if(ins == use.length){
            System.out.println("空きがありません。");
            return;
        }
        
        //挿入前要素の添字を探す
        int no = 2;              //2番目が挿入前要素(新データは3番目に挿入)
        int before = 0;          //ポインタを辿って先頭から2番目を探す
        for(int i = 0; i < no && before != -1; i++){
            before = next[before];
        }
        if(before == -1){
            System.out.println("挿入位置が不正です。");
            return;
        }
        
        //挿入処理
        name[ins] = "五橋";        //挿入位置に新データを代入
        use[ins] = true;           //挿入位置の使用状況をtureにする
        next[ins] = next[before];  //新データの次は、前要素の元々の次
        next[before] = ins;        //前要素の次は、新データとなる
        
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
