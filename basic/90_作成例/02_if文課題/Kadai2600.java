/******************************************************************************
 * うるう年の判定
 *****************************************************************************/
public class Kadai2600{
    public static void main(String[] args){
        //西暦年の入力
        int year = Keyboard.readInt("西暦年");
        
        //うるう年の判定と表示
        if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
            System.out.println("うるう年です。");
        }else{
            System.out.println("平年です。");
        }
        
        //以下は、同じ処理の別の書き方
/*
        if(year % 400 == 0){
            System.out.println("うるう年です。");
        }else if(year % 100 == 0){
            System.out.println("平年です。");
        }else if(year % 4 == 0){
            System.out.println("うるう年です。");
        }else{
            System.out.println("平年です。");
        }
*/
/*
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    System.out.println("うるう年です。");
                }else{
                    System.out.println("平年です。");
                }
            }else{
                System.out.println("うるう年です。");
            }
        }else{
            System.out.println("平年です。");
        }
*/
    }
}
