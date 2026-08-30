package fileclient.receiver;

import fileclient.receiverframe.MessageReceiver;
import fileclient.receiverframe.ReceiverManager;
import fileclient.receiverframe.ReceiveException;

import java.util.ArrayList;

public class DirReceiver implements MessageReceiver {

	private ReceiverManager rm;
	private ArrayList alResult;
	
	public DirReceiver(){
		rm = new ReceiverManager();
		alResult = new ArrayList();
	}
	
	public DirReceiver(ReceiverManager rm){
		this.rm = rm;
		alResult = new ArrayList();
	}
	
	public boolean execMessage(String str) throws ReceiveException{
		if(str.equals("Error")){
			rm.completeExec();
			throw new ReceiveException("ファイル一覧が取得できません。");
		}else if(str.equals("\\")){
			rm.completeExec();
			return true;
		}
		
		alResult.add(str);
		return false;
	}
	
	public ArrayList getResult(){
		ArrayList al = alResult;
		alResult = new ArrayList();
		return al;
	}
}
