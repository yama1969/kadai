import java.awt.BorderLayout;  //フレーム内GUI部品の配置方法を定義したクラス
import javax.swing.JFrame;     //GUIフレームクラス

/*******************************************************************************
 * 玉打遊戯のGUIフレーム。ゲームの起動と終了、および画面の初期表示をする。
 *
 * @author 山田　洋 2008/5/1
 ******************************************************************************/
public class TamaUchiGame extends JFrame{
    private TamaUchiGamePanel panel;  //玉打遊戯本体のパネル
    private int width = 400;          //フレーム幅
    private int height = 300;         //フレーム高さ
    
    /***************************************************************************
     * フレームを準備し、画面表示する。引数なしコンストラクタ。
     **************************************************************************/
    public TamaUchiGame(){
        super("玉打遊戯");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ＸボタンをクリックするとSystem.exit()するように設定
        
        panel = new TamaUchiGamePanel(width,height);    //遊戯パネルの生成
        add(panel, BorderLayout.CENTER);                //遊戯パネルをフレーム中央に配置
        
        setSize(width, height);                         //フレームの幅・高さを設定
        setVisible(true);                               //フレーム表示を開始
    }
    
    /***************************************************************************
     * 玉打遊戯のGUIフレームを生成する。
     **************************************************************************/
    public static void main(String[] args){
        new TamaUchiGame();                             //玉打遊戯のGUIフレーム生成
    }
}
