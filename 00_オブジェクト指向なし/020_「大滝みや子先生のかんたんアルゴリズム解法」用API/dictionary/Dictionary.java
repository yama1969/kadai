import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *	英単語辞書の読込み・書出しを担うクラスです。
 *	このクラスはインスタンス化できません。
 *
 *	@author	山田洋
 *	@date	2009/12/2
 */
public class Dictionary{
	/** インスタンス化禁止 */
	private Dictionary(){}
	
	/**
	 *	英単語辞書を読込みます。<br>
	 *	第1引数で指定された英単語辞書ファイルを開き、そのファイルからレコードを
	 *	読込んで、第2・第3引数で渡された配列に、それぞれ英単語と訳語を代入
	 *	します。ファイル上のレコード数が配列のサイズを越える場合は、配列が
	 *	いっぱいになった時点で読込みを停止します。<br>
	 *	なお、ファイルが見つからないなどの例外が発生した場合、その旨の
	 *	メッセージをコンソールへ出力します。
	 *
	 *	@param filename	英単語辞書ファイル名
	 *	@param eitan	英単語配列
	 *	@param yaku		訳語配列
	 *	@return			読込んだ単語数(=レコード数)
	 */
	public static int readFile(String filename, String[] eitan, String[] yaku){
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String record = null;
			int n = 0;
			while( ((record = br.readLine()) != null) && n < eitan.length ){
				String[] fields = record.split(",");
				eitan[n] = fields[0];
				yaku[n] = fields[1];
				n++;
			}
			fr.close();
			br.close();
			return n;
		}catch(FileNotFoundException e){
			System.out.println("入力ファイルが存在しません。");
		}catch(IOException e){
			System.out.println("ファイル入力エラーです。");
		}
		return 0;
	}
	
	/**
	 *	英単語辞書を書出します。<br>
	 *	第1引数で指定された英単語辞書ファイルを開き、第2・第3引数で渡された
	 *	配列に格納されている英単語と訳語からレコードを作成して、そのレコードを
	 *	英単語辞書ファイルへ書出します。指定された英単語辞書ファイルが既存の
	 *	場合、既存ファイルは上書きされます。<br>
	 *	なお、ファイルが作成できないなどの例外が発生した場合、その旨の
	 *	メッセージをコンソールへ出力します。
	 *
	 *	@param filename	英単語辞書ファイル名
	 *	@param eitan	英単語配列
	 *	@param yaku		訳語配列
	 *	@param n		書出す英単語数
	 *	@return			true:書出し正常、false:書出し失敗
	 *	@exception ArrayIndexOutOfBoundsException	配列サイズより大きな単語数を指定した場合
	 */
	public static boolean writeFile(String filename, String[] eitan, String[] yaku, int n){
		try{
			PrintWriter pw = new PrintWriter(filename);
			for(int i = 0; i < n; i++){
				pw.println(eitan[i] + "," + yaku[i]);
			}
			pw.flush();
			return true;
		}catch(FileNotFoundException e){
			System.out.println("出力ファイルが作成できません。");
		}
		return false;
	}
}
