package k_class_instance;
public class IntQueue{
    private int[] queue;
    private int start = 0;
    private int count = 0;
    
    /**
     * コンストラクタ
     */
    public IntQueue(int size){
        if(size < 0){
            size = 0;
        }
        queue = new int[size];
    }
    
    /**
     * キューにデータを入れる
     */
    public int enqueue(int dat){
        if(count >= queue.length){
            return -1;
        }
        queue[(start + count) % queue.length] = dat;
        count++;
        return count;
    }
    
    /**
     * キューからデータを取り出す
     */
    public int dequeue(){
        if(count <= 0){
            return Integer.MIN_VALUE;
        }
        int dat = queue[start];
        start = ++start % queue.length;
        count--;
        return dat;
    }
    
    /**
     * 次にキューから取り出される値を得る
     */
    public int get(){
        if(count <= 0){
            return Integer.MIN_VALUE;
        }
        return queue[start];
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
        start = 0;
        count = 0;
    }
}
