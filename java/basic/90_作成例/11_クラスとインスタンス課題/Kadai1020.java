public class Kadai1020{
    public static void main(String[] args){
        
        int size = Keyboard.readInt("キューサイズ");
        IntQueue iq = new IntQueue(size);
        
        String prompt = "[e:終了, i:入れる, o:取出す, g:先頭データ, c:データ数, a:全クリア]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'i':
                int dat = Keyboard.readInt("入れる値");
                int num = iq.enqueue(dat);
                if(num == -1){
                    System.out.println("すでに満杯です。");
                }else{
                    System.out.println(num + "個");
                }
                break;
            case 'o':
                dat = iq.dequeue();
                if(dat == Integer.MIN_VALUE){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(dat);
                    num = iq.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'g':
                dat = iq.get();
                if(dat == Integer.MIN_VALUE){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(dat);
                    num = iq.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'c':
                System.out.println(iq.getCount() + "個");
                break;
            case 'a':
                iq.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
