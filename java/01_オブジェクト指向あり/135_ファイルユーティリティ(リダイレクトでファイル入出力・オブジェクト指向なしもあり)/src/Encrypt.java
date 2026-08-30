/*
 暗号化機能クラス
*/
public class Encrypt extends TFileFunc{
	/*
	 暗号化実行
	*/
	@Override
	public void exec(IO io){
		String line = null;
		while((line = io.read()) != null){
			StringBuffer wline = new StringBuffer();
			for(int i = 0; i < line.length(); i++){
				//文字コードに5を足す
				int c = (int)line.charAt(i) + 5;
				//足したコードを16進数の文字列に変換して書き込み文字列に追加
				wline.append("x" + Integer.toHexString(c));
			}
			io.write(wline);
		}
	}
}
