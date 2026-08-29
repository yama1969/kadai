package b_if;

/******************************************************************************
 * 2つのサイコロ
 *****************************************************************************/
public class Kadai1400{
    public static void main(String[] args){
        //2つのサイコロの目を乱数で決定し、表示
        int dice1 = (int)(Math.random() * 6.0) + 1;
        int dice2 = (int)(Math.random() * 6.0) + 1;
        System.out.print(dice1 + ", " + dice2 + " の ");
        
        //2つのサイコロの目の合計から丁半判定
        if((dice1 + dice2) % 2 == 0){
            System.out.println("丁");
        }else{
            System.out.println("半");
        }
    }
}
