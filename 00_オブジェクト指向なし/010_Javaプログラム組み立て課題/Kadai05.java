//05数値を1から15まで表示するプログラム
public class Kadai05{
	public static void main(String[] args){
		int[] data = new int[15];
		int i;
		for(i = 0; i < 15; i++){
			data[i] = i + 1;
		}
		for(i = 0; i < 15; i++){
			System.out.println(data[i]);
		}
	}
}
