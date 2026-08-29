public class Kadai2020{
    public static void main(String[] args){
        
        ObjectListG<MemberS> members = new ObjectListG<MemberS>();
        
        String prompt = "[e:終了, n:新規, a:一覧, r:貸出, b:返却]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'n':
                System.out.println("新会員登録");
                MemberS m = MemberS.newMember();
                members.add(members.size(), m);
                break;
            case 'a':
                Object[] list = members.toArray();
                for(int i = 0; i < list.length; i++){
                    m = (MemberS)list[i];
                    System.out.println(m);
                }
                break;
            case 'r':
                m = search(members);
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
    private static MemberS search(ObjectListG<MemberS> members){
        int no = Keyboard.readInt("会員番号");
        Object[] list = members.toArray();
        int i = 0;
        while(i < list.length && ((MemberS)list[i]).getNo() != no){
            i++;
        }
        if(i >= list.length){
            System.out.println("その番号の会員は存在しません。");
            return null;
        }
        return (MemberS)list[i];
    }
}
