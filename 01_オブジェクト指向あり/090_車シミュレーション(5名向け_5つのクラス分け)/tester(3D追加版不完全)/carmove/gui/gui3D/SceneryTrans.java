package carmove.gui.gui3D;

import java.awt.Color;
import java.awt.Graphics;

public class SceneryTrans{
	public static void draw(double x, double y, double z, double direction, int width, int height, int depth, int space_x, int space_y, int[][] points, Graphics g){
		int[][] screen_p = new int[points.length][2];
		int n = points.length - 1;
		
		//点のスクリーン座標計算
		for(int i = 0; i < n; i++){
			//点の空間XYZ座標を得る(車からの相対座標)
			double sx = (double)(space_x + points[i][SceneryObject.X]) - x;
			double sy = (double)(space_y + points[i][SceneryObject.Y]) - y;
			double sz = (double)points[i][SceneryObject.Z] - z;
			
			//視点の向きになるよう回転
			double w = sx;
			sx = sx * Math.cos(direction) - sy * Math.sin(direction);
			sy = w * Math.sin(direction) + sy * Math.cos(direction);
			
			//スクリーン座標系に変換
			double dep_rate = sy / (double)depth;
			screen_p[i][0] = (int)(sx / dep_rate);
			screen_p[i][1] = (int)((double)height / 2.0 - (sz / dep_rate));
		}
		
		//色の作成
		Color color = new Color(points[n][0], points[n][1], points[n][2]);
		
		//描画
		g.setColor(color);
		
		int[][] xy_swap = new int[2][screen_p.length];
		for(int i = 0; i < screen_p.length; i++){
			for(int j = 0; j < screen_p[i].length; j++){
				xy_swap[j][i] = screen_p[i][j];
			}
		}
		g.fillPolygon(xy_swap[0], xy_swap[1], 4);
	}
}
