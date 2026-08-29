package k_class_instance;
/******************************************************************************
 String型データを保持するオブジェクト
 データ構造に使用するため、隣のデータへの参照を持つ
*******************************************************************************/
public class StringDat{
    private String data;     //このデータが持つ値
    private StringDat neighbor; //このデータの隣のデータ
    
    /**
     * コンストラクタ
     */
    public StringDat(String data, StringDat neighbor){
        this.data = data;
        this.neighbor = neighbor;
    }
    
    /**
     * 値を得る
     */
    public String getData(){
        return data;
    }
    
    /**
     * 隣のデータへの参照を得る
     */
    public StringDat getNeighbor(){
        return neighbor;
    }
    
    /**
     * 隣のデータへの参照をセットする
     */
    public void setNeighbor(StringDat neighbor){
        this.neighbor = neighbor;
    }
}
