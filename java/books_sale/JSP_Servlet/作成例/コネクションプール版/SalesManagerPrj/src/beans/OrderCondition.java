package beans;

import java.time.LocalDateTime;

public class OrderCondition extends Order{
    private LocalDateTime enddate;
    
    public void setEnddate(LocalDateTime enddate){
        this.enddate = enddate;
    }
    
    public LocalDateTime getEnddate(){
        return enddate;
    }
}
