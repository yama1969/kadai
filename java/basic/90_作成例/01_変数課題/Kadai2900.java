/******************************************************************************
 * より実用的に ～ 地球上の2地点間の距離を計算する
 *****************************************************************************/
public class Kadai2900{
    public static void main(String[] args){
        //各地点の座標
        double sendai_lati = 38.260264;    //仙台駅北緯
        double sendai_long = 140.882031;   //      東経
        double yamagata_lati = 38.248650;  //山形駅北緯
        double yamagata_long = 140.327484; //      東経
        double sapporo_lati = 43.068637;   //札幌駅北緯
        double sapporo_long = 141.350784;  //      東経
        double niigata_lati = 37.912065;   //新潟駅北緯
        double niigata_long = 139.061658;  //      東経
        
        //各地点の座標の表示
        System.out.println("仙台駅 北緯" + sendai_lati   + "度, 東経" + sendai_long   + "度");
        System.out.println("山形駅 北緯" + yamagata_lati + "度, 東経" + yamagata_long + "度");
        System.out.println("札幌駅 北緯" + sapporo_lati  + "度, 東経" + sapporo_long  + "度");
        System.out.println("新潟駅 北緯" + niigata_lati  + "度, 東経" + niigata_long  + "度");
        System.out.println();
        
        //距離計算に使う定数の定義
        final double RPD = Math.PI / 180.0;//度→radian変換係数(Radians Per Degree)
        final double R = 6370.0;           //地球半径[km]
        
        //仙台駅－山形駅-------------------------------------------------------
        //  計算用変数への代入
        double lati1 = sendai_lati;
        double long1 = sendai_long;
        double lati2 = yamagata_lati;
        double long2 = yamagata_long;
        //  距離計算
        double dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                                  + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                                  + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("仙台駅－山形駅 " + dist + "km");
        
        //仙台駅－札幌駅-------------------------------------------------------
        //  計算用変数への代入
        lati2 = sapporo_lati;
        long2 = sapporo_long;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("仙台駅－札幌駅 " + dist + "km");
        
        //仙台駅－新潟駅-------------------------------------------------------
        //  計算用変数への代入
        lati2 = niigata_lati;
        long2 = niigata_long;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("仙台駅－新潟駅 " + dist + "km");
        
        //山形駅－札幌駅-------------------------------------------------------
        //  計算用変数への代入
        lati1 = yamagata_lati;
        long1 = yamagata_long;
        lati2 = sapporo_lati;
        long2 = sapporo_long;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("山形駅－札幌駅 " + dist + "km");
        
        //山形駅－新潟駅-------------------------------------------------------
        //  計算用変数への代入
        lati2 = niigata_lati;
        long2 = niigata_long;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("山形駅－新潟駅 " + dist + "km");
        
        //札幌駅－新潟駅-------------------------------------------------------
        //  計算用変数への代入
        lati1 = sapporo_lati;
        long1 = sapporo_long;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("札幌駅－新潟駅 " + dist + "km");
        
        
        System.out.println();
        System.out.println("おまけ");
        
        //仙台駅－自由の女神---------------------------------------------------
        //  計算用変数への代入
        lati1 = sendai_lati;
        long1 = sendai_long;
        lati2 = 40.689282;
        long2 = -74.044552;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("仙台駅 － ニューヨーク 自由の女神 " + dist + "km");
        
        //仙台駅－シドニーオペラハウス-----------------------------------------
        //  計算用変数への代入
        lati2 = -33.857097;
        long2 = 151.215107;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("仙台駅 － シドニー オペラハウス   " + dist + "km");
        
        //仙台駅－ロンドン ビックベン------------------------------------------
        //  計算用変数への代入
        lati2 = 51.500767;
        long2 = -0.124653;
        //  距離計算
        dist = R * Math.acos(Math.cos(lati1 * RPD) * Math.cos(long1 * RPD) * Math.cos(lati2 * RPD) * Math.cos(long2 * RPD)
                           + Math.cos(lati1 * RPD) * Math.sin(long1 * RPD) * Math.cos(lati2 * RPD) * Math.sin(long2 * RPD)
                           + Math.sin(lati1 * RPD) * Math.sin(lati2 * RPD));
        //  距離表示
        System.out.println("仙台駅 － ロンドン ビックベン     " + dist + "km");
        
    }
}
