class Threejanken{
	public static void main(String[] args){
		//手の名前と種類の名前
		String[] name = {"Ａ", "Ｂ", "Ｃ"};
		String[] hand = {"グー", "チョキ", "パー"};
		
		//3人の手を決める
		int[] kind = new int[3];
		for(int i = 0; i < kind.length; i++){
			kind[i] = (int)(Math.random() * 3);
		}
		
		//各人の手の表示
		for(int i = 0; i < kind.length; i++){
			System.out.println(name[i] + "は" + hand[kind[i]] + "を出しました。");
		}
		
		//勝敗判定
		int result = judge(kind[0], kind[1], kind[2]);
		
		//結果表示
		if(result == 0){
			System.out.println("あいこです。");
		}else{
			String winner = "";
			int bit = 1;
			for(int i = 0; i < name.length; i++){
				if((result & bit) != 0){
					winner += name[i];
				}
				bit *= 2;
			}
			System.out.println(winner + "の勝ち！");
		}
	}
	
	//勝敗判定3人
	//@return 勝者のbitを立てた整数 bit1:A, bit2:B, bit3:C
	static int judge(int a, int b, int c){
		int[][][] result = {
			{//A:グー
				//C:  グー, チョキ,   パー
				   {     0,      3,      4},//B:グー
				   {     5,      1,      0},//B:チョキ
				   {     2,      0,      6} //B:パー
			},
			{//A:チョキ
				//C:  グー, チョキ,   パー
				   {     6,      2,      0},//B:グー
				   {     4,      0,      3},//B:チョキ
				   {     0,      5,      1} //B:パー
			},
			{//A:パー
				//C:  グー, チョキ,   パー
				   {     1,      0,      5},//B:グー
				   {     0,      6,      2},//B:チョキ
				   {     3,      4,      0} //B:パー
			}
		};
		return result[a][b][c];
	}
}
