package k_class_instance;
public class IntStackEx{
    private IntDat topData = null;  //最上データへの参照
    private int count = 0;          //データ個数
    
    public int push(int dat){
        IntDat newData = new IntDat(dat, topData);
        topData = newData;
        count++;
        return count;
    }
    
    public int pop(){
        if(count <= 0){
            return Integer.MIN_VALUE;
        }
        IntDat popData = topData;
        topData = popData.getNeighbor();
        count--;
        return popData.getData();
    }
    
    public int get(){
        if(count <= 0){
            return Integer.MIN_VALUE;
        }
        return topData.getData();
    }
    
    public int getCount(){
        return count;
    }
    
    public void clear(){
        topData = null;
        count = 0;
    }
}
