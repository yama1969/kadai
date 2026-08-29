package carmove.gui.gui3D;

import java.awt.Graphics;

public class SceneryDrawerENE implements SceneryDrawer{
	public void drawScenery(double x, double y, double direction, SceneryMap map, Graphics g){
		SceneryObject[][] objects = map.getMap();
		int width = map.getWidth();
		int height = map.getHeight();
		int depth = map.getDepth();
		
		//北東～東を向いている
		//  X座標の大から小へ、Y座標の大(添字の小から大)から小へ描く
		
		//  X座標からY座標を取りたいので、Y/Xの傾きを求める
		double wide = Math.atan((double)width / (double)depth);
		double r_slope = 1.0 / Math.tan(direction + wide);
		double l_slope = 1.0 / Math.tan(direction - wide);
		
		//X座標の大から小へ、オブジェクトを順番に取り出す。
		//map_y		オブジェクトマップの添字
		//map_x
		//space_y	描くマップグリッドの空間座標
		//space_x
		int map_x_max = objects[0].length - 1;
		for(int map_x = map_x_max; map_x > 0; map_x--){
			int space_x = map_x * SceneryMap.GRID;
			//描画が車の後ろにきたか
			if(space_x < (int)x){
				break;
			}
			
			//マップ配列の、現在処理中のX座標における、描画すべきY座標の範囲を求める(つまり、視界に入る範囲)
			double space_y_min = ((double)space_x - x) * r_slope + y;
			double space_y_max = ((double)space_x - x) * l_slope + y;
			int map_y_max = (objects.length - 1) - (int)Math.floor(space_y_min / (double)SceneryMap.GRID);
			int map_y_min = (objects.length - 1) - (int)Math.ceil(space_y_max / (double)SceneryMap.GRID);
			
			if(map_y_max > objects.length - 1){
				map_y_max = objects.length - 1;
			}else if(map_y_max < 0){
				map_y_max = 0;
			}
			if(map_y_min > objects.length - 1){
				map_y_min = objects.length - 1;
			}else if(map_y_min < 0){
				map_y_min = 0;
			}
			
			//Y座標の大から小(添字の小から大)へ、オブジェクトを順番に取り出す
			for(int map_y = map_y_min; map_y < map_y_max + 1; map_y++){
				int space_y = map_y * SceneryMap.GRID;
				
				if(objects[map_y][map_x] != null){
					int[][][][] faces = objects[map_y][map_x].getFaces();
					
					//地面、南面、西面の順に描画
					if(faces[SceneryObject.GROUND] != null){
						for(int i = 0; i < faces[SceneryObject.GROUND].length; i++){
							SceneryTrans.draw(x, y, map.getViewZ(), direction, width, height, depth, space_x, space_y, faces[SceneryObject.GROUND][i], g);
						}
					}
					if(faces[SceneryObject.SOUTH] != null){
						for(int i = 0; i < faces[SceneryObject.SOUTH].length; i++){
							SceneryTrans.draw(x, y, map.getViewZ(), direction, width, height, depth, space_x, space_y, faces[SceneryObject.SOUTH][i], g);
						}
					}
					if(faces[SceneryObject.WEST] != null){
						for(int i = 0; i < faces[SceneryObject.WEST].length; i++){
							SceneryTrans.draw(x, y, map.getViewZ(), direction, width, height, depth, space_x, space_y, faces[SceneryObject.WEST][i], g);
						}
					}
				}
			}
		}
	}
}
