/******************************************************************************
 * 料金判定
 *****************************************************************************/
public class Kadai1700{
    public static void main(String[] args){
        //料金表を表示
        System.out.println("+--------+------+--------+");
        System.out.println("| 会員   | 男性 | \\4,000 |");
        System.out.println("|        +------+--------+");
        System.out.println("|        | 女性 | \\2,500 |");
        System.out.println("+--------+------+--------+");
        System.out.println("| 非会員 | 男性 | \\5,000 |");
        System.out.println("|        +------+--------+");
        System.out.println("|        | 女性 | \\3,500 |");
        System.out.println("+--------+------+--------+");
        System.out.println();
        
        //種別と性別を入力
        int member = Keyboard.readInt("種別[会員 = 0, 非会員 = 0以外]");
        int gender = Keyboard.readInt("性別[男性 = 0, 女性 = 0以外]");
        System.out.println();
        
        //種別と性別に従い料金判定
        switch(member){
        case 0:      //会員
            switch(gender){
            case 0:  //  男性
                System.out.println("\\4,000です。");
                break;
            default: //  女性
                System.out.println("\\2,500です。");
            }
            break;
        default:     //非会員
            switch(gender){
            case 0:  //  男性
                System.out.println("\\5,000です。");
                break;
            default: //  女性
                System.out.println("\\3,500です。");
            }
        }
    }
}
