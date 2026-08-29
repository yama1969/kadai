import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

/*
 計算出題のユーザーインターフェースGUI版
*/
public class CalcUI extends JFrame{
    
    private JLabel     ques_label; //問題表示ラベル
    private JTextField ans_text;   //回答入力テキストボックス
    private JButton    ans_button; //回答ボタン
    private JLabel     mess_label; //メッセージ表示ラベル
    
    private int        ans;        //回答数値
    private boolean    input;      //回答状態(true:回答済, false:未回答)
    
    //テキストボックス入力およびボタン押下時のリスナ
    private class AnsAction implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            synchronized(CalcUI.this){
                String str = ans_text.getText();
                ans = -1;
                try{
                    ans = Integer.parseInt(str);  //回答数値を設定
                    input = true;                 //回答済にする
                }catch(NumberFormatException ex){
                    //ansは-1のまま
                }
                CalcUI.this.notifyAll();
            }
        }
    }
    
    //コンストラクタ。画面表示まで
    public CalcUI(){
        Font font = new Font("Default", Font.BOLD, 24);
        
        //回答用パネル作成
        AnsAction action = new AnsAction();
        ans_text = new JTextField();        //回答入力テキストボックス
        ans_text.addActionListener(action);
        ans_text.setFont(font);
        ans_button = new JButton("回答");   //回答ボタン
        ans_button.setFont(font);
        ans_button.addActionListener(action);
        
        JPanel ans_panel = new JPanel();
        ans_panel.setLayout(new GridLayout(1, 2));
        
        ans_panel.add(ans_text);
        ans_panel.add(ans_button);
        
        //問題表示ラベル作成
        ques_label = new JLabel("問題");
        ques_label.setFont(font);
        
        //問題表示ラベルと回答用パネルの組立て
        JPanel ques_panel = new JPanel();
        ques_panel.setLayout(new BorderLayout());
        
        ques_panel.add(ques_label, BorderLayout.CENTER);
        ques_panel.add(ans_panel, BorderLayout.EAST);
        
        //メッセージ表示ラベル作成
        mess_label = new JLabel("メッセージ");
        mess_label.setFont(font);
        
        //全体パネルの作成と組立て
        JPanel ground_panel = new JPanel();
        ground_panel.setLayout(new GridLayout(2, 1));
        
        ground_panel.add(ques_panel);
        ground_panel.add(mess_label);
        
        add(ground_panel);
        
        //ウィンドウ設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //×ボタン設定
        setSize(400, 200);
        setVisible(true);
        
        //未回答状態
        input = false;
    }
    
    //メッセージの表示
    public void showMessage(String message){
        mess_label.setText(message);
    }
    
    //問題の表示
    public void showQuestion(String question){
        ques_label.setText(question + " = ");
    }
    
    //回答入力
    public synchronized int inputAnswer(){
        try{
            while(!input){
                wait();
            }
        }catch(InterruptedException e){
        }
        input = false;
        ans_text.setText("");
        return ans;
    }
    
    //正答誤答の表示
    public void showCheck(boolean check){
        if(check){
            mess_label.setText("正解！");
        }else{
            mess_label.setText("不正解・・・");
        }
    }
    
    //最終結果の表示
    public void showResult(int right, int num){
        String mess = "全" + num + "問中、" + right + "問正解でした。";
        mess_label.setText(mess);
    }
}
