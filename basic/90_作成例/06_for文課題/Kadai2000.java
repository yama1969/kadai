/******************************************************************************
 * sin”gƒOƒ‰ƒt‚ð•\Ž¦
 *****************************************************************************/
public class Kadai2000{
    public static void main(String[] args){
        double cycle = 100.0;
        //”g‚Ì•`‰æ‚ð5‰ñŒJ‚è•Ô‚·
        for(int i = 0; i < 5; i++){
            //”g‚Ð‚Æ‚Â‚Ì•`‰æ(1s‚Ì•`‰æ‚ð100‰ñŒJ‚è•Ô‚·)
            for(double x = 0.0; x < cycle; x += 1.0){
                //1s‚Ì•`‰æ
                //  y‚Ì’l(=*‚Ì”)‚ð‹‚ß‚é
                int y = (int)(40.0 * Math.sin(x * 2.0 * Math.PI / cycle) + 40.0);
                //  *‚ð•\Ž¦
                for(int j = 0; j < y; j++){
                    System.out.print('*');
                }
                //  _‚ð•\Ž¦
                for(int j = 0; j < 80 - y; j++){
                    System.out.print('_');
                }
                //  ‰üs
                System.out.println();
            }
        }
    }
}
