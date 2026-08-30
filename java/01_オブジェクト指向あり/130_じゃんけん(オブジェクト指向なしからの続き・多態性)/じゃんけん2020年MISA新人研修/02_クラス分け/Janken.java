//じゃんけんの手を表すクラス
class Hand{
	int kind;		//手の種類 0:グー, 1:チョキ, 2:パー
	String name;	//名前
	
	//コンストラクタ(オブジェクトの初期状態セット)
	Hand(String name){
		this.name = name;
		changeKind();
	}
	
	//種類の変更
	void changeKind(){
		kind = (int)(Math.random() * 3);
	}
	
	//勝敗判定(勝ったHandをreturn,あいこのときはnull)
	//static ・・・ クラスのメンバー(オブジェクト共通)
	static Hand judge(Hand a, Hand b){
		Hand[][] shouhai = {
			{null, a   ,    b},
			{b   , null,    a},
			{a   , b   , null}
		};
		return shouhai[a.kind][b.kind];
	}
}

//画面表示を担うクラス
class UserInterface{
	//手の表示
	void showKind(Hand hand){
		String[] te = {"グー", "チョキ", "パー"};
		
		System.out.println(hand.name + "は" + te[hand.kind] + "を出しました。");
	}
	
	//勝敗表示
	void showResult(Hand winner){
		if(winner != null){
			System.out.println(winner.name + "の勝ちです。");
		}else{
			System.out.println("あいこです。");
		}
	}
}

//流れを制御するクラス
class Controller{
	//全体の順序制御
	public static void main(String[] args){
		Hand a = new Hand("Aさん");	//Aさんの手オブジェクト生成
		Hand b = new Hand("Bさん");	//Bさんの手オブジェクト生成
		
		a.changeKind();			//Aさんの手の種類変更
		b.changeKind();			//Bさんの手の種類変更
		
		UserInterface ui = new UserInterface();	//ユーザインターフェースオブジェクト生成
		ui.showKind(a);//Aさんの手の表示
		ui.showKind(b);//Bさんの手の表示
		
		Hand winner = Hand.judge(a,b);//勝敗判定
		
		ui.showResult(winner);	//勝敗表示
	}
}
