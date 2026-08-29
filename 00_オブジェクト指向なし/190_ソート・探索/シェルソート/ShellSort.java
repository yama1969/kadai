public class ShellSort{
    public static void main(String[] args){
        int[] dat = new int[25];
        int   end;
        int   left;
        int   interval;
        int   group;
        int   i,w;
        
        for(i = 0; i < dat.length; i++){
            dat[i] = (int)(Math.random()*100.0);
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
        
        interval = dat.length / 2;
        while(interval >= 1){
            for(group = 0; group < interval; group++){
                for(end = group + interval; end < dat.length; end += interval){
                    for(left = end - interval; left >= 0; left -= interval){
                        if(dat[left] > dat[left + interval]){
                            w = dat[left];
                            dat[left] = dat[left + interval];
                            dat[left + interval] = w;
                        }else{
                            break;
                        }
                    }
                }
            }
            interval /= 2;
        }
        
        for(i = 0; i < dat.length; i++){
            System.out.printf("%3d",dat[i]);
        }
        System.out.println();
    }
}
