import java.util.Scanner;

//ユーザの手
public class UserHand implements Hand{
	private String name;	//手の名前
	private int kind;		//手の種類
	
	//コンストラクタ
	public UserHand(String name){
		this.name = name;
	}
	
	//手を変える
	@Override
	public void changeKind(){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("手(0:グー, 1:チョキ, 2:パー) > ");
		kind = sc.nextInt();
	}
	
	//勝敗判定
	@Override
	public Hand judge(Hand aite){
		int result = (kind + 3 - aite.getKind()) % 3;	//0:あいこ, 1:相手勝利, 2:自分勝利
		
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
