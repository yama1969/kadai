package carmove.gui;

/** デフォルトパッケージのCarを利用できるようにするためのインターフェース */
public interface Car{
    public void move();
    public void move(int brake_d_push, int accel_d_push, int handle_d_rotate);
    public void setName(String name);
    public double getX();
    public double getY();
    public int getDirection();
    
    public void createCar();  //デフォルトパッケージのCarを作り直すメソッド
}
