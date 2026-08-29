/*
大量のデータをソートする演習を作ろうと思い、ちょっと作ってみたプログラム。
Excel2010で作成した1048576件の0～9999999999の整数(つまりlong型)をソートする。

2010年頃にも10万件程度のデータをソートする演習をやったが、
そのときは、全データを配列に読み込むとメモリオーバーになるので、
どうしてもマージソートをしなくてはならなかった。
(もちろん、JVMの設定でメモリ上限を変更することはできるが、
 他システムへの影響もあるだろうから、お勧めできない。
 なお、ArrayListを紹介する前段階なので、配列で対処している。)

しかも、データ件数を調べるために最初にファイル読み込みをすると、
それだけで時間がかかっていたので、ファイル読み込みは一度だけにする工夫も
必要だった。

それを2015年のJavaクラスでもやらせてみようと思ったのだが・・・。

この演習、Java7では腕力勝負でも全く問題ないのだね。
実行してみたら、メモリオーバーにもならず、素早く処理を終えてしまう。
マシンの性能もあるのだろうが。。。

挿入ソートでは時間がかかるので、クイックソートにしてみたくらい。
クイックソートにしたって、メモリをほとんど圧迫しない。
表示させてみたら、クイックソートのメソッド再帰呼び出しは1397791回だった。
階層としては20くらいか。log(1397791,2)でいいんだよな。
・・・と思って、これも表示させてみたら49。えぇぇぇぇなんでぇぇぇぇ。
あ、そか。20は完全に均等に分けられた場合の数値か。
何れにしても、50程度のコールスタックじゃあメモリは圧迫しないな。
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ManyDataSort{
    public static void main(String[] args){
        FileReader fr = null;
        BufferedReader br = null;
        
        int cnt = 0;
        try{
            System.out.println("ファイルを開きます。");
            fr = new FileReader("rand_num.txt");
            try{
                System.out.println("ファイル読み込みを開始します。");
                br = new BufferedReader(fr);
                String line = null;
                while((line = br.readLine()) != null){
                    cnt++;
                }
                System.out.println(cnt + "件のデータがあります。");
            }catch(IOException e){
                System.out.println("入出力エラーです。");
            }finally{
                try{
                    System.out.println("ファイルを閉じます。");
                    br.close();
                }catch(IOException e){
                    System.out.println("読み込みファイルクローズエラーです。");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("読み込みファイルがありません。");
        }
        
        System.out.println("long型配列を生成します。");
        long[] nums = new long[cnt];
        
        try{
            System.out.println("ファイルを開きます。");
            fr = new FileReader("rand_num.txt");
            try{
                System.out.println("配列にファイルのデータを読み込みます。");
                br = new BufferedReader(fr);
                cnt = 0;
                String line = null;
                while((line = br.readLine()) != null){
                    nums[cnt] = Long.parseLong(line);
                    cnt++;
                }
                System.out.println("読み込み完了しました。");
            }catch(IOException e){
                System.out.println("入出力エラーです。");
            }finally{
                try{
                    System.out.println("ファイルを閉じます。");
                    br.close();
                }catch(IOException e){
                    System.out.println("読み込みファイルクローズエラーです。");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("読み込みファイルがありません。");
        }
        
//        System.out.println("データを挿入ソートします。");
//        insertSort(nums);
        System.out.println("データをクイックソートします。");
        quickSort(nums, 0, nums.length - 1);
        System.out.println("メソッド呼出しは" + quick_cnt + "回");
        System.out.println("メソッド呼出し階層深さは" + quick_max_depth);
        System.out.println("現在の階層深さは" + quick_depth);
        
        System.out.println("ソート済データを保存します。");
        FileWriter fw = null;
        PrintWriter pw = null;
        try{
            fw = new FileWriter("sort_num.txt");
            pw = new PrintWriter(fw);
            for(cnt = 0; cnt < nums.length; cnt++){
                pw.println(nums[cnt]);
            }
            pw.flush();
            pw.close();
        }catch(IOException e){
            System.out.println("書込み用ファイルが開けません。");
        }
        
        System.out.println("全て完了。");
    }
    
    public static void insertSort(long[] nums){
        for(int i = 1; i < nums.length; i++){
//            System.out.println(i + " / " + (nums.length - 1));
            for(int j = i - 1; j > -1; j--){
                if(nums[j] > nums[j + 1]){
                    long temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }else{
                    break;
                }
            }
        }
    }
    
    private static int quick_cnt = 0;
    private static int quick_depth = 0;
    private static int quick_max_depth = 0;
    
    public static void quickSort(long[] nums, int start, int end){
        quick_cnt++;
        quick_depth++;
        if(quick_depth > quick_max_depth){
            quick_max_depth = quick_depth;
        }
        
        if(start >= end){
            quick_depth--;
            return;
        }
        
        int left = start;
        int right = end;
        while(left < right){
            if(nums[left] >= nums[left + 1]){
                long temp = nums[left];
                nums[left] = nums[left + 1];
                nums[left + 1] = temp;
                left++;
            }else{
                long temp = nums[right];
                nums[right] = nums[left + 1];
                nums[left + 1] = temp;
                right--;
            }
        }
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
        quick_depth--;
    }
}
