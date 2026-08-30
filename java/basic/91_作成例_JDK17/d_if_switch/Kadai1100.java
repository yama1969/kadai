package d_if_switch;

/******************************************************************************
 * 月の日数を表示(うるう年対応)
 *****************************************************************************/
public class Kadai1100{
    public static void main(String[] args){
        //年と月の入力
        int year = Keyboard.readInt("年");
        int month = Keyboard.readInt("月");
        
        //日数の決定
        switch(month){
        case 1:
            System.out.println("31日");
            break;
        case 2:
            //2月はうるう年の判定
            if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
                System.out.println("29日");
            }else{
                System.out.println("28日");
            }
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
    }
}
