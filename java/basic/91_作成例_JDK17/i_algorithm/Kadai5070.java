package i_algorithm;
/******************************************************************************
 * 双方向リスト構造への拡張
 *****************************************************************************/
public class Kadai5070{
    public static void main(String[] args){
        //配列の生成
        final int SIZE = 20;               //データ配列の長さ
        String[] name = new String[SIZE];  //駅名配列
        int[] next = new int[SIZE];        //次ポインタ配列
        int[] back = new int[SIZE];        //前ポインタ配列
        boolean[] use = new boolean[SIZE]; //要素使用状況配列
        
        //初期値の設定
        next[0] = SIZE - 1;   //先頭データは無し(次は最後尾アンカー)
        use[0] = true;        //添字0は先頭を指すものとして常に使用中
        back[SIZE - 1] = 0;   //最後尾データは無し(前は先頭アンカー)
        use[SIZE - 1] = true; //添字SIZE-1は最後尾を指すものとして常に使用中
        
        //データ操作
        String msg = "コマンド[挿入:i, 削除:d, 駅表示:v, 駅逆順表示:r, "
                   + "配列表示:a, 終了:e]";
        char cmd = 0;
        do{
            //コマンド入力
            cmd = Keyboard.readChar(msg);
            switch(cmd){
            case 'i': //挿入コマンド
                int no = Keyboard.readInt("挿入位置[先頭より前:0]");
                if(no > -1){
                    //挿入前要素の添字を探す
                    int before = 0;
                    for(int i = 0; i < no && before != SIZE - 1; i++){
                        before = next[before];
                    }
                    if(before == SIZE - 1){
                        System.out.println("挿入位置が不正です。");
                    }else{
                        //挿入前要素の位置(添字)が正常ならば
                        //挿入要素の添字を割り当てる
                        int ins = 0;
                        for(ins = 0; ins < SIZE && use[ins]; ins++){
                        }
                        if(ins == SIZE){
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
                            //  新データの前は、次要素の元々の前
                            back[ins] = back[next[ins]];
                            //  次要素の前は、新データとなる
                            back[next[ins]] = ins;
                        }
                    }
                }else{
                    System.out.println("挿入位置が不正です。");
                }
                break;
            case 'd': //削除コマンド
                no = Keyboard.readInt("削除位置");
                if(no > 0){
                    //削除前要素の添字を探す
                    int before = 0;
                    for(int i = 0; i < no - 1 && before != SIZE - 1; i++){
                        before = next[before];
                    }
                    if(before == SIZE - 1){
                        System.out.println("削除位置が不正です。");
                    }else{
                        //削除前要素の位置(添字)が正常ならば
                        //削除要素の添字は削除前要素の次ポインタの内容
                        int del = next[before];
                        if(del == SIZE - 1){
                            System.out.println("削除位置が不正です。");
                        }else{
                            //削除する位置(添字)が正常ならば
                            //削除処理
                            use[del] = false;           //使用状況をfalseにする
                            next[before] = next[del];   //削除前要素の次は、
                                                        //削除要素の元々の次
                            back[next[del]] = back[del];//削除次要素の前は、
                                                        //削除要素の元々の前
                        }
                    }
                }else{
                    System.out.println("削除位置が不正です。");
                }
                break;
            case 'v': //リスト表示コマンド
                //駅名配列を次ポインタに従い表示
                int cnt = 1;
                int pos = next[0];
                while(pos != SIZE - 1){
                    System.out.printf("%3d %s%n", cnt, name[pos]);
                    cnt++;
                    pos = next[pos];
                }
                break;
            case 'r': //リスト逆順表示コマンド
                //駅名配列を前ポインタに従い表示
                cnt = -1;
                pos = back[SIZE - 1];
                while(pos != 0){
                    System.out.printf("%3d %s%n", cnt, name[pos]);
                    cnt--;
                    pos = back[pos];
                }
                break;
            case 'a': //配列表示コマンド
                //各配列を添字順に表示
                for(int i = 0; i < name.length; i++){
                    System.out.printf("%2d %6b %3d %3d %s%n", i, use[i], next[i], back[i], name[i]);
                }
                break;
            case 'e': //終了コマンド
            default:
            }
            System.out.println();
        }while(cmd != 'e');
    }
}
