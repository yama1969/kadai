public class Graph{
    public static void main(String[] args){
        int width = 30;
        int cycle1 = 89;
        int cycle2 = 97;
        double t1 = 0.0;
        double t2 = 0.0;
        while(true){
            int y = (int)(Math.sin(t1 * Math.PI) * (double)width) + width;
            for(int i = 0; i < y; i++){
                System.out.print('#');
            }
            for(int i = y; i < width * 2; i++){
                System.out.print('.');
            }
            System.out.println();
            
            y = (int)(Math.cos(t2 * Math.PI) * (double)width) + width;
            for(int i = 0; i < y; i++){
                System.out.print('.');
            }
            for(int i = y; i < width * 2; i++){
                System.out.print('#');
            }
            System.out.println();
            
            t1 += 2.0/(double)cycle1;
            if(t1 >= 2.0){
                t1 -= 2.0;
            }
            t2 += 2.0/(double)cycle2;
            if(t2 >= 2.0){
                t2 -= 2.0;
            }
        }
    }
}
