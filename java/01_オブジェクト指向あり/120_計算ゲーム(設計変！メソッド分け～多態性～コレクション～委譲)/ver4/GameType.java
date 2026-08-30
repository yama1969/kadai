public class GameType implements Mondai, ScoreManager{
    private Mondai mondai;
    private ScoreManager ranking;
    
    public GameType(Mondai mondai, ScoreManager ranking){
        this.mondai = mondai;
        this.ranking = ranking;
    }
    
    public int showQuestion(){
        return mondai.showQuestion();
    }
    
    public long setStartTime(){
        return ranking.setStartTime();
    }
    
    public long setEndTime(){
        return ranking.setEndTime();
    }
    
    public void calcScore(int num_question, int goodAns){
        ranking.calcScore(num_question, goodAns);
    }
    
    public void showRank(){
        ranking.showRank();
    }
    
    public String getName(){
        return ranking.getName();
    }
}
