import java.io.*;

/*
 文字列入出力を担うクラス
 コンストラクタの引数で入力オブジェクトと出力オブジェクトを指定する。
 デフォルトは標準出力および標準入力。
*/
public class IO{
	private BufferedReader br = null;	//入力オブジェクト
	private PrintStream ps = null;		//出力オブジェクト
	
	/*
	 コンストラクタ
	 入力オブジェクトおよび出力オブジェクトの指定を行う。
	 デフォルトは標準出力および標準入力。
	*/
	public IO(){
		this(null, null);
	}
	
	public IO(BufferedReader br){
		this(br, null);
	}
	
	public IO(PrintStream ps){
		this(null, ps);
	}
	
	public IO(BufferedReader br, PrintStream ps){
		if(br == null){
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}else{
			this.br = br;
		}
		
		if(ps == null){
			this.ps = System.out;
		}else{
			this.ps = ps;
		}
	}
	
	/*
	 1行を読み込む
	 不正時、およびストリームエンド時はnullを返す。
	*/
	public String read(){
		String line = null;
		try{
			line = br.readLine();
		}catch(Exception e){
		}
		return line;
	}
	
	/*
	 1行を書き出す
	*/
	public void write(String line){
		System.out.println(line);
	}
	
	public void write(StringBuffer line){
		System.out.println(line);
	}
}
