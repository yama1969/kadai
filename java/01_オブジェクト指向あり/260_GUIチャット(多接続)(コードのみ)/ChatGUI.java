import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
  チャットGUIアプリケーションです。
*/
public class ChatGUI extends JFrame implements Chat{
    //-----フィールド-----------------------------------------------------------
    private ChatProtocol chatPro;                                               //プロトコル実装
    private boolean      chatOn;                                                //チャット実行中=true
    private boolean      changeName;                                            //ユーザ名変更中=true
    private String       myName;                                                //ユーザ名
    
    //  画面上部                                                                //GUI部品
    private JTextField  tf_name;    //名前入力ボックス
    private JTextField  tf_add;     //接続先入力ボックス
    private JButton     bt_conn;    //Chat開始ボタン
    private JPanel      pl_north;   //画面上部部品用パネル
    //  画面中央
    private JTextArea   ta_mess;    //メッセージ表示エリア
    private JScrollPane sp_mess;    //メッセージ表示エリアスクロールパネル
    //  画面下部
    private JTextField  tf_input;   //メッセージ入力ボックス
    private JButton     bt_send;    //送信ボタン
    private JPanel      pl_south;   //画面下部部品用パネル
    //  画面右側
    private JTextArea   ta_users;   //接続ユーザ一覧表示エリア
    private JScrollPane sp_users;   //ユーザ一覧エリアスクロールパネル
    private JPanel      pl_east;    //画面右側部品用パネル
    
    //-----コンストラクタ-------------------------------------------------------
    public ChatGUI(){
        super("Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        setLayout(new BorderLayout());
        
        Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 10);                //画面内ラベル共通フォント
        JPanel panel;                                                           //画面構成作業用パネル
        JLabel label;                                                           //画面構成作業用ラベル
        
        //GUI画面構成-----------------------------------------------------------
        pl_north = new JPanel();                                                //  画面上部
        pl_north.setLayout(new BorderLayout());
        
        label = new JLabel("名前");
        label.setFont(font);
        tf_name = new JTextField(5);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(label);
        panel.add(tf_name);
        pl_north.add(panel, BorderLayout.WEST);
        
        label = new JLabel("接続先アドレス");
        label.setFont(font);
        tf_add = new JTextField();
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(label);
        panel.add(tf_add);
        pl_north.add(panel, BorderLayout.CENTER);
        
        JPanel space = new JPanel();
        bt_conn = new JButton("開始");
        panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(space);
        panel.add(bt_conn);
        pl_north.add(panel, BorderLayout.EAST);
        
        ta_mess = new JTextArea();                                              //  画面中央部
        ta_mess.setEditable(false);
        sp_mess = new JScrollPane(ta_mess);
        
        pl_south = new JPanel();                                                //  画面下部
        pl_south.setLayout(new BorderLayout());
        
        tf_input = new JTextField();
        bt_send = new JButton("送信");
        
        pl_south.add(tf_input, BorderLayout.CENTER);
        pl_south.add(bt_send, BorderLayout.EAST);
        
        pl_east = new JPanel();                                                 //  画面右側
        pl_east.setLayout(new BorderLayout());
        
        label = new JLabel("参加者リスト");
        label.setFont(font);
        ta_users = new JTextArea(0,8);
        ta_users.setEditable(false);
        sp_users = new JScrollPane(ta_users);
        
        pl_east.add(label, BorderLayout.NORTH);
        pl_east.add(sp_users, BorderLayout.CENTER);
        
        add(pl_north, BorderLayout.NORTH);                                      //フレーム全体の構成
        add(sp_mess, BorderLayout.CENTER);
        add(pl_south, BorderLayout.SOUTH);
        add(pl_east, BorderLayout.EAST);
        
        //GUI部品イベントリスナ設定---------------------------------------------
        bt_conn.addActionListener(new ActionListener(){                         //開始ボタン押下
            public void actionPerformed(ActionEvent ae){
                chatOnOff();
            }
        });
        
        bt_send.addActionListener(new ActionListener(){                         //送信ボタン押下
            public void actionPerformed(ActionEvent ae){
                sendMessage();
            }
        });
        
        tf_input.addActionListener(new ActionListener(){                        //送信テキストEnter入力
            public void actionPerformed(ActionEvent ae){
                sendMessage();
            }
        });
        
        //初期化終了
        chatPro = null;
        chatOn = false;
        changeName = false;
        setVisible(true);
    }
    
