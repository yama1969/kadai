/*******************************************************************************
 * アプリケーション中の表示文字列                                              *
 *******************************************************************************/
class Messages{
    static final String TITLE    = "Net対戦MineSweeper";                        //フレームタイトル
    static final String USER     = "User";                                      //ログインユーザフィールドのラベル
    static final String PASS     = "Pass";                                      //ログインパスワードフィールドのラベル
    static final String MATEINIT = "対戦相手:未接続";                           //対戦相手初期表示
    static final String MATE     = "対戦相手:";                                 //対戦相手表示
    
    static final String LOGIN    = "Login";                                     //ログインボタン初期表示
    static final String START    = "Start";                                     //ログインボタンログイン後表示
    static final String WAIT     = "Wait...";                                   //ログインボタン待機中表示
    static final String CHECKON  = "Check ON ";                                 //ログインボタンCHECK ON表示
    static final String CHECKOFF = "Check OFF";                                 //ログインボタンCHECK OFF表示
    static final String RESET    = "Reset";                                     //ログインボタンリセット表示
    
    static final String MESSINIT  = "ログインして下さい。";                     //メッセージラベル初期表示
    static final String MESSCONN  = 
        "ログイン処理と対戦相手の決定を待機しています。";                       //メッセージラベル対戦相手決定待ち
    static final String MESSLOGIN = "ログインしました。機雷を";                 //メッセージラベルログイン後表示
    static final String MESSMINE  = "個配置して下さい";
    static final String MESSOVER_L= "機雷は";                                   //メッセージラベル機雷数過多表示
    static final String MESSOVER_R= "個です。";
    static final String MESSWAIT  = "相手の機雷配置を待機しています。";         //メッセージラベル相手待機中
    static final String MESSSTART = "ゲームを開始します。";                     //メッセージラベルゲーム開始
    static final String MESSME    = "あなたの番です。";                         //メッセージラベル自分の番
    static final String MESSMATE  = "相手の番です。お待ちください。";           //メッセージラベル相手の番
    static final String MESSWIN   = "あなたの勝ちです。";                       //メッセージラベル勝ち
    static final String MESSLOSE  = "あなたの負けです。";                       //メッセージラベル負け
    static final String MESSDRAW  = "引き分けです。";                           //メッセージラベル引き分け
    
    static final String NETNONSERVER  = "サーバ名が不明です。";                 //ネット関連エラーメッセージ
    static final String NETCONERROR   = "ネットワーク接続時エラーです。";       //
    static final String NETSTARTERROR = "ネット通信開始時エラーです。";         //
    static final String NETLOGINERROR = "ユーザ名・パスワードが不正です。";     //
    static final String NETNOMATE     = "対戦相手がいません。";                 //
    
    static final String ERROR     = "エラーが発生しました。中止します。";       //致命的なエラー発生の表示
    static final String ERRMYMINE = "自機雷位置送信で";
    static final String ERRWAIT   = "相手機雷位置待機で";
    static final String ERRGAME   = "ゲーム中に";
}
