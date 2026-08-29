//if文の練習として、変数の比較をする。
public class Algo02_Compare{
    public static void main(String[] args){
        //まずは2変数でやってみる。
        System.out.println("まずは2変数でやってみる。");
        int a = (int)(Math.random() * 3) + 1;
        int b = (int)(Math.random() * 3) + 1;
        
        System.out.println("a,b = " + a + "," + b);
        
        if(a > b){
            System.out.println("aの方が大きい。");
        }else if(a < b){
            System.out.println("bの方が大きい。");
        }else{
            System.out.println("2つは等しい。");
        }
        System.out.println();
        
        //次に、3変数でやってみる。
        System.out.println("次に、3変数でやってみる。");
        int x = (int)(Math.random() * 3) + 1;
        int y = (int)(Math.random() * 3) + 1;
        int z = (int)(Math.random() * 3) + 1;
        
        System.out.println("x,y,z = " + x + "," + y + "," + z);
        
        //ここの条件式は全部必要なのか、などとやると、かなり時間を取る。
        if(x > y && x > z){
            System.out.println("xが一番大きい。");
        }else if(y > x && y > z){
            System.out.println("yが一番大きい。");
        }else if(z > x && z > y){
            System.out.println("zが一番大きい。");
        }else{
            System.out.println("大きい値を持つ2変数が等しいか、3変数とも等しい。");
        }
    }
}
