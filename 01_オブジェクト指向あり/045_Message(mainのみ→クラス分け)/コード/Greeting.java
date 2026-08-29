import java.io.*;

class Greeting{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("メッセージを入力してください。=> ");
		String mess = br.readLine();
		
		System.out.println();
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < i; j++){
				System.out.print("　");
			}
			System.out.println(mess);
		}
	}
}
