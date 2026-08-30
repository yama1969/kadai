/******************************************************************************
 * 数くらべ
 *****************************************************************************/
public class Kadai1215{
    public static void main(String[] args){
        //2つの変数に入力
        int a = Keyboard.readInt("a");
        int b = Keyboard.readInt("b");
        System.out.println();
        
        //比較して結果を表示
        if(a >= b){
            System.out.println("aはb以上だ。bはa以下だ。");
        }else{
            System.out.println("aはbより小さい。bはaより大きい。");
        }
    }
}
