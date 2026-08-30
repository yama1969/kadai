import java.io.*;

class Greeting3{
	public static void main(String[] args) throws IOException{
		MessageInput mi = new MessageInput();
		MessageShow ms = new MessageShow();
		
		String m = mi.input();
		ms.setMessage(m);
		ms.show();
	}
	
}
