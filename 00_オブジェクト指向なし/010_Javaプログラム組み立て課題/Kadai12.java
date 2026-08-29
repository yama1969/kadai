//12@‰ÁZ‚¾‚¯‚ÅŠ|‚¯Z‚ğ‚·‚éƒvƒƒOƒ‰ƒ€
public class Kadai12{
	public static void main(String[] args){
		int a = 8;
		int b = 9;
		int result = 0;
		int i;
		System.out.print(a + " ~ " + b + "  ");
		for(i = 0; i < 32; i++){
			if((b & 1) != 0){
				result = result + a;
			}
			b = b >> 1;
			a = a << 1;
			if(b == 0){
				break;
			}
		}
		System.out.println(result);
	}
}
