package c_switch;

/******************************************************************************
 * この免許で運転できるのは？
 *****************************************************************************/
public class Kadai1400{
    public static void main(String[] args){
        //免許種別を一覧表示
        System.out.println("原動機付自転車免許 ・・・ 1");
        System.out.println("普通自動車免許     ・・・ 2");
        System.out.println("中型自動車免許     ・・・ 3");
        System.out.println("大型自動車免許     ・・・ 4");
        System.out.println();
        
        //免許種別を入力
        int license = Keyboard.readInt("免許種別");
        System.out.println();
        
        //入力された免許種類に応じて、運転可能自動車を表示
        System.out.println("運転できる自動車");
        switch(license){
        case 4:
            System.out.print(" 大型自動車 ");
        case 3:
            System.out.print(" 中型自動車 ");
        case 2:
            System.out.print(" 普通自動車 ");
        case 1:
            System.out.print(" 原付自転車 ");
            break;
        default:
            System.out.print("免許種別が分かりません");
        }
    }
}
