/******************************************************************************
 * 簡単なサイコロ
 *****************************************************************************/
public class Kadai1300{
    public static void main(String[] args){
        //サイコロの目を乱数で決定
        int dice = (int)(Math.random() * 6.0) + 1;
        
        //丁半の判定と表示
        if(dice % 2 == 0){
            System.out.println(dice + " の 丁");
        }else{
            System.out.println(dice + " の 半");
        }
    }
}
