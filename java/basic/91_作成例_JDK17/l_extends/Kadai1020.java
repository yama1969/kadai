package l_extends;
public class Kadai1020{
    public static void main(String[] args){
        
        ObjectQueue oq = new ObjectQueue();
        
        String prompt = "[e:終了, i:入れる, o:取出す, g:先頭データ, c:データ数, a:全クリア]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'i':
                int dat = Keyboard.readInt("入れる値");
                int num = oq.enqueue(dat);
                System.out.println(num + "個");
                break;
            case 'o':
                Integer o_dat = (Integer)oq.dequeue();
                if(o_dat == null){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(o_dat);
                    num = oq.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'g':
                o_dat = (Integer)oq.get();
                if(o_dat == null){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(o_dat);
                    num = oq.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'c':
                System.out.println(oq.getCount() + "個");
                break;
            case 'a':
                oq.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
