package b_if;

/******************************************************************************
 * 俳句の韻律入れ替え遊び
 *****************************************************************************/
public class Kadai1500{
    public static void main(String[] args){
        String haiku = null;                    //組上げた俳句の文字列
        
        //0～1の乱数を生成し、その値により韻律を選択(第1韻律)
        int sele = (int)(Math.random() * 2.0);
        if(sele == 0){
            haiku = "しずけさや ";
        }else{
            haiku = "ふるいけや ";
        }
        
        //0～1の乱数を生成し、その値により韻律を選択(第2韻律)
        sele = (int)(Math.random() * 2.0);
        if(sele == 0){
            haiku += "いわにしみいる ";
        }else{
            haiku += "かわずとびこむ ";
        }
        
        //0～1の乱数を生成し、その値により韻律を選択(第3韻律)
        sele = (int)(Math.random() * 2.0);
        if(sele == 0){
            haiku += "せみのこえ";
        }else{
            haiku += "みずのおと";
        }
        
        //組み上がった俳句を表示
        System.out.println(haiku);
    }
}
