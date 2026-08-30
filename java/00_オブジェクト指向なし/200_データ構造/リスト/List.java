public class List{
    public static void main(String[] args){
        String[] dat  = new String[11];
        int[]    next = new int[11];
        int[]    back = new int[11];
        int[]    del  = new int[11];
        String   str;
        int      index;
        int      insert;
        
        //初期化処理
        next[0] = 0;
        back[0] = 0;
        for(index = 1; index < 11; index++){
            del[index] = 1;
        }
        
        while(true){
            do{
                //データ表示処理
                System.out.println();
                for(index = next[0]; index != 0; index = next[index]){
                    System.out.println(dat[index]);
                }
                System.out.println();
                
                //処理位置入力
                index = 0;
                str = InputKey.inStr("操作位置を指定してください。(先頭はstart)");
                if(!str.equals("start")){
                    do{
                        index = next[index];
                        if(index == 0){
                            System.out.println("該当データありません。");
                            break;
                        }
                    }while(!str.equals(dat[index]));
                }
            }while(index == 0 && !str.equals("start"));
            
            //処理内容入力
            str = InputKey.inStr("挿入データを入力してください。(削除はdel)");
            if(str.equals("del")){
                //削除処理
                if(index != 0){
                    del[index] = 1;
                    next[back[index]] = next[index];
                    back[next[index]] = back[index];
                }
            }else{
                //挿入処理
                insert = 1;
                while(true){
                    if(del[insert] != 0){
                        del[insert] = 0;
                        dat[insert] = str;
                        back[insert] = index;
                        next[insert] = next[index];
                        next[index] = insert;
                        back[next[insert]] = insert;
                        break;
                    }
                    insert++;
                    if(insert >= 11){
                        System.out.println("これ以上挿入できません。");
                        break;
                    }
                }
            }
        }
    }
}
