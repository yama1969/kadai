package beans;

import java.time.LocalDateTime;

public class Order{
    private int           no;
    private LocalDateTime datetime;
    private Item          item;
    private int           quantity;
    private String        sei;
    private String        mei;
    private String        pref;
    private String        add;
    private String        tel;
    private String        mail;

    public void setNo(int no){
        this.no = no;
    }
    
    public void setDatetime(LocalDateTime datetime){
        this.datetime = datetime;
    }
    
    public void setItem(Item item){
        this.item = item;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setSei(String sei){
        this.sei = sei;
    }
    
    public void setMei(String mei){
        this.mei = mei;
    }
    
    public void setPref(String pref){
        this.pref = pref;
    }
    
    public void setAdd(String add){
        this.add = add;
    }
    
    public void setTel(String tel){
        this.tel = tel;
    }
    
    public void setMail(String mail){
        this.mail = mail;
    }
    
    public int getNo(){
        return no;
    }
    
    public LocalDateTime getDatetime(){
        return datetime;
    }
    
    public Item getItem(){
        return item;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public String getSei(){
        return sei;
    }
    
    public String getMei(){
        return mei;
    }
    
    public String getPref(){
        return pref;
    }
    
    public String getAdd(){
        return add;
    }
    
    public String getTel(){
        return tel;
    }
    
    public String getMail(){
        return mail;
    }
}
