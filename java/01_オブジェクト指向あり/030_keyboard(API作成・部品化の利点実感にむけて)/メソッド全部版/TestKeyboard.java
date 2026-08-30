public class TestKeyboard{
    public static void main(String[] args){
        System.out.println("数値入力テストです。");
        int intans = Keyboard.readInt("数値整数");
        System.out.println(intans + "が入力されました。");
        System.out.println();
        
        System.out.println("文字列入力テストです。");
        String stans = Keyboard.readString("文字列");
        System.out.println(stans + "が入力されました。");
        System.out.println();
        
        System.out.println("文字入力テストです。");
        char chans = Keyboard.readChar("文字");
        System.out.println(chans + "が入力されました。");
        System.out.println();
    }
}
