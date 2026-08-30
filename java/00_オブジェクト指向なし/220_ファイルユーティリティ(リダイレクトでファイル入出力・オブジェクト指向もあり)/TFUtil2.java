import java.io.*;

/**
 テキストファイル変換プログラム
 @auther 山田 洋
*/
public class TFUtil2{
	public static void main(String[] args) throws IOException{
		if(args.length < 1){
			return;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		switch(args[0]){
		case "1":	//コピー
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
			break;
		case "2":	//暗号化
			while((line = br.readLine()) != null){
				StringBuffer wline = new StringBuffer();
				for(int i = 0; i < line.length(); i++){
					//文字コードに5を足す
					int c = (int)line.charAt(i) + 5;
					//足したコードを16進数の文字列に変換
					StringBuffer hex = new StringBuffer(Integer.toHexString(c));
					//16進数を4桁に整える
					while(hex.length() < 4){
						hex.insert(0, "0");
					}
					//4桁16進数を書き込み文字列に追加
					wline.append(hex);
				}
				System.out.println(wline);
			}
			break;
		case "3":	//復号化
			while((line = br.readLine()) != null){
				StringBuffer wline = new StringBuffer();
				for(int i = 0; i < line.length(); i += 4){
					//4桁16進数文字列を取り出す
					String code = line.substring(i, i + 4);
					//16進数を10進数変換し、5を引いて元の文字コードにする
					char c = (char)(Integer.parseUnsignedInt(code, 16) - 5);
					//戻った文字を書き込み文字列に追加
					wline.append(c);
				}
				System.out.println(wline);
			}
			break;
		}
	}
}
