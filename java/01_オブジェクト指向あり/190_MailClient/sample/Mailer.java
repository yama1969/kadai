import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *  メールソフトのＧＵＩを提供するクラスです。<br>
 *  このクラスは、以下のクラスを利用しています。<br>
 *  メール送信　　：<a href="MailSender.html">MailSender</a><br>
 *  メール受信　　：<a href="MailReceive.html">MailReceive</a><br>
 *  メール自動受信：<a href="AutoReceive.html">AutoReceive</a><br>
 *  メール一覧　　：<a href="ReceivedMail.html">ReceivedMail</a><br>
 */
public class Mailer extends JFrame implements AutoReceiveInterface{

    private ReceivedMail   rmList;
    private boolean        blDel       = true;
    private Thread         thAutoRec;
    private AutoReceive    arAutoRec;
    private boolean        blAutoRec   = false;

    private JPanel         pnlSetting;
    private JPanel         pnlLabel;
    private JPanel         pnlBox;
    private JLabel         lblServer;
    private JTextField     txtServer;
    private JLabel         lblAccount;
    private JTextField     txtAccount;
    private JLabel         lblPass;
    private JPasswordField txtPass;
    private JPanel         pnlOption;
    private JCheckBox      cbxDelete;
    private JCheckBox      cbxAutoRec;
    private JPanel         pnlAutoRec;
    private JLabel         lblAutoRec;
    private JTextField     txtAutoRec;
    private JLabel         lblSecond;

    private JPanel         pnlMail;
    private JList          lstIndex;
    private JScrollPane    scrIndex;
    private JTextArea      tarMessage;
    private JScrollPane    scrMessage;

    private JPanel         pnlFoot;
    private JPanel         pnlCommand;
    private JButton        btnSend;
    private JButton        btnReceive;
    private JTextField     txtStat;

