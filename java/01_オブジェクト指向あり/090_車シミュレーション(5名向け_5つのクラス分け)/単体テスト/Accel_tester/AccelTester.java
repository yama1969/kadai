public class AccelTester{
    public static void main(String[] args){
        Accel accel = new Accel();
        Tire t = new Tire(accel);
        
        accel.movePush(15, t);
        if(accel.getPush() != 15){
            System.out.println("Å°Å°Å°Å°Å°movePush(15, t) Error!");
        }else{
            System.out.println("movePush(15, t) OK!");
        }
        accel.movePush(1, t);
        if(accel.getPush() != 15){
            System.out.println("Å°Å°Å°Å°Å°movePush(1, t) Error!");
        }else{
            System.out.println("movePush(1, t) OK!");
        }
        accel.movePush(-15, t);
        if(accel.getPush() != 0){
            System.out.println("Å°Å°Å°Å°Å°movePush(-15, t) Error!");
        }else{
            System.out.println("movePush(-15, t) OK!");
        }
        accel.movePush(-1, t);
        if(accel.getPush() != 0){
            System.out.println("Å°Å°Å°Å°Å°movePush(-1, t) Error!");
        }else{
            System.out.println("movePush(-1, t) OK!");
        }
        accel.fullPush(t);
        if(accel.getPush() != 15){
            System.out.println("Å°Å°Å°Å°Å°fullPush(t) Error!");
        }else{
            System.out.println("fullPush(t) OK!");
        }
        accel.resetPush(t);
        if(accel.getPush() != 0){
            System.out.println("Å°Å°Å°Å°Å°resetPush(t) Error!");
        }else{
            System.out.println("resetPush(t) OK!");
        }
        accel.renewSpeed(t);
    }
}

class Tire{
    Accel accel;
    
    public Tire(Accel accel){
        this.accel = accel;
    }
    
    public void changeSpeed(int d_speed){
        System.out.println("Tire.changeSpeed() Called. d_speed = " + d_speed);
        if(accel != null){
            int push = accel.getPush();
            if(d_speed != 12 * push / 18){
                System.out.println("Å°Å°Å°Å°Å°Speed Calculation Error!");
            }
        }
    }
    
    public int getSpeed(){
        System.out.println("Tire.getSpeed() Called.");
        return 0;
    }
}
