/*******************************************************************************
 * 機雷ボタン情報                                                              *
 *******************************************************************************/
class SweepButtonInfo{
    private SweepButton[][] sb_array;                                           //機雷ボタン配列
    
    private int myMineNum;                                                      //自機雷数
    private int myMineMax;                                                      //自機雷最大数
    
    private Mode mode;                                                          //ゲーム状態
    static enum Mode{
        INIT,                                                                   //初期状態
        POSTING,                                                                //自機雷配置中
        WAIT,                                                                   //相手機雷配置待ち
        GAME_ME,                                                                //ゲーム中 自分の番
        GAME_MATE                                                               //         相手の番
    };
    
    private boolean b_check;                                                    //機雷チェック(true:チェック中)
    private boolean b_click;                                                    //クリックしたか(true:した)
    private int click_x;                                                        //クリックしたボタンのx座標
    private int click_y;                                                        //                    y座標
    
    /***************************************************************************
     * コンストラクタ
     */
    SweepButtonInfo(SweepButton[][] array, int max){
        if(max < 1){
            max = 1;
        }
        sb_array = array;
        myMineNum = 0;
        myMineMax = max;
        mode = Mode.INIT;
        b_check = false;
        clearClick();
    }
    
    /***************************************************************************
     * 自機雷数を増やす
     */
    synchronized int addMineNum(){
        return myMineNum++;
    }
    
    /***************************************************************************
     * 自機雷数を減らす
     */
    synchronized int subMineNum(){
        return myMineNum--;
    }
    
    /***************************************************************************
     * 自機雷数を得る
     */
    synchronized int getMineNum(){
        return myMineNum;
    }
    
    /***************************************************************************
     * ゲーム中クリック情報をクリア
     */
    synchronized void clearClick(){
        b_click = false;
        click_x = -1;
        click_y = -1;
        notifyAll();
    }
    
    /***************************************************************************
     * ゲーム中クリック情報をセット
     */
    synchronized boolean setClickInfo(int x, int y){
        if(b_click){                                                            //二重click防止
            return b_check;
        }
        b_click = true;
        click_x = x;
        click_y = y;
        notifyAll();
        return b_check;
    }
    
    /***************************************************************************
     * ゲーム中クリック情報を得る(クリックするまで待機)
     */
    synchronized MineInfo getClickInfo(){
        try{
            while(!b_click){
                wait();
            }
            int c = MineInfo.CLICK;
            if(b_check){
                c = MineInfo.CHECK;
            }
            MineInfo mi = new MineInfo(click_x, click_y, c);
            clearClick();
            return mi;
        }catch(InterruptedException e){
            return null;
        }
    }
    
    /***************************************************************************
     * 機雷ボタンを操作する(相手側の操作をするときに使う)
     */
    void push(MineInfo mi){
        if(mi == null){
            return;
        }
        int x = mi.getX();
        int y = mi.getY();
        int m = mi.getManipu();
        boolean ch = false;
        if(m == MineInfo.CHECK){
            ch = true;
        }
        try{
            sb_array[x][y].push(ch);
        }catch(ArrayIndexOutOfBoundsException e){
        }
    }
    
    /***************************************************************************
     * チェックモードを切り替える
     */
    synchronized boolean changeCheck(){
        if(b_click){                                                            //ゲームクリック状態では切り替えしない
            return b_check;
        }
        b_check = !b_check;
        return b_check;
    }
    
    /***************************************************************************
     * チェックモードを取得する
     */
    synchronized boolean getCheck(){
        return b_check;
    }
    
    /***************************************************************************
     * ゲーム状態(モード)を設定する
     */
    synchronized void setMode(Mode newMode){
        mode = newMode;
        if(mode.equals(Mode.GAME_ME)){                                          //ゲーム中自分の番ならクリック状況リセット
            clearClick();
        }
    }
    
    /***************************************************************************
     * ゲーム状態(モード)を取得する
     */
    synchronized Mode getMode(){
        return mode;
    }
    
    /***************************************************************************
     * 周りの機雷数を取得する
     */
    int getAroundMineNum(int x, int y){
        int count = 0;
        for(int j = y - 1; j < y + 2; j++){
            for(int i = x - 1; i < x + 2; i++){
                try{
                    SweepButton.Status s = sb_array[i][j].getStatus();
                    if(
                        s.equals(SweepButton.Status.MYMINE)  ||
                        s.equals(SweepButton.Status.MYCHECK) ||
                        s.equals(SweepButton.Status.MINE)    ||
                        s.equals(SweepButton.Status.CHECKED) ||
                        s.equals(SweepButton.Status.EXPLODE)
                    ){
                        count++;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                }
            }
        }
        return count;
    }
    
    /***************************************************************************
     * 全機雷がチェックされたかを調べる(true:チェック完了)
     */
    boolean isAllCheck(){
        for(int i = 0; i < sb_array.length; i++){
            for(int j = 0; j < sb_array[i].length; j++){
                SweepButton.Status s = sb_array[i][j].getStatus();
                if(
                    s.equals(SweepButton.Status.MYMINE) ||
                    s.equals(SweepButton.Status.MINE)
                ){
                    return false;
                }
            }
        }
        return true;
    }
    
    /***************************************************************************
     * 全機雷を開示する
     */
    void openAllMine(){
        for(int x = 0; x < sb_array.length; x++){
            for(int y = 0; y < sb_array[x].length; y++){
                switch(sb_array[x][y].getStatus()){
                    case CLOSE:                                                 //機雷なし・未チェック
                    case OPEN:                                                  //機雷なし
                    case BADCHECK:                                              //機雷なし・チェック済み
                    case MYMINE:                                                //自機雷あり・未チェック
                    case MYCHECK:                                               //自機雷あり・チェック済み
                        break;
                    case MINE:                                                  //機雷あり・未チェック
                        sb_array[x][y].setStatus(SweepButton.Status.MYMINE);
                        addMineNum();
                        break;
                    case CHECKED:                                               //機雷あり・チェック済み
                        sb_array[x][y].setStatus(SweepButton.Status.MYCHECK);
                        addMineNum();
                        break;
                    case EXPLODE:                                               //機雷爆発
                }
            }
        }
    }
    
    /***************************************************************************
     * 全ボタンをリセットする
     */
    void reset(){
        for(int x = 0; x < sb_array.length; x++){
            for(int y = 0; y < sb_array[x].length; y++){
                sb_array[x][y].reset();
            }
        }
        myMineNum = 0;
        mode = Mode.INIT;
    }
}
