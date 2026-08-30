public class HashTable{
    public static void main(String[] args){
        String[] dat  = new String[10];
        int      i;
        String   str;
        char     ch;
        int      hash;
        
        //初期化処理
        for(i = 0; i < 10; i++){
            dat[i] = null;
        }
        
        //データ格納処理
        System.out.println("データ格納します。");
        for(i = 0; i < 10; i++){
            str = InputKey.inStr("文字列データ");
            ch = str.charAt(0);
            hash = ch % 10;
            if(dat[hash] == null){
                dat[hash] = str;
            }else{
                System.out.println("格納不能でした。");
            }
        }
        
        //データ探索処理
        System.out.println();
        System.out.println("データ探索します。");
        while(true){
            str = InputKey.inStr("探索文字列");
            ch = str.charAt(0);
            hash = ch % 10;
            if(dat[hash].equals(str)){
                System.out.println(hash);
            }else{
                System.out.println("見つかりません。");
            }
        }
    }
}
