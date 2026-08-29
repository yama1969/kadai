import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.NotSerializableException;
import java.util.ArrayList;

/**
 指定ファイルにArrayListオブジェクトを保存する。
 ArrayListに登録したオブジェクトも要Serializable。
*/
public class ArrayListFiling{
    //-----コンストラクタを使用不可にする---------------------------------------
    private ArrayListFiling(){
    }
    
    //-----ファイルの読込み-----------------------------------------------------
    public static ArrayList read(String filePath){
        ArrayList list = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream( filePath );
            ois = new ObjectInputStream( fis );
            
            list = (ArrayList)ois.readObject();
        }catch(NotSerializableException e){
            System.out.println("ファイルを読込みできません。(直列化不可オブジェクトあり)");
        }catch(IOException e){                                                  //ファイルが無かった場合等
            //e.printStackTrace();
            System.out.println("データファイルがありませんでした。");
            return null;
        }catch(ClassNotFoundException e){                                       //ois.readObject()が投げる
            e.printStackTrace();
            return null;
        }finally{
            try{
                if( ois != null ){
                    ois.close();
                }
            }catch( IOException e ){
                e.printStackTrace();
            }
        }
        return list;
    }
    
    //-----ファイル保存---------------------------------------------------------
    public static void write(ArrayList list, String filePath){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream( filePath );
            oos = new ObjectOutputStream( fos );
            oos.writeObject( list );
            oos.flush();
        }catch(NotSerializableException e){
            System.out.println("ファイルを保存できません。(直列化不可オブジェクトあり)");
        }catch(IOException e){
            e.printStackTrace();
            return;
        }finally{
            try{
                if( oos != null ){
                    oos.close();
                }
            }catch( IOException e ){
                e.printStackTrace();
            }
        }
    }
}
