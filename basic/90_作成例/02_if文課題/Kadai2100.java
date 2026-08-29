/******************************************************************************
 * 料金判定
 *****************************************************************************/
public class Kadai2100{
    public static void main(String[] args){
        //料金表の表示
        System.out.println("+------+------+--------+");
        System.out.println("| 成人 | 男性 | \\5,000 |");
        System.out.println("|      +------+--------+");
        System.out.println("|      | 女性 | \\3,500 |");
        System.out.println("+------+------+--------+");
        System.out.println("| 7才～19才   | \\1,200 |");
        System.out.println("+-------------+--------+");
        System.out.println("| 7才未満     | 無料   |");
        System.out.println("+-------------+--------+");
        System.out.println();
        
        //性別と年齢の入力
        int gender = Keyboard.readInt("性別[0:男性, 0以外:女性]");
        int age = Keyboard.readInt("年齢");
        
        //料金表の従い判定
        if(age < 7){
            System.out.println("無料です。");
        }else if(age < 20){
            System.out.println("\\1,200です。");
        }else{
            if(gender == 0){
                System.out.println("\\5,000です。");
            }else{
                System.out.println("\\3,500です。");
            }
        }
    }
}
