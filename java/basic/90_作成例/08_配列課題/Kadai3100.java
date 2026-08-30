/******************************************************************************
 * 成績情報を並び替える
 *****************************************************************************/
public class Kadai3100{
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
        
        //ソート処理後のデータ表示
        for(int i = 0; i < score.length; i++){
            System.out.println(name[i] + "\t" + score[i]);
        }
    }
}
