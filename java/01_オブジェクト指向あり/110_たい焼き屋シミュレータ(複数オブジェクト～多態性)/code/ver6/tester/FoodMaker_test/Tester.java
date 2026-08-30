class Tester implements FoodStockable{
    public static void main(String[] args){
        new Tester().test();
    }
    
    void test(){
        FoodMaker fm = new FoodMaker(this,100);
    }
    
    public void putStock(Eatable food){
        System.out.println("put:" + food);
    }
}

interface FoodStockable{void putStock(Eatable food);}
interface Eatable{}
class SorceTaiyaki implements Eatable{SorceTaiyaki(int price){System.out.println("make Sorce.");}}
class TsubuanTaiyaki implements Eatable{TsubuanTaiyaki(int price){System.out.println("make Tsubuan.");}}
class CreamTaiyaki implements Eatable{CreamTaiyaki(int price){System.out.println("make Cream.");}}
