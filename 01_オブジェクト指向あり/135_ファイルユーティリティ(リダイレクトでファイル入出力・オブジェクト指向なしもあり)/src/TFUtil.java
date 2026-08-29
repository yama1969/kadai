import java.io.*;

/**
 テキストファイル変換プログラム
 @auther 山田 洋
*/
public class TFUtil{
	public static void main(String[] args) throws IOException{
		//各機能を担うオブジェクトの配列を作成
		//機能追加時はここだけを編集。
		TFileFunc[] func = {
			new Copy(),			//1:コピー
			new Encrypt(),		//2:暗号化
			new Decrypt()		//3:復号化
		};
		
		//機能番号の取得
		if(args.length < 1){
			return;
		}
		int no = Integer.parseInt(args[0]);
		
		//実行
		IO io = new IO();
		func[no - 1].exec(io);
	}
}
