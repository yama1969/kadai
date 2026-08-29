package f_for;
/******************************************************************************
 * 九九表を表示
 *****************************************************************************/
public class Kadai1900{
    public static void main(String[] args){
        //i×jのiを1～9変化させる
        for(int i = 1; i < 10; i++){
            //i×jのjを1～9変化させる
            for(int j = 1; j < 10; j++){
                System.out.printf("%3d",(i * j));  //かけ算結果の表示
            }
            System.out.println();                  //改行
        }
    }
}
