public class Select{
    public static Quiz exec(){
        Quiz q = null;
        
        int kind = Keyboard.readInt("1:‘«‚µŽZ 2:ˆø‚«ŽZ 3:Š|‚¯ŽZ 4:Š„‚èŽZ");
        System.out.println();
        
        switch(kind){
        case 2:
            q = new Sub();
            break;
        case 3:
            q = new Mul();
            break;
        case 4:
            q = new Div();
            break;
        case 1:
        default:
            q = new Add();
            break;
        }
        return q;
    }
}
