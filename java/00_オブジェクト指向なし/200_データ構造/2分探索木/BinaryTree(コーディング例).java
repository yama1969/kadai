/*
  2分探索木の原理とアルゴリズムを説明。
  その後、チーム毎にアクティビティ図を作成。
  各チームは図を作成すると共に、Javaコードを入力して動作確認していたため、
  最後のコーディングは講師提示の図に基づいて作業をして頂いた。
  (他人の図に基づいてコーディングする練習のため。)

  最初に整数値データを10回入力すると、2分探索木に格納される。
  処理簡略化のため、木の階層の限界から格納できなかったデータがあっても、データ入力は10回で終わるようにする。
  データ入力が終了すると、プログラムは探索状態に移る。
  探索状態では、探索したいデータを入力すると、格納添字を表示する。
  探索データが見つからない場合は、その旨を表示する。
*/

public class BinaryTree{
    public static void main(String[] args){
        int[] dat = new int[15];
        int   i;
        int   index;
        int   num;
        
        //全データ未格納
        for(i = 0; i < 15; i++){
            dat[i] = 0x80000000;
        }
        
        //データ格納
        System.out.println("データを10個入力してください。");
        for(i = 0; i < 10; i++){
            num = InputKey.inNum();
            index = 0;
            while(index < 15 && dat[index] != 0x80000000){
                if(dat[index] < num){
                    index = index * 2 + 2;
                }else{
                    index = index * 2 + 1;
                }
            }
            if(index < 15){
                dat[index] = num;
            }else{
                System.out.println("格納できませんでした。");
            }
        }
        
        //データ探索
        System.out.println("探索したいデータを入力してください。");
        while(true){
            num = InputKey.inNum();
            index = 0;
            while(index < 15 && dat[index] != 0x80000000 && dat[index] != num){
                if(dat[index] < num){
                    index = index * 2 + 2;
                }else{
                    index = index * 2 + 1;
                }
            }
            if(index > 14 || dat[index] == 0x80000000){
                System.out.println("見つかりませんでした。");
            }else{
                System.out.println(index);
            }
        }
    }
}
