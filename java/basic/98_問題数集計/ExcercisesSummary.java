import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ExcercisesSummary{
    public static void main(String[] args){
        //各課題のフォルダを検索する
        File currentdir = new File("../");                          //カレントディレクトリ
        String[] dirs = currentdir.list();                          //ディレクトリ内一覧
        
        ArrayList<String> exerciseslist = new ArrayList<String>();  //課題ファイル名リスト
        int total_num = 0;                                          //総課題数
        
        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("課題一覧.csv")));
            
            //課題種別番号順に課題ファイルを検索
            for(int i = 0; i < 90; i++){
                String dirhead = ("0" + i + "_");
                dirhead = dirhead.substring(dirhead.length() - 3);
                for(int j = 0; j < dirs.length; j++){
                    if(dirs[j].indexOf(dirhead) == 0){
                        //該当番号のフォルダ発見→カレントディレクトリ移動とリスト取得
                        currentdir = new File("../" + dirs[j]);
                        String[] files = currentdir.list();
                        for(String filename : files){
                            if(filename.length() > 5){
                                if(filename.substring(filename.length() - 6).equals("課題.txt")){
                                    //課題ファイル発見
                                    exerciseslist.add(filename);
                                    filename = currentdir.getPath() + File.separator + filename;
                                    int subtotal_num = 0;
                                    try{
                                        BufferedReader reader = new BufferedReader(new FileReader(filename));
                                        String line = null;
                                        //課題ファイル読込→課題タイトルのみ抽出・出力
                                        while((line = reader.readLine()) != null){
                                            if(line.indexOf("■■ 課題") == 0){
                                                total_num++;
                                                subtotal_num++;
                                                System.out.printf("%4d ",total_num);
                                                System.out.printf("%3d ",i);
                                                System.out.printf("%3d ",subtotal_num);
                                                System.out.println(line);
                                                
                                                writer.print(total_num + ",");
                                                writer.print(i + ",");
                                                writer.print(subtotal_num + ",");
                                                writer.println(line);
                                            }
                                        }
                                        reader.close();
                                    }catch(FileNotFoundException e){
                                        System.out.println("File Not Found : " + filename);
                                    }catch(IOException e){
                                        System.out.println("IOエラー : " + filename);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //課題ファイル一覧の出力
            int i = 1;
            for(String exercise : exerciseslist){
                exercise = exercise.substring(0, exercise.length() - 4);
                
                System.out.printf("%3d ",i);
                System.out.println(exercise);
                
                writer.print(i + ",");
                writer.println(exercise);
                
                i++;
            }
            
            writer.close();
        }catch(IOException e){
            System.out.println("一覧ファイル出力エラー");
        }
    }
}
