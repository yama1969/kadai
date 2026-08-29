public class BubbleSort{
    public static void main(String[] args){
        int[] dat = {5, 24, 10, 11, 8};
        int   work;
        int   end_cnt;
        int   left_cnt;
        
        //データ初期状態表示
        for(int i = 0; i < 5; i++){
            System.out.print(dat[i] + " ");
        }
        System.out.println();
        
        for(end_cnt = 4; end_cnt > 0; end_cnt--){
            for(left_cnt = 0; left_cnt < end_cnt; left_cnt++){
                if(dat[left_cnt] > dat[left_cnt + 1]){
                    work = dat[left_cnt];
                    dat[left_cnt] = dat[left_cnt + 1];
                    dat[left_cnt + 1] = work;
                }
                
                //データ途中状態表示
                for(int i = 0; i < 5; i++){
                    System.out.print(dat[i] + " ");
                }
                System.out.println();
            }
        }
    }
}
