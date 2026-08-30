package i_algorithm;
/******************************************************************************
 * 素因数分解
 *****************************************************************************/
public class Kadai1040{
    public static void main(String[] args){
        //値の入力
        int num = Keyboard.readInt("2以上の自然数");
        if(num < 2){
            System.out.println("値が不正です。");
            return;
        }
        
        //初期値代入
        System.out.print(num + " = ");
        boolean x_mark = false;  //×の表示フラグ(true:表示)
        int prime = 2;           //最初の素数
        
        //素因数分解処理と表示
        while(num != 1){         //numが1なら終了
            //値numを素数primeで何回割れるかを数える
            //方法は前回の課題と同じ
            int cnt = 0;
            while(num % prime == 0){
                cnt++;
                num /= prime;
            }
            
            //割れた数が1以上なら、素因数としてprimeを表示
            if(cnt != 0){
                if(x_mark){
                    System.out.print(" × ");
                }else{
                    x_mark = true;
                }
                System.out.print(prime + " ^ " + cnt);
            }
            
            //次の素数primeを求める
            //primeより大きい整数で、最小の素数を求める
            //方法は以前の課題と同じ
            if(prime < 3){
                prime++;
            }else{
                if(prime % 2 == 0){
                    prime--;
                }
                boolean p = false;
                do{
                    prime += 2;
                    p = true;
                    int max = (int)(Math.sqrt((double)prime)) + 1;
                    for(int i = 3; i < max && p; i += 2){
                        if(prime % i == 0){
                            p = false;
                        }
                    }
                }while(!p);
            }
        }
        
        //終了時の改行
        System.out.println();
    }
}
