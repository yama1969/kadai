public class Three{
    public static void main(String[] args){
        for(int x = 0; x < 20 ; x++){
            double dx = (double)x;
            for(int i = 0; i < (int)(dx * dx * dx / 9.0 - 10.0 / 3.0 * dx * dx + 25.0 * dx); i++){
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
