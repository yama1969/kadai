/******************************************************************************
 int型データを保持するオブジェクト
 データ構造に使用するため、隣のデータへの参照を持つ
*******************************************************************************/
public class IntDat{
    private int data;        //このデータが持つ値
    private IntDat neighbor; //このデータの隣のデータ
    
    /**
     * コンストラクタ
     */
    public IntDat(int data, IntDat neighbor){
        this.data = data;
        this.neighbor = neighbor;
    }
    
    /**
     * 値を得る
     */
    public int getData(){
        return data;
    }
    
    /**
     * 隣のデータへの参照を得る
     */
    public IntDat getNeighbor(){
        return neighbor;
    }
    
    /**
     * 隣のデータへの参照をセットする
     */
    public void setNeighbor(IntDat neighbor){
        this.neighbor = neighbor;
    }
}
