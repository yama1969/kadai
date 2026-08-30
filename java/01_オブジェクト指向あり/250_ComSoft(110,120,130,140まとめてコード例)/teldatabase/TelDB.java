package teldatabase;

import connect.connectGUI;
import connect.Controller;

import java.util.ArrayList;
import java.util.Iterator;

public class TelDB implements connectGUI{

	private int port = 23;
	private Controller	controll;
	private ExecSQL esql;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new TelDB();
	}
	
	public TelDB(){
		controll = new Controller(this, port);
		esql = new ExecSQL();
	}
	
	public void receiveConnect(String host){
		System.out.println(host+"が接続しました。");
		controll.sendMessage("Hello!");
	}
	
	public void receiveMessage(String strComm){
		ArrayList result = null;
		Iterator i;
		
		if(strComm.equals("exit")){
			controll.end_conn();
			controll = new Controller(this, port);
			return;
		}
		
		try{
			result = esql.execSQL(strComm);
			i = result.iterator();
			while(i.hasNext()){
				controll.sendMessage((String)i.next());
			}
		}catch(SqlException se){
			String str = se.getMessage();
			controll.sendMessage("SyntaxError:"+str);
			System.out.println(str);
		}catch(DataException de){
			String str = de.getMessage();
			controll.sendMessage("DataError:"+str);
			System.out.println(str);
		}
	}
}
