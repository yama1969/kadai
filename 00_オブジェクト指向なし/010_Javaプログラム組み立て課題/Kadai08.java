//08　0から100の範囲の乱数を20個表示するプログラム
public class Kadai08{
	public static void main(String[] args){
		int[] data = new int[20];
		int i;
		for(i = 0; i < 20; i++){
			data[i] = (int)(Math.random() * 101);
			System.out.println(data[i]);
		}
	}
}
