public class BubbleSort2{
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
        
        end_cnt = 4;
        while(end_cnt > 0){
            left_cnt = 0;
            while(left_cnt < end_cnt){
                if(dat[left_cnt] > dat[left_cnt + 1]){
                    work = dat[left_cnt];
                    dat[left_cnt] = dat[left_cnt + 1];
                    dat[left_cnt + 1] = work;
                }
                left_cnt++;
                
                //データ途中状態表示
                for(int i = 0; i < 5; i++){
                    System.out.print(dat[i] + " ");
                }
                System.out.println();
            }
            end_cnt--;
        }
    }
}
