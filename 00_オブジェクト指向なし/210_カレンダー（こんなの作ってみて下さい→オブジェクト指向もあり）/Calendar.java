import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*******************************************************************************
 * いわゆる万年カレンダー
 *
 * @author 山田　洋 2008/5/6
 ******************************************************************************/
public class Calendar{
    /** 曜日計算元期2001/1/1 (元期は必ず元日とすること) */
    public static final int BASEYEAR = 2001;
    /** 元期の曜日=月曜日 */
    public static final int BASEWEEKDAY = 1;
    
    /***************************************************************************
     * 年と月の入力を行い、入力された年・月のカレンダーを表示
     **************************************************************************/
    public static void main(String[] args){
        System.out.println("任意の年・月のカレンダーを表示します。");
        int year = inputNum("年 ");
        int month = inputNum("月 ");
        System.out.println();
        showCalendar(year, month);
    }
    
    /***************************************************************************
     * メッセージ付きキーボード整数入力処理
     *
     * @param  mess 入力時メッセージ文字列
     * @return 入力された整数(エラー発生時は1)
     **************************************************************************/
    public static int inputNum(String mess){
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
    
    /***************************************************************************
     * 指定された年月のカレンダーを表示する
     *
     * @param year  表示するカレンダーの年
     * @param month 表示するカレンダーの月
     **************************************************************************/
    public static void showCalendar(int year, int month){
        int weekday = getWeekday(year, month, 1);                   //指定月の1日の曜日
        int maxDay = getMonthDay(year, month);                      //指定月の日数
        
        System.out.println(year + "年" + month + "月");
        System.out.println("日　月　火　水　木　金　土");
        
        for(int i = 0; i < weekday; i++){                           //1日の曜日まで空白表示
            System.out.print("    ");
        }
        
        for(int day = 1; day <= maxDay; day++){                     //各日付表示
            if(day < 10){                                           //1桁日は0を補完
                System.out.print(" ");
            }
            System.out.print(day + "  ");
            
            weekday = (weekday + 1) % 7;                            //曜日を進める
            if(weekday == 0){                                       //日曜に戻ったら改行
                System.out.println();
            }
        }
    }
    
    /***************************************************************************
     * 指定された日付の曜日を得る
     *
     * @param  year  年
     * @param  month 月
     * @param  day   日
     * @return 曜日(日:0 月:1 火:2 水:3 木:4 金:5 土:6)
     **************************************************************************/
    public static int getWeekday(int year, int month, int day){
        int days = getDays(BASEYEAR, year) - 1;                       //まずは元期から指定年まえ年末までの日数を求める
        for(int i = 1;i < month; days += getMonthDay(year, i), i++);  //さらに指定月まえ月末までの日数を加算する
        days += day;                                                  //そして指定月分の日数を加算する
        
                                                                      //日数を7で割った余りに元期の曜日を足して、指定日の曜日を得る
                                                                      //(日数が負の場合にも対応するため、%ではなく、Math.floor()を利用)
        return ( days - (int)Math.floor((double)days / 7.0) * 7 + BASEWEEKDAY) % 7;
    }
    
    /***************************************************************************
     * 指定された年・月の日数を得る。例えば2008年2月ならば29。
     *
     * @param year  年
     * @param month 月
     * @return 日数(存在しない月なら0)
     **************************************************************************/
    public static int getMonthDay(int year, int month){
        if(month < 0 || month > 12){
            return 0;
        }
        
        int[][] monthday = {{31,28,31,30,31,30,31,31,30,31,30,31},
                            {31,29,31,30,31,30,31,31,30,31,30,31}};
        return monthday[getLeap(year)][month - 1];
    }
    
    /***************************************************************************
     * 指定された年が閏年か否かを判定
     *
     * @param  year 年
     * @return 閏年なら1、そうでなければ0
     **************************************************************************/
    public static int getLeap(int year){
        if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){ //閏年なら1を返す。でなければ0を返す。
            return 1;
        }
        return 0;
    }
    
    /***************************************************************************
     * 年Aの元日と年Bの元日との間の日数を算出。
     * 年数差に365日を掛け、さらに閏年の数だけ加算して求める。
     *
     * @param  yearA 年A
     * @param  yearB 年B
     * @return 年A元日から年B元日までの日数。年A > 年Bなら負となる。
     **************************************************************************/
    public static int getDays(int yearA, int yearB){
        int leap = getMultiNum(yearA, yearB, 4);                   //4年ごとに閏年
        leap = leap - getMultiNum(yearA, yearB, 100);              //しかし、100年に1回は閏年でない
        leap = leap + getMultiNum(yearA, yearB, 400);              //とはいっても、400年に1回は閏年
        
        return (yearB - yearA) * 365 + leap;                       //yearA<=yearBなら正、でなければ負となる。
    }
    
    /***************************************************************************
     * 整数A以上整数B未満の間に存在する指定整数の倍数の個数を得る。
     * 例えば、5以上18未満の間の4の倍数は3個である。なお、整数A > 整数Bの場合は負数を返す。
     *
     * @param  numA 整数A
     * @param  numB 整数B
     * @param  mult 指定整数
     * @return 整数A以上整数B未満の間の指定整数の倍数の個数 (整数A > 整数Bの場合は負)
     **************************************************************************/
    public static int getMultiNum(int numA, int numB, int mult){
        int sign = 1;                                              //整数A<=整数Bに整える
        if(numA > numB){
            int w = numA;
            numA = numB;
            numB = w;
            sign = -1;
        }
        
        int multA = (int)Math.floor((double)numA / (double)mult);  //整数Aまでのmultの倍数の個数(負数にも対応するため、Math.floor()利用)
        int multB = (int)Math.floor((double)numB / (double)mult);  //整数Bまでのmultの倍数の個数
        int multiNum = multB - multA;
        
        if(numA % mult == 0){                                      //整数Aがmultの倍数だった場合、整数A以上の個数にするため、個数を1増やす
            multiNum++;
        }
        if(numB % mult == 0){                                      //整数Bがmultの倍数だった場合、整数B未満の個数にするため、個数を1減らす
            multiNum--;
        }
        
        return sign * multiNum;                                    //numA<=numBなら正、そうでなければ負
    }
}
