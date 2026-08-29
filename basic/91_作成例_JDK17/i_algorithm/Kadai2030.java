package i_algorithm;
/******************************************************************************
 * シェルソート
 *****************************************************************************/
public class Kadai2030{
    public static void main(String[] args){
        //配列の初期化
        int[] dat = new int[30];
        for(int i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random() * 90) + 10;
        }
        
        //ソート前の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        //配列並べ替え(シェルソート)
        //飛び幅を変えるループ
        for(int width = dat.length / 2; width > 0; width /= 2){
            //挿入ソートの先頭位置を変えるループ
            for(int top = 0; top < width; top++){
                //挿入ソートのループ(挿入データ位置を変える)
                for(int inspos = top + width; inspos < dat.length; inspos += width){
                    int insdat = dat[inspos];
                    int comp = inspos;
                    //値比較ループ(比較位置を変える)
                    while(comp > top && dat[comp - width] < insdat){
                        dat[comp] = dat[comp - width];
                        comp -= width;
                    }
                    if(comp <= top){
                        comp = top;
                    }
                    dat[comp] = insdat;
                }
            }
        }
        
        //ソート後の配列の表示
        for(int i = 0; i < dat.length; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
    }
}
