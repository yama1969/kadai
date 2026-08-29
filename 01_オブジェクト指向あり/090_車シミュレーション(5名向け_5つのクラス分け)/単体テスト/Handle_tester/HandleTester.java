public class HandleTester{
    public static void main(String[] args){
        Handle handle = new Handle();
        Tire t = new Tire(handle);
        
        handle.moveDirection(90, t);
        if(handle.getDirection() != 90){
            System.out.println("Å°Å°Å°Å°Å°moveDirection(90, t) Error!");
        }else{
            System.out.println("moveDirection(90, t) OK!");
        }
        handle.moveDirection(1, t);
        if(handle.getDirection() != 90){
            System.out.println("Å°Å°Å°Å°Å°moveDirection(1, t) Error!");
        }else{
            System.out.println("moveDirection(1, t) OK!");
        }
        handle.moveDirection(-180, t);
        if(handle.getDirection() != -90){
            System.out.println("Å°Å°Å°Å°Å°moveDirection(-180, t) Error!");
        }else{
            System.out.println("moveDirection(-180, t) OK!");
        }
        handle.moveDirection(-1, t);
        if(handle.getDirection() != -90){
            System.out.println("Å°Å°Å°Å°Å°moveDirection(-1, t) Error!");
        }else{
            System.out.println("moveDirection(-1, t) OK!");
        }
        handle.resetDirection(t);
        if(handle.getDirection() != 0){
            System.out.println("Å°Å°Å°Å°Å°resetDirection(t) Error!");
        }else{
            System.out.println("resetDirection(t) OK!");
        }
    }
}

class Tire{
    Handle handle;
    
    public Tire(Handle handle){
        this.handle = handle;
    }
    
    public void setDirection(int direction){
        if( direction != handle.getDirection() / 2 ){
            System.out.println("Å°Å°Å°Å°Å°Tire.setDirection() Called. Calculation Error!");
        }else{
            System.out.println("Tire.setDirection() Called.");
        }
    }
}
