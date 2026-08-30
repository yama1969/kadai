public class ObjectStack{
    private ObjectDat topData = null;  //最上データへの参照
    private int count = 0;             //データ個数
    
    public int push(Object dat){
        ObjectDat newData = new ObjectDat(dat, topData);
        topData = newData;
        count++;
        return count;
    }
    
    public Object pop(){
        if(count <= 0){
            return null;
        }
        ObjectDat popData = topData;
        topData = popData.getNeighbor();
        count--;
        return popData.getData();
    }
    
    public Object get(){
        if(count <= 0){
            return null;
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
