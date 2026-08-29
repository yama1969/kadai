package i_algorithm;
/******************************************************************************
 * 素因数分解に向けて
 * ある整数より大きい素数のうち、最も小さいものを探す
 *****************************************************************************/
public class Kadai1020{
    public static void main(String[] args){
        //1以上の整数(自然数)の入力
        int num = Keyboard.readInt("自然数");
        if(num < 1){
            System.out.println("入力値が不正です。");
            return;
        }
        
        //3未満であれば、その+1が次の素数
        if(num < 3){
            System.out.println(num + 1);
            return;
        }
        
        //偶数なら、その次の奇数から探索を開始する
        //探索では最初に+2するので、予め-1する
        if(num % 2 == 0){
            num--;
        }
        
        //探索開始
        boolean prime = false;
        do{
            //入力値より大きい奇数について、小さい方から順に素数か否かを調べる
            //調べる方法は前の課題と同じ
            num += 2;
            prime = true;
            int max = (int)(Math.sqrt((double)num)) + 1;
            for(int i = 3; i < max && prime; i += 2){
                if(num % i == 0){
                    prime = false;
                }
            }
        }while(!prime);   //素数が見つかるまで奇数を増やし、探索継続
        
        //見つかった素数の表示
        System.out.println(num);
    }
}
