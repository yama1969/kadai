import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * サイコロの目を当てるゲーム
 */
public class DiceGame{
    /** サイコロの最大目 */
    private final int MAX = 6;
    
    /** メインメソッド */
    public static void main(String[] args){
        new DiceGame().game();
    }
    
    /**
     * ゲームの全体処理
     */
    public void game(){
        System.out.println("サイコロを振るよ。コロコロ・・・");
        System.out.println("はい！出た目を当ててね。");
        Dice dice = new Dice(MAX);                 //サイコロ
        InputNum in = new InputNum(1,MAX);         //数値入力API
        
        int count = 0;                             //回答カウンタ
        boolean hit = false;                       //正答フラグ
        for(count = 0; count < MAX && !hit; count++){
            System.out.println();
            System.out.print("1～" + MAX + "の数を入れて。＞");
            int inNum = in.input();
            switch(inNum){
            case -3:
                System.out.println("入力エラーだから中断するね。");
                System.exit(1);
                break;
            case -2:
                System.out.println("1～" + MAX + "の数だってば～！");
                break;
            case -1:
                System.out.println("サイコロなんだから、1～" + MAX + "でしょ。もぉ～！！");
                break;
            default:
                switch(dice.compareNum(inNum)){
                case -1:
                    System.out.println("もっと小さい数だよ。");
                    break;
                case 0:
                    System.out.println("すごい！大当たり～～～！");
                    hit = true;
                    break;
                case 1:
                    System.out.println("もっと大きい数だよ。");
                    break;
                default:
                    System.out.println("なによこれ～～！プログラムミスじゃない！");
                    System.exit(1);
                    break;
                }
            }
            
        }
        if(!hit){
            System.out.println("ダメダメ！　" + MAX + "回やっても当たんないなんて、ありえない～～！");
        }
    }
}

/**
 * サイコロを表すクラス。目の最大値は初期化の時のみ指定可能。
 */
class Dice{
    /** 現在の目 */
    private int num;
    /** 目の最大値 */
    private final int max;
    
    /**
     * 引数なしコンストラクタ。
     * 目の最大値を6に設定し、サイコロを振る。
     */
    public Dice(){
        max = 6;
        throwDice();
    }
    
    /**
     * 目の最大値を引数に取るコンストラクタ。
     * 目の最大値を設定し、サイコロを振る。目の最大値が1未満の場合は、1に修正される。
     * @param maxNum 目の最大値
     */
    public Dice(int maxNum){
        if(maxNum < 1){
            maxNum = 1;
        }
        max = maxNum;
        throwDice();
    }
    
    /**
     * サイコロを投げる。
     * @return 出た目
     */
    public int throwDice(){
        num = (int)(Math.random() * (double)max) + 1;
        return num;
    }
    
    /**
     * 現在のサイコロの目を得る。
     * @return 現在の目
     */
    public int getNum(){
        return num;
    }
    
    /**
     * サイコロの目の最大値を得る。
     * @return 目の最大値
     */
    public int getMaxNum(){
        return max;
    }
    
    /**
     * 目の比較をする。
     * @param compNum 比較したい目の値
     * @return -1:このサイコロの方が小さい 0:目は等しい 1:このサイコロの方が大きい Integer.MIN_VALUE:比較不能
     */
    public int compareNum(int compNum){
        if(compNum < 1 || compNum > max){
            return Integer.MIN_VALUE;
        }
        if(num < compNum){
            return -1;
        }else if(compNum < num){
            return 1;
        }
        return 0;
    }
}

/**
 * ある範囲内のint型数値を入力するクラス。範囲は0以上で、初期化時のみ指定可能。
 */
class InputNum{
    /** 入力範囲下限値 */
    private final int min;
    /** 入力範囲上限値 */
    private final int max;
    
    /**
     * 引数なしコンストラクタ。下限を0に、上限を10に設定する。
     */
    public InputNum(){
        min = 0;
        max = 10;
    }
    
    /**
     * 下限値と上限値を指定するコンストラクタ。引数minが負の場合、下限値は0となる。
     * @param min 下限値
     * @param max 上限値
     */
    public InputNum(int min, int max){
        if(min < 0){
            min = 0;
        }
        this.min = min;
        this.max = max;
    }
    
    /**
     * 数値入力をする。
     * @return 入力された数値。範囲外の場合は-1、数値でない場合は-2、入出力エラーの場合は-3。
     */
    public int input(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        try{
            n = Integer.parseInt(reader.readLine());
            if(n < min || max < n){
                return -1;
            }
        }catch(IOException e){
            return -3;
        }catch(NumberFormatException e){
            return -2;
        }
        return n;
    }
}
