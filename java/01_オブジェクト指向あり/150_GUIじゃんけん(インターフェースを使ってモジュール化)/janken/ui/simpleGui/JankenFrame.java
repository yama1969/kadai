package janken.ui.simpleGui;

import janken.frame.JankenUI;
import janken.frame.JankenMotion;
import janken.frame.JankenHand;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 じゃんけんGUI画面のフレーム。ユーザーインターフェースモジュールの境界クラスです。
 ゲーム動作モジュールの境界クラスのインスタンスへの参照を持ち、ゲーム動作モジュールとのやり取りは全てこのクラスのインスタンスが担います。
 ユーザーインターフェースモジュール内の他のインスタンスがゲーム動作モジュールとやり取りする場合は、
 このクラスのインスタンスから委譲されるか、このクラスのインスタンスへ委譲するようにします。
 */
public class JankenFrame extends JFrame implements JankenUI {
    private JankenMotion motion = null;
    private HandPicPanel hpp = null;
    private HandSelPanel hsp = null;
    private JTextField text = null;
    
    /**
     唯一のコンストラクタです。GUI画面の設定および表示を行います。
     */
    public JankenFrame(){
        //タイトルとイベントリスナ設定
        setTitle("じゃんけん");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //ウィンドウレイアウトの設定
        setLayout(new BorderLayout());
        
        //中央部
        hpp = new HandPicPanel(this);
        add(hpp, BorderLayout.CENTER);
        
        //南部
        hsp = new HandSelPanel(this);
        text = new JTextField();
        JPanel south = new JPanel();
        south.setLayout(new GridLayout(2,1));
        south.add(hsp);
        south.add(text);
        add(south, BorderLayout.SOUTH);
        
        //ウィンドウサイズ設定と表示
        setSize(440,540);
        setVisible(true);
    }
    
    /**
     ゲーム動作モジュールの境界クラスのインスタンスの参照をセットします。<br>
     このメソッドはJankenUIインターフェースの実装です。
     @param motion ゲーム動作モジュールの境界クラスのインスタンス
     */
    @Override
    public void setMotion(JankenMotion motion){
        this.motion = motion;
    }
    
    /**
     じゃんけん対戦相手側の手の、表示の高速回転を開始します。<br>
     このメソッドはJankenUIインターフェースの実装です。
     実際にはHandPicPanelクラスのインスタンスへ委譲しています。
     */
    @Override
    public void startRoll(){
        hpp.startRoll();
    }
    
    /**
     じゃんけん対戦相手側の手の、表示の高速回転を停止します。<br>
     このメソッドはJankenUIインターフェースの実装です。
     実際にはHandPicPanelクラスのインスタンスへ委譲しています。
     */
    @Override
    public void stopRoll(){
        hpp.stopRoll();
    }
    
    /**
     じゃんけん対戦相手側の手を表示します。<br>
     このメソッドはJankenUIインターフェースの実装です。
     実際にはHandPicPanelクラスのインスタンスへ委譲しています。
     */
    @Override
    public void showHand(JankenHand hand){
        hpp.showHand(hand);
    }
    
    /**
     画面に表示する文字列をセットします。<br>
     このメソッドはJankenUIインターフェースの実装です。
     このメソッドが呼び出されると、すぐにテキストボックスに表示します。
     */
    @Override
    public void setMessage(String mess){
        text.setText(mess);
    }
    
    /**
     画面に表示している文字列を消します。<br>
     このメソッドはJankenUIインターフェースの実装です。
     このメソッドが呼び出されると、すぐにテキストボックスをクリアします。
     */
    @Override
    public void clearMessage(){
        text.setText(null);
    }
    
    /**
     プレイヤーが選択した手をゲーム動作モジュールへ通知します。<br>
     このメソッドはJankenMotionインターフェースのselectHand()メソッドを呼び出します。
     @param hand 通知するじゃんけんの手
     */
    public void selectHand(JankenHand hand){
        motion.selectHand(hand);
    }
}
