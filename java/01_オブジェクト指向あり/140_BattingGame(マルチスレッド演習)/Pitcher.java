/******************************************************************************
 * バッティングゲームのピッチャークラス
 ******************************************************************************/
public class Pitcher{
    private long motion_time = 1000L;
    private int distance = 15;
    private long strike_time = 0L;
    
    /**************************************************************************
     * ボールを投げる
     */
    public void throwBall(){
        System.out.println("ピッチャー振りかぶって・・・");
        sleep(motion_time);
        System.out.println("投げました！");
        for(int i = 0; i < distance; i++){
            sleep(100);
            System.out.println("　　　　⑪　　　　");
        }
        strike_time = System.currentTimeMillis();
    }
    
    /**************************************************************************
     * ボールがベースを通過した時刻を得る
     * @return 通過時刻[ms]
     */
    public long getStrikeTime(){
        return strike_time;
    }
    
    /**************************************************************************
     * 動作休止メソッド
     * @param time 休止時間[ms]
     */
    private static void sleep(long time){
        try{
            Thread.sleep(time);
        }catch(InterruptedException e){
        }
    }
}
