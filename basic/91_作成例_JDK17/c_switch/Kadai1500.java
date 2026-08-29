package c_switch;

/******************************************************************************
 * 動きをランダムに指示
 *****************************************************************************/
public class Kadai1500{
    public static void main(String[] args){
        //1～6の乱数を生成し、表示
        int move = (int)(Math.random() * 6.0) + 1;
        System.out.println("no = " + move);
        System.out.println();
        
        //生成した乱数に従って、動きを決定
        switch(move){
        case 1:
        case 2:
        case 3:
        case 4:
            System.out.print("直進せよ");
            break;
        case 5:
            System.out.print("右を向け");
            break;
        case 6:
            System.out.print("左を向け");
            break;
        default:
            System.out.print("その動きはサポートされていない。");
        }
    }
}
