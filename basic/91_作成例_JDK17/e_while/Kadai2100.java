package e_while;

/******************************************************************************
 * 入力された文字のコードを表示(EOFで終了)
 *****************************************************************************/
public class Kadai2100{
    public static void main(String[] args){
        int c = (int)Keyboard.readChar("文字[Ctrl+z:終了]");//最初の文字入力
        while(c != 0){                                      //nullでない→継続
            System.out.printf("%d(0x%x)\n",c,c);            //文字コード表示
            System.out.println();
            c = (int)Keyboard.readChar("文字[Ctrl+z:終了]");//次の文字入力
        }
    }
}
