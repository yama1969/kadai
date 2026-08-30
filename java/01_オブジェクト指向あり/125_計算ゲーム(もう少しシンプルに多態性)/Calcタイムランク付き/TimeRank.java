/**
 * タイムランキングを保持するクラス
 */
public class TimeRank{
    public static final int MAX_REC = 10;  //記録最大保持数
    private int[] time = null;             //時間記録
    private String[] name = null;          //名前記録
    
    public TimeRank(){
        time = new int[MAX_REC + 1];
        for(int i = 0; i < time.length; i++){
            time[i] = Integer.MAX_VALUE;
        }
        name = new String[MAX_REC + 1];
    }
    
    public int insert(int time, String name){
        int i = this.time.length - 2;
        while(i > -1 && this.time[i] > time){
            this.time[i + 1] = this.time[i];
            this.name[i + 1] = this.name[i];
            i--;
        }
        this.time[i + 1] = time;
        this.name[i + 1] = name;
        
        if(i > this.time.length - 3){
            return -1;
        }
        return i + 2;
    }
    
    public void show(){
        for(int i = 0; i < time.length - 1 && time[i] != Integer.MAX_VALUE; i++){
            System.out.println((i + 1) + "位 " + name[i] + " (" + time[i] + "ms)");
        }
    }
}
