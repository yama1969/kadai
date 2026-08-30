import java.io.*;

class Records{
    
    public static void main(String[] args) throws IOException{
        String[] names = new String[5];
        int[]    scores = new int[5];
        
        //初期化
        for(int i = 0; i < names.length; i++){
            names[i] = "";
            scores[i] = 0;
        }
        
        int cmd_no = 0;
        do{
            cmd_no = showMenu();
            System.out.println();
            switch(cmd_no){
            case 1:
                showRecords(names, scores);
                break;
            case 2:
                System.out.println("データ数 = " + countRecords(names));
                break;
            case 3:
                System.out.println("合計点数 = " + sumRecords(scores));
                break;
            case 4:
                System.out.println("平均点数 = " + averageRecords(names, scores));
                break;
            case 5:
                int count = countRecords(names);
                if(count < names.length){
                    names[count] = inputName();
                    scores[count] = inputScore();
                }else{
                    System.out.println("これ以上は入力できません。");
                }
            }
            System.out.println();
        }while(cmd_no != 6);
        
    }
    
    static int showMenu() throws IOException{
        System.out.println("1.成績表示");
        System.out.println("2.データ数表示");
        System.out.println("3.合計点表示");
        System.out.println("4.平均点表示");
        System.out.println("5.データ入力");
        System.out.println("6.終了");
        System.out.print("番号を入力して下さい：");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(br.readLine());
    }
    
    static void showRecords(String[] names, int[] scores){
        int count = countRecords(names);
        if(count == 0){
            System.out.println("データはありません。");
        }else{
            System.out.println("成績一覧");
            for(int i = 0; i < count; i++){
                System.out.println(names[i] + "\t" + scores[i]);
            }
        }
    }
    
    static int countRecords(String[] names){
        int count = 0;
        for(int i = 0; i < names.length; i++){
            if(!names[i].equals("")){
                count++;
            }
        }
        return count;
    }
    
    static int sumRecords(int[] scores){
        int sum = 0;
        for(int i = 0; i < scores.length; i++){
            sum += scores[i];
        }
        return sum;
    }
    
    static double averageRecords(String[] names, int[] scores){
        double sum = sumRecords(scores);
        double count = countRecords(names);
        return sum / count;
    }
    
    static String inputName() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("名前：");
        return br.readLine();
    }
    
    static int inputScore() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("点数：");
        return Integer.parseInt(br.readLine());
    }
}
