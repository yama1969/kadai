import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *	アルゴリズムの教科書「大滝みや子先生のかんたんアルゴリズム解法」の54ページの問題
 *	「２次元配列要素への集計」で利用できる、ファイル読込みクラスです。<br>
 *	データファイル名は"CorpInfoFile.csv"です。これはカンマ区切りCSV形式のファイルです。
 */
public class CorpInfo{
	private static ArrayList<String[]> records;
	private static boolean f_read;
	private static int no;
	
	static{
		f_read = false;
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("CorpInfoFile.csv"));
			
			records = new ArrayList<String[]>();
			
			String record = null;
			while((record = br.readLine()) != null){
				records.add(record.split(",", 4));
			}
			no = 0;
			f_read = true;
		}catch(FileNotFoundException e){
			System.out.println("CorpInfoFile.csv is not found.");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 *	このクラスはインスタンス化しません。
	 */
	private CorpInfo(){
	}
	
	/**
	 *	CorpInfoFile.csvから、1件のレコードを読込みます。読込みレコードは自動的に次のレコードへ
	 *	移りますので、このメソッドを呼び出すたびに1件目、2件目、・・・と順次読込むことができます。
	 *	読込まれたレコードのデータはString配列の各要素に格納され、その配列がリターンされます。
	 *
	 *	@return 読込みレコードの各フィールドが入ったString配列
	 */
	public static String[] readRecord(){
		if(!f_read){
			return null;
		}
		
		String[] record = null;
		try{
			record = records.get(no);
			no++;
		}catch(IndexOutOfBoundsException e){
		}
		return record;
	}
	
	/**
	 *	CorpInfoFile.csvの読込みレコードを先頭へ移動します。このメソッドを呼び出した後に
	 *	readRecord()メソッドを呼び出すと、先頭レコードが読込まれます。
	 */
	public static void reset(){
		no = 0;
	}
}
