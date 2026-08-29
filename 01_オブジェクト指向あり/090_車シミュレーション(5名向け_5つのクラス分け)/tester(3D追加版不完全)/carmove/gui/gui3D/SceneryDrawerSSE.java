package carmove.gui.gui3D;

import java.awt.Graphics;

public class SceneryDrawerSSE implements SceneryDrawer{
	public void drawScenery(double x, double y, double direction, SceneryMap map, Graphics g){
		SceneryObject[][] objects = map.getMap();
		int width = map.getWidth();
		int height = map.getHeight();
		int depth = map.getDepth();
		
		//南東～南を向いている
		//  Y座標の小から大(添字の大から小)へ、X座標の大から小へ描く
		
		//  Y座標からX座標を取りたいので、X/Yの傾きを求める
		double wide = Math.atan((double)width / (double)depth);
		double r_slope = Math.tan(direction + wide);
		double l_slope = Math.tan(direction - wide);
		
		//Y座標の小から大(添字の大から小)へ、オブジェクトを順番に取り出す。
		//map_y		オブジェクトマップの添字
		//map_x
		//space_y	描くマップグリッドの空間座標
		//space_x
		int map_y_max = objects.length - 1;
		for(int map_y = map_y_max; map_y > -1; map_y--){
			int space_y = (map_y_max - map_y) * SceneryMap.GRID;
			//描画が車の後ろにきたか
			if(space_y > (int)y){
				break;
			}
			
			//マップ配列の、現在処理中のY座標における、描画すべきX座標の範囲を求める(つまり、視界に入る範囲)
			double space_x_min = ((double)space_y - y) * r_slope + x;
			double space_x_max = ((double)space_y - y) * l_slope + x;
			int map_x_max = (int)Math.ceil(space_x_max / (double)SceneryMap.GRID);
			int map_x_min = (int)Math.floor(space_x_min / (double)SceneryMap.GRID);
			
			if(map_x_max > objects[0].length - 1){
				map_x_max = objects[0].length - 1;
			}else if(map_x_max < 0){
				map_x_max = 0;
			}
			if(map_x_min > objects[0].length - 1){
				map_x_min = objects[0].length - 1;
			}else if(map_x_min < 0){
				map_x_min = 0;
			}
			
			//X座標の大から小へ、オブジェクトを順番に取り出す
			for(int map_x = map_x_max; map_x > map_x_min - 1; map_x--){
				int space_x = map_x * SceneryMap.GRID;
				
				if(objects[map_y][map_x] != null){
					int[][][][] faces = objects[map_y][map_x].getFaces();
					
					//地面、西面、北面の順に描画
					if(faces[SceneryObject.GROUND] != null){
						for(int i = 0; i < faces[SceneryObject.GROUND].length; i++){
							SceneryTrans.draw(x, y, map.getViewZ(), direction, width, height, depth, space_x, space_y, faces[SceneryObject.GROUND][i], g);
						}
					}
					if(faces[SceneryObject.WEST] != null){
						for(int i = 0; i < faces[SceneryObject.WEST].length; i++){
							SceneryTrans.draw(x, y, map.getViewZ(), direction, width, height, depth, space_x, space_y, faces[SceneryObject.WEST][i], g);
						}
					}
					if(faces[SceneryObject.NORTH] != null){
						for(int i = 0; i < faces[SceneryObject.NORTH].length; i++){
							SceneryTrans.draw(x, y, map.getViewZ(), direction, width, height, depth, space_x, space_y, faces[SceneryObject.NORTH][i], g);
						}
					}
				}
			}
		}
	}
}
