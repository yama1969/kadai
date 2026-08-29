public class Kadai2500{
    public static void main(String[] args){
        //1•¶Žš‚Ì“ü—Í(•¶ŽšƒR[ƒh‚Åˆ—‚·‚é‚½‚ßAintŒ^‚Ö“ü—Í)
        int c = (int)Keyboard.readChar("1•¶Žš");
        
        //•¶ŽšƒR[ƒh‚Ì”ÍˆÍ‚©‚çA•¶ŽšŽí‚ð”»’è
        if(c >= 48 && c <= 57){
            System.out.println("”¼Šp”’l");
        }else if(c >= 65 && c <= 90){
            System.out.println("”¼Šp‰p‘å•¶Žš");
        }else if(c >= 97 && c <= 122){
            System.out.println("”¼Šp‰p¬•¶Žš");
        }else if(c >= 33 && c <= 96){
            System.out.println("”¼Šp‹L†");
        }else if(c <= 127){
            System.out.println("§Œä•¶Žš");
        }else{
            System.out.println("‘SŠp•¶Žš");
        }
    }
}
