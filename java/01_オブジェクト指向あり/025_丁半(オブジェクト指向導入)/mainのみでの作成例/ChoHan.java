import java.util.Random;
import java.util.Scanner;

public class ChoHan{
    public static void main(String[] args){
        final int NUM = 2;
        Scanner in = new Scanner(System.in);
        Random r = new Random();
        
        System.out.print("c:’š h:”¼ e:I—¹ => ");
        String cmd = in.nextLine();
        
        int cnt = 0;
        int win = 0;
        while(!cmd.equals("e")){
            cnt++;
            int sum = 0;
            for(int i = 0; i < NUM; i++){
                int eye = r.nextInt(6) + 1;
                sum += eye;
                System.out.print(eye + " ");
            }
            if(sum % 2 == 0){
                System.out.println("‚Ì’š");
                if(cmd.equals("c")){
                    win++;
                }
            }else{
                System.out.println("‚Ì”¼");
                if(cmd.equals("h")){
                    win++;
                }
            }
            System.out.println(win + "Ÿ" + (cnt - win) + "”s");
            System.out.println();
            
            System.out.print("c:’š h:”¼ e:I—¹ => ");
            cmd = in.nextLine();
        }
    }
}
