package a_hensu;

/******************************************************************************
 * 変数を効率良く使う。3つの円柱を巻くのに必要な紐の長さは
 *****************************************************************************/
public class Kadai2700{
    public static void main(String[] args){
        //紐の長さ合計
        double sum = 0.0;
        
        //1つめの円柱：直径の入力と長さの計算と合計への加算
        int d = Keyboard.readInt("1つめの円柱の直径[cm]");
        double length = (double)d * Math.PI;
        System.out.println("必要な紐の長さ = " + length + " [cm]");
        System.out.println();
        sum += length;
        
        //2つめの円柱：直径の入力と長さの計算と合計への加算
        d = Keyboard.readInt("2つめの円柱の直径[cm]");
        length = (double)d * Math.PI;
        System.out.println("必要な紐の長さ = " + length + " [cm]");
        System.out.println();
        sum += length;
        
        //3つめの円柱：直径の入力と長さの計算と合計への加算
        d = Keyboard.readInt("3つめの円柱の直径[cm]");
        length = (double)d * Math.PI;
        System.out.println("必要な紐の長さ = " + length + " [cm]");
        System.out.println();
        sum += length;
        
        //合計長さの表示
        System.out.println();
        System.out.println("必要な紐の長さの合計 = " + sum + " [cm]");
    }
}
