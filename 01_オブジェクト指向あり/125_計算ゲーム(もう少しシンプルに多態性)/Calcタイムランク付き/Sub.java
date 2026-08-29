public class Sub extends Calc{
    private int x;
    private int y;
    private int ans;
    
    public Sub(){
        next();
    }
    
    @Override
    public void next(){
        x = (int)(Math.random() * 11.0);
        y = (int)(Math.random() * (double)(x + 1));
        ans = x - y;
    }
    
    @Override
    public String getQuestion(){
        return x + " - " + y;
    }
    
    @Override
    public boolean check(int ans){
        if(this.ans == ans){
            return true;
        }
        return false;
    }
}
