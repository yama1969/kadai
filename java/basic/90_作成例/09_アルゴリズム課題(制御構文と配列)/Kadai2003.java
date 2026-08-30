/******************************************************************************
 * トップ2の値を配列の左端に移動する
 *****************************************************************************/
public class Kadai2003{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = { 8, 3, 1, 7, 5, 0, 4, 6, 9, 2};
        
        //処理前の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        //最大値移動
        for(int comp = dat.length - 1; comp > 0; comp--){
            if(dat[comp - 1] < dat[comp]){
                int w = dat[comp];
                dat[comp] = dat[comp - 1];
                dat[comp - 1] = w;
            }
        }
        //No2の値移動
        for(int comp = dat.length - 1; comp > 1; comp--){
            if(dat[comp - 1] < dat[comp]){
                int w = dat[comp];
                dat[comp] = dat[comp - 1];
                dat[comp - 1] = w;
            }
        }
        
        //処理後の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
}