    //-----チャット開始／終了処理（開始ボタン押下）-----------------------------
    private void chatOnOff(){
        if(chatOn){                                                             //チャット中
            if(changeName){                                                     //  ハンドル名変更
                myName = tf_name.getText();
                chatPro.setName(myName);
                appendMessage("（ハンドル名を変更しました。）\n");
                tf_name.setEnabled(false);
                bt_conn.setText("終了");
                showUsers();
                changeName = false;
            }else{                                                              //  チャット終了
                appendMessage("（チャットを終了しました。）\n");
                ta_users.setText(null);
                tf_name.setEnabled(true);
                tf_add.setEnabled(true);
                tf_input.setEnabled(false);
                bt_conn.setText("開始");
                chatOn = false;
                chatPro.exit();
            }
        }else{                                                                  //チャット開始
            myName = tf_name.getText();
            try{
                chatPro = new ChatProtocol(this, myName);
            }catch(ChatProtocolException e){
                appendMessage("（チャットを開始できませんでした。名前の入力をご確認ください。）\n");
                return;
            }
            
            if(chatPro.startWait()){
                appendMessage("（接続待受けを開始しました。）\n");
            }else{
                appendMessage("（接続待受けは開始しませんでした。）\n");
            }
            
            try{
                String add = tf_add.getText();
                chatPro.connect(add);
                appendMessage("（" + add + "へ接続しました。）\n");
            }catch(ChatProtocolException e){
                appendMessage("（" + e.getLocalizedMessage() + "）\n");
            }
            
            appendMessage("（チャットを開始しました。）\n");
            showUsers();
            tf_name.setEnabled(false);
            tf_add.setEnabled(false);
            tf_input.setEnabled(true);
            bt_conn.setText("終了");
            chatOn = true;
        }
    }
    
    //-----メッセージ送信処理（送信ボタン押下）---------------------------------
    private void sendMessage(){
        String mess = tf_input.getText();
        if(!mess.equals("")){
            chatPro.sendMessage(mess);
            appendMessage("<" + myName + "> " + mess + "\n");
            tf_input.setText(null);
        }
    }
    
    //-----ユーザ一覧取得-------------------------------------------------------
    private void showUsers(){
        ta_users.setText(null);
        ArrayList<String> users = chatPro.getNames();
        for(String name : users){
            ta_users.append(name + "\n");
        }
    }
    
    //-----メッセージ表示エリア文字列追加---------------------------------------
    private void appendMessage(String mess){
        ta_mess.append(mess);
        ta_mess.setCaretPosition(ta_mess.getText().length());
    }

    /***************************************************************************
      Chatの実装
    ***************************************************************************/
    //-----新ユーザ接続---------------------------------------------------------
    public void newUser(String name){
        appendMessage("（" + name + "さんが参加しました。）\n");
        showUsers();
    }
    
    //-----既存ユーザ発見-------------------------------------------------------
    public void addUser(String name){
        showUsers();
    }
    
    //-----重複ユーザ発見-------------------------------------------------------
    public void changeName(){
        appendMessage("（ハンドル名の重複が見つかりました。ハンドル名を変更して下さい。）\n");
        tf_name.setEnabled(true);
        bt_conn.setText("名変更");
        changeName = true;
    }
    
    //-----メッセージ着信-------------------------------------------------------
    public void message(String mess){
        appendMessage(mess + "\n");
    }
    
    //-----ユーザ切断-----------------------------------------------------------
    public void exitUser(String name){
        appendMessage("（" + name + "さんが退室しました。）\n");
        showUsers();
    }
    
    //----ユーザ回線切断--------------------------------------------------------
    public void disconUser(String name){
        appendMessage("（" + name + "さんの接続が切れました。）\n");
        showUsers();
    }
    
    /***************************************************************************
      main()
    ***************************************************************************/
    public static void main(String[] args){
        new ChatGUI();
    }
}
