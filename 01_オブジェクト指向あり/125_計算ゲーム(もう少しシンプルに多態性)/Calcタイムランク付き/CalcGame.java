public class CalcGame{
    public void exec(){
        Add add = new Add();
        Sub sub = new Sub();
        
        int cmd = 0;
        System.out.println("1:足し算");
        System.out.println("2:引き算");
        System.out.println("0:終了");
        cmd = Keyboard.readInt("選んでください");
        
        while(cmd != 0){
            Calc calc = null;
            switch(cmd){
            case 1:
                calc = add;
                break;
            case 2:
                calc = sub;
                break;
            default:
                System.out.println("もう一度入れ直してね。");
            }
            if(calc != null){
                boolean result = true;
                int i = 0;
                long st = System.currentTimeMillis();
                for(i = 0; i < 5 && result; i++){
                    calc.next();
                    System.out.print((i + 1) + "問目 : ");
                    int ans = Keyboard.readInt(calc.getQuestion());
                    result = calc.check(ans);
                }
                long et = System.currentTimeMillis();
                System.out.println();
                if(result){
                    String name = Keyboard.readString("名前");
                    int r = calc.insert((int)(et - st), name);
                    if(r > 0){
                        System.out.println(r + "位です！");
                    }else{
                        System.out.println("ランク外です。。");
                    }
                }else{
                    System.out.println("間違えたので失格！");
                }
                System.out.println();
                calc.showRank();
                System.out.println();
            }
            System.out.println("1:足し算");
            System.out.println("2:引き算");
            System.out.println("0:終了");
            cmd = Keyboard.readInt("選んでください");
        }
    }
    
    public static void main(String[] args){
        new CalcGame().exec();
    }
}
