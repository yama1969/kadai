//10　0から1000の範囲の乱数50個の中から最小値を探すプログラム
public class Kadai10{
	public static void main(String[] args){
		int[] data = new int[50];
		int i, min;
		for(i = 0; i < 50; i++){
			data[i] = (int)(Math.random() * 1001);
			System.out.println(data[i]);
		}
		min = data[0];
		for(i = 1; i < 50; i++){
			if(min > data[i]){
				min = data[i];
			}
		}
		System.out.println("最小値は" + min + "です。");
	}
}
