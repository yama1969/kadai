import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/*******************************************************************************
 * ネットワーク対戦マインスイーパです。<br>
 * お互いに配置した機雷を探し当てるゲームです。自分と対戦相手が交互にパネルを
 * 開いていきます。開いたパネルに自分や相手の機雷が存在したら、そのパネルを
 * 開いた人の負けです。<br>
 * 機雷があると確信できるパネルにはチェックを入れて下さい。自分と相手の機雷の
 * 全てにチェックを入れることができたら、最後の１つをチェックした人の勝ちです。
 * チェックのお手つきは負けとなります。<br>
 * <br>
 * 操作方法：<br>
 * <ol>
 *   <li>IDとパスワードを入れ、ログインボタンをクリックして下さい。
 *       ゲームサーバに接続し、対戦相手が適当に割り当てられます。</li>
 *   <li>次に自分の機雷を配置します。適当なパネルをクリックすると、そのパネルに
 *       機雷が配置されます。配置済みのパネルをクリックすると、機雷が除かれま
 *       す。配置する機雷の数は決まっています。</li>
 *   <li>機雷の配置が終わったら、スタートボタンをクリックして下さい。相手側の
 *       機雷配置を受信した後、ゲーム開始になります。</li>
 *   <li>手番は、機雷配置を遅く終えた方が先手になります。自分の手番になったら、
 *       適当なパネルを開くかチェックします。</li>
 *   <li>ゲーム中は、右上のボタンがチェックボタンになっています。<br>
 *       　[CHECK OFF]と表示　・・・　クリックしたパネルを開く<br>
 *       　[CHECK ON]と表示　・・・　クリックしたパネルをチェックする。<br>
 *       チェックのON/OFFはチェックボタンをクリックする度に切り替わります</li>
 *   <li>機雷を開いてしまうか、機雷のない場所をチェックするか、全ての機雷が
 *       チェックされるとゲーム終了です。終了すると機雷の全位置が表示されます。
 *       </li>
 *   <li>終了後、右上のリセットボタンをクリックすると、初期状態に戻ります。
 *       </li>
 * </ol>
 * 
 *******************************************************************************/
public class Sweeper{
    //各定数
    static final int SIZE_X = 9;                                                //ゲーム操作ボタン横個数
    static final int SIZE_Y = 9;                                                //ゲーム操作ボタン縦個数
    static final int NUM_MINE = 5;                                              //配置できる機雷数
    static final int WIN_WIDTH = 350;                                           //ウインドウ幅
    static final int WIN_HEIGHT = 417;                                          //ウインドウ高さ
    
    //サーバ通信
    private SweeperProtocol protocol;                                           //サーバとの通信プロトコル実装
    
    //GUI部品
    private JFrame          frame;                                              //メインフレーム
    
    private JPanel          pn_login;                                           //ログインパネル
    private JPanel          pn_userpass;                                        //  ユーザ・パスワードパネル
    private JPanel          pn_user;                                            //  ユーザ入力パネル
    private JLabel          lb_user;                                            //    ユーザ入力ラベル
    private JTextField      tf_user;                                            //    ユーザ入力フィールド
    private JPanel          pn_pass;                                            //  パスワードパネル
    private JLabel          lb_pass;                                            //    パスワード入力ラベル
    private JPasswordField  pf_pass;                                            //    パスワード入力フィールド
    private JButton         bt_login;                                           //  ログインボタン
    private JLabel          lb_playmate;                                        //  対戦相手ラベル
    
    private JPanel          pn_game;                                            //ゲームパネル
    private SweepButton[][] sb_array;                                           //  ゲーム操作ボタン配列
    private SweepButtonInfo sb_info;                                            //  ゲーム操作ボタン情報
    
    private JLabel          lb_mess;                                            //メッセージラベル
    
    //状態変数
    private Status stat;
    static enum Status{                                                         //このオブジェクト全体の状態
        INIT,                                                                   //    未ログイン
        LOGIN,                                                                  //    ログイン済・ゲーム準備中
        GAME                                                                    //    ゲーム中
    };
    
