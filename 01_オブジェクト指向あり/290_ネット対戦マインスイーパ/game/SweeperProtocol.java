import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

/*******************************************************************************
 * サーバとの通信プロトコル                                                    *
 *******************************************************************************/
class SweeperProtocol{
    //定数
    public static final int PORT     = 7777;                                    //サーバとの通信ポート番号
    public static final int TIMEOUT  = 3000;                                    //ソケットタイムアウト[ms]
    public static final int MATETIME = 60000;                                   //対戦相手待ち時間[ms]
    public static final int GAMETIME = 0;                                       //相手操作待ち時間[ms](0:無限)
    
    //ネットワーク
    private Socket          sock;                                               //サーバとの通信ソケット
    private BufferedReader  so_reader;                                          //ソケット受信ストリーム
    private PrintWriter     so_writer;                                          //ソケット送信ストリーム
    
    //通信で得られる情報
    private String              playmate;                                       //対戦相手名
    private ArrayList<MineInfo> al_mine;                                        //対戦相手機雷位置情報配列
    private long                matetime;                                       //相手機雷位置情報受信時刻(先攻後攻決定に使用)
    private boolean             b_first;                                        //先攻フラグ(true:先攻)
    
    //通信状態
    private Status stat;
    public static enum Status{
        UNLOGIN,                                                                //ログイン前
        
        NORMAL,                                                                 //ログイン 正常
        UNKNOWN_HOST,                                                           //         サーバ名不明
        CONNECT_ERROR,                                                          //         接続失敗
        START_ERROR,                                                            //         通信開始失敗
        ACCOUNT_ERROR,                                                          //         認証失敗
        NO_PLAYMATE,                                                            //         対戦者無し
        
        MATEMINE,                                                               //機雷位置 相手受信済み
        MYMINE,                                                                 //         自分送信済み
        BOTHMINE,                                                               //         送受信済み
        MINE_ERROR,                                                             //         送受信失敗
        
        FIRST_ERROR,                                                            //先攻後攻 決定異常終了
        
        GAME_ME,                                                                //ゲーム中 自分の番
        GAME_MATE,                                                              //         相手の番
        GAME_ERROR                                                              //         異常状態
    };
    
    //勝敗状態
    public static enum Result{
        WIN,                                                                    //勝ち
        LOSE,                                                                   //負け
        DRAW                                                                    //引き分け
    }
    
    /***************************************************************************
     * コンストラクタ
     */
    public SweeperProtocol(){
        sock = null;
        so_reader = null;
        so_writer = null;
        playmate = null;
        al_mine = null;
        matetime = Long.MAX_VALUE;
        b_first = false;
        setStatus(Status.UNLOGIN);
    }
    
    /***************************************************************************
     * ステータスを設定する
     */
    private synchronized Status setStatus(Status newStat){
        this.stat = newStat;
        switch(stat){
            case NORMAL:                                                        //ログイン正常なら機雷位置受信開始
                new RecieveMine().start();
                break;
            case BOTHMINE:                                                      //機雷位置交換終了なら先攻確認
                new SendFirst().start();
                break;
            default:
        }
        notifyAll();
        return stat;
    }
    
    /***************************************************************************
     * ステータスを得る
     */
    public synchronized Status getStatus(){
        return stat;
    }
    
    /***************************************************************************
     * ステータスを比較する
     */
    public synchronized boolean checkStatus(Status isStat){
        return stat.equals(isStat);
    }
    
    /***************************************************************************
     * ログインする
     */
    public Status login(String user, String pass){
                                                                                //未ログインのときのみ実行
        if(!stat.equals(Status.UNLOGIN)){
            return null;
        }
                                                                                //サーバへ接続
/*
        try{
            sock = new Socket("サーバ名", PORT);
            sock.setSoTimeout(TIMEOUT);
        }catch(UnknownHostException ex){
            return setStatus(Status.UNKNOWN_HOST);
        }catch(IOException ex){
            return setStatus(Status.CONNECT_ERROR);
        }
                                                                                //受信・送信オブジェクト作成
        try{
            so_reader = new BufferedReader(
                new InputStreamReader(sock.getInputStream())
            );
            so_writer = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(sock.getOutputStream())
                )
            );
        }catch(IOException ex){
            return setStatus(Status.START_ERROR);
        }
                                                                                //サーバへログイン
        so_writer.println(user.trim());
        so_writer.println(pass.trim());
                                                                                //応答・対戦相手受信
        try{
            String res = so_reader.readLine();                                  //  応答受信
            if(res == null){
                return setStatus(Status.START_ERROR);
            }
            
            if(!res.toLowerCase().equals("ack")){
                return setStatus(Status.ACCOUNT_ERROR);
            }
            
            sock.setSoTimeout(MATETIME);
            res = so_reader.readLine();                                         //  対戦相手受信
            sock.setSoTimeout(TIMEOUT);
            if(res == null){
                return setStatus(Status.START_ERROR);
            }
            if(res.toLowerCase().equals("null")){
                return setStatus(Status.NO_PLAYMATE);
            }
            playmate = res;
        }catch(IOException e){
            return setStatus(Status.START_ERROR);
        }
        
        try{
            sock.setSoTimeout(GAMETIME);
        }catch(IOException e){
            return setStatus(Status.START_ERROR);
        }
        
        return setStatus(Status.NORMAL);
*/
        //test_coode↓----------------------------------------------------------
        if(user.equals("") || pass.equals("")){
            return setStatus(Status.ACCOUNT_ERROR);
        }
        try{
            Thread.sleep((long)(Math.random() * 20000.0) + 1L);
        }catch(InterruptedException e){
        }
        playmate = "山田";
        return setStatus(Status.NORMAL);
        //test_coode↑----------------------------------------------------------
    }
    
