/******************************************************************************
  二分探索木クラス
    
    整数データを二分探索木の構造で持つ。
    データ構造に対する次の4つの機能を持つ
    (1)データ探索
    (2)データ挿入
    (3)データ表示
    (4)データ削除
 ******************************************************************************/
public class BinarySearchTree{
    
    //データを持つ配列
    private static int[] dat = {38, 27, 63, 15, 32, 51, 82, 12, 23, 28, 35, 44, 60, 73, 96};
    
    //-------------------------------------------------------------------------
    // メインメソッド～各メソッドを呼び出す
    //-------------------------------------------------------------------------
    public static void main(String[] args){
        //表示
        System.out.println("データは次の通りです。");
        show();
        
        //探索1
        System.out.println("28を探索します。存在します。");
        int num = 28;
        search(num);
        
        //探索2
        System.out.println("29を探索します。存在しません。");
        num = 29;
        search(num);
        
        //挿入1
        System.out.println("28を挿入します。重複でエラーとなります。");
        num = 28;
        insert(num);
        
        //挿入2
        System.out.println("29を挿入します。空き無しでエラーとなります。");
        num = 29;
        insert(num);
        
        //削除1
        System.out.println("29を削除します。存在しないのでエラーとなります。");
        num = 29;
        delete(num);
        
        //削除2
        System.out.println("28を削除します。");
        num = 28;
        delete(num);
        
        //表示
        System.out.println("データは次のようになりました。");
        show();
        
        //挿入3
        System.out.println("26を挿入します。空き無しでエラーとなります。");
        num = 26;
        insert(num);
        
        //挿入4
        System.out.println("30を挿入します。");
        num = 30;
        insert(num);
        
        //表示
        System.out.println("データは次のようになりました。");
        show();
        
    }
    
    //-------------------------------------------------------------------------
    // 課題1～探索
    //   引数：num 探索値
    //   戻値：値の存在した添字
    //-------------------------------------------------------------------------
    public static int search(int num){
        int i = 0;
        while(i < dat.length && dat[i] != num && dat[i] !=0){
            if(dat[i] > num){
                i = (i + 1) * 2 - 1;
            }else{
                i = (i + 1) * 2;
            }
        }
        if(i < dat.length && dat[i] != 0){
            System.out.println(num + "は添字" + i + "にあります。");
        }else{
            System.out.println(num + "は見つかりませんでした。");
        }
        return i;
    }
    
    //-------------------------------------------------------------------------
    // 課題2～挿入
    //   引数：num 挿入値
    //   戻値：なし
    //-------------------------------------------------------------------------
    public static void insert(int num){
        int i = 0;
        boolean ins = false;
        while(i < dat.length && dat[i] != num){
            if(dat[i] == 0){
                dat[i] = num;
                ins = true;
            }else{
                if(dat[i] > num){
                    i = (i + 1) * 2 - 1;
                }else{
                    i = (i + 1) * 2;
                }
            }
        }
        if(ins){
            System.out.println("値を添字" + i + "に挿入しました。");
        }else{
            if(i < dat.length){
                System.out.println("既に挿入済みの値です。");
            }else{
                System.out.println("空きがありませんでした。");
            }
        }
    }
    
    //-------------------------------------------------------------------------
    // 課題3～表示
    //   引数：なし
    //   戻値：なし
    //-------------------------------------------------------------------------
    public static void show(){
        int i = 0;
        int stat = 2;
        while(i != 0 || stat != 0){
            if(i >= dat.length || dat[i] == 0 || stat == 0){
                stat = i % 2;
                i = (i - 1) / 2;
            }else{
                if(stat == 1){
                    System.out.print(dat[i] + " ");
                    i = (i + 1) * 2;
                }else{
                    i = (i + 1) * 2 - 1;
                }
                stat = 2;
            }
        }
        System.out.println();
    }
    
    //-------------------------------------------------------------------------
    // 課題4～削除
    //   引数：num 削除値
    //   戻値：なし
    //-------------------------------------------------------------------------
    public static void delete(int num){
        boolean del = false;
        int i = search(num);
        if(i < dat.length && dat[i] != 0){
            dat[i] = 0;
            del = true;
            int l = (i + 1) * 2 - 1;
            int r = (i + 1) * 2;
            while((l < dat.length && dat[l] != 0) || (r < dat.length && dat[r] != 0)){
                int j = 0;
                if(r < dat.length && dat[r] != 0){
                    j = r;
                    l = (j + 1) * 2 - 1;
                    while(l < dat.length && dat[l] != 0){
                        j = l;
                        l = (j + 1) * 2 - 1;
                    }
                }else{
                    j = l;
                    r = (j + 1) * 2;
                    while(r < dat.length && dat[r] != 0){
                        j = r;
                        r = (j + 1) * 2;
                    }
                }
                dat[i] = dat[j];
                i = j;
                dat[i] = 0;
                l = (i + 1) * 2 - 1;
                r = (i + 1) * 2;
            }
        }
        if(del){
            System.out.println("値" + num + "を削除しました。");
        }else{
            System.out.println("削除する値" + num + "が見つかりませんでした。");
        }
    }
}
