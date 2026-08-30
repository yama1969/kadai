public class ChatControl{
    private final int PORT = 5555;
    private final ChatUI chatUi;
    private final ConnectWaiter conWaiter;
    
    public ChatControl(ChatUI ui){
        chatUi = ui;
    }
    
    /** 接続待受を開始 @return ture:開始成功 false:開始失敗*/
    public boolean startWait(){
    }
}