    /***************************************************************************
     * ログインによって得られた対戦相手名を取得
     */
    public String getPlaymate(){
        return playmate;
    }
    
    /***************************************************************************
     * 機雷位置を相手へ送信
     */
    public boolean sendMyMine(ArrayList<MineInfo> list){
                                                                                //ログイン正常か
                                                                                //相手機雷位置受信済みのときのみ実行
        synchronized(this){                                                     //  2回Statusをチェックするのでsynchronized
            if(!checkStatus(Status.NORMAL) && !checkStatus(Status.MATEMINE)){
                return false;
            }
        }
        
/*
        for(MineInfo mi : list){
            so_writer.println(mi.getX());
            so_writer.println(mi.getY());
        }
        so_writer.println("end");
        long mytime = System.currentTimeMillis();
        so_writer.println(mytime);
                                                                                //先攻後攻決定
        synchronized(this){                                                     //  matetimeを守るためsynchronized
            if(mytime < matetime){                                              //  相手が機雷位置をまだ送信していないなら、
                b_first = false;                                                //  matetimeは最大値
            }else{
                b_first = true;
            }
                                                                                //通信状態変更
            if(checkStatus(Status.MATEMINE)){                                   //  他の状態変更が割込まないようにsynchronized
                setStatus(Status.BOTHMINE);
            }else if(checkStatus(Status.NORMAL)){
                setStatus(Status.MYMINE);
            }else{
                setStatus(Status.MINE_ERROR);
                return false;
            }
        }
*/
        //test_coode↓----------------------------------------------------------
        b_first = false;
        if((int)(Math.random() * 2.0) == 0){
            b_first = true;
        }
        synchronized(this){
            if(checkStatus(Status.MATEMINE)){
                setStatus(Status.BOTHMINE);
            }else if(checkStatus(Status.NORMAL)){
                setStatus(Status.MYMINE);
            }else{
                setStatus(Status.MINE_ERROR);
                return false;
            }
        }
        //test_coode↑----------------------------------------------------------
        
        return true;
    }
    
    /***************************************************************************
     * 対戦相手機雷位置受信スレッド                                            *
     ***************************************************************************/
    private class RecieveMine extends Thread{
        @Override
        public void run(){
                                                                                //ログイン正常のときのみ実行
            if(!checkStatus(Status.NORMAL)){
                return;
            }
                                                                                //相手機雷位置受信
            al_mine = new ArrayList<MineInfo>();
/*
            try{
                while(true){
                    String str_x = so_reader.readLine();
                    if(str_x == null){
                        setStatus(Status.MINE_ERROR);
                        return;
                    }
                    if(str_x.equals("end")){
                        break;
                    }
                    int x = Integer.parseInt(str_x);
                    String str_y = so_reader.readLine();
                    if(str_y == null){
                        setStatus(Status.MINE_ERROR);
                        return;
                    }
                    int y = Integer.parseInt(str_y);
                    if(x < 0 || y < 0){
                        throw new NumberFormatException("under zero.");
                    }
                    al_mine.add(new MineInfo(x, y));
                }
            }catch(NumberFormatException e){
                setStatus(Status.MINE_ERROR);
                return;
            }catch(IOException e){
                setStatus(Status.MINE_ERROR);
                return;
            }
                                                                                //相手送信時刻受信
            try{
                matetime = Long.parseLong(so_reader.readLine());
            }catch(NumberFormatException e){
                setStatus(Status.MINE_ERROR);
                return;
            }catch(IOException e){
                setStatus(Status.MINE_ERROR);
                return;
            }
*/
        //test_coode↓----------------------------------------------------------
        try{
            Thread.sleep((long)(Math.random() * 15000) + 1L);
        }catch(InterruptedException e){
        }
        for(int i = 0; i < 5; i++){
            int x = (int)(Math.random() * 9);
            int y = (int)(Math.random() * 9);
            al_mine.add(new MineInfo(x, y));
        }
        //test_coode↑----------------------------------------------------------

                                                                                //通信状態変更
            synchronized(SweeperProtocol.this){
                if(checkStatus(Status.MYMINE)){
                    setStatus(Status.BOTHMINE);
                }else{
                    setStatus(Status.MATEMINE);
                }
            }
        }
    }
    
