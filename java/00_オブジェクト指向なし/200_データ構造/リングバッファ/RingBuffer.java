/*
  講師がホワイトボードに流れ図を描き、プロジェクタを用いて
  図をどのようにコードに起こすかをデモンストレーションした。

  整数値データを入力すると、リングバッファに格納される。
  0を入力すると、リングバッファの先頭のデータを表示する。
  表示済みデータは捨てられる。
*/

public class RingBuffer{
    public static void main(String[] args){
        int[] dat = new int[5];  //リングバッファ
        int   s;                 //データ先頭添字
        int   e;                 //データ最後尾添字
        int   in;                //入力数値

        s = 0;
        e = -1;

        while(true){
            in = InputKey.inNum();
            if(in == 0){
                //表示処理
                if(e != -1){
                    System.out.println(dat[e]);
                    if(s != e){
                        e = (e + 1) % 5;   //添字4の次が0になるように
                    }else{
                        e = -1;            //データなしにする
                    }
                }else{
                    System.out.println("データありません。");
                }
            }else{
                //格納処理
                if((s + 1) % 5 != e){
                    s = (s + 1) % 5;
                    dat[s] = in;
                    if(e == -1){
                        e = s;
                    }
                }else{
                    System.out.println("データがいっぱいです。");
                }
            }
        }
    }
}
