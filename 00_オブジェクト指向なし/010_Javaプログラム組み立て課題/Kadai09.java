//09　0から9の範囲の乱数50個の中から最初の5を探すプログラム
public class Kadai09{
	public static void main(String[] args){
		int[] data = new int[50];
		int i;
		for(i = 0; i < 50; i++){
			data[i] = (int)(Math.random() * 10);
			System.out.println(data[i]);
		}
		for(i = 0; i < 50; i++){
			if(data[i] == 5){
				System.out.println("5は" + (i + 1) + "番目にあります。");
				break;
			}
		}
		if(i == 50){
			System.out.println("5は見つかりませんでした。");
		}
	}
}
