/******************************************************************************
 * リスト構造の実現に向けて
 * 任意のデータを任意の位置に追加する
 *****************************************************************************/
public class Kadai5030{
    public static void main(String[] args){
        //配列の生成
        final int SIZE = 6;                //データ配列の長さ
        String[] name = new String[SIZE];  //駅名配列
        int[] next = new int[SIZE];        //次ポインタ配列
        boolean[] use = new boolean[SIZE]; //要素使用状況配列
        
        //初期値の設定
        next[0] = -1;    //先頭データは無し
        use[0] = true;   //添字0は先頭を指すものとして常に使用中
        
        //データ操作
        int no = 0;
        while(no > -1){
            //各配列を添字順に表示
            for(int i = 0; i < name.length; i++){
                System.out.printf("%3d %6b %3d  %s%n", i, use[i], next[i], name[i]);
            }
            System.out.println();
            
            //駅名配列をポインタに従い表示
            int pos = next[0];
            int cnt = 1;
            while(pos != -1){
                System.out.printf("%3d %s%n", cnt, name[pos]);
                cnt++;
                pos = next[pos];
            }
            System.out.println();
            
            //挿入位置の入力
            no = Keyboard.readInt("挿入位置[先頭より前:0、終了:-1以下]");
            
            //挿入処理
            if(no > -1){
                //終了コマンドでなければ
                //挿入前要素の添字を探す
                int before = 0;
                for(int i = 0; i < no && before != -1; i++){
                    before = next[before];
                }
                if(before < 0){
                    System.out.println("挿入位置が不正です。");
                }else{
                    //挿入前要素の位置(添字)が正常ならば
                    //挿入要素の添字を割り当てる
                    int ins = 0;
                    for(ins = 0; ins < use.length && use[ins]; ins++){
                    }
                    if(ins == use.length){
                        System.out.println("空きがありません。");
                    }else{
                        //挿入できる位置(添字)があれば
                        //挿入処理
                        //  挿入位置に新データを代入
                        name[ins] = Keyboard.readString("駅名");
                        //  挿入位置の使用状況をtureにする
                        use[ins] = true;
                        //  新データの次は、前要素の元々の次
                        next[ins] = next[before];
                        //  前要素の次は、新データとなる
                        next[before] = ins;
                    }
                }
            }
            System.out.println();
        }
    }
}
