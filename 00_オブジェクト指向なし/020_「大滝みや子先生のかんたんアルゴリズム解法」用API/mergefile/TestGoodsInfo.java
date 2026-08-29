public class TestGoodsInfo{
	public static void main(String[] args){
		String[] fields = null;
		
		while((fields = GoodsInfo.readRecord("fileA.csv")) != null){
			GoodsInfo.writeRecord("fileC.csv", fields);
		}
		while((fields = GoodsInfo.readRecord("fileB.csv")) != null){
			GoodsInfo.writeRecord("fileC.csv", fields);
		}
	}
}
