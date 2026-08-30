/******************************************************************************
 * バッティングゲームの審判をするクラス。
 * ゲーム進行も担う。
 ******************************************************************************/
public class Referee{
    private Pitcher pitcher = null;       //ピッチャー
    private Batter batter = null;       //バッター
    
    /**************************************************************************
     * コンストラクタ。ピッチャーとバッターをセットする。
     * @param p ピッチャーオブジェクト
     * @param b バッターオブジェクト
     */
    public Referee(Pitcher p, Batter b){
        pitcher = p;
        batter = b;
    }
    
    /**************************************************************************
     * ゲーム開始
     */
    public void playball(){
        if(pitcher == null || batter == null){
            System.out.println("ピッチャーとバッターが揃っていないので、ゲーム開始できません。");
            return;
        }
        
        batter.start();
        
        pitcher.throwBall();
        
        long diff = Math.abs(batter.getSwingTime() - pitcher.getStrikeTime());
        if(diff < 100L){
            System.out.println("ヒット！");
        }else{
            System.out.println("ストライーク！");
        }
        
        batter.stopBatting();
    }
}
