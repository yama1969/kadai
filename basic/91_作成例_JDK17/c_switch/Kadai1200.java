package c_switch;

/******************************************************************************
 * 月の日数を表示
 *****************************************************************************/
public class Kadai1200{
    public static void main(String[] args){
        //月の入力
        int month = Keyboard.readInt("月");
        
        //月に従って日数を表示
        switch(month){
        case 1:
            System.out.println("31日");
            break;
        case 2:
            System.out.println("28日");
            break;
        case 3:
            System.out.println("31日");
            break;
        case 4:
            System.out.println("30日");
            break;
        case 5:
            System.out.println("31日");
            break;
        case 6:
            System.out.println("30日");
            break;
        case 7:
            System.out.println("31日");
            break;
        case 8:
            System.out.println("31日");
            break;
        case 9:
            System.out.println("30日");
            break;
        case 10:
            System.out.println("31日");
            break;
        case 11:
            System.out.println("30日");
            break;
        case 12:
            System.out.println("31日");
            break;
        default:
            System.out.println("月は1～12です。");
        }
        
        //次のように書いても同じ動作
/*
        switch(month){
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            System.out.println("31日");
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            System.out.println("30日");
            break;
        case 2:
            System.out.println("28日");
            break;
        default:
            System.out.println("月は1～12です。");
        }
*/
    }
}
