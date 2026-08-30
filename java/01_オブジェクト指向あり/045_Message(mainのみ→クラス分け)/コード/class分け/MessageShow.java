import java.io.*;

class MessageShow{
	public String message;
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void show(){
		System.out.println();
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < i; j++){
				System.out.print("@");
			}
			System.out.println(message);
		}
	}
}
