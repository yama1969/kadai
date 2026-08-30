import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *  メール送信ソフトのＧＵＩを提供するクラスです。
 */
public class MailSender extends JFrame{

    private JPanel      pnlHead;
    private JPanel      pnlLabel;
    private JPanel      pnlBox;
    private JLabel      lblServer;
    private JTextField  txtServer;
    private JLabel      lblFrom;
    private JTextField  txtFrom;
    private JLabel      lblTo;
    private JTextField  txtTo;
    private JLabel      lblMessage;
    private JScrollPane scrMessage;
    private JTextArea   tarMessage;
    private JPanel      pnlFoot;
    private JButton     btnSend;
    private JTextField  txtStat;
    private Container   conFrame;

    /**
     *  メール送信GUI画面のコンストラクタです。
     *  @param title メール送信GUI画面タイトル
     */
    public MailSender(String title){
        super(title);
        try{
            UIManager.setLookAndFeel(
              "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }catch(Exception e){
            System.out.println(e.toString());
        }

        lblServer  = new JLabel(" Server : ");
        txtServer  = new JTextField("mail.jc-21.ac.jp");
        lblFrom    = new JLabel(" From : ");
        txtFrom    = new JTextField("h.yamada@jc-21.co.jp");
        lblTo      = new JLabel(" To : ");
        txtTo      = new JTextField("jc20200@jc-21.ac.jp");
        lblMessage = new JLabel(" Message");
        tarMessage = new JTextArea("TEST!");
        scrMessage = new JScrollPane(tarMessage);
        btnSend    = new JButton("送信");
        txtStat    = new JTextField();

        pnlLabel = new JPanel(new GridLayout(4,1));
        pnlLabel.add(lblServer);
        pnlLabel.add(lblFrom);
        pnlLabel.add(lblTo);
        pnlLabel.add(lblMessage);

        pnlBox = new JPanel(new GridLayout(4,1));
        pnlBox.add(txtServer);
        pnlBox.add(txtFrom);
        pnlBox.add(txtTo);

        pnlHead = new JPanel(new BorderLayout());
        pnlHead.add(pnlLabel, BorderLayout.WEST);
        pnlHead.add(pnlBox, BorderLayout.CENTER);

        pnlFoot = new JPanel(new GridLayout(2,1));
        pnlFoot.add(btnSend);
        pnlFoot.add(txtStat);

        conFrame = getContentPane();
        conFrame.setLayout(new BorderLayout());
        conFrame.add(pnlHead, BorderLayout.NORTH);
        conFrame.add(scrMessage, BorderLayout.CENTER);
        conFrame.add(pnlFoot, BorderLayout.SOUTH);

        btnSend.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                txtStat.setText("");
                MailSend ms = new MailSend();
                try{
                    ms.setServer(txtServer.getText());
                    ms.setFromAddress(txtFrom.getText());
                    ms.setToAddress(txtTo.getText());
                    ms.setMessage(tarMessage.getText());
                    ms.sendMail();
                    txtStat.setText("送信しました。");
                }catch(MailAddressException mae){
                    txtStat.setText(mae.getMessage());
                }catch(MailSettingException mse){
                    txtStat.setText(mse.getMessage());
                }catch(MailSendException msde){
                    txtStat.setText(msde.getMessage());
                }
            }
        });
    }

    /**
     *  メール送信GUI画面のメインメソッドです。<br>
     *  メール送信GUIを単独で起動する場合に利用してください。引数は使用しません。
     */
    public static void main(String[] args){
        MailSender ms = new MailSender("Mail Sender");
        ms.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ms.MailSenderStart();
    }

    /**
     *  メール送信GUI画面を表示します。
     */
    public void MailSenderStart(){
        setSize(300,300);
        setVisible(true);
    }
}
