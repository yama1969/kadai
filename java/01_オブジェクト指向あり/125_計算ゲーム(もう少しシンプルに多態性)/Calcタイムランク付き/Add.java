public class Add extends Calc{
    private int x;
    private int y;
    private int ans;
    
    public Add(){
        next();
    }
    
    @Override
    public void next(){
        x = (int)(Math.random() * 11.0);
        y = (int)(Math.random() * (double)(10 - x + 1));
        ans = x + y;
    }
    
    @Override
    public String getQuestion(){
        return x + " + " + y;
    }
    
    @Override
    public boolean check(int ans){
        if(this.ans == ans){
            return true;
        }
        return false;
    }
}
