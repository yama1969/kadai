package fileclient.receiver;

import fileclient.receiverframe.MessageReceiver;
import fileclient.receiverframe.ReceiverManager;
import fileclient.receiverframe.ReceiveException;

import java.util.ArrayList;

public class FileClientReceiverManager extends ReceiverManager {
	
	public final static int CD = 1;
	public final static int DIR = 2;
	public final static int GET = 3;
	
	private CdReceiver	cd;
	private DirReceiver	dir;
	private GetReceiver	get;
	private MessageReceiver	currentReceiver;
	private int currentNo;
	
	private boolean exec;
	
	public FileClientReceiverManager(){
		cd = new CdReceiver(this);
		dir = new DirReceiver(this);
		get = new GetReceiver(this);
		
		currentReceiver = null;
		currentNo = 0;
		exec = false;
	}
	
	public synchronized MessageReceiver changeReceiver(int rec){
		while(exec){
			try{
				wait();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		switch(rec){
			case CD:
				currentReceiver = cd;
				currentNo = CD;
				exec = true;
				break;
			case DIR:
				currentReceiver = dir;
				currentNo = DIR;
				exec = true;
				break;
			case GET:
				currentReceiver = get;
				currentNo = GET;
				exec = true;
				break;
			default:
				currentReceiver = null;
				exec = false;
		}
		notifyAll();
		return currentReceiver;
	}
	
	public synchronized void completeExec(){
		exec = false;
		notifyAll();
	}
	
	public boolean execMessage(String str) throws ReceiveException{
		if(currentReceiver != null){
			return currentReceiver.execMessage(str);
		}
		return true;
	}
	
	public ArrayList getResult(){
		ArrayList al = null;
		if(currentReceiver != null){
			al = currentReceiver.getResult();
		}
		return al;
	}
	
	public int getCurrentReceiver(){
		return currentNo;
	}
}
