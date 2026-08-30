import java.util.Calendar;

public class MemberS{
    private int      no;                //会員No.
    private String   name;              //会員氏名
    private String   tel;               //会員電話番号
    private ObjectListG<String> titles; //貸出中タイトル
    private Calendar backDay;           //返却期日
    private int kind;                   //会員種別
    
    private static int count;   //今までのインスタンス生成数
    
    /**
     * コンストラクタ
     */
    public MemberS(int kind){
        titles = new ObjectListG<String>();
        count++;
        this.no = count;
        this.kind = kind;
    }
    
    /**
     * 会員No.を得る。
     */
    public int getNo(){
        return no;
    }
    
    /**
     * 氏名をセットする。
     */
    public String setName(String name){
        String old = this.name;
        this.name = name;
        return old;
    }
    
    /**
     * 氏名を得る
     */
    public String getName(){
        return name;
    }
    
    /**
     * 電話番号をセットする。
     */
    public String setTel(String tel){
        String old = this.tel;
        this.tel = tel;
        return old;
    }
    
    /**
     * 電話番号を得る。
     */
    public String getTel(){
        return tel;
    }
    
    /**
     * 貸出処理をする
     */
    public boolean rent(){
        if(kind == 0){
            if(titles.get(1) != null){
                System.out.println("既に貸出済みなので、さらなる貸出はできません。");
                return false;
            }
            
            String title = Keyboard.readString("タイトル");
            titles.add(0, title);
            Calendar cur = Calendar.getInstance();
            backDay = Calendar.getInstance();
            backDay.set(cur.get(Calendar.YEAR),
                        cur.get(Calendar.MONTH),
                        cur.get(Calendar.DATE) + 8,
                        0, 0, 0);
        }else{
            int i = 1;
            String title = null;
            while((title = Keyboard.readString(i + "個めタイトル")) != null){
                titles.add(titles.size(), title);
                i++;
            }
        }
        return true;
    }
    
    /**
     * 返却処理をする
     */
    public boolean back(){
        if(kind == 0){
            String title = titles.get(1);
            if(title == null){
                System.out.println("貸出中のタイトルはありません。");
                return false;
            }
            
            System.out.println("タイトル：" + title);
            titles.remove(1);
            Calendar cur = Calendar.getInstance();
            if(cur.after(backDay)){
                System.out.println("期日過ぎの返却です。");
            }
            backDay = null;
        }else{
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
        }
        return true;
    }
    
    /**
     * 会員情報の文字列表現を得る
     */
    public String toString(){
        String str = no + " , " + name;
        switch(kind){
        case 0:
            str += " , 通常 ";
            break;
        case 1:
            str += " , 特別 ";
            break;
        }
        Object[] list = titles.toArray();
        for(int i = 0; i < list.length; i++){
            str += "," + (String)list[i];
        }
        if(backDay != null){
            str += " , " + backDay.get(Calendar.YEAR);
            str += "/" + (backDay.get(Calendar.MONTH) + 1);
            str += "/" + backDay.get(Calendar.DATE);
            str += " 00:00";
        }
        return str;
    }
    
    /**
     * 新規会員を生成する
     */
    public static MemberS newMember(){
        String name = Keyboard.readString("氏名");
        String tel = Keyboard.readString("電話番号");
        int kind = Keyboard.readInt("種別[通常:0, 特別:1]");
        while(kind < 0 || kind > 1){
            kind = Keyboard.readInt("種別[通常:0, 特別:1]");
        }
        MemberS m = new MemberS(kind);
        m.setName(name);
        m.setTel(tel);
        return m;
    }
    
    /**
     * 今までに生成したインスタンス数を得る
     */
    public static int getCount(){
        return count;
    }
    
    /**
     * 会員種別を得る
     */
    public int getKind(){
        return kind;
    }
}
