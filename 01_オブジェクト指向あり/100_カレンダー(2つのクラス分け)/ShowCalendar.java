import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*******************************************************************************
 * いわゆる万年カレンダー
 *
 * @author 山田　洋 2008/5/6
 ******************************************************************************/
public class ShowCalendar{
    /***************************************************************************
     * 年と月の入力を行い、入力された年・月のカレンダーを表示
     **************************************************************************/
    public static void main(String[] args){
        System.out.println("任意の年・月のカレンダーを表示します。");
        int year = inputNum("年 ");
        int month = inputNum("月 ");
        System.out.println();
        Calendar.showCalendar(year, month);
    }
    
    /***************************************************************************
     * メッセージ付きキーボード整数入力処理
     *
     * @param  mess 入力時メッセージ文字列
     * @return 入力された整数(エラー発生時は1)
     **************************************************************************/
    private static int inputNum(String mess){
        int num = 1;
        System.out.print(mess + "=>");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            num = Integer.parseInt(reader.readLine());
        }catch(IOException e){
            System.out.println("入力エラーが発生しました。");
            num = 1;
        }catch(NumberFormatException e){
            System.out.println("整数以外が入力されました。");
            num = 1;
        }
        return num;
    }
}
