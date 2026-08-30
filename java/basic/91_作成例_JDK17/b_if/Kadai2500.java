package b_if;

public class Kadai2500{
    public static void main(String[] args){
        //1文字の入力(文字コードで処理するため、int型へ入力)
        int c = (int)Keyboard.readChar("1文字");
        
        //文字コードの範囲から、文字種を判定
        if(c >= 48 && c <= 57){
            System.out.println("半角数値");
        }else if(c >= 65 && c <= 90){
            System.out.println("半角英大文字");
        }else if(c >= 97 && c <= 122){
            System.out.println("半角英小文字");
        }else if(c >= 33 && c <= 96){
            System.out.println("半角記号");
        }else if(c <= 127){
            System.out.println("制御文字");
        }else{
            System.out.println("全角文字");
        }
    }
}
