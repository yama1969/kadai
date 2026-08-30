/**
かけ算ゲーム用のMondaiオブジェクトとRankingオブジェクトを組み合わせたGameType
*/
public class MultiGame extends GameType{
    //-----コンストラクタ-------------------------------------------------------
    public MultiGame(){
        super(new MultiMondai(), new Ranking("かけ算"));
    }
}
