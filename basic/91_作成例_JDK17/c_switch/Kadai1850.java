package c_switch;

/******************************************************************************
 * 少し込み入った料金判定
 *****************************************************************************/
public class Kadai1850{
    public static void main(String[] args){
        //料金表の表示
        System.out.println("+------+--------+--------+");
        System.out.println("| 女性 | 成人   | \\2,500 |");
        System.out.println("|      +--------+--------+");
        System.out.println("|      | 未成年 | \\1,800 |");
        System.out.println("+------+--------+--------+");
        System.out.println("| 男性 | 県内   | \\3,500 |");
        System.out.println("|      +--------+--------+");
        System.out.println("|      | 県外   | \\3,000 |");
        System.out.println("+------+--------+--------+");
        System.out.println();
        
        //性別の入力
        int gender = Keyboard.readInt("性別[女性 = 0, 男性 = 0以外]");
        
        //性別による振り分け
        switch(gender){
        case 0:      //女性
            //成人の入力
            int adult = Keyboard.readInt("成人ですか[はい = 0, いいえ = 0以外]");
            System.out.println();
            switch(adult){
            case 0:  //  成人
                System.out.println("\\2,500です。");
                break;
            default: //  未成人
                System.out.println("\\1,800です。");
            }
            break;
        default:     //男性
            //県内外の入力
            int pref = Keyboard.readInt("県内ですか[はい = 0, いいえ = 0以外]");
            System.out.println();
            switch(pref){
            case 0:  //  県内
                System.out.println("\\3,500です。");
                break;
            default: //  県外
                System.out.println("\\3,000です。");
            }
        }
    }
}
