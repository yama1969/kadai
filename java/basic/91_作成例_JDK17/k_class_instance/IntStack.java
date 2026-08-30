package k_class_instance;
public class IntStack{
    private int[] stack;
    private int count = 0;
    
    public IntStack(int size){
        if(size < 0){
            size = 0;
        }
        stack = new int[size];
    }
    
    public int push(int dat){
        if(count >= stack.length){
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
