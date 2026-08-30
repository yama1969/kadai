/******************************************************************************
 Object型データを保持するオブジェクト
 データ構造に使用するため、隣のデータへの参照を持つ
*******************************************************************************/
public class ObjectDat{
    private Object data;        //このデータが持つ値
    private ObjectDat neighbor; //このデータの隣のデータ
    
    /**
     * コンストラクタ
     */
    public ObjectDat(Object data, ObjectDat neighbor){
        this.data = data;
        this.neighbor = neighbor;
    }
    
    /**
     * 値を得る
     */
    public Object getData(){
        return data;
    }
    
    /**
     * 隣のデータへの参照を得る
     */
    public ObjectDat getNeighbor(){
        return neighbor;
    }
    
    /**
     * 隣のデータへの参照をセットする
     */
    public void setNeighbor(ObjectDat neighbor){
        this.neighbor = neighbor;
    }
}
