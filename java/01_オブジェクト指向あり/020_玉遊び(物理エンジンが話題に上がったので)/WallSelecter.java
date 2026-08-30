import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*******************************************************************************
 * 玉シミュレータ(?)の壁をオン・オフするボタンのパネル
 ******************************************************************************/
public class WallSelecter extends JPanel{
    private TamaFrame     frame;
    private JToggleButton left_bt;
    private boolean left;
    private JToggleButton right_bt;
    private boolean right;
    
    /***************************************************************************
     * コンストラクタ。ボタンの生成とイベントリスナの登録をする
     **************************************************************************/
    public WallSelecter(TamaFrame tamaframe){
        this.frame = tamaframe;
        
        left = false;
        left_bt = new JToggleButton("左の壁");
        left_bt.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 left = !left;
                 frame.setLeftWall(left);
             }
        });
        
        right = false;
        right_bt = new JToggleButton("右の壁");
        right_bt.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 right = !right;
                 frame.setRightWall(right);
             }
        });
        
        setLayout(new GridLayout(1, 2));
        add(left_bt);
        add(right_bt);
    }
}
