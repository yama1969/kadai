//ロボットの手
public class RobotHand implements Hand{	//implements=実現する
	private String name;		//名前
	private int kind;			//手の種類 0:グー, 1:チョキ, 2:パー
	
	//コンストラクタ
	public RobotHand(String name){
		this.name = name;
	}
	
	//手を変える
	@Override
	public void changeKind(){
		kind = (int)(Math.random() * 3);
	}
	
	//勝敗判定
	@Override
	public Hand judge(Hand aite){
		int result = (this.kind + 3 - aite.getKind()) % 3;	//0:あいこ, 1:相手勝利, 2:自分勝利
		
		switch(result){
		case 0:
			return null;
		case 1:
			return aite;
		}
		return this;
	}
	
	//名前を得る
	@Override
	public String getName(){
		return name;
	}
	
	//手の種類を得る
	@Override
	public int getKind(){
		return kind;
	}
}
