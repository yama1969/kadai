package k_class_instance;
public class Kadai2010{
    public static void main(String[] args){
        
        Member[] members = new Member[10];
        int cnt = 0;
        
        String prompt = "[e:終了, n:新規, a:一覧, r:貸出, b:返却]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'n':
                if(cnt >= members.length){
                    System.out.println("これ以上登録できません。");
                }else{
                    System.out.println("新会員登録");
                    int no = Keyboard.readInt("会員番号");
                    String name = Keyboard.readString("氏名");
                    String tel = Keyboard.readString("電話番号");
                    Member m = new Member(no);
                    m.setName(name);
                    m.setTel(tel);
                    members[cnt] = m;
                    cnt++;
                }
                break;
            case 'a':
                for(int i = 0; i < members.length && members[i] != null; i++){
                    System.out.println(members[i].toString());
                }
                break;
            case 'r':
                Member m = search(members);
                if(m != null){
                    m.rent();
                }
                break;
            case 'b':
                m = search(members);
                if(m != null){
                    m.back();
                }
                break;
            default:
            }
            System.out.println();
        }
    }
    
    /**
     * 入力した会員番号のインスタンスを探索
     */
    private static Member search(Member[] members){
        int no = Keyboard.readInt("会員番号");
        int index = 0;
        while(index < members.length && members[index] != null && members[index].getNo() != no){
            index++;
        }
        if(index >= members.length || members[index] == null){
            System.out.println("その番号の会員は存在しません。");
            return null;
        }
        return members[index];
    }
}
