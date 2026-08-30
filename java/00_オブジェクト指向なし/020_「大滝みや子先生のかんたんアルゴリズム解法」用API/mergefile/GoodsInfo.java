import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *	カンマ区切りのテキストファイルに対し、レコードの読込みと書出しを行うクラスです。
 *	ファイルは、適宜任意のファイルを指定できます。<br>
 *	なお、このクラスはインスタンス化できません。
 */
public class GoodsInfo{
	/** オープンした読込みファイル名のリスト */
	private static ArrayList<String> listReadFileName = new ArrayList<String>();
	/** オープンした読込みファイルのリーダーリスト */
	private static ArrayList<BufferedReader> listReader = new ArrayList<BufferedReader>();
	/** オープンした書出しファイル名のリスト */
	private static ArrayList<String> listWriteFileName = new ArrayList<String>();
	
	/** インスタンス化禁止 */
	private GoodsInfo(){}
	
	/**
	 *	ファイルからレコードを1件読込みます。<br>
	 *	引数で指定されたファイルから、レコードを1件読込み、
	 *	レコードの各フィールドをString配列に分解してリターンします。<br>
	 *	ファイルエンドなどでレコードが読込まれなかった場合は、nullをリターン
	 *	します。<br>
	 *	ファイルは、カレントディレクトリ内のものに限ります。
	 *
	 *	@param filename	ファイル名
	 *	@return 読込まれたレコード(読込まれなかった場合はnull)
	 */
	public static String[] readRecord(String filename){
		//準備
		String[] fields = null;							//読込まれたフィールド
		BufferedReader br = null;						//指定ファイルのリーダー
		int index = listReadFileName.indexOf(filename);	//オープン中ファイルの識別番号
		
		//指定ファイルのリーダーオブジェクト取得
		if(index > -1){
			//指定ファイルがオープン済み
			br = listReader.get(index);
		}else{
			//指定ファイルは未オープン
			try{
				br = new BufferedReader(new FileReader(filename));
				listReadFileName.add(filename);
				listReader.add(br);
			}catch(FileNotFoundException e){
				System.out.println("ファイル「" + filename + "」が見つかりません。");
			}
		}
		
		//フィールド読込み
		try{
			String record = br.readLine();
			if(record != null){
				fields = record.split(",");
			}
		}catch(IOException e){
			System.out.println("ファイル読込みエラーが発生しました。");
		}
		
		return fields;
	}
	
	/**
	 *	ファイルへレコードを1件書出します。<br>
	 *	第1引数で指定されたファイルへ、第2引数で与えられたレコードを
	 *	書出します。<br>
	 *	ファイルは、最初のレコードを書出すときに新規作成されます。
	 *	最初のレコードを書出すときに、指定したファイルと同名のファイルがあれば、
	 *	既存ファイルを破棄した上で新規作成します。従って、「同一ファイルを
	 *	読込みながら書出しする」ことは出来ませんので、ご注意下さい。<br>
	 *	ファイルは、カレントディレクトリ内のものに限ります。<br>
	 *	正常に書出された場合はtrueを、書出されなかった場合はfalseを
	 *	リターンします。
	 *
	 *	@param filename	ファイル名
	 *	@param fields	各フィールドのString配列
	 *	@return true:書出し成功、false:書出し失敗
	 */
	public static boolean writeRecord(String filename, String[] fields){
		//準備
		PrintWriter pw = null;
		int index = listWriteFileName.indexOf(filename);	//オープン中ファイルの識別番号
		
		//指定ファイルのライターオブジェクト生成
		try{
			if(index > -1){
				//指定ファイルは2件目以降の書出し(追加モード、自動書出し)
				pw = new PrintWriter(new FileWriter(filename, true), true);
			}else{
				//指定ファイルは1件目の書出し(上書モード、自動書出し)
				pw = new PrintWriter(new FileWriter(filename, false), true);
				listWriteFileName.add(filename);
			}
		}catch(IOException e){
			System.out.println("ファイルを書出し用に開くことが出来ませんでした。");
			return false;
		}
		
		//ファイルへのレコード書出し
		if(fields.length < 1){
			return false;
		}
		StringBuffer str = new StringBuffer(fields[0]);
		for(int i = 1; i < fields.length; i++){
			str.append(",");
			str.append(fields[i]);
		}
		pw.println(str.toString());
		
		return true;
	}
}
