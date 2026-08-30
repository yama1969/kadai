/*
 * 「とにかく温め」メニュー
 */
public class Tonikaku implements Menu{
	@Override
	public void execMenu(){
		System.out.println("出力「強」");
		
		//10秒待つ (Thread.sleep()を使っても良い)
		long time = System.currentTimeMillis();
		time += 10000L;
		while(time > System.currentTimeMillis()){
		}
		
		System.out.println("動作を終了");
	}
}

