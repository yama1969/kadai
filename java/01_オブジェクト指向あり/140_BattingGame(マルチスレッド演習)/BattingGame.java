/******************************************************************************
 * バッティングゲームのスタータークラス
 ******************************************************************************/
public class BattingGame{
    public static void main(String[] args){
        Batter batter = new Batter();
        Pitcher pitcher = new Pitcher();
        new Referee(pitcher, batter).playball();
        System.exit(0);
    }
}
