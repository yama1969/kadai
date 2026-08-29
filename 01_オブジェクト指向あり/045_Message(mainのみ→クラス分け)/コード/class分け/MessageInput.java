import java.io.*;

class MessageInput{
	public String input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("メッセージを入力してください。=> ");
		String mess = br.readLine();
		
		return mess;
	}
}
