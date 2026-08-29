package j_method;
import java.util.Scanner;

public class Keyboard {
    private static Scanner in = new Scanner(System.in);
    
    /*
     プロンプト表示
    */
    private static void prompt(String mess){
        System.out.print(mess + " => ");
    }

    /*
     整数を入力します。
     int型整数以外の入力があった場合や異常があった場合は、
     int型の最小値(-2147483648)を返します。
    */
    public static int readInt(String mess){
        prompt(mess);
        String line = in.nextLine();
        int indat = Integer.MIN_VALUE;
        try{
            indat = Integer.parseInt(line);
        }catch(NumberFormatException e){
        }
        return indat;
    }

    public static int readInt(){
        return readInt("");
    }

    /*
     1文字を入力します。
     複数文字が入力された場合は、先頭の1文字を返します。
     異常があった場合は文字コード0を返します。
    */
    public static char readChar(String mess){
        prompt(mess);
        String line = in.nextLine();
        char indat = (char)0;
        try{
            indat = line.charAt(0);
        }catch(IndexOutOfBoundsException  e){
        }
        return indat;
    }

    public static char readChar(){
        return readChar("");
    }

    /*
     文字列を入力します。
     異常があった場合は、nullを返します。
    */
    public static String readString(String mess){
        prompt(mess);
        return in.nextLine();
    }

    public static String readString(){
        return readString("");
    }
}
