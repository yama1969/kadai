public class Controller{
	public static void main(String[] args){
		
		//Hand h = new Hand();	//abstractクラスはnewできない
		
		//コマンドライン引数 rr:ロボット同士, ru:ロボットと人間, その他:人間同士
		Hand ha = null;	//Aさんの手
		Hand hb = null;	//Bさんの手
		
		switch(args[0]){
		case "rr":
			ha = new RobotHand("Aさん");
			hb = new RobotHand("Bさん");
			break;
		case "ru":
			ha = new UserHand("Aさん");
			hb = new RobotHand("Bさん");
			break;
		default:
			ha = new UserHand("Aさん");
			hb = new UserHand("Bさん");
		}
		
		//手を変える
		ha.changeKind();
		hb.changeKind();
		
		//手を表示
		System.out.println(ha.getName() + ":" + ha.getKind());
		System.out.println(hb.getName() + ":" + hb.getKind());
		
		//判定
		Hand winner = ha.judge(hb);
		
		//結果表示
		if(winner == null){
			System.out.println("あいこです。");
		}else{
			System.out.println(winner.getName() + "の勝ち。");
		}
	}
}
