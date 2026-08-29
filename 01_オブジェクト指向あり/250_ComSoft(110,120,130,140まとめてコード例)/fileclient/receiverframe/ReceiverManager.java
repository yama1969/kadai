package fileclient.receiverframe;

import java.util.ArrayList;

public class ReceiverManager implements MessageReceiver {
	
	public MessageReceiver changeReceiver(int rec){
		return null;
	}
	
	public void completeExec(){
	}
	
	public int getCurrentReceiver(){
		return -1;
	}
	
	public boolean execMessage(String str) throws ReceiveException {
		return true;
	}
	
	public ArrayList getResult(){
		return null;
	}
}
