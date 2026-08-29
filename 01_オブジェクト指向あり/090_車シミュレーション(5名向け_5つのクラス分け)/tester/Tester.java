import carmove.gui.GUITest;

/** carmove.guiパッケージのGUITestと、デフォルトパッケージのCarとを橋渡しするクラス */
public class Tester implements carmove.gui.Car{
    private GUITest gt;
    private Car     car;
    
    public Tester(){
        car = new Car("STUDENT_CAR");
        gt = new GUITest(this);
    }
    
    public void move(){
        car.move();
    }
    
    public void move(int brake_d_push, int accel_d_push, int handle_d_rotate){
        car.move(brake_d_push, accel_d_push, handle_d_rotate);
    }
    
    public void setName(String name){
        car.setName(name);
    }
    
    public double getX(){
        return car.getX();
    }
    
    public double getY(){
        return car.getY();
    }
    
    public int getDirection(){
        return car.getDirection();
    }
    
    public void createCar(){
        car = new Car("STUDENT_CAR");
    }
    
    public static void main(String[] args){
        new Tester();
    }
}
