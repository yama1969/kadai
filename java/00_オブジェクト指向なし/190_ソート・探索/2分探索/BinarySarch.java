public class BinarySarch{
    public static void main(String[] args){
        int[] dat = new int[20];          //データ列
        int[] no  = new int[dat.length];  //データ番号
        int sarch;                        //探索値
        int left;                         //探索範囲左端
        int right;                        //探索範囲右端
        int mid;                          //探索位置
        int i,j;
        
        //データ入力
        System.out.println("データ入力をします。");
        for(i = 0; i < dat.length; i++){
            dat[i] = InputKey.inNum((i + 1) + "個目のデータ");
            no[i] = i + 1;
        }
        System.out.println();
        
        //データソート(挿入ソート)
        for(i = 1; i < dat.length; i++){
            int w_dat = dat[i];
            int w_no = no[i];
            for(j = i; j > 0 && dat[j - 1] > w_dat ; j--){
                dat[j] = dat[j - 1];
                no[j] = no[j - 1];
            }
            dat[j] = w_dat;
            no[j] = w_no;
        }
        
/*
        //ソート済みデータ表示
        for(i = 0; i < dat.length; i++){
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
        System.out.println();
*/
        
        //データ探索
        System.out.println("探索をします。");
        while(true){
            sarch = InputKey.inNum("探索値");
            left = 0;
            right = dat.length - 1;
            while(left <= right){
                mid = (left + right) / 2;
                if(dat[mid] == sarch){
                    System.out.println("番号" + no[mid]);
                    break;
                }
                if(dat[mid] < sarch){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
                if(left > right){
                    System.out.println("見つかりませんでした。");
                }
            }
            System.out.println();
        }
    }
}
