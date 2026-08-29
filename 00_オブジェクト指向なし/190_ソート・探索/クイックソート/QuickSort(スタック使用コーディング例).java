/**
 * QuickSort メソッド無使用バージョン
 */
public class QuickSort{
    public static void main(String[] args){
        int[] dat = new int[20];                            //データ列
        int left;                                           //グループ分け対象左端
        int right;                                          //グループ分け対象右端
        int small;                                          //小値グループ右端(この添字を含まず左側が小値グループ)
        int great;                                          //大値グループ左端(この添字を含まず右側が大値グループ)
        int[] stack = new int[(dat.length - 1) * 2];        //スタック
        int sp;                                             //スタックポインタ
        int swap;                                           //値交換時退避用
        
        //-----データ初期化＆状態表示-----------------------
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 100);
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
        
        //-----スタック初期化-------------------------------
        sp = -1;                                            //スタックポインタ初期化
        stack[++sp] = 0;                                    //最初に全データの範囲をスタックに積む
        stack[++sp] = dat.length - 1;                       //スタックは必ず左・右の順に積む
        
        while(sp >= 0){
            //-----スタックからデータ範囲読み出し-----------
            right = stack[sp--];
            left = stack[sp--];
            small = left;                                   //小値グループを左に、大値グループを右に分けるので、smallの初期値はleft
            great = right - 1;                              //データ範囲右端の値をグループ分けの基準値とするため、greatの初期値はright - 1
            
            //-----データを小値と大値のグループに分ける-----
            while(small <= great){
                if(dat[small] >= dat[right]){
                    if(dat[great] < dat[right]){            //smallの指すデータが大値なら、必ずgreatを-1する状況に持ち込む
                        swap = dat[small];
                        dat[small] = dat[great];
                        dat[great] = swap;
                        small++;
                    }
                    great--;
                }else{
                    small++;                                //smallが指すデータが小値なら、そのまま小値グループを拡大
                }
            }
            //-----基準値をグループの間に移動---------------
            swap = dat[right];                              //この処理により、smallは基準値の添字となる
            dat[right] = dat[small];                        //基準値と等しい値は大値グループに含めるので、smallと交換する
            dat[small] = swap;
            
            //-----データ途中状態表示-----------------------
            for(int i = 0; i < dat.length; i++){
                if(left <= i && i <= right){
                    System.out.printf("%3d",dat[i]);
                }else{
                    System.out.print("   ");
                }
            }
            System.out.println();
            
            //----さらなるグループ分けの必要性判断----------
            if(small - left > 1){                           //グループのデータ数が2個以上なら、さらにグループ分けが必要
                stack[++sp] = left;
                stack[++sp] = small - 1;
            }
            if(right - small > 1){
                stack[++sp] = small + 1;
                stack[++sp] = right;
            }
        }
        
        //-----データ終了状態表示---------------------------
        for(int i = 0; i < dat.length; i++){
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
    }
}
