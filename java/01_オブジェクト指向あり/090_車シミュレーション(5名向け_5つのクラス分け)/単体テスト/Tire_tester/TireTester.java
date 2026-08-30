public class TireTester{
    public static void main(String[] args){
        Tire tire = new Tire();
        
        tire.setDirection(-45);
        if(tire.getDirection() != -45){
            System.out.println("Å°Å°Å°Å°Å°setDirection(-45) Error!");
        }else{
            System.out.println("setDirection(-45) OK!");
        }
        tire.setDirection(-46);
        if(tire.getDirection() != -45){
            System.out.println("Å°Å°Å°Å°Å°setDirection(-46) Error!");
        }else{
            System.out.println("setDirection(-46) OK!");
        }
        tire.setDirection(45);
        if(tire.getDirection() != 45){
            System.out.println("Å°Å°Å°Å°Å°setDirection(45) Error!");
        }else{
            System.out.println("setDirection(45) OK!");
        }
        tire.setDirection(46);
        if(tire.getDirection() != 45){
            System.out.println("Å°Å°Å°Å°Å°setDirection(46) Error!");
        }else{
            System.out.println("setDirection(46) OK!");
        }
        tire.changeSpeed(5);
        if(tire.getSpeed() != 5){
            System.out.println("Å°Å°Å°Å°Å°changeSpeed(5) Error!");
        }else{
            System.out.println("changeSpeed(5) OK!");
        }
        tire.changeSpeed(-5);
        if(tire.getSpeed() != 0){
            System.out.println("Å°Å°Å°Å°Å°changeSpeed(-5) Error!");
        }else{
            System.out.println("changeSpeed(-5) OK!");
        }
        tire.changeSpeed(-1);
        if(tire.getSpeed() != 0){
            System.out.println("Å°Å°Å°Å°Å°changeSpeed(-1) Error!");
        }else{
            System.out.println("changeSpeed(-1) OK!");
        }
    }
}
