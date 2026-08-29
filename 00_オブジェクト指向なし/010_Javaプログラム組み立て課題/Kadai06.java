//06”’l‚ğ1‚©‚ç10000‚Ü‚Å‰ÁZ‚·‚éƒvƒƒOƒ‰ƒ€
public class Kadai06{
	public static void main(String[] args){
		int[] data = new int[10000];
		int i;
		int result = 0;
		for(i = 0; i < 10000; i++){
			data[i] = i + 1;
		}
		for(i = 0; i < 10000; i++){
			result = result + data[i];
		}
		System.out.println("1‚©‚ç10000‚Ü‚Å‘«‚·‚Æ " + result);
	}
}
