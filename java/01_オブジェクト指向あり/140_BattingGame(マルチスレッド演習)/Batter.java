import java.io.IOException;
import java.io.Console;

/******************************************************************************
 * バッティングゲームのバッタークラス。
 * 任意の時刻にスイングする。
 ******************************************************************************/
public class Batter extends Thread{
    
    private long swing_time = 0L;       //スイング時刻(ms)
    private boolean run = true;         //実行フラグ。実行中はtrue
    
    /**************************************************************************
     * バッティング開始
     */
    @Override
    public void run(){
        try{
            int buf_s = System.in.available();
            int buf_e = buf_s;
            while(run){
                do{
                    buf_e = System.in.available();
                }while(buf_s == buf_e && run);
                if(run){
                    swing_time = System.currentTimeMillis();
                    buf_s = buf_e;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**************************************************************************
     * スイング時刻を得る
     * @return スイング時刻[ms]
     */
    public long getSwingTime(){
        return swing_time;
    }
    
    /**************************************************************************
     * バッティング終了。再開はできない。
     */
    public void stopBatting(){
        run = false;
        Console con = System.console();
        try{
            while(System.in.available() != 0){
                con.readPassword();
            }
        }catch(IOException e){
        }
    }
}
