import java.util.Calendar;

public class Member{
    private int      no;        //会員No.
    private String   name;      //会員氏名
    private String   tel;       //会員電話番号
    private String   title;     //貸出中タイトル
    private Calendar backDay;   //返却期日
    
    /**
     * コンストラクタ
     */
    public Member(int no){
        this.no = no;
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
        if(title != null){
            System.out.println("既に貸出済みなので、さらなる貸出はできません。");
            return false;
        }
        
        title = Keyboard.readString("タイトル");
        Calendar cur = Calendar.getInstance();
        backDay = Calendar.getInstance();
        backDay.set(cur.get(Calendar.YEAR),
                    cur.get(Calendar.MONTH),
                    cur.get(Calendar.DATE) + 8,
                    0, 0, 0);
        return true;
    }
    
    /**
     * 返却処理をする
     */
    public boolean back(){
        if(title == null){
            System.out.println("貸出中のタイトルはありません。");
            return false;
        }
        
        System.out.println("タイトル：" + title);
        title = null;
        Calendar cur = Calendar.getInstance();
        if(cur.after(backDay)){
            System.out.println("期日過ぎの返却です。");
        }
        return true;
    }
    
    /**
     * 会員情報の文字列表現を得る
     */
    public String toString(){
        String str = no + " , " + name;
        if(title != null){
            str += " , " + title;
            str += " , " + backDay.get(Calendar.YEAR);
            str += "/" + (backDay.get(Calendar.MONTH) + 1);
            str += "/" + backDay.get(Calendar.DATE);
            str += " 00:00";
        }
        return str;
    }
}
