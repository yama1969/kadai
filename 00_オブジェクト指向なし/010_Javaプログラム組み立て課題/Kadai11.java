//11@0‚©‚ç1000‚Ì”ÍˆÍ‚Ì—”10ŒÂ‚ğ¸‡‚É•À‚×‘Ö‚¦‚éƒvƒƒOƒ‰ƒ€
public class Kadai11{
	public static void main(String[] args){
		int[] data = new int[10];
		int i, j, min, w;
		for(i = 0; i < 10; i++){
			data[i] = (int)(Math.random() * 1001);
			System.out.println(data[i]);
		}
		System.out.println();
		for(i = 0; i < 9; i++){
			min = i;
			for(j = i + 1; j < 10; j++){
				if(data[min] > data[j]){
					min = j;
				}
			}
			w = data[i];
			data[i] = data[min];
			data[min] = w;
		}
		for(i = 0; i < 10; i++){
			System.out.println(data[i]);
		}
	}
}
