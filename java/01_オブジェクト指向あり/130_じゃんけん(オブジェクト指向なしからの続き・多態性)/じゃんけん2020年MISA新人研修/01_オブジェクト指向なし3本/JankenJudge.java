class JankenJudge{
	public static void main(String[] args){
		String[] te = {"グー", "チョキ", "パー"};
		
		//じゃんけんの手を決める
		int a = (int)(Math.random() * 3);
		int b = (int)(Math.random() * 3);
		
		//手の表示
		System.out.println("Aさん：" + te[a]);
		System.out.println("Bさん：" + te[b]);
		
		//勝敗判定
		int result = (a + 3 - b) % 3;
		
		//結果表示
		switch(result){
		case 0:
			System.out.println("あいこです。");
			break;
		case 1:
			System.out.println("Bさんの勝ち！");
			break;
		case 2:
			System.out.println("Aさんの勝ち！");
			break;
		default:
			System.out.println("判定ミス");
		}
	}
}
