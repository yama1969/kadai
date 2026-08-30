//if文とfor文を復習しつつ、配列へ誘導するための演習。
//-5～+4のデータを5個用意し、0以上のデータの個数を数える。
public class Algo03_Count{
    public static void main(String[] args){
        //まずは変数をデータ数分用意した方法を書かせる。
        System.out.println("まずは変数をデータ数分用意した方法");
        int dat1 = (int)(Math.random() * 10) - 5;
        int dat2 = (int)(Math.random() * 10) - 5;
        int dat3 = (int)(Math.random() * 10) - 5;
        int dat4 = (int)(Math.random() * 10) - 5;
        int dat5 = (int)(Math.random() * 10) - 5;
        
        System.out.println(dat1);
        System.out.println(dat2);
        System.out.println(dat3);
        System.out.println(dat4);
        System.out.println(dat5);
        
        int count = 0;
        if(dat1 >= 0){
            count++;
        }
        if(dat2 >= 0){
            count++;
        }
        if(dat3 >= 0){
            count++;
        }
        if(dat4 >= 0){
            count++;
        }
        if(dat5 >= 0){
            count++;
        }
        System.out.println("0以上の数 = " + count);
        System.out.println();
        
        //次に、データ用変数を1つにして同様の動作を書く。
        //上の処理と違い、データが残らないことを気付かせること。
        System.out.println("次に、データ用変数を1つにして同様の動作");
        count = 0;
        for(int i = 0; i < 5; i++){
            int dat = (int)(Math.random() * 10) - 5;
            System.out.println(dat);
            if(dat >= 0){
                count++;
            }
        }
        System.out.println("0以上の数 = " + count);
        System.out.println();
        
        //最後に、配列を解説し、同様の処理を配列で行う。
        //プログラムが簡素になり、かつデータも残る。
        System.out.println("最後に、配列を解説し、同様の処理を配列で行う。");
        int[] dats = new int[5];
        count = 0;
        for(int i = 0; i < 5; i++){
            dats[i] = (int)(Math.random() * 10) - 5;
            System.out.println(dats[i]);
            if(dats[i] >= 0){
                count++;
            }
        }
        System.out.println("0以上の数 = " + count);
    }
}
