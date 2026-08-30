package i_algorithm;
/******************************************************************************
 * リスト構造の実現に向けて
 * 任意の位置のデータを削除する
 *****************************************************************************/
public class Kadai5050{
    public static void main(String[] args){
        //駅名配列の初期化
        String[] name = {
            null, "五橋", "仙台", "勾当台公園", "広瀬通", "愛宕橋"
        };
        //次ポインタ配列の初期化
        int[] next = {
            3, 5, 1, 4, 2, -1
        };
        //要素使用状況配列の初期化
        boolean[] use = {
            true, true, true, true, true, true
        };
        
        //データ操作
        int no = 1;
        while(no > 0){
            //各配列を添字順に表示
            for(int i = 0; i < name.length; i++){
                System.out.printf("%2d %6b %3d  %s%n", i, use[i], next[i], name[i]);
            }
            System.out.println();
            
            //駅名配列をポインタに従い表示
            int cnt = 1;
            int pos = next[0];
            while(pos != -1){
                System.out.printf("%3d %s%n", cnt, name[pos]);
                cnt++;
                pos = next[pos];
            }
            System.out.println();
            
            //削除位置の入力
            no = Keyboard.readInt("削除位置[終了:0以下]");
            
            //削除処理
            if(no > 0){
                //終了コマンドでなければ
                //削除前要素の添字を探す
                int before = 0;
                for(int i = 0; i < no - 1 && before != -1; i++){
                    before = next[before];
                }
                if(before < 0){
                    System.out.println("削除位置が不正です。");
                }else{
                    //削除前要素の位置(添字)が正常ならば
                    //削除要素の添字は削除前要素の次ポインタの内容
                    int del = next[before];
                    if(del < 0){
                        System.out.println("削除位置が不正です。");
                    }else{
                        //削除する位置(添字)が正常ならば
                        //削除処理
                        use[del] = false;          //使用状況をfalseにする
                        next[before] = next[del];  //削除前要素の次は、
                                                   //削除要素の元々の次
                    }
                }
            }
            System.out.println();
        }
    }
}
