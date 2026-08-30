/*
 * 「焦げ目もきれいに」メニュー
 */
public class Kogeme implements Menu{
	@Override
	public void execMenu(){
		for(int i = 0; i < 3; i++){
			System.out.println("出力「強」");
			
			//(5 - 繰り返し回数)秒待つ
			long time = System.currentTimeMillis();
			time += 5000L - (long)i * 1000L;
			while(time > System.currentTimeMillis()){
			}
			
			System.out.println("「オーブン」");
			
			//(3 - 繰り返し回数)秒待つ
			time += 3000L - (long)i * 1000L;
			while(time > System.currentTimeMillis()){
			}
		}
		System.out.println("動作を終了");
	}
}
