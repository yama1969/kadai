package fileclient.receiver;

import fileclient.receiverframe.MessageReceiver;
import fileclient.receiverframe.ReceiverManager;
import fileclient.receiverframe.ReceiveException;

import java.util.ArrayList;

public class CdReceiver implements MessageReceiver {

	private ReceiverManager rm;
	
	public CdReceiver(){
		rm = new ReceiverManager();
	}
	
	public CdReceiver(ReceiverManager rm){
		this.rm = rm;
	}
	
	public boolean execMessage(String str) throws ReceiveException{
		rm.completeExec();
		if(str.equals("ok")){
		}else if(str.equals("Error")){
			throw new ReceiveException("カレントディレクトリを変更できません。");
		}else{
			throw new ReceiveException("恐らくカレントディレクトリを変更できません。\n不明な応答です。\n" + str);
		}
		return true;
	}
	
	public ArrayList getResult(){
		return null;
	}
}
