package k_class_instance;
public class Kadai1010{
    public static void main(String[] args){
        
        int size = Keyboard.readInt("スタックサイズ");
        IntStack is = new IntStack(size);
        
        String prompt = "[e:終了, i:積む, o:取出す, g:最上データ, c:データ数, a:全クリア]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'i':
                int dat = Keyboard.readInt("pushする値");
                int num = is.push(dat);
                if(num == -1){
                    System.out.println("すでに満杯です。");
                }else{
                    System.out.println(num + "個");
                }
                break;
            case 'o':
                dat = is.pop();
                if(dat == Integer.MIN_VALUE){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(dat);
                    num = is.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'g':
                dat = is.get();
                if(dat == Integer.MIN_VALUE){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(dat);
                    num = is.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'c':
                System.out.println(is.getCount() + "個");
                break;
            case 'a':
                is.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
