/**
足し算ゲーム用のMondaiオブジェクトとRankingオブジェクトを組み合わせたGameType
*/
public class AddGame extends GameType{
    //-----コンストラクタ-------------------------------------------------------
    public AddGame(){
        super(new AddMondai(), new Ranking("足し算"));
    }
}
