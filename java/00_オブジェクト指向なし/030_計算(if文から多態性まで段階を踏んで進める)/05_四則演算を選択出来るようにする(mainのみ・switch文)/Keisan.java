import java.io.*;

public class Keisan{
    public static void main(String[] args){
        final int KIND_NUM = 4;   //計算種類の数
        final int ADD = 0;        //加算
        final int SUB = 1;        //減算
        final int MUL = 2;        //乗算
        final int DIV = 3;        //除算
        
        final int QUES_NUM = 10;   //出題数
        final int MIN = 1;         //問題に使う数の最小値
        final int MAX = 9;         //問題に使う数の最大値
        
        boolean malinput = false;  //不正入力の有無 true:不正入力あり
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //計算の種類を選択する
        int kind = 0;              //種類(ADD,SUB,MUL,DIVのいずれか)
        System.out.println("これから計算問題を" + QUES_NUM + "問だします。");
        do{
            System.out.println();
            System.out.println("計算の種類を選択してください。");
            System.out.print(ADD + ":加算 " + SUB + ":減算 " + MUL + ":乗算 " + DIV + ":除算 => ");
            try{
                malinput = false;
                String line = reader.readLine();
                kind = Integer.parseInt(line);
                if(kind < 0 || kind > 3){
                    malinput = true;
                }
            }catch(NumberFormatException e){
                malinput = true;
            }catch(IOException e){
                System.out.println("キーボードエラーのため、プログラムを中断します。");
                return;
            }
        }while(malinput);
        
        //問題を繰り返し出す。
        System.out.println();
        int correct = 0;         //正答数
        long stime = System.currentTimeMillis();
        for(int i = 0; i < QUES_NUM; i++){
            //問題を作る
            int a = 0;
            int b = 0;
            int ans = 0;
            switch(kind){
            case ADD: //加算
            case MUL: //乗算
                a = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
                b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
                break;
            case SUB: //減算
                b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
                ans = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
                a = ans + b;
                break;
            case DIV: //除算
                b = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
                ans = (int)(Math.random() * (MAX - MIN + 1)) + MIN;
                a = ans * b;
                break;
            default: //ここにはこないはず
                System.out.println("プログラムが不正に終了しました。");
                return;
            }
            
            do{
                //問題を表示
                System.out.print("[第" + (i + 1) + "問] ");
                switch(kind){
                case ADD: //加算
                    System.out.print(a + " ＋ " + b + " ＝ ");
                    break;
                case SUB: //減算
                    System.out.print(a + " － " + b + " ＝ ");
                    break;
                case MUL: //乗算
                    System.out.print(a + " × " + b + " ＝ ");
                    break;
                case DIV: //減算
                    System.out.print(a + " ÷ " + b + " ＝ ");
                    break;
                default: //ここにはこないはず
                    System.out.println("プログラムが不正に終了しました。");
                    return;
                }
                
                //回答入力
                try{
                    malinput = false;
                    String line = reader.readLine();
                    ans = Integer.parseInt(line);
                }catch(NumberFormatException e){
                    malinput = true;
                }catch(IOException e){
                    System.out.println("キーボードエラーのため、プログラムを中断します。");
                    return;
                }
            }while(malinput);
            
            //正誤判定
            switch(kind){
            case ADD:  //加算
                if(ans == a + b){
                    System.out.println("正解！");
                    correct++;
                }else{
                    System.out.println("まちがい。。。");
                }
                break;
            case SUB:  //減算
                if(ans == a - b){
                    System.out.println("正解！");
                    correct++;
                }else{
                    System.out.println("まちがい。。。");
                }
                break;
            case MUL:  //乗算
                if(ans == a * b){
                    System.out.println("正解！");
                    correct++;
                }else{
                    System.out.println("まちがい。。。");
                }
                break;
            case DIV:  //除算
                if(ans == a / b){
                    System.out.println("正解！");
                    correct++;
                }else{
                    System.out.println("まちがい。。。");
                }
                break;
            default: //ここにはこないはず
                System.out.println("プログラムが不正に終了しました。");
                return;
            }
        }
        long etime = System.currentTimeMillis();
        double time = (double)(etime - stime) / 1000.0;
        
        //結果表示
        double rate = (double)correct * 100.0/ (double)QUES_NUM;
        int score = (int)(rate * 10.0 / time);
        
        System.out.println();
        System.out.println(QUES_NUM + "問中、" + correct + "問正解。");
        System.out.println("正答率は" + (int)rate + "%でした。");
        System.out.println("経過時間は" + time + "秒");
        System.out.println("スコアは" + score + "点");
    }
}
