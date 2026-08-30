package a_hensu;

/******************************************************************************
 * boolean型とは
 *****************************************************************************/
public class Kadai3700{
    public static void main(String[] args){
        //2つのint型変数にダイン祐
        int a = Keyboard.readInt("整数a");
        int b = Keyboard.readInt("整数b");
        System.out.println();
        
        //式a > bの表示。式自体がboolean型なので、trueまたはfalseが表示される
        System.out.println("a > b = " + (a > b));
    }
}
