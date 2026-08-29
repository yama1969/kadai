public abstract class Calc{
    private TimeRank rank = new TimeRank();
    
    public int insert(int time, String name){
        return rank.insert(time, name);
    }
    
    public void showRank(){
        rank.show();
    }
    
    public abstract void next();
    public abstract String getQuestion();
    public abstract boolean check(int ans);
}
