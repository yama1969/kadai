/*
 コピー機能クラス
*/
public class Copy extends TFileFunc{
	/*
	 コピー実行
	*/
	@Override
	public void exec(IO io){
		String line = null;
		while((line = io.read()) != null){
			io.write(line);
		}
	}
}
