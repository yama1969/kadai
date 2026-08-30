/**
引き算ゲーム用のMondaiオブジェクトとRankingオブジェクトを組み合わせたGameType
*/
public class SubGame extends GameType{
    //-----コンストラクタ-------------------------------------------------------
    public SubGame(){
        super(new SubMondai(), new Ranking("ひき算"));
    }
}
