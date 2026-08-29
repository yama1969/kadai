package b_if;

/******************************************************************************
 * 駅まで何で行きますか
 *****************************************************************************/
public class Kadai1550{
    public static void main(String[] args){
        //駅までの距離とバス路線有無の入力
        int dist = Keyboard.readInt("駅までの距離[km]");
        int bus = Keyboard.readInt("バス路線の有無[0:なし, 0以外:あり]");
        System.out.println();
        
        //利用交通の選択
        if(dist > 3){
            //遠い場合は公共交通機関利用
            if(bus == 0){
                System.out.println("タクシーで行きます。");
            }else{
                System.out.println("バスで行きます。");
            }
        }else{
            //近い場合は徒歩
            System.out.println("徒歩で行きます。");
        }
    }
}
