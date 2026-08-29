/******************************************************************************
 * 月の足し算と引き算
 *****************************************************************************/
public class Kadai4150{
    public static void main(String[] args){
        //月の入力
        int month = Keyboard.readInt("月");
        
        //負の値に対する剰余計算は言語によって癖がある。
        //予め12を足すことにより、途中で負の値にしないようにする。
        int newmonth = ((month + 12 - 1) + 5) % 12 + 1;
        System.out.println("5ヶ月後は " + newmonth + "月");
        
        newmonth = ((month + 12 - 1) - 5) % 12 + 1;
        System.out.println("5ヶ月前は " + newmonth + "月");
    }
}
