public class Kadai2030{
    public static void main(String[] args){
        
        MemberEx[] members = new MemberEx[10];
        int cnt = 0;
        
        String prompt = "[e:I—¹, n:V‹K, a:ˆê——, r:‘İo, b:•Ô‹p]";
        char cmd = 0;
        while((cmd = Keyboard.readChar(prompt)) != 'e'){
            switch(cmd){
            case 'n':
                if(cnt >= members.length){
                    System.out.println("‚±‚êˆÈã“o˜^‚Å‚«‚Ü‚¹‚ñB");
                }else{
                    System.out.println("V‰ïˆõ“o˜^");
                    MemberEx m = MemberEx.newMember();
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
                MemberEx m = search(members);
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
     * “ü—Í‚µ‚½‰ïˆõ”Ô†‚ÌƒCƒ“ƒXƒ^ƒ“ƒX‚ğ’Tõ
     */
    private static MemberEx search(MemberEx[] members){
        int no = Keyboard.readInt("‰ïˆõ”Ô†");
        int index = 0;
        while(index < members.length && members[index] != null && members[index].getNo() != no){
            index++;
        }
        if(index >= members.length || members[index] == null){
            System.out.println("‚»‚Ì”Ô†‚Ì‰ïˆõ‚Í‘¶İ‚µ‚Ü‚¹‚ñB");
            return null;
        }
        return members[index];
    }
}
