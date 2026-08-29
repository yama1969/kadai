import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 暗号化サンプル。5字の文字列を暗号化/復号化する。
 平文は暗号文に、暗号文は平文に変換される。
 
 キーを1にした場合、ひらがな文字はひらがな文字に、カタカナ文字はカタカナ文字に変換される。
 ただし、小文字の「ぁ」「ァ」は対象外。ごめんなさい。
 
 平文を「こんにちは」に、キーを「1」または「2」にすると綺麗に動く。
 
 変数とデータの型、演算子、キャスト、暗黙の型変換まで終わったところでの演習課題。
 このとき使用した教本では既にキーボード入力が紹介されていたので、それを使用。
 別の教本のときは入力APIを提供するべし。
 
 演習後の解説で、基本情報の暗号化方式と暗号化キーについて復習解説もした。
 2015/6/11(木)
*/
class Encryption{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("5文字を入力 = ");
        String line = br.readLine();
        System.out.print("整数キーを入力=");
        int key = Integer.parseInt(br.readLine());
        
        char c1 = line.charAt(0);
        char c2 = line.charAt(1);
        char c3 = line.charAt(2);
        char c4 = line.charAt(3);
        char c5 = line.charAt(4);
        
        c1 = (char)(c1 ^ key);
        c2 = (char)(c2 ^ key);
        c3 = (char)(c3 ^ key);
        c4 = (char)(c4 ^ key);
        c5 = (char)(c5 ^ key);
        System.out.println("" + c1 + c2 + c3 + c4 + c5);
    }
}