    /**
     *  GUI画面のコンストラクタです。
     *  @param title GUI画面タイトル
     */
    public Mailer(String title){
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        try{
            UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }catch(Exception e){
            System.out.println(e.toString());
        }

        //受信メール一覧
        rmList = new ReceivedMail();

        //GUIコントロール配置
        lblServer = new JLabel("　受信サーバ：");
        txtServer = new JTextField("mail.jc-21.ac.jp");
        lblAccount = new JLabel("　ユーザＩＤ：");
        txtAccount = new JTextField("jc20200");
        lblPass = new JLabel("　パスワード：");
        txtPass = new JPasswordField("999999");

        pnlLabel = new JPanel(new GridLayout(3,1));
        pnlLabel.add(lblServer);
        pnlLabel.add(lblAccount);
        pnlLabel.add(lblPass);

        pnlBox = new JPanel(new GridLayout(3,1));
        pnlBox.add(txtServer);
        pnlBox.add(txtAccount);
        pnlBox.add(txtPass);

        cbxDelete = new JCheckBox("サーバからメール削除", blDel);
        cbxAutoRec = new JCheckBox("自動受信", blAutoRec);
        lblAutoRec = new JLabel("　　間隔:");
        txtAutoRec = new JTextField("10");
        txtAutoRec.setHorizontalAlignment(JTextField.RIGHT);
        lblSecond  = new JLabel("秒　");
        pnlAutoRec = new JPanel(new BorderLayout());
        pnlAutoRec.add(lblAutoRec, BorderLayout.WEST);
        pnlAutoRec.add(txtAutoRec, BorderLayout.CENTER);
        pnlAutoRec.add(lblSecond, BorderLayout.EAST);
        pnlOption = new JPanel(new GridLayout(3,1));
        pnlOption.add(cbxDelete);
        pnlOption.add(cbxAutoRec);
        pnlOption.add(pnlAutoRec);

        pnlSetting = new JPanel(new BorderLayout());
        pnlSetting.add(pnlLabel, BorderLayout.WEST);
        pnlSetting.add(pnlBox, BorderLayout.CENTER);
        pnlSetting.add(pnlOption, BorderLayout.EAST);

        lstIndex = new JList();
        scrIndex = new JScrollPane(lstIndex);
        tarMessage = new JTextArea();
        scrMessage = new JScrollPane(tarMessage);

        pnlMail = new JPanel(new BorderLayout());
        pnlMail.add(scrIndex, BorderLayout.WEST);
        pnlMail.add(scrMessage, BorderLayout.CENTER);

        btnSend = new JButton("メール送信");
        btnReceive = new JButton("メール受信");

        pnlCommand = new JPanel(new GridLayout(1,2));
        pnlCommand.add(btnSend);
        pnlCommand.add(btnReceive);

        txtStat = new JTextField();

        pnlFoot = new JPanel(new GridLayout(2,1));
        pnlFoot.add(pnlCommand);
        pnlFoot.add(txtStat);

        Container conFrame = getContentPane();
        conFrame.setLayout(new BorderLayout());
        conFrame.add(pnlSetting, BorderLayout.NORTH);
        conFrame.add(pnlMail,    BorderLayout.CENTER);
        conFrame.add(pnlFoot,    BorderLayout.SOUTH);

        //送信ボタン押下アクションリスナ
        btnSend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                MailSender ms = new MailSender("メール送信");
                Point p = new Point();
                p = getLocation();
                ms.setLocation((int)p.getX()+30, (int)p.getY()+30);
                ms.MailSenderStart();
            }
        });

        //受信ボタン押下アクションリスナ
        btnReceive.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                receive();
            }
        });

        //メール選択アクションリスナ
        lstIndex.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent le){
                int no = lstIndex.getMinSelectionIndex();
                tarMessage.setText("");
                try{
                    tarMessage.setText(rmList.getMessage(no));
                }catch(IndexOutOfBoundsException ie){
                    //リスト書換えのとき、選択が-1になります。
                }
            }
        });

        //サーバメール削除チェックボックス切り替えリスナ
        cbxDelete.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                blDel = !blDel;
            }
        });

        //自動受信チェックボックス切り替えリスナ
        cbxAutoRec.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                blAutoRec = !blAutoRec;
                changeAutoReceive();
            }
        });

        //自動受信の開始
        changeAutoReceive();
    }

    /**
     *  現在の設定に従い、自動受信を開始または停止します。
     */
    private void changeAutoReceive(){
        if(blAutoRec){
            //自動受信開始
            txtAutoRec.setEditable(false);
            txtAutoRec.setForeground(Color.GRAY);
            int i = Integer.parseInt(txtAutoRec.getText().trim());
            if(i < 10){
                i = 10;
            }
            arAutoRec = new AutoReceive(this, i);
            thAutoRec = new Thread(arAutoRec);
            thAutoRec.start();
        }else{
            //自動受信停止
            if(arAutoRec != null){
                arAutoRec.stop();
            }
            thAutoRec = null;
            arAutoRec = null;
            txtAutoRec.setEditable(true);
            txtAutoRec.setForeground(Color.BLACK);
        }
    }

    /**
     *  ＧＵＩの設定に従ってメール受信します。<br>
     *  これはAutoReceiveInterfaceインターフェースの実装メソッドです。
     */
    public void receive(){
        MailReceive mr = new MailReceive();
        mr.setServer(txtServer.getText().trim());
        mr.setUser(txtAccount.getText().trim());
        mr.setPass(String.copyValueOf(txtPass.getPassword()).trim());
        mr.setDelete(blDel);
        txtStat.setText("");
        ReceivedMail mails = null;
        try{
            mails = mr.receiveMail();
        }catch(MailSettingException mse){
            txtStat.setText(mse.getMessage());
            return;
        }catch(MailReceiveException mre){
            txtStat.setText(mre.getMessage());
            return;
        }

        txtStat.setText(mails.getMailCount()+"件受信しました");
        rmList.addReceivedMail(mails);
        lstIndex.setListData(rmList.getArrayFrom().toArray());
    }

    /**
     *  メールソフトメインメソッドです。引数は利用しません。
     */
    public static void main(String[] args){
        Mailer ml = new Mailer("Mailer");
        ml.setSize(800,500);
        ml.setVisible(true);
    }
}
