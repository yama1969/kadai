public class Div extends Quiz{
    private int x = 0;
    private int y = 0;
    
    public void next(){
        x = (int)(Math.random() * 9.0) + 1;
        y = (int)(Math.random() * 9.0) + 1;
        x = x * y;
    }
    
    public String getQuestion(){
        return x + " € " + y;
    }
    
    public boolean check(int ans){
        if(ans == x / y){
            return true;
        }
        return false;
    }
}
