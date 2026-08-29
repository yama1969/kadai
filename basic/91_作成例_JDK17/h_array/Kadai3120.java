package h_array;
/******************************************************************************
 * 成績情報を並び替え、集計情報を表示する
 *****************************************************************************/
public class Kadai3120{
    public static void main(String[] args){
        //成績データ
        String[] name = new String[5];  //氏名の配列
        int[] score = new int[5];       //点数の配列
        
        //氏名と点数の入力
        for(int i = 0; i < score.length; i++){
            name[i] = Keyboard.readString((i + 1) + "人目:氏名");
            score[i] = Keyboard.readInt((i + 1) + "人目:点数");
        }
        System.out.println();
        
        //ソート処理前のデータ表示
        for(int i = 0; i < score.length; i++){
            System.out.println(name[i] + "\t" + score[i]);
        }
        System.out.println();
        
        //ソート処理
        for(int ins = 1; ins < score.length; ins++){
            String ins_name = name[ins];
            int ins_score = score[ins];
            int comp = 0;
            for(comp = ins - 1; comp > -1 && score[comp] < ins_score; comp--){
                name[comp + 1] = name[comp];
                score[comp + 1] = score[comp];
            }
            name[comp + 1] = ins_name;
            score[comp + 1] = ins_score;
        }
        
        //ソート処理後のデータ表示と点数の合計→平均計算のため
        int sum = 0;
        for(int i = 0; i < score.length; i++){
            System.out.println(name[i] + "\t" + score[i]);
            sum += score[i];
        }
        
        //平均・最高・最低の表示
        System.out.println();
        System.out.println("平均点 : " + ((double)sum / (double)score.length));
        System.out.println("最高点 : " + score[0]);
        System.out.println("最低点 : " + score[score.length - 1]);
    }
}
