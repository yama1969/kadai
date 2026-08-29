import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ChatCUI implements ChatUI{
    private final ChatControl chatCtrl;
    
    public ChatCUI(){
        chatCtrl = new ChatControl(this);
        startChat();
    }
    
    public void startChat(){
        if(chatCtrl.startWait()){
            System.out.println("接続待受を開始しました。");
        }else{
            System.out.println("接続待受は開始しませんでした。");
        }
        printPrompt();
        
        BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
        try{
            while(true){
                String line = keyIn.readLine();
                if(line.substring(0,4).equals("exit")){
                    break;
                }
                if(line.substring(0,4).equals("con ")){
                    String add = line.substring(4);
                    chatCtrl.connect(add);
                }
                chatCtrl.sentMessage(line);
            }
        }catch(IOException e){
            System.out.println("キーボードエラーのため、ソフトウェアを中断します。");
        }finally{
            chatCtrl.shutdown();
        }
    }
    
    public void waitStopped(){
        System.out.println("接続待受を終了しました。");
        printPrompt();
    }
    
    public void messageReceived(String mess){
        System.out.println(message);
        printPrompt();
    }
    
    private void printPrompt(){
        System.out.print("message > ");
    }
}
