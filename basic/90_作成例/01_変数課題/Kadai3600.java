public class Kadai3600{
    public static void main(String[] args){
        //6つのchar型変数にデータを代入(暗号化データ)
        char c1 = 'O';
        char c2 = 'Q';
        char c3 = 'Q';
        char c4 = 'X';
        char c5 = 'F';
        char c6 = 'T';
        
        //処理前データの表示
        System.out.println("" + c1 + c2 + c3 + c4 + c5 + c6);
        
        //各文字データに決められた数値を減算(復号処理)
        c1 -= 1;
        c2 -= 2;
        c3 -= 3;
        c4 -= 4;
        c5 -= 5;
        c6 -= 6;
        
        //処理後データの表示(平文データ)
        System.out.println("" + c1 + c2 + c3 + c4 + c5 + c6);
    }
}
