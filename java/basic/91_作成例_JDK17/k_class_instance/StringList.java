package k_class_instance;
public class StringList{
    private String[] list;
    private int[] next;
    private boolean[] use;
    
    /**
     * コンストラクタ。配列に初期値を設定する。
     */
    public StringList(int size){
        list = new String[size + 1];
        next = new int[size + 1];
        use = new boolean[size + 1];
        
        next[0] = -1;    //先頭データなし
        use[0] = true;   //配列の先頭は使用中
    }
    
    /**
     * リストにデータを追加する。
     */
    public boolean add(int index, String dat){
        if(index < 0){
            return false;  //挿入位置不正
        }
        
        //挿入前要素を探す
        int before = 0;
        for(int i = 0; i < index && before != -1; i++){
            before = next[before];
        }
        if(before < 0){
            return false;   //挿入位置不正
        }else{
            //挿入要素を探す
            int ins = 0;
            for(ins = 0; ins < use.length && use[ins]; ins++){
            }
            if(ins == use.length){
                return false;  //空きなし
            }else{
                //挿入
                list[ins] = dat;
                use[ins] = true;
                next[ins] = next[before];
                next[before] = ins;
            }
        }
        return true;
    }
    
    /**
     * 指定位置のデータを得る
     */
    public String get(int index){
        int pos = 0;
        for(int i = 0; i < index && pos != -1; i++){
            pos = next[pos];
        }
        if(pos < 0){
            return null;
        }
        return list[pos];
    }
    
    /**
     * 指定位置のデータを削除する
     */
    public String remove(int index){
        if(index <= 0){
            return null;   //削除位置不正
        }
        
        String dat = null;
        //削除前要素を探す
        int before = 0;
        for(int i = 0; i < index - 1 && before != -1; i++){
            before = next[before];
        }
        if(before < 0){
            return null; //削除位置不正
        }else{
            //削除要素を探す
            int del = next[before];
            if(del < 0){
                return null; //削除位置不正
            }else{
                //削除
                dat = list[del];
                use[del] = false;
                next[before] = next[del];
            }
        }
        return dat;
    }
    
    /**
     * 現在のデータ数を得る
     */
    public int size(){
        int size = 0;
        int pos = 0;
        while(next[pos] != -1){
            size++;
            pos = next[pos];
        }
        return size;
    }
    
    /**
     * リストを配列で得る
     */
    public String[] toArray(){
        int size = size();
        String[] array = new String[size];
        
        int pos = next[0];
        for(int i = 0; i < list.length && pos != -1; i++){
            array[i] = list[pos];
            pos = next[pos];
        }
        return array;
    }
    
    /**
     * 全データを消去する
     */
    public void clear(){
        for(int i = 1; i < use.length; i++){
            use[i] = false;
        }
        next[0] = -1;
    }
}
