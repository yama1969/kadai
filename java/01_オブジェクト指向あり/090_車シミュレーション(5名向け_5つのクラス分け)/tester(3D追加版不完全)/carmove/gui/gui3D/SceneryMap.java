package carmove.gui.gui3D;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * シーナリーオブジェクトをマップに展開した情報を持つクラスです。
 */
public class SceneryMap{
	private SceneryDrawer[] drawer;
	private SceneryObject[][] map;				//シーナリーオブジェクトマップ
	private int width = 550;					//スクリーン幅
	private int height = 500;					//スクリーン高さ
	private int depth = 1000;					//プレーヤー視点とスクリーンとの距離
	private double viw_z = 100.0;				//視点のZ座標
	public static final int GRID = 1000;		//シーナリーグリッドの大きさ(単位ドット)
	
	/**
	 * コンストラクタ。マップデータファイルを読込み、マップデータを
	 * 実際のシーナリーオブジェクト配列のイメージで保持します。
	 */
	public SceneryMap(){
		SceneryObjCollection collection = new SceneryObjCollection();
		
		try{
			//マップの大きさを知るため、ひとまず全記述を読込み
			BufferedReader br = new BufferedReader(new FileReader("SceneryMap.dat"));
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			while((line = br.readLine()) != null){
				lines.add(line);
			}
			//読込んだ行数分だけmapの行を取る
			map = new SceneryObject[lines.size()][];
			//各行の記述をシーナリーオブジェクトに変換
			for(int i = 0; i < lines.size(); i++){
				String str = lines.get(i);
				map[i] = new SceneryObject[str.length()];
				for(int j = 0; j < str.length(); j++){
					String name = str.substring(j, j + 1);
					map[i][j] = collection.getSceneryObjectByName(name);	//同じシーナリーオブジェクトは、同一インスタンスを利用する形になる
				}
			}
			//シーナリー描画オブジェクトの準備
			drawer = new SceneryDrawer[8];
			drawer[0] = new SceneryDrawerNNE();
			drawer[1] = new SceneryDrawerENE();
			drawer[2] = new SceneryDrawerESE();
			drawer[3] = new SceneryDrawerSSE();
			drawer[4] = new SceneryDrawerNNE();
			drawer[5] = new SceneryDrawerNNE();
			drawer[6] = new SceneryDrawerNNE();
			drawer[7] = new SceneryDrawerNNW();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * シーナリーオブジェクトのマップ配列を得ます。
	 *
	 * @return シーナリーオブジェクトのマップ配列
	 */
	public SceneryObject[][] getMap(){
		return map;
	}
	
	public double getViewZ(){
		return viw_z;
	}
	
	public void drawScenery(double x, double y, double direct, Graphics g){
		//空と地面を描画
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, width, height / 2);
		g.setColor(Color.GREEN);
		g.fillRect(0, height / 2, width, height / 2);
		
		int d = (int)(((direct + 180.0) % 360.0) / 45.0);
		drawer[d].drawScenery(x, y, direct * Math.PI / 180.0, this, g);
	}
	
	public void setScreenSize(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public void setDepth(int depth){
		this.depth = depth;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getDepth(){
		return depth;
	}
}
