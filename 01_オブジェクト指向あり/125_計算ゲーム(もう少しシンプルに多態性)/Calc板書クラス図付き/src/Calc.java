public class Calc{
    public static void main(String[] args){
        Quiz q = Select.exec();
        
        int score = 0;
        for(int i = 1; i <= 10; i++){
            q.next();
            int ans = Keyboard.readInt(i + " 問 : " + q.getQuestion());
            if(q.check(ans)){
                System.out.println("正解！");
                score++;
            }else{
                System.out.println("残念・・・");
            }
            System.out.println();
        }
        System.out.println(score + "問正解でした。");
    }
}
