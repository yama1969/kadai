package carmove.gui.gui3D;

import java.util.ArrayList;

/**
 * シーナリーオブジェクトクラス。ひとつひとつのシーナリーオブジェクトの情報を持ちます。
 */
public class SceneryObject{
	private String name;					//オブジェクト識別名
	private int[][][][] faces;				//各面の頂点座標(添字は[方角][図形番号][点番号][XYZまたはRGB])
	/** 方角の個数 */
	public static final int NUM_DIR = 5;
	/** 北を表す番号 */
	public static final int NORTH = 0;
	/** 東を表す番号 */
	public static final int EAST = 1;
	/** 南を表す番号 */
	public static final int SOUTH = 2;
	/** 西を表す番号 */
	public static final int WEST = 3;
	/** 地面を表す番号 */
	public static final int GROUND = 4;
	/** X座標の番号。(面座標配列の第4添字に使う) */
	public static final int X = 0;
	/** Y座標の番号。(面座標配列の第4添字に使う) */
	public static final int Y = 1;
	/** Z座標の番号。(面座標配列の第4添字に使う) */
	public static final int Z = 2;
	/** 赤の番号。(面座標配列の第4添字に使う) */
	public static final int RED = 0;
	/** 緑の番号。(面座標配列の第4添字に使う) */
	public static final int GREEN = 1;
	/** 青の番号。(面座標配列の第4添字に使う) */
	public static final int BLUE = 2;
	
	/**
	 * シーナリーオブジェクトの識別名を引数に取るコンストラクタ。
	 *
	 * @param name	シーナリーオブジェクトの識別名
	 */
	public SceneryObject(String name){
		setName(name);
	}
	
	/**
	 * シーナリーオブジェクト識別名の登録
	 *
	 * @param name	シーナリーオブジェクトの識別名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * シーナリーオブジェクト面の登録
	 *
	 * @param faces	面座標配列(添字は[方角][図形番号][点番号][XYZまたはRGB])
	 * @return	true:登録成功、false:登録失敗
	 */
	public boolean setFaces(int[][][][] faces){
		//方角の数のチェック
		if(faces.length > NUM_DIR){
			return false;
		}
		for(int d = 0; d < faces.length; d++){
			for(int n = 0; n < faces[d].length; n++){
				//四角形+色のチェック
				if(faces[d][n].length != 5){
					return false;
				}
				for(int i = 0; i < faces[d][n].length; i++){
					//XYZまたはRGBのチェック
					if(faces[d][n][i].length != 3){
						return false;
					}
					for(int j = 0; j < faces[d][n][4].length; j++){
						//色情報のチェック
						if(faces[d][n][4][j] < 0 || faces[d][n][4][j] > 255){
							return false;
						}
					}
				}
			}
		}
		//チェックOK
		this.faces = faces;
		return true;
	}
	
	/**
	 * シーナリーオブジェクト識別名の取得
	 *
	 * @return	シーナリーオブジェクト識別名
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * シーナリーオブジェクト面の取得
	 *
	 * @return	面座標配列(添字は[方角][図形番号][点番号][XYZまたはRGB])
	 */
	public int[][][][] getFaces(){
		return faces;
	}
	
	/**
	 * ほとんどデバッグ用に作成したtoString()メソッド
	 */
	@Override
	public String toString(){
		String[] d_str = {"北","東","南","西","地面"};
		
		String str = "name:" + name + "\n";
		for(int d = 0; d < faces.length; d++){
			str += "  " + d_str[d] + "\n";
			for(int n = 0; n < faces[d].length; n++){
				str += "      ";
				for(int i = 0; i < faces[d][n].length; i++){
					str += "" + faces[d][n][i][0];
					for(int j = 1; j < faces[d][n][i].length; j++){
						str += "," + faces[d][n][i][j];
					}
					str += " ";
				}
				str += "\n";
			}
		}
		return str;
	}
}
