public class StringListEx{
    //先頭のデータオブジェクト(実データの先頭を持たせるためのダミー)
    private StringDat start = new StringDat(null, null);
    //データ数
    private int count = 0;
    
    /**
     * リストにデータを追加する。
     */
    public boolean add(int index, String dat){
        if(index < 0){
            return false;  //挿入位置不正(前過ぎ)
        }
        
        StringDat before = search(index);
        if(before == null){
            return false;  //挿入位置不正(後ろ過ぎ)
        }
        
        StringDat newDat = new StringDat(dat, before.getNeighbor());
        before.setNeighbor(newDat);
        count++;
        return true;
    }
    
    /**
     * 指定位置のデータを得る
     */
    public String get(int index){
        if(index <= 0){
            return null;  //位置不正(前過ぎ)
        }
        StringDat dat = search(index); //後ろ過ぎのときはnull
        if(dat != null){
            return dat.getData();
        }
        return null;
    }
    
    /**
     * 指定位置のデータを削除する
     */
    public String remove(int index){
        if(index <= 0){
            return null;   //削除位置不正(前過ぎ)
        }
        
        StringDat before = search(index - 1);
        if(before == null){
            return null;   //削除位置不正(後ろ過ぎ。その前さえもない)
        }
        StringDat dat = before.getNeighbor();
        if(dat != null){   //位置が後ろ過ぎのときはnull
            before.setNeighbor(dat.getNeighbor());
            count--;
            return dat.getData();
        }
        return null;
    }
    
    /**
     * 現在のデータ数を得る
     */
    public int size(){
        return count;
    }
    
    /**
     * リストを配列で得る
     */
    public String[] toArray(){
        String[] array = new String[size()];
        
        StringDat dat = start;
        for(int i = 0; i < array.length; i++){
            dat = dat.getNeighbor();
            array[i] = dat.getData();
        }
        return array;
    }
    
    /**
     * 全データを消去する
     */
    public void clear(){
        start.setNeighbor(null);
        count = 0;
    }
    
    /**
     * 指定した位置のデータを得る
     */
    private StringDat search(int index){
        StringDat dat = start;
        int pos = 0;
        while(pos < index && dat != null){
            pos++;
            dat = dat.getNeighbor();
        }
        return dat;      //位置が後ろ過ぎのときはnull
    }
}
