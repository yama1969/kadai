package fileclient.receiver;

import fileclient.receiverframe.MessageReceiver;
import fileclient.receiverframe.ReceiverManager;
import fileclient.receiverframe.ReceiveException;

import java.util.ArrayList;

public class GetReceiver implements MessageReceiver {

	private ReceiverManager rm;
	private ArrayList alResult;
	
	public GetReceiver(){
		rm = new ReceiverManager();
		alResult = new ArrayList();
	}
	
	public GetReceiver(ReceiverManager rm){
		this.rm = rm;
		alResult = new ArrayList();
	}
	
	public boolean execMessage(String str) throws ReceiveException{
		if(str.equals("Error")){
			rm.completeExec();
			throw new ReceiveException("ファイルを取得できません。");
		}else if(str.equals("EOF")){
			rm.completeExec();
			return true;
		}
		alResult.add(new Byte(str));
		return false;
	}
	
	public ArrayList getResult(){
		ArrayList al = alResult;
		alResult = new ArrayList();
		return al;
	}
}
