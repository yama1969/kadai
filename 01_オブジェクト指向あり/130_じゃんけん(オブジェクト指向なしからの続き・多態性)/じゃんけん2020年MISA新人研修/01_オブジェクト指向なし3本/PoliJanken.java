class PoliJanken{
	/**************************************************************************
	* 多人数じゃんけん
	**************************************************************************/
	public static void main(String[] args){
		int num = 10;
		
		if(args.length > 0){
			try{
				num = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
			}
		}
		
		String[] hand = {"グー", "チョキ", "パー"};
		
		//手の種類の決定
		int[] kind = new int[num];
		for(int i = 0; i < kind.length; i++){
			kind[i] = (int)(Math.random() * 3);
		}
		
		//手の表示
		for(int i = 0; i < kind.length; i++){
			System.out.println("No." + (i + 1) + " " + hand[kind[i]]);
		}
		
		//勝敗判定
		int result = judge(kind);
		
		//勝者表示
		if(result < 0){
			System.out.println("あいこです。");
		}else{
			System.out.println("勝者");
			for(int i = 0; i < kind.length; i++){
				if(kind[i] == result){
					System.out.println("No." + (i + 1));
				}
			}
		}
	}
	
	/**************************************************************************
	* 勝敗判定
	* @param kind 出した手の配列
	* @return 勝ちの手 -1:あいこ, 0:グー, 1:チョキ, 2:パー
	**************************************************************************/
	static int judge(int[] kind){
		int[] hand = new int[3]; //出した人がいた場合1 添字0:グー, 1:チョキ, 2:パー
		
		//種類ごとの出した人の調査
		for(int k : kind){
			hand[k] = 1;
		}
		
		//判定
		int result = hand[0] + hand[1] * 2 + hand[2] * 4;
		
		switch(result){
		case 3://グーチョキ
			return 0;
		case 5://グーパー
			return 2;
		case 6://チョキパー
			return 1;
		}
		return -1;
	}
}
