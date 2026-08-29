package l_extends;
public class ObjectQueue{
    private ObjectDat start = null; //先頭のデータオブジェクト
    private ObjectDat end = null;   //最後尾のデータオブジェクト
    private int count = 0;          //データ数
    
    /**
     * キューにデータを入れる
     */
    public int enqueue(Object dat){
        ObjectDat newData = new ObjectDat(dat, null);
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
    public Object dequeue(){
        if(start == null){
            return null;
        }
        Object dat = start.getData();
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
    public Object get(){
        if(start == null){
            return null;
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
