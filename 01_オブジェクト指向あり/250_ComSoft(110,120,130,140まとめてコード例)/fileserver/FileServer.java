package fileserver;

import connect.Controller;
import connect.connectGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileServer implements connectGUI{

	private Controller	controll;
	private String currentDir;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new FileServer();
	}
	
	public FileServer(){
		controll = new Controller(this);
	}
	
	public void receiveMessage(String strMess){
		if(strMess.equals("dir")){
			getDirFiles();
		}else if(strMess.substring(0,3).equals("cd ")){
			changeCurrentDir(strMess.substring(3).trim());
		}else if(strMess.substring(0,4).equals("get ")){
			getFile(strMess.substring(4).trim());
		}else if(strMess.equals("exit")){
			exitFileServer();
		}
	}
	
	public void receiveConnect(String strHost){
		System.out.println(strHost+"が接続しました。");
		currentDir = "C:\\";
	}
	
	//ファイル一覧取得
	private void getDirFiles(){
		File dir = new File(currentDir);
		String[] files = null;
		int cnt;

		try{
			files = dir.list();
		}catch(SecurityException se){
			controll.sendMessage("Error");
			return;
		}
		
		for(cnt = 0; cnt < files.length; cnt++){
			dir = new File(currentDir+files[cnt]);
			if(dir.isDirectory()){
				files[cnt] = "<" + files[cnt] + ">";
			}
			controll.sendMessage(files[cnt]);
		}
		controll.sendMessage("\\");
	}
	
	//カレントディレクトリ変更
	private void changeCurrentDir(String strDir){
		File dir = new File(strDir);
		if(!dir.isDirectory()){
			controll.sendMessage("Error");
		}else{
			currentDir = strDir;
			controll.sendMessage("ok");
		}
	}
	
	//ファイル取得
	private void getFile(String strFileName){
		File file = new File(currentDir + strFileName);
		int dat;
		
		try{
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			while( (dat = bis.read()) != -1 ){
				controll.sendMessage(Byte.toString((byte)dat));
			}
			controll.sendMessage("EOF");
		}catch(FileNotFoundException fe){
			controll.sendMessage("Error");
		}catch(IOException ie){
			controll.sendMessage("Error");
		}
	}
	
	//切断要求
	private void exitFileServer(){
		controll.end_conn();
		controll = new Controller(this);
	}
}
