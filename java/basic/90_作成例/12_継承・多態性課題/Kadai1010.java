public class Kadai1010{
    public static void main(String[] args){
        
        ObjectStack os = new ObjectStack();
        
        String prompt = "[e:終了, i:積む, o:取出す, g:最上データ, c:データ数, a:全クリア]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'i':
                int dat = Keyboard.readInt("pushする値");
                int num = os.push(new Integer(dat));
                System.out.println(num + "個");
                break;
            case 'o':
                Object o_dat = os.pop();
                if(o_dat == null){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(((Integer)o_dat).intValue());
                    num = os.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'g':
                o_dat = os.get();
                if(o_dat == null){
                    System.out.println("すでに空です。");
                }else{
                    System.out.println(((Integer)o_dat).intValue());
                    num = os.getCount();
                    System.out.println(num + "個");
                }
                break;
            case 'c':
                System.out.println(os.getCount() + "個");
                break;
            case 'a':
                os.clear();
                System.out.println("クリアしました。");
                break;
            default:
            }
            System.out.println();
        }
        
    }
}