    //イベントリスナここから
    /***************************************************************************
     * ログインアクションリスナ                                                *
     * (ゲーム開始前のサーバログイン処理)                                      *
     ***************************************************************************/
    private ActionListener loginListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //ログインの度に新しいプロトコル通信--------------------------------
            protocol = new SweeperProtocol();
            
            //途中でラベル表示等を変えるため、スレッドにする--------------------
            new Thread(){
                @Override
                public void run(){
                    //入力ID・Passの取得と画面操作------------------------------
                    String user = tf_user.getText().trim();                     //ユーザ名・パスワード取得
                    String pass = new String(pf_pass.getPassword()).trim();
                    lb_mess.setText(Messages.MESSCONN);                         //メッセージラベル表示
                    tf_user.setEnabled(false);                                  //ユーザ名入力禁止
                    pf_pass.setEnabled(false);                                  //パスワード入力禁止
                    bt_login.setText(Messages.WAIT);                            //ログインボタンをWAIT表示に
                    bt_login.setEnabled(false);
                    
                    //ログイン--------------------------------------------------
                    SweeperProtocol.Status result = protocol.login(user, pass);
                    
                    //ログイン結果処理------------------------------------------
                    switch(result){
                        case NORMAL:                                            //ログイン正常
                            lb_mess.setText(                                    //  メッセージラベル設定
                                Messages.MESSLOGIN
                                + NUM_MINE
                                + Messages.MESSMINE
                            );
                            lb_playmate.setText(                                //  対戦者表示
                                Messages.MATE + protocol.getPlaymate()
                            );
                            bt_login.setText(Messages.START);                   //  ログインボタンをSTART表示に
                            bt_login.removeActionListener(loginListener);       //  ログインボタンをSTARTボタンに
                            bt_login.addActionListener(startListener);
                            bt_login.setEnabled(true);
                            stat = Status.LOGIN;                                //  状態をログイン済みにする
                            sb_info.setMode(SweepButtonInfo.Mode.POSTING);      //  ゲーム操作ボタンを配置モードにする
                            frame.repaint();
                            break;
                        case UNKNOWN_HOST:                                      //サーバ名不明
                            error_noMessage(Messages.NETNONSERVER);
                            break;
                        case CONNECT_ERROR:                                     //接続失敗
                            error_noMessage(Messages.NETCONERROR);
                            break;
                        case START_ERROR:                                       //通信開始失敗
                            error_noMessage(Messages.NETSTARTERROR);
                            break;
                        case ACCOUNT_ERROR:                                     //認証失敗
                            error_noMessage(Messages.NETLOGINERROR);
                            break;
                        case NO_PLAYMATE:                                       //対戦相手無し
                            error_noMessage(Messages.NETNOMATE);
                            break;
                        default:
                            throw new RuntimeException("ログイン時の異常処理が発生。");
                    }
                }
            }.start();
        }
    };
    
    /***************************************************************************
     * スタートアクションリスナ                                                *
     * (機雷配置後のゲーム開始処理)                                            *
     ***************************************************************************/
    private ActionListener startListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            //途中でボタン表示等を変えるため、スレッドにする--------------------
            new Thread(){
                @Override
                public void run(){
                    //配置機雷数チェック----------------------------------------
                    int num = sb_info.getMineNum();
                    if(num != NUM_MINE){                                        //規定数でないなら配置し直し
                        lb_mess.setText(
                            Messages.MESSOVER_L
                            + NUM_MINE
                            + Messages.MESSOVER_R
                        );
                        return;
                    }
                    
                    //自機雷情報を相手に送信------------------------------------
                    ArrayList<MineInfo> al = new ArrayList<MineInfo>();         //自機雷配列作成
                    for(int y = 0; y < SIZE_Y; y++){
                        for(int x = 0; x < SIZE_X; x++){
                            if(sb_array[x][y].checkStatus(
                                    SweepButton.Status.MYMINE
                            )){
                                al.add(new MineInfo(x, y));
                            }
                        }
                    }
                                                                                //自機雷送信
                    boolean res = protocol.sendMyMine(al);
                    if(!res){                                                   //送信失敗の場合中断・初期化
                        System.out.println(protocol.getStatus());
                        error(Messages.ERRMYMINE);
                        return;
                    }
                    
                    //対戦相手の機雷情報を受信----------------------------------
                    sb_info.setMode(SweepButtonInfo.Mode.WAIT);                 //ゲーム操作ボタンを待機状態にする
                    bt_login.setText(Messages.WAIT);                            //ログインボタンをWAIT表示に
                    bt_login.setEnabled(false);
                    lb_mess.setText(Messages.MESSWAIT);
                    al = protocol.getMineInfoArray();                           //相手機雷受信
                    if(al == null){                                             //受信失敗の場合中断・初期化
                        System.out.println(protocol.getStatus());
                        error(Messages.ERRWAIT);
                        return;
                    }
                    
                    //相手機雷情報をゲーム操作ボタンに反映----------------------
                    for(MineInfo mi : al){
                        int x = mi.getX();
                        int y = mi.getY();
                        if(!sb_array[x][y].checkStatus(                         //自機雷と相手機雷が重なった場合は自機雷
                               SweepButton.Status.MYMINE
                            )
                        ){
                            sb_array[x][y].setStatus(SweepButton.Status.MINE);
                        }
                    }
                    
                    //ゲームスレッド開始----------------------------------------
                    lb_mess.setText(Messages.MESSSTART);
                    new GameExec().start();
                }
            }.start();
        }
    };
    
    /***************************************************************************
     * チェックモード切替アクションリスナ                                      *
     * (ゲーム中の機雷チェックモード切替)                                      *
     ***************************************************************************/
    private ActionListener checkListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(sb_info.changeCheck()){
                bt_login.setText(Messages.CHECKON);
            }else{
                bt_login.setText(Messages.CHECKOFF);
            }
        }
    };
    
    /***************************************************************************
     * リセットアクションリスナ                                                *
     * (ゲーム終了後の結果表示状態から初期状態に戻す)                          *
     ***************************************************************************/
    private ActionListener resetListener = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            error_noMessage(Messages.MESSINIT);
        }
    };
    
    /***************************************************************************
     * ウィンドウイベントリスナ                                                *
     ***************************************************************************/
    private WindowListener windowListener = new WindowListener(){
        /*******************************************************************
         * ウインドウ閉じる処理
         */
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
        
        public void windowActivated(WindowEvent e){}
        public void windowClosed(WindowEvent e){}
        public void windowDeactivated(WindowEvent e){}
        public void windowDeiconified(WindowEvent e){}
        public void windowIconified(WindowEvent e){}
        public void windowOpened(WindowEvent e){}
    };
    //イベントリスナここまで
    
    /***************************************************************************
     * ゲーム実行スレッド                                                      *
     ***************************************************************************/
    private class GameExec extends Thread{
        @Override
        public void run(){
            //画面をゲーム状態にする--------------------------------------------
            bt_login.setText(Messages.CHECKOFF);                                //ログインボタン表示切替
            bt_login.removeActionListener(startListener);                       //ログインボタンをチェックボタンに。
            bt_login.addActionListener(checkListener);
            
            //状態をゲーム初期にする--------------------------------------------
            sb_info.clearClick();                                               //クリッククリア
            if(sb_info.getCheck()){                                             //チェックモードOFF
                sb_info.changeCheck();
            }
            bt_login.setEnabled(true);
            stat = Status.GAME;                                                 //状態をゲーム中にする
            
            //ゲーム開始--------------------------------------------------------
            while(true){
                MineInfo mi = null;                                             //操作したゲーム操作ボタン情報
                int result = 0;                                                 //勝敗判定結果
                switch(protocol.getStatus()){
                    //自分の番--------------------------------------------------
                    case GAME_ME:
                        lb_mess.setText(Messages.MESSME);                       //メッセージラベル表示
                        sb_info.setMode(SweepButtonInfo.Mode.GAME_ME);          //ボタンを自分の番モードにする
                        mi = sb_info.getClickInfo();                            //操作ボタン情報を取得
                        protocol.sendMyManipu(mi);                              //操作を相手へ送信
                        result = judge(true, mi);                               //勝敗判定
                        break;
                    //相手の番--------------------------------------------------
                    case GAME_MATE:
                        lb_mess.setText(Messages.MESSMATE);                     //メッセージラベル表示
                        sb_info.setMode(SweepButtonInfo.Mode.GAME_MATE);        //ボタンを相手の番モードにする
                        mi = protocol.receiveMateManipu();                      //相手操作を受信
                        sb_info.push(mi);                                       //相手操作通りにボタンを操作する
                        result = judge(false, mi);                              //勝敗判定
                        break;
                    //ここには来ない--------------------------------------------
                    default:
                        error(Messages.ERRGAME);
                        throw new RuntimeException("ゲーム中異常処理発生。");
                }
                //勝敗結果処理--------------------------------------------------
                if(result == 1){
                    endGame(SweeperProtocol.Result.WIN);
                    break;
                }else if(result == -1){
                    endGame(SweeperProtocol.Result.LOSE);
                    break;
                }
                //手番が終わったときの再表示------------------------------------
                frame.repaint();
            }
            //ゲームが終わったときの再表示--------------------------------------
            frame.repaint();
        }
        
        /*******************************************************************
         * 勝敗判定
         * @param me 手番(true:自分 false:相手)
         * @param mi ゲーム操作ボタン情報
         * @return 判定結果(1:自分の勝ち 0:勝敗なし -1:自分の負け)
         */
        private int judge(boolean me, MineInfo mi){
            int result = -1;
            if(me){
                result = 1;
            }
            
            int x = mi.getX();
            int y = mi.getY();
            
            //機雷を開いたか----------------------------------------------------
            try{
                if(
                    sb_array[x][y].checkStatus(SweepButton.Status.EXPLODE) ||
                    sb_array[x][y].checkStatus(SweepButton.Status.BADCHECK)
                ){
                    return -result;
                }
            }catch(ArrayIndexOutOfBoundsException e){                           //相手側のゲーム盤が大きいとこれが発生する。無視する。
            }
            
            //全機雷チェックか--------------------------------------------------
            if(sb_info.isAllCheck()){
                return result;
            }
            
            return 0;
        }
        
        /***********************************************************************
         * ゲーム終了処理
         */
        private void endGame(SweeperProtocol.Result result){
            if(!protocol.checkEnd(result)){
                error(Messages.ERRGAME);
                return;
            }
            
            switch(result){                                                     //メッセージラベル表示
                case WIN:
                    lb_mess.setText(Messages.MESSWIN);
                    break;
                case LOSE:
                    lb_mess.setText(Messages.MESSLOSE);
                    break;
                case DRAW:
                    lb_mess.setText(Messages.MESSDRAW);
                    break;
                default:
                    throw new RuntimeException("終了表示時の異常処理が発生。");
            }
            sb_info.openAllMine();                                              //全機雷開示
            bt_login.removeActionListener(checkListener);                       //ログインボタンをリセットボタンに
            bt_login.addActionListener(resetListener);
            bt_login.setText(Messages.RESET);
        }
    }
    
    /***************************************************************************
     * エラー表示とアプリ初期化(エラーメッセージ付き)
     */
    private void error(String mess){
        error_noMessage(mess + Messages.ERROR);
    }
    
    /***************************************************************************
     * エラー表示とアプリ初期化(引数の文字列のみ表示)
     */
    private void error_noMessage(String mess){
        lb_mess.setText(mess);                                                  //エラーメッセージ表示
        tf_user.setEnabled(true);                                               //ユーザ名入力可
        pf_pass.setEnabled(true);                                               //パスワード入力可
        ActionListener[] al = bt_login.getActionListeners();                    //ログインボタン アクションリスナ初期化
        for(int i = 0; i < al.length; i++){
            bt_login.removeActionListener(al[i]);
        }
        bt_login.addActionListener(loginListener);
        bt_login.setText(Messages.LOGIN);
        bt_login.setEnabled(true);
        sb_info.reset();                                                        //機雷ボタン初期化
        lb_playmate.setText(Messages.MATEINIT);                                 //相手表示初期化
        stat = Status.INIT;                                                     //ステータス初期化
    }
    
    /***************************************************************************
     * 引数を取らない唯一のコンストラクタ。
     * ウィンドウ表示も行います。
     */
    public Sweeper(){
        //状態リセット---------------------------------------------------------
        stat = Status.INIT;
        
        //ログインパネル組み立て------------------------------------------------
        lb_user = new JLabel(Messages.USER);                                    //ユーザパネル 入力ラベル
        tf_user = new JTextField();                                             //             入力フィールド
        pn_user = new JPanel();                                                 //             ユーザパネル
        pn_user.setLayout(new BorderLayout());                                  //             パネルへ配置
        pn_user.add(lb_user, BorderLayout.WEST);
        pn_user.add(tf_user, BorderLayout.CENTER);
        
        lb_pass = new JLabel(Messages.PASS);                                    //パスワードパネル 入力ラベル
        pf_pass = new JPasswordField();                                         //                 入力フィールド
        pf_pass.addActionListener(loginListener);                               //
        pn_pass = new JPanel();                                                 //                 パスワードパネル
        pn_pass.setLayout(new BorderLayout());                                  //                 パネルへ配置
        pn_pass.add(lb_pass, BorderLayout.WEST);
        pn_pass.add(pf_pass, BorderLayout.CENTER);
        
        pn_userpass = new JPanel();                                             //ユーザ・パスワードパネル
        pn_userpass.setLayout(new GridLayout(2,1));
        pn_userpass.add(pn_user);
        pn_userpass.add(pn_pass);
        
        bt_login = new JButton(Messages.LOGIN);                                 //ログインボタン
        bt_login.addActionListener(loginListener);
        lb_playmate = new JLabel(Messages.MATEINIT);                            //対戦相手ラベル
        
        pn_login = new JPanel();                                                //ログインパネル全体
        pn_login.setLayout(new BorderLayout());
        pn_login.add(pn_userpass, BorderLayout.CENTER);
        pn_login.add(bt_login, BorderLayout.EAST);
        pn_login.add(lb_playmate, BorderLayout.SOUTH);
        
        //ゲームパネル組み立て--------------------------------------------------
        pn_game = new JPanel();                                                 //ゲームパネル
        pn_game.setLayout(new GridLayout(SIZE_Y, SIZE_X));
        sb_array = new SweepButton[SIZE_X][SIZE_Y];                             //  機雷ボタン配列
        sb_info = new SweepButtonInfo(sb_array, NUM_MINE);                      //  自機雷最大数設定
        for(int y = 0; y < SIZE_Y; y++){
            for(int x = 0; x < SIZE_X; x++){
                sb_array[x][y] = new SweepButton(x, y, sb_info);                //  機雷ボタン生成
                pn_game.add(sb_array[x][y]);                                    //  機雷ボタン配置
            }
        }
        
        //メッセージラベル------------------------------------------------------
        lb_mess = new JLabel(Messages.MESSINIT);
        
        //メインフレーム組み立て------------------------------------------------
        frame = new JFrame(Messages.TITLE);
        frame.setLayout(new BorderLayout());
        frame.add(pn_login, BorderLayout.NORTH);
        frame.add(pn_game,  BorderLayout.CENTER);
        frame.add(lb_mess,  BorderLayout.SOUTH);
        frame.setSize(WIN_WIDTH, WIN_HEIGHT);
        
        frame.addWindowListener(windowListener);
        
        frame.setVisible(true);
    }
    
    /***************************************************************************
     * 起動メソッド
     * @param args 使用しません
     */
    public static void main(String[] args){
        Sweeper s = new Sweeper();
    }
    
}
