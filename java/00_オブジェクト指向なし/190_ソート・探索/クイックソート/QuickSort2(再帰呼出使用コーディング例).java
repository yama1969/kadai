/**
 * QuickSort2 メソッド使用バージョン
 */
public class QuickSort2{
    public static void main(String[] args){
        int[] dat = new int[20];                            //データ列
        //-----データ初期化＆状態表示-----------------------
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 100);
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
        
        sort(dat, 0, dat.length - 1);
        
        //-----データ終了状態表示---------------------------
        for(int i = 0; i < dat.length; i++){
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
    }
    
    //-----クイックソート実行(再帰呼出)---------------------
    //  dat:データ列
    //  left:グループ分け対象左端
    //  right:グループ分け対象右端
    public static void sort(int[] dat, int left, int right){
        int small;                                          //小値グループ右端(この添字を含まず左側が小値グループ)
        int great;                                          //大値グループ左端(この添字を含まず右側が大値グループ)
        int swap;                                           //値交換時退避用
        
        small = left;                                       //小値グループを左に、大値グループを右に分けるので、smallの初期値はleft
        great = right - 1;                                  //データ範囲右端の値をグループ分けの基準値とするため、greatの初期値はright - 1
        
        //-----データを小値と大値のグループに分ける---------
        while(small <= great){
            if(dat[small] >= dat[right]){
                if(dat[great] < dat[right]){                //smallの指すデータが大値なら、必ずgreatを-1する状況に持ち込む
                    swap = dat[small];
                    dat[small] = dat[great];
                    dat[great] = swap;
                    small++;
                }
                great--;
            }else{
                small++;                                    //smallが指すデータが小値なら、そのまま小値グループを拡大
            }
        }
        //-----基準値をグループの間に移動-------------------
        swap = dat[right];                                  //この処理により、smallは基準値の添字となる
        dat[right] = dat[small];                            //基準値と等しい値は大値グループに含めるので、smallと交換する
        dat[small] = swap;
        
        //-----データ途中状態表示---------------------------
        for(int i = 0; i < dat.length; i++){
            if(left <= i && i <= right){
                System.out.printf("%3d",dat[i]);
            }else{
                System.out.print("   ");
            }
        }
        System.out.println();
        
        //----さらなるグループ分けの必要性判断--------------
        if(small - left > 1){                               //グループのデータ数が2個以上なら、さらにグループ分けが必要
            sort(dat, left, small - 1);
        }
        if(right - small > 1){
            sort(dat, small + 1, right);
        }
    }
}
