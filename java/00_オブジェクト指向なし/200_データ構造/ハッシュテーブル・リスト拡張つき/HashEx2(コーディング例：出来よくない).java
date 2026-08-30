public class HashEx2{
    public static void main(String[] args){
        //Hashテーブル用
        String[] dat  = new String[10];
        int[]    top  = new int[10];
        int      cmd;
        String   str;
        char     ch;
        int      hash;
        int      i;
        //リスト用
        String[] dat_ex = new String[20];
        int[]    next   = new int[20];
        int[]    back   = new int[20];
        int[]    del    = new int[20];
        int      index;
        int      insert;
        
        //初期化処理
        for(i = 0; i < 10; i++){
            dat[i] = null;
            top[i] = -1;
        }
        for(index = 0; index < 20; index++){
            del[index] = 1;
        }
        
        while(true){
            //コマンド入力
//            cmd = InputKey.inNum("0:追加 1:探索 2:削除 ");
            cmd = InputKey.inNum("0:追加 1:探索 ");
            if(cmd == 0){
                //データ格納処理
                str = InputKey.inStr("文字列データ");
                ch = str.charAt(0);
                hash = ch % 10;
                if(dat[hash] == null){
                    dat[hash] = str;
                }else{
                    for(insert = 0; insert < 20; insert++){
                        if(del[insert] == 1){
                            break;
                        }
                    }
                    if(insert == 20){
                        System.out.println("格納不能でした。");
                    }else{
                        index = top[hash];
                        if(index != -1){
                            while(next[index] != -1){
                                index = next[index];
                            }
                        }
                        //insert位置に挿入。前はindex。index == -1ならリスト先頭。
                        del[insert] = 0;
                        dat_ex[insert] = str;
                        next[insert] = -1;
                        back[insert] = index;
                        if(index == -1){
                            top[hash] = insert;
                        }else{
                            next[index] = insert;
                        }
                    }
                }
            }else if(cmd == 1){
                //データ探索処理
                str = InputKey.inStr("探索文字列");
                ch = str.charAt(0);
                hash = ch % 10;
                if(str.equals(dat[hash])){
                    System.out.println(hash);
                }else{
                    index = top[hash];
                    while(index != -1){
                        if(dat_ex[index].equals(str)){
                            System.out.println("list"+index);
                            break;
                        }
                        index = next[index];
                    }
                    if(index == -1){
                        System.out.println("見つかりません。");
                    }
                }
            }else if(cmd == 2){
                //データ削除処理
                str = InputKey.inStr("削除文字列");
                ch = str.charAt(0);
                hash = ch % 10;
                if(str.equals(dat[hash])){
                    //ハッシュテーブルから削除(位置hash)
                    dat[hash] = null;
                    if(top[hash] != -1){
                        dat[hash] = dat_ex[top[hash]];
                        del[top[hash]] = 1;
                        top[hash] = next[top[hash]];
                        back[top[hash]] = -1;
                    }
                }else{
                    index = top[hash];
                    while(index != -1){
                        if(dat_ex[index].equals(str)){
                            //リストから削除(位置index)
                            del[index] = 1;
                            if(next[index] != -1){
                                back[next[index]] = back[index];
                            }
                            if(back[index] != -1){
                                next[back[index]] = next[index];
                            }else{
                                top[hash] = next[index];
                            }
                            break;
                        }
                        index = next[index];
                    }
                    if(index == -1){
                        System.out.println("見つかりません。");
                    }
                }
            }
        }
    }
}
