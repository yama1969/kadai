package j_method;
public class IntStack2{  //課題5010のためにサイズを大きくしたもの
    private final int SIZE = 450;
    private int[] stack = new int[SIZE];
    private int count = 0;
    
    public int push(int dat){
        if(count >= SIZE){
            return -1;
        }
        stack[count] = dat;
        count++;
        return count;
    }
    
    public int pop(){
        if(count <= 0){
            return Integer.MIN_VALUE;
        }
        count--;
        return stack[count];
    }
    
    public int get(){
        if(count <= 0){
            return Integer.MIN_VALUE;
        }
        return stack[count - 1];
    }
    
    public int getCount(){
        return count;
    }
    
    public void clear(){
        count = 0;
    }
}
