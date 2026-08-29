import java.io.*;

class Greeting2{
	public static void main(String[] args) throws IOException{
		String m = input();
		show(m);
	}
	
	public static String input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("メッセージを入力してください。=> ");
		String mess = br.readLine();
		
		return mess;
	}
	
	public static void show(String m){
		System.out.println();
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < i; j++){
				System.out.print("　");
			}
			System.out.println(m);
		}
	}
}
