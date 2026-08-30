public class CarTester{
    public static void main(String[] args){
        Car car = new Car("テスト中");
        System.out.println("move() Exection.");
        car.move();
        System.out.println();
        
        car = new Car("テスト中");
        System.out.println("move(brake 10, accel 15, handle 90) Exection");
        car.move(10,15,90);
        double data = car.getX();
        if(data < 1.9641855032959 || data > 1.9641855032960){
            System.out.println("■■■■■X point Error!");
        }else{
            System.out.println("X point OK!");
        }
        data = car.getY();
        if(data < 1.9641855032959 || data > 1.9641855032960){
            System.out.println("■■■■■Y point Error!");
        }else{
            System.out.println("Y point OK!");
        }
        if(car.getDirection() != 45){
            System.out.println("■■■■■Direction Error!");
        }else{
            System.out.println("Direction OK!");
        }
        
        car = new Car();
        car.move(0, 0, 90);	//45度
        car.move();			//90度
        car.move();			//135度
        car.move();			//-180度
        if(car.getDirection() != -180){
            System.out.println("■■■■■PlusOverDirection Error! " + car.getDirection());
        }else{
            System.out.println("PlusDirection OK!");
        }
        
        car = new Car();
        car.move(0, 0, -90);//-45度
        car.move();			//-90度
        car.move();			//-135度
        car.move();			//-180度
        car.move(0, 0, 88);	//179度
        if(car.getDirection() != 179){
            System.out.println("■■■■■MinusOverDirection Error! " + car.getDirection());
        }else{
            System.out.println("MinusDirection OK!");
        }
    }
}

class Tire{
    int speed = 10;
    int direction = 45;
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    public void setDirection(int direction){
        this.direction = direction;
    }
    
    public int getSpeed(){
        System.out.println("Tire.getSpeed() Called!");
        return speed;
    }
    
    public int getDirection(){
        System.out.println("Tire.getDirection() Called!");
        return direction;
    }
}

class Handle{
    private int direction = 0;
    
    public void moveDirection(int d_direction, Tire t){
        System.out.println("Handle.moveDirection() Called! d_direction = " + d_direction);
        direction += d_direction;
        t.setDirection(direction / 2);
    }
}

class Brake{
    public void movePush(int d_push, Tire t){
        System.out.println("Brake.movePush() Called! d_push = " + d_push);
    }
    
    public void renewSpeed(Tire t){
        System.out.println("Brake.renewSpeed() Called!");
    }
    
    public void resetPush(Tire t){
        System.out.println("■■■■■Brake.resetPush() Called! That process is not in the specification!");
    }
    
    public int getPush(){
        System.out.println("■■■■■Brake.getPush() Called! That process is not in the specification!");
        return 0;
    }
}

class Accel{
    public void movePush(int d_push, Tire t){
        System.out.println("Accel.movePush() Called! d_push = " + d_push);
    }
    
    public void renewSpeed(Tire t){
        System.out.println("Accel.renewSpeed() Called!");
    }
    
    public void resetPush(Tire t){
        System.out.println("■■■■■Accel.renewSpeed() Called! That process is not in the specification!");
    }
    
    public int getPush(){
        System.out.println("■■■■■Accel.getPush() Called! That process is not in the specification!");
        return 0;
    }
}
