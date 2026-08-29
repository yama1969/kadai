public class IntQueueEx{
    private IntDat start = null; //先頭のデータオブジェクト
    private IntDat end = null;   //最後尾のデータオブジェクト
    private int count = 0;       //データ数
    
    /**
     * キューにデータを入れる
     */
    public int enqueue(int dat){
        IntDat newData = new IntDat(dat, null);
        if(end != null){
            end.setNeighbor(newData);
        }else{
            start = newData;
        }
        end = newData;
        count++;
        return count;
    }
    
    /**
     * キューからデータを取り出す
     */
    public int dequeue(){
        if(start == null){
            return Integer.MIN_VALUE;
        }
        int dat = start.getData();
        count--;
        start = start.getNeighbor();
        if(start == null){
            end = null;
        }
        return dat;
    }
    
    /**
     * 次にキューから取り出される値を得る
     */
    public int get(){
        if(start == null){
            return Integer.MIN_VALUE;
        }
        return start.getData();
    }
    
    /**
     * キューに格納されている値の数を得る
     */
    public int getCount(){
        return count;
    }
    
    /**
     * キューの値をクリアする
     */
    public void clear(){
        start = null;
        end = null;
        count = 0;
    }
}
