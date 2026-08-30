/******************************************************************************
 * スタック(FILO)の実現に向けて
 * 配列から逆順にデータを取り出す
 *****************************************************************************/
public class Kadai4010{
    public static void main(String[] args){
        //配列と取り出し位置の初期化
        //  配列初期化(各要素を乱数で決める)
        int[] stack = new int[10];
        for(int i = 0; i < stack.length; i++){
            stack[i] = (int)(Math.random() * 90.0) + 10;
        }
        //  取り出し位置の初期化
        int pos = stack.length;
        
        //データ操作
        String msg = "値[0以上:表示, -1:取出し, -2以下:終了]";
        int cmd = 0;
        while((cmd = Keyboard.readInt(msg)) > -2){
            if(cmd >= 0){
                //データの表示
                for(int i = 0; i < pos; i++){
                    System.out.print(" " + stack[i]);
                }
                System.out.println();
            }else{
                //データの取り出し
                if(pos <= 0){
                    System.out.println("もう値はありません。");
                }else{
                    pos--;
                    System.out.println(stack[pos]);
                }
            }
            System.out.println("値個数 " + pos);
            System.out.println();
        }
    }
}
