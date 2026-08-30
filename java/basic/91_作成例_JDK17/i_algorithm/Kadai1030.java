package i_algorithm;
/******************************************************************************
 * 素因数分解に向けて
 * 整数がある素数で何回割れるかを表示する
 *****************************************************************************/
public class Kadai1030{
    public static void main(String[] args){
        //値の入力
        int num = Keyboard.readInt("自然数(割られる数)");
        int prime = Keyboard.readInt("自然数(素数の元)");
        if(num < 1 || prime < 1){
            System.out.println("値が不正です。");
            return;
        }
        
        //primeより大きい整数で、最小の素数を求める
        //方法は前回の課題と同じ
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
        
        //値numを素数primeで何回割れるかを数える
        int cnt = 0;              //割った回数
        int div = num;            //商
        while(div % prime == 0){  //割り切れる限り、割り続ける
            cnt++;
            div /= prime;
        }
        
        //結果表示
        System.out.println(num + " = " + prime + " ^ " + cnt + " × " + div);
    }
}
