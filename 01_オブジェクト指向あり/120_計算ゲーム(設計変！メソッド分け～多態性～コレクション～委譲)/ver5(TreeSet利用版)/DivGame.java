/**
わり算ゲーム用のMondaiオブジェクトとRankingオブジェクトを組み合わせたGameType
*/
public class DivGame extends GameType{
    //-----コンストラクタ-------------------------------------------------------
    public DivGame(){
        super(new DivMondai(), new Ranking("わり算"));
    }
}
