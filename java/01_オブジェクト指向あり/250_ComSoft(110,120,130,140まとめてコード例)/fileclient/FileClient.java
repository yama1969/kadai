package fileclient;

import connect.ConnectException;
import connect.connectGUI;
import connect.Controller;

import fileclient.receiver.CdReceiver;
import fileclient.receiver.DirReceiver;
import fileclient.receiver.GetReceiver;
import fileclient.receiver.FileClientReceiverManager;
import fileclient.receiverframe.ReceiverManager;
import fileclient.receiverframe.MessageReceiver;
import fileclient.receiverframe.ReceiveException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class FileClient extends JFrame implements connectGUI {

	//ラベル・ボタン等の固定表示文字列
	private static final String	STR_APPLINAME = "Get File Client";
	private static final String	STR_CONNECT_LABEL = "　ホスト名";
	private static final String	STR_DIR_LABEL = "　カレントディレクトリ";
	private static final String	STR_SAVE_LABEL = "　保存先";
	private static final String	STR_CONNECT_BUTTON = "接続";
	private static final String	STR_DIRSAVE_BUTTON = "変更";
	private static final String	STR_GET_BUTTON = "ファイル取得／ディレクトリ変更";
	private static final String	STR_HOST_DEFAULT = "localhost";
	private static final String	STR_SAVE_DEFAULT = "c:\\receive";
	private static final String 	STR_DIR_DEFAULT = "c:\\";
	
	private Controller		controll;
	private MessageBox		messageBox;
	private ReceiverManager	receiverManager;
	private MessageReceiver	receiver;
	private String			saveFileName;
	
	private JLabel			lblHost;
	private JTextField		txtHost;
	private JButton			btnConnect;
	private JLabel			lblDir;
	private JTextField		txtDir;
	private JButton			btnChangeDir;
	private JLabel			lblSave;
	private JTextField		txtSave;
	private JButton			btnSave;
	private JPanel			pnlLabel;
	private JPanel			pnlText;
	private JPanel			pnlButton;
	private JPanel			pnlHedder;
	private JList			lstFiles;
	private JScrollPane		sclFiles;
	private JButton			btnGetFile;
	
	private ArrayList		alFileList;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new FileClient();
	}
	
	public FileClient(){
		super(STR_APPLINAME);
		saveFileName = "";
		receiverManager = new FileClientReceiverManager();
		
		lblHost = new JLabel(STR_CONNECT_LABEL);
		lblDir = new JLabel(STR_DIR_LABEL);
		lblSave = new JLabel(STR_SAVE_LABEL);
		pnlLabel = new JPanel(new GridLayout(3,1));
		pnlLabel.add(lblHost);
		pnlLabel.add(lblDir);
		pnlLabel.add(lblSave);
		
		txtHost = new JTextField(STR_HOST_DEFAULT);
		txtDir = new JTextField();
		txtSave = new JTextField();
		pnlText = new JPanel(new GridLayout(3,1));
		pnlText.add(txtHost);
		pnlText.add(txtDir);
		pnlText.add(txtSave);
		
		btnConnect = new JButton(STR_CONNECT_BUTTON);
		btnChangeDir = new JButton(STR_DIRSAVE_BUTTON);
		btnSave = new JButton(STR_DIRSAVE_BUTTON);
		pnlButton = new JPanel(new GridLayout(3,1));
		pnlButton.add(btnConnect);
		pnlButton.add(btnChangeDir);
		pnlButton.add(btnSave);
		
		pnlHedder = new JPanel(new BorderLayout());
		pnlHedder.add(pnlLabel, BorderLayout.WEST);
		pnlHedder.add(pnlText, BorderLayout.CENTER);
		pnlHedder.add(pnlButton, BorderLayout.EAST);
		
		lstFiles = new JList();
		sclFiles = new JScrollPane(lstFiles);
		
		btnGetFile = new JButton(STR_GET_BUTTON);
		
		Container frame = getContentPane();
		frame.add(pnlHedder, BorderLayout.NORTH);
		frame.add(sclFiles, BorderLayout.CENTER);
		frame.add(btnGetFile, BorderLayout.SOUTH);
		
		alFileList = null;
		controll = new Controller(this, false);
		messageBox = new MessageBox(this);
		
		btnConnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(btnConnect.getText().equals(STR_CONNECT_BUTTON)){
					connectServer();
				}else{
					disconnectServer();
				}
			}
		});
		
		btnChangeDir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				changeDir();
			}
		});

		btnGetFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				getFile();
			}
		});

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				closeWindow();
			}
		});
		

		setSize(500,500);
		setVisible(true);
	}

	public void receiveMessage(String strMess) {
		if(receiver == null){
			return;
		}
		try{
			if( receiver.execMessage(strMess) ){
				ArrayList al = receiver.getResult();
				switch(receiverManager.getCurrentReceiver()){
					case FileClientReceiverManager.DIR:
						alFileList = al;
						lstFiles.setListData(al.toArray());
						break;
					case FileClientReceiverManager.GET:
						saveFile(al);
						break;
					default:
				}
			}
		}catch(ReceiveException re){
			messageBox.disp(re.getMessage());
		}
	}

	public void receiveConnect(String strHost) {
		messageBox.disp("接続を受けました。\nそんなバカな！");
		controll.end_conn();
		System.exit(0);
	}

	private void connectServer(){
		String host = txtHost.getText().trim();
		if(!host.equals("")){
			try{
				controll.sendConnect(host);
				changeConnectButton();
				txtDir.setText(STR_DIR_DEFAULT);
				txtSave.setText(STR_SAVE_DEFAULT);
				receiver = receiverManager.changeReceiver(FileClientReceiverManager.DIR);
				controll.sendMessage("dir");
			}catch(ConnectException ce){
				messageBox.disp("接続に失敗しました。\n" + ce.getMessage());
			}
		}
	}
	
	private void disconnectServer(){
		controll.sendMessage("exit");
		controll.end_conn();
		changeConnectButton();
		txtDir.setText("");
		txtSave.setText("");
		controll = new Controller(this, false);
	}
	
	private void changeDir(){
		String dir = txtDir.getText().trim();
		if(!dir.equals("")){
			try{
				receiver = receiverManager.changeReceiver(FileClientReceiverManager.CD);
				controll.sendMessage("cd " + dir);
				receiver = receiverManager.changeReceiver(FileClientReceiverManager.DIR);
				controll.sendMessage("dir");
			}catch(ConnectException ce){
				messageBox.disp("通信エラーです。\n" + ce.getMessage());
			}
		}
	}
	
	private void getFile(){
		if(lstFiles.isSelectionEmpty() || alFileList == null){
			return;
		}
		
		String strSelect = (String)alFileList.get(lstFiles.getMinSelectionIndex());
		
		if(strSelect.charAt(0) == '<' && strSelect.charAt(strSelect.length() - 1) == '>'){
			String strDir = txtDir.getText().trim();
			if(strDir.charAt(strDir.length() - 1) != '\\'){
				strDir = strDir + "\\";
			}
			strSelect = strDir + strSelect.substring(1, strSelect.length() - 1) + "\\";
			txtDir.setText(strSelect);
			changeDir();
		}else{
			saveFileName = strSelect;
			messageBox.disp("ファイル「" + saveFileName + "」を取得しています。");
			try{
				receiver = receiverManager.changeReceiver(FileClientReceiverManager.GET);
				controll.sendMessage("get " + saveFileName);
			}catch(ConnectException ce){
				messageBox.disp("通信エラーです。\n" + ce.getMessage());
			}
		}
	}
	
	private void closeWindow(){
		try{
			controll.sendMessage("exit");
		}catch(ConnectException ce){
			System.out.println(ce.getMessage());
		}
		controll.end_conn();
		System.exit(0);
	}
	
	private void saveFile(ArrayList dat){
		int cnt;
		String strSaveDir = txtSave.getText().trim();
		
		if(strSaveDir.charAt(strSaveDir.length() - 1) != '\\'){
			strSaveDir = strSaveDir + "\\";
		}
		
		try{
			File file = new File(strSaveDir + saveFileName);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			
			for(cnt = 0; cnt < dat.size(); cnt++){
				bos.write( ((Byte)dat.get(cnt)).intValue() );
			}
			bos.flush();
			messageBox.disp("\n\nファイル「"+saveFileName+"」を保存しました。");
		}catch(IOException ie){
			messageBox.disp("ファイルを保存できません。");
		}
	}
	
	private void changeConnectButton(){
		if(btnConnect.getText().equals(STR_CONNECT_BUTTON)){
			txtHost.setEnabled(false);
			btnConnect.setText("切断");
		}else{
			txtHost.setEnabled(true);
			btnConnect.setText(STR_CONNECT_BUTTON);
		}
	}
}
