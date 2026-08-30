package carmove.gui.gui3D;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * シーナリーオブジェクトコレクション。言わば、シーナリーオブジェクトのカタログです。
 */
public class SceneryObjCollection{
	/** オブジェクトのリスト */
	private ArrayList<SceneryObject> list_obj;
	
	/**
	 * コンストラクタ。オブジェクトファイルからデータを読込み、オブジェクトリストを作成します。
	 */
	public SceneryObjCollection(){
		list_obj = new ArrayList<SceneryObject>();
		try{
			//オブジェクトファイル開く
			BufferedReader br = new BufferedReader(new FileReader("SceneryObj.dat"));
			String line = null;
			
			//オブジェクト読込み
			while((line = br.readLine()) != null){
				if(line.length() > 1 && line.substring(0, 2).equals("//")){		//コメント行。次の行へ
					continue;
				}
				if(line.equals("")){
					continue;								//空白行。次の行へ
				}
				SceneryObject scenery_obj = new SceneryObject(line);
				
				//オブジェクトの面情報読込み準備
				ArrayList<ArrayList<int[][]>> dat = new ArrayList<ArrayList<int[][]>>();
				dat.add(new ArrayList<int[][]>());	//北・東・南・西・地面用に5つ用意
				dat.add(new ArrayList<int[][]>());
				dat.add(new ArrayList<int[][]>());
				dat.add(new ArrayList<int[][]>());
				dat.add(new ArrayList<int[][]>());
				
				//オブジェクトの面情報読込み
				while((line = br.readLine()) != null){
					if(line.length() > 1 && line.substring(0, 2).equals("//")){	//コメント行。次の行へ
						continue;
					}
					String[] fields = line.split(" +");		//空白(連続あり)で分割
					if(fields.length < 1){
						continue;							//単なる空行。次の行へ
					}
					if(fields[0].equals("END")){			//オブジェクト終了。次のオブジェクトへ
						break;
					}
					if(fields.length < 16){					//データ不足行。とりあえず次の行へ
						continue;
					}
					
					//面方向読込み
					int dir = 0;
					if(fields[0].equals("N")){
						dir = SceneryObject.NORTH;
					}else if(fields[0].equals("E")){
						dir = SceneryObject.EAST;
					}else if(fields[0].equals("S")){
						dir = SceneryObject.SOUTH;
					}else if(fields[0].equals("W")){
						dir = SceneryObject.WEST;
					}else if(fields[0].equals("G")){
						dir = SceneryObject.GROUND;
					}else{
						continue;							//方向不正。とりあえず次の行へ
					}
					
					//面データ読込み
					int[][] points = new int[5][3];
					for(int i = 0; i < 4; i++){				//四角形なので4点の座標を読込む
						try{
							points[i][SceneryObject.X] = Integer.parseInt(fields[i * 3 + 1]);
							points[i][SceneryObject.Y] = Integer.parseInt(fields[i * 3 + 2]);
							points[i][SceneryObject.Z] = Integer.parseInt(fields[i * 3 + 3]);
						}catch(NumberFormatException e){
							//数値異常の場合は0として登録するので、記述は無し
						}
					}
					
					//色データ読込み						//第1添字4は色データ
					try{
						points[4][SceneryObject.RED] = Integer.parseInt(fields[13]);
						points[4][SceneryObject.GREEN] = Integer.parseInt(fields[14]);
						points[4][SceneryObject.BLUE] = Integer.parseInt(fields[15]);
					}catch(NumberFormatException e){
						//異常時は黒(r,g,b=0,0,0)として登録するので、記述無し
					}
					
					//面リストへ登録
					dat.get(dir).add(points);
				}
				
				//作成した面リストを配列に変換(添字は[方角][図形番号][点番号][XYZまたはRGB])
				int[][][][] faces = new int[SceneryObject.NUM_DIR][][][];
				for(int i = 0; i < dat.size(); i++){
					ArrayList<int[][]> list = dat.get(i);
					int num_square = list.size();
					faces[i] = new int[num_square][][];
					for(int j = 0; j < num_square; j++){
						faces[i][j] = list.get(j);
					}
				}
				
				//オブジェクトへ面の登録
				if(!scenery_obj.setFaces(faces)){
					System.out.println("読込まれたオブジェクト情報に不正があります。");	//デバッグプリント
				}
				//コレクションへ追加
				list_obj.add(scenery_obj);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 名前を指定して、シーナリーオブジェクトを得ます。
	 *
	 * @param name	シーナリーオブジェクト識別名
	 * @return	該当するシーナリーオブジェクト(該当ない場合はnull)
	 */
	public SceneryObject getSceneryObjectByName(String name){
		if(list_obj == null || name == null){
			return null;
		}
		for(SceneryObject o : list_obj){
			String get_name = o.getName();
			if(get_name != null){
				if(get_name.equals(name)){
					return o;
				}
			}
		}
		return null;
	}
}
