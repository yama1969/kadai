/*
 * 「じっくり温め」メニュー
 */
public class Jikkuri implements Menu{
	@Override
	public void execMenu(){
		for(int i = 0; i < 3; i++){
			System.out.println("出力「強」");
			
			//2秒待つ (Thread.sleep()を使っても良い)
			long time = System.currentTimeMillis();
			time += 2000L;
			while(time > System.currentTimeMillis()){
			}
			
			System.out.println("出力「弱」");
			
			//1秒待つ (Thread.sleep()を使っても良い)
			time += 1000L;
			while(time > System.currentTimeMillis()){
			}
		}
		System.out.println("動作を終了");
	}
}
