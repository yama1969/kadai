public class BrakeTester{
    public static void main(String[] args){
        Brake brake = new Brake();
        Tire t = new Tire();
        brake.movePush(15, t);
        if(brake.getPush() != 15){
            System.out.println("Å°Å°Å°Å°Å°movePush(15, t) Error!");
        }else{
            System.out.println("movePush(15, t) OK!");
        }
        brake.movePush(1, t);
        if(brake.getPush() != 15){
            System.out.println("Å°Å°Å°Å°Å°movePush(1, t) Error!");
        }else{
            System.out.println("movePush(1, t) OK!");
        }
        brake.movePush(-15, t);
        if(brake.getPush() != 0){
            System.out.println("Å°Å°Å°Å°Å°movePush(-15, t) Error!");
        }else{
            System.out.println("movePush(-15, t) OK!");
        }
        brake.movePush(-1, t);
        if(brake.getPush() != 0){
            System.out.println("Å°Å°Å°Å°Å°movePush(-1, t) Error!");
        }else{
            System.out.println("movePush(-1, t) OK!");
        }
        brake.fullPush(t);
        if(brake.getPush() != 15){
            System.out.println("Å°Å°Å°Å°Å°fullPush(t) Error!");
        }else{
            System.out.println("fullPush(t) OK!");
        }
        brake.resetPush(t);
        if(brake.getPush() != 0){
            System.out.println("Å°Å°Å°Å°Å°resetPush(t) Error!");
        }else{
            System.out.println("resetPush(t) OK!");
        }
        brake.renewSpeed(t);
    }
}

class Tire{
    public void changeSpeed(int d_speed){
        System.out.println("Tire.changeSpeed() Called. d_speed = " + d_speed);
    }
}
