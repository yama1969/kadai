/*
 復号化機能クラス
*/
public class Decrypt extends TFileFunc{
	/*
	 復号化実行
	*/
	@Override
	public void exec(IO io){
		String line = null;
		while((line = io.read()) != null){
			StringBuffer wline = new StringBuffer();
			int end = 0;
			while(end < line.length()){
				//16進数文字列を取り出す
				int start = end + 1;
				end = line.indexOf('x', start);
				if(end < 0){
					end = line.length();
				}
				String code = line.substring(start, end);
				//16進数を10進数変換し、5を引いて元の文字コードにする
				char c = (char)(Integer.parseUnsignedInt(code, 16) - 5);
				//戻った文字を書き込み文字列に追加
				wline.append(c);
			}
			io.write(wline);
		}
	}
}
