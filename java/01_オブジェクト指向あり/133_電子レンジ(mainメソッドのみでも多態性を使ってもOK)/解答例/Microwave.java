import java.util.Scanner;
import java.util.HashMap;

/*
 * 電子レンジの動作プログラム
 */
public class Microwave{
	public static void main(String[] args){
		//メニューリスト作成(HashMap使うなんてずるい？ まぁ頑張ればArrayListでも・・・)
		HashMap<Integer, Menu> menuList = new HashMap<Integer, Menu>();
		menuList.put(11, new Tonikaku());
		menuList.put(12, new Jikkuri());
		menuList.put(21, new Kogeme());
		
		//メニュー番号入力
		Menu selectedMenu = null;
		while(selectedMenu == null){
			try{
				System.out.println("[11]とにかく温め");
				System.out.println("[12]じっくり温め");
				System.out.println("[21]焦げ目もきれいに");
				System.out.print("メニュー番号 => ");
				Scanner sc = new Scanner(System.in);
				int no = sc.nextInt();
				
				selectedMenu = menuList.get(no);
				if(selectedMenu == null){
					throw new Exception();
				}
			}catch(Exception e){
				System.out.println("正しいメニュー番号を入力してください。");
				System.out.println();
				System.out.println();
			}
		}
		
		//メニュー実行
		selectedMenu.execMenu();
	}
}
