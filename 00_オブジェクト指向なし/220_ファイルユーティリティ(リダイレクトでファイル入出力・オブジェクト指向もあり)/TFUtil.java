import java.io.*;

/**
 テキストファイル変換プログラム
 @auther 山田 洋
*/
public class TFUtil{
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
					wline.append((char)(line.charAt(i) + 5));
				}
				System.out.println(wline);
			}
			break;
		case "3":	//復号化
			while((line = br.readLine()) != null){
				StringBuffer wline = new StringBuffer();
				for(int i = 0; i < line.length(); i++){
					wline.append((char)(line.charAt(i) - 5));
				}
				System.out.println(wline);
			}
			break;
		}
	}
}
