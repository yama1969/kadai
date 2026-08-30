public class ObjectList{
    //先頭のデータオブジェクト(実データの先頭を持たせるためのダミー)
    private ObjectDat start = new ObjectDat(null, null);
    //データ数
    private int count = 0;
    
    /**
     * リストにデータを追加する。
     */
    public boolean add(int index, Object dat){
        if(index < 0){
            return false;  //挿入位置不正(前過ぎ)
        }
        
        ObjectDat before = search(index);
        if(before == null){
            return false;  //挿入位置不正(後ろ過ぎ)
        }
        
        ObjectDat newDat = new ObjectDat(dat, before.getNeighbor());
        before.setNeighbor(newDat);
        count++;
        return true;
    }
    
    /**
     * 指定位置のデータを得る
     */
    public Object get(int index){
        if(index <= 0){
            return null;  //位置不正(前過ぎ)
        }
        ObjectDat dat = search(index); //後ろ過ぎのときはnull
        if(dat != null){
            return dat.getData();
        }
        return null;
    }
    
    /**
     * 指定位置のデータを削除する
     */
    public Object remove(int index){
        if(index <= 0){
            return null;   //削除位置不正(前過ぎ)
        }
        
        ObjectDat before = search(index - 1);
        if(before == null){
            return null;   //削除位置不正(後ろ過ぎ。その前さえもない)
        }
        ObjectDat dat = before.getNeighbor();
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
    public Object[] toArray(){
        Object[] array = new Object[size()];
        
        ObjectDat dat = start;
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
    private ObjectDat search(int index){
        ObjectDat dat = start;
        int pos = 0;
        while(pos < index && dat != null){
            pos++;
            dat = dat.getNeighbor();
        }
        return dat;      //位置が後ろ過ぎのときはnull
    }
}
