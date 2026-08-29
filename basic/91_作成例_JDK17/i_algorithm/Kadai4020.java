package i_algorithm;
/******************************************************************************
 * スタック(FILO)の実現
 *****************************************************************************/
public class Kadai4020{
    public static void main(String[] args){
        //配列と格納位置の初期化
        int[] stack = new int[10];  //データ格納用配列
        int pos = 0;                //次の格納位置(データ数)
        
        //データ操作
        String msg = "値[1以上:データ, 0:表示, -1:取出し, -2以下:終了]";
        int cmd = 0;
        while((cmd = Keyboard.readInt(msg)) > -2){
            if(cmd > 0){
                //データの格納
                if(pos >= stack.length){
                    System.out.println("もう値は入りません。");
                }else{
                    stack[pos] = cmd;
                    pos++;
                }
            }else if(cmd == 0){
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
