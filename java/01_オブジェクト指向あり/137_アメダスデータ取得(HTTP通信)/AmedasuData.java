/*
 気象庁のWebサーバからアメダスの降水量データ(CSV)を得るプログラム
 
 PrintWriterとBufferedReaderを作ってしまえば、
 あとはファイル入出力もネット入出力も同じだ、ということを示すために使用
 
 それと、HTTPに従ったプログラミングとは例えばこういうこと、を示すため
 
 気象庁サーバからデータを得る、って何だかカッコよくない？
*/

import javax.net.ssl.SSLSocketFactory;
import javax.net.SocketFactory;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class AmedasuData{
	public static void main(String[] args){
		String server = "www.data.jma.go.jp";
		SocketFactory factory = SSLSocketFactory.getDefault();
		try(
			Socket soc = factory.createSocket(server, 443);
			PrintWriter pw = new PrintWriter(soc.getOutputStream());
			InputStreamReader isr = new InputStreamReader(soc.getInputStream(), "Shift-JIS");
			
			BufferedReader br = new BufferedReader(isr);
		){
			pw.println("GET /stats/data/mdrr/pre_rct/alltable/pre24h00_rct.csv HTTP/1.1");
			pw.println("Host: " + server);
			pw.println("Connection: close");
			pw.println();
			pw.flush();
			
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
