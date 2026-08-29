import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Zoo{
    public static void main(String[] args){
        Animal[] animal = new Animal[3];
        animal[0] = new Dog();
        animal[1] = new Cat();
        animal[2] = new Cow();
        
        //一覧表示
        for(int i = 0; i < animal.length; i++){
            System.out.println((i + 1) + ":" + animal[i].getAnimalName());
        }
        System.out.print("どれかを選んで下さい。＞ ");
        //選択入力
        int num = input(1, animal.length);
        //選択したものを動かす
        if(num > 0){
            animal[num - 1].eat();
        }
    }
    
    static int input(int min, int max){
        final int ERROR = -1;            //エラー時の戻り値
        
        if(min < 0 || min > max){
            return ERROR;
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        try{
            num = Integer.parseInt(reader.readLine());
        }catch(IOException e){
            System.out.println("キーボードエラーです。");
            System.out.println(e);
            return ERROR;
        }catch(NumberFormatException e){
            System.out.println("入力が数値でありません。");
            System.out.println(e);
            return ERROR;
        }
        if(num < min || num > max){
            System.out.println("数値が範囲外です。");
            return ERROR;
        }
        return num;
    }
}
