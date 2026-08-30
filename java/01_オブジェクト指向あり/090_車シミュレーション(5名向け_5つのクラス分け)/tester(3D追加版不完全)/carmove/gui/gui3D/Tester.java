package carmove.gui.gui3D;

public class Tester{
	public static void main(String[] args){
		SceneryMap sm = new SceneryMap();
		SceneryObject[][] map = sm.getMap();
		
		//読み込まれたマップの表示
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] != null){
					System.out.print(map[i][j].getName());
				}else{
					System.out.print("　");
				}
			}
			System.out.println();
		}
		
		//マップに配置された各オブジェクトの座標表示
		/*
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				if(map[i][j] != null){
					System.out.println("object:" + map[i][j].getName());
					int[][][][] faces = map[i][j].getFaces();
					for(int d = 0; d < faces.length; d++){
						System.out.println("direction:" + d);
						for(int n = 0; n < faces[d].length; n++){
							System.out.println("face:" + n);
							for(int p = 0; p < faces[d][n].length; p++){
								System.out.println("point:" + p);
								System.out.println(faces[d][n][p][0] + "," + faces[d][n][p][1] + "," + faces[d][n][p][2]);
							}
						}
					}
				}
			}
		}
		*/
		
//		SceneryDrawerNNE sd = new SceneryDrawerNNE();
//		sd.drawScenery(0.0, -1.0, 23.0 * Math.PI / 180.0, sm);
	}
}
