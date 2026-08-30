/******************************************************************************
 * 二分探索木の内容を降順に表示
 *****************************************************************************/
public class Kadai3030{
    public static void main(String[] args){
        final int SIZE = 15;
        final int NODAT = 99;
        
        //配列の初期化
        int[] dat = new int[SIZE];
        for(int i = 0; i < dat.length; i++){
            dat[i] = NODAT;
        }
        
        //整数乱数を生成し、二分探索木へ格納する
        for(int i = 0; i < SIZE * 5; i++){
            int w = (int)(Math.random() * 80) + 10;
            int pos = 0;
            
            while(pos < dat.length && dat[pos] != w){
                if(dat[pos] == NODAT){
                    dat[pos] = w;                 //その節点に代入
                }else{
                    if(w < dat[pos]){
                        pos = (pos + 1) * 2 - 1;  //左の子へ進む
                    }else{
                        pos = (pos + 1) * 2;      //右の子へ進む
                    }
                }
            }
        }
        
        //格納値を降順に表示
        int pos = 0;          //現在の添字
        int back = 0;         //移動状態(-1:左子から, 1:右子から, 0:親から)
        
        while(!(back == -1 && pos == 0)){  //左からの戻りでルートなら終了
            switch(back){
            case -1: //左からの戻り------------------------------------------
                if(pos % 2 == 1){                          //自分の左右を確認
                    back = -1;                               //自分は左の子
                }else{
                    back = 1;                                //自分は右の子
                }
                pos = (pos - 1) / 2;                       //親へ戻る
                break;
            case 0:  //親からの移動------------------------------------------
                if(pos < dat.length && dat[pos] != NODAT){ //値ある場合
                    back = 0;                                //自分は親
                    pos = (pos + 1) * 2;                     //右の子へ進む
                }else{                                     //値ない場合
                    if(pos % 2 == 1){                        //自分の左右を確認
                        back = -1;                             //自分は左の子
                    }else{
                        back = 1;                              //自分は右の子
                    }
                    pos = (pos - 1) / 2;                     //親へ戻る
                }
                break;
            case 1:  //右からの戻り------------------------------------------
                System.out.print(dat[pos] + " ");          //自分を表示
                back = 0;                                  //自分は親
                pos = (pos + 1) * 2 - 1;                   //左の子へ進む
            }
        }
        
    }
}