    /***************************************************************************
     * 対戦相手機雷位置を取得(未受信なら待機)
     */
    public synchronized ArrayList<MineInfo> getMineInfoArray(){
        while(true){
            if(checkStatus(Status.MYMINE)){                                     //自機雷のみ送信済みなら待機
                try{
                    wait();
                }catch(InterruptedException e){
                    setStatus(Status.MINE_ERROR);
                    return null;
                }
            }else if(                                                           //相互受信済みなら取得
                checkStatus(Status.BOTHMINE) ||
                checkStatus(Status.GAME_ME) ||
                checkStatus(Status.GAME_MATE)
            ){
                return al_mine;
            }else{                                                              //それ以外は異常
                setStatus(Status.MINE_ERROR);
                return null;
            }
        }
    }
    
    /***************************************************************************
     * 先攻後攻送受信スレッド                                                  *
     ***************************************************************************/
    private class SendFirst extends Thread{
        public void run(){
                                                                                //機雷位置送受信済みのときのみ実行
            if(!checkStatus(Status.BOTHMINE)){
                return;
            }
            
/*
            String fst = "second";
            if(b_first){
                fst = "first";
            }
            try{
                so_writer.println(fst);
                String str = so_reader.readLine();
                if(str == null){
                    setStatus(Status.FIRST_ERROR);
                    return;
                }
                if(str.equals(fst)){                                            //相手が自分と同じを主張したらエラー
                    setStatus(Status.FIRST_ERROR);
                    return;
                }
            }catch(IOException e){
                setStatus(Status.FIRST_ERROR);
                return;
            }
*/
                                                                                //状態変更
            if(b_first){
                setStatus(Status.GAME_ME);
            }else{
                setStatus(Status.GAME_MATE);
            }
            return;
        }
    }
    
    /***************************************************************************
     * 相手操作を受信
     */
    public MineInfo receiveMateManipu(){
                                                                                //相手の番のときのみ実行
        if(!checkStatus(Status.GAME_MATE)){
            return null;
        }
        
        MineInfo mi = null;
/*
        try{
                                                                                //x座標受信
            String str_x = so_reader.readLine();
            if(str_x == null){
                setStatus(Status.GAME_ERROR);
                return null;
            }
            int x = Integer.parseInt(str_x);
                                                                                //y座標受信
            String str_y = so_reader.readLine();
            if(str_y == null){
                setStatus(Status.GAME_ERROR);
                return null;
            }
            int y = Integer.parseInt(str_y);
                                                                                //操作受信
            String str_m = so_reader.readLine();
            if(str_m == null){
                setStatus(Status.GAME_ERROR);
                return null;
            }
            int mani = 0;
            if(str_m.equals("click")){
                mani = MineInfo.CLICK;
            }else if(str_m.equals("check")){
                mani = MineInfo.CLICK;
            }else{
                setStatus(Status.GAME_ERROR);
                return null;
            }
            
            mi = new MineInfo(x, y, mani);
            
        }catch(NumberFormatException e){
            setStatus(Status.GAME_ERROR);
            return null;
        }catch(IOException e){
            setStatus(Status.GAME_ERROR);
            return null;
        }
*/
        //test_coode↓----------------------------------------------------------
        try{
            Thread.sleep((long)(Math.random() * 2000.0) + 1000L);
        }catch(InterruptedException e){
        }
        
        int x = (int)(Math.random() * 9);
        int y = (int)(Math.random() * 9);
        mi = new MineInfo(x, y, MineInfo.CLICK);
        //test_coode↑----------------------------------------------------------
        
        setStatus(Status.GAME_ME);                                              //相手が終われば自分の番
        return mi;
    }
    
    /***************************************************************************
     * 自分操作を送信
     */
    public boolean sendMyManipu(MineInfo mi){
                                                                                //自分の番のときのみ実行
        if(!checkStatus(Status.GAME_ME)){
            return false;
        }
        
/*
        so_writer.println(mi.getX());                                           //x座標送信
        so_writer.println(mi.getY());                                           //y座標送信
        switch(mi.getManipu()){                                                 //操作送信
            case MineInfo.CHECK:
                so_writer.println("check");
                break;
            case MineInfo.CLICK:
            default:
                so_writer.println("click");
        }
*/
        setStatus(Status.GAME_MATE);                                            //自分が終われば相手の番
        return true;
    }
    
    /***************************************************************************
     * 終了確認
     */
    public boolean checkEnd(Result result){
                                                                                //ゲーム中のみ実行
        synchronized(this){
            if(!checkStatus(Status.GAME_ME) && !checkStatus(Status.GAME_MATE)){
                return false;
            }
        }
                                                                                //送受信文字列作成
/*
        String result_me = "lose";
        String result_mate = "lose";
        if(result.equals(Result.WIN)){
            result_me = "win";
        }else if(result.equals(Result.LOSE)){
            result_mate = "win";
        }else{
            result_me = "draw";
            result_mate = "draw";
        }
                                                                                //送信および受信
        so_writer.println(result_me);
        try{
            String str = so_reader.readLine();
            if(str == null){
                setStatus(Status.GAME_ERROR);
                return false;
            }
            if(!str.equals(result_mate)){
                setStatus(Status.GAME_ERROR);
                return false;
            }
        }catch(IOException e){
            setStatus(Status.GAME_ERROR);
            return false;
        }
*/
        
        return true;
    }
}
