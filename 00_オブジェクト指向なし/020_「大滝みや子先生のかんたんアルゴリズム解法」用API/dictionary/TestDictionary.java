public class TestDictionary{
	public static void main(String[] args){
		String[] eitan = new String[100];
		String[] yaku = new String[100];
		
		eitan[0] = "apple";
		yaku[0] = "‚è‚ñ‚²";
		eitan[1] = "bed";
		yaku[1] = "ƒxƒbƒh";
		eitan[2] = "chear";
		yaku[2] = "‚¢‚·";
		
		Dictionary.writeFile("dic.txt", eitan, yaku, 101);
		
		eitan = new String[100];
		yaku = new String[100];
		int n = Dictionary.readFile("dic.txt", eitan, yaku);
		for(int i = 0; i < n; i++){
			System.out.println(eitan[i] + " = " + yaku[i]);
		}
	}
}
