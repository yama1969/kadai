import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/*******************************************************************************
 * アカウント管理クラス
 ******************************************************************************/
public class Account{
    private String fileName = "id.csv";                                         //アカウントファイル
    private HashMap<String, Socket> loginID;                                    //ログイン中ID一覧
    
    /***************************************************************************
     * コンストラクタ
     */
    public Account(){
        loginID = new HashMap<String, Socket>();
    }
    
    /***************************************************************************
     * ログイン処理(true:成功)
     */
    public boolean login(String id, String pass, Socket sock){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            while((line = reader.readLine()) != null){
                int sep = line.indexOf((int)',');
                if(sep < 0){
                    continue;
                }
                String read_id = line.substring(0,sep);
                String read_pass = line.substring(sep + 1);
                if(read_id.equals(id) && read_pass.equals(pass)){
                    loginID.put(id, sock);
                    return true;
                }
            }
        }catch(IOException e){
        }
        return false;
    }
    
    /***************************************************************************
     * ログオフ処理(true:正常にログオフ,false:指定IDはログオン中でない)
     */
    public boolean logoff(String id){
        if(loginID.containsKey(id)){
            loginID.remove(id);
            return true;
        }
        return false;
    }
    
    /***************************************************************************
     * ログオン中IDのソケットを得る
     */
    public Socket getSocket(String id){
        if(loginID.containsKey(id)){
            return loginID.get(id);
        }
        return null;
    }
    
    /***************************************************************************
     * 指定ID以外のログオン中IDをランダムに選択する
     */
    public String getRandomID(String id){
        if(!loginID.containsKey(id)){                                           //指定IDがログイン中か？
            return null;
        }
        int size = loginID.size();                                              //ログイン数
        if(size < 2){                                                           //指定ID以外にログイン中あり？
            return null;
        }
        
        String[] ids = (String[])(loginID.keySet().toArray());
        double dsize = (double)size;
        int num = 0;
        do{
            num = (int)(Math.random() * dsize);                                 //ランダムに選択
        }while(ids[num].equals(id));
        
        return ids[num];
    }
}
