package l_extends;
public class SPMember extends MemberEx{
    private ObjectListG<String> titles; //貸出中タイトル
    
    /**
     * コンストラクタ
     */
    public SPMember(){
        titles = new ObjectListG<String>();
    }
    
    /**
     * 貸出処理をする
     */
    @Override
    public boolean rent(){
        int i = 1;
        String title = null;
        while((title = Keyboard.readString(i + "個めタイトル")) != null){
            titles.add(titles.size(), title);
            i++;
        }
        return true;
    }
    
    /**
     * 返却処理をする
     */
    @Override
    public boolean back(){
        Object[] list = titles.toArray();
        if(list.length == 0){
            System.out.println("貸出中のタイトルはありません。");
            return false;
        }
        
        String title = Keyboard.readString("返却タイトル");
        int i = 0;
        while(i < list.length && !((String)list[i]).equals(title)){
            i++;
        }
        if(i >= list.length){
            System.out.println("そのタイトルは貸出中ではありません。");
            return false;
        }
        titles.remove(i + 1);
        return true;
    }
    
    /**
     * 会員情報の文字列表現を得る
     */
    @Override
    public String toString(){
        String str = getNo() + " , " + getName() + " , 特別 ";
        Object[] list = titles.toArray();
        for(int i = 0; i < list.length; i++){
            str += "," + (String)list[i];
        }
        return str;
    }
    
    /**
     * 新規会員を生成する
     */
    public static SPMember newMember(){
        String name = Keyboard.readString("氏名");
        String tel = Keyboard.readString("電話番号");
        SPMember m = new SPMember();
        m.setName(name);
        m.setTel(tel);
        return m;
    }
}
