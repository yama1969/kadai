package fileclient.receiverframe;

import java.util.ArrayList;

public interface MessageReceiver {
	//受信処理。str:受信データ。受信データをArrayListに入れる。一連の処理終了ならtrueを返す。
	public abstract boolean execMessage(String str) throws ReceiveException ;
	
	//一連の処理で得られた結果を納めたArrayListを返す。
	public abstract ArrayList getResult();
}
