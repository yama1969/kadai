import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Subway{
    //地下鉄(すごろくボード)の情報
    private static ArrayList<String> stations = new ArrayList<String>();

    //プレイヤーの情報
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<Integer> positions = new ArrayList<Integer>();
    private static ArrayList<Integer> directions = new ArrayList<Integer>();
    private static ArrayList<Integer> goal = new ArrayList<Integer>();

    //その他の情報
    private static Scanner in = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception{
        //初期化処理
        init();

        //ゲーム本処理
        int player = 0;       //プレイヤー番号
        boolean all = false;  //全員ゴールしたらtrue
        do{
            if(positions.get(player) != -1){
                showBoard();                          //状況表示
                int eye = dice(player);               //サイコロをふる
                run(player, eye);                     //駒を進める
                all = judge(player);                  //ゴール判定
            }
            player = (player + 1) % names.size();     //次のプレイヤー
        }while(!all);
        
        //ゲーム終了
        System.out.println("===================================================");
        System.out.println("～～結果発表～～");
        for(int i = 0; i < goal.size(); i++){
            System.out.println((i + 1) + "位：" + names.get(goal.get(i)) + "さん");
        }
    }

    //-------------------------------------------------------------------------
    // 初期化処理
    // 引数 : なし
    // 戻値 : なし
    //-------------------------------------------------------------------------
    public static void init(){
        stations.add("泉中央　　");
        stations.add("八乙女　　");
        stations.add("黒松　　　");
        stations.add("旭ヶ丘　　");
        stations.add("台原　　　");
        stations.add("北仙台　　");
        stations.add("北四番丁　");
        stations.add("勾当台公園");
        stations.add("広瀬通　　");
        stations.add("仙台　　　");
        stations.add("五橋　　　");
        stations.add("愛宕橋　　");
        stations.add("河原町　　");
        stations.add("長町一丁目");
        stations.add("長町　　　");
        stations.add("長町南　　");
        stations.add("富沢　　　");
        
        System.out.println("～～ 仙台市営地下鉄南北線すごろく ～～");
        System.out.println();
        System.out.print("プレイヤー名を入力(e:完了) => ");
        String name = in.nextLine();
        while(!name.equals("e")){
            names.add(name);
            positions.add(0);
            directions.add(1);
            System.out.print("プレイヤー名を入力(e:完了) => ");
            name = in.nextLine();
        }
    }

    //-------------------------------------------------------------------------
    // すごろく状況表示
    // 引数 : なし
    // 戻値 : なし
    //-------------------------------------------------------------------------
    public static void showBoard(){
        System.out.println("===================================================");
        for(int i = 0; i < stations.size(); i++){
            System.out.print(stations.get(i));
            for(int j = 0; j < positions.size(); j++){
                if(positions.get(j) == i){
                    System.out.print(" " + names.get(j));
                    if(directions.get(j) > 0){
                        System.out.print("↓");
                    }else{
                        System.out.print("↑");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("あがり ");
        for(int i = 0; i < goal.size(); i++){
            int p = goal.get(i);
            System.out.print((i + 1) + "位:" + names.get(p) + " ");
        }
        System.out.println();
        System.out.println("===================================================");
    }
    
    //-------------------------------------------------------------------------
    // サイコロをふる
    // 引数 : 現在のプレイヤー番号
    // 戻値 : サイコロの目
    //-------------------------------------------------------------------------
    public static int dice(int player){
        System.out.println(names.get(player) + "さんの番です。");
        System.out.print("(Enterキーでサイコロをふる)");
        in.nextLine();
        
        int eye = rand.nextInt(6) + 1;
        System.out.println("[" + eye + "]");
        
        return eye;
    }
    
    //-------------------------------------------------------------------------
    // 駒を進める
    // 引数 : player 現在のプレイヤー番号, eye 出たサイコロの目
    // 戻値 : 進んだ後のプレイヤーの位置
    //-------------------------------------------------------------------------
    public static int run(int player, int eye){
        int pos = positions.get(player);
        int dir = directions.get(player);
        for(int i = 0; i < eye; i++){
            pos = pos + dir;
            if(pos == 0 || pos == stations.size() - 1){
                dir = dir * (-1);
            }
        }
        directions.set(player, dir);
        positions.set(player, pos);
        
        return pos;
    }
    
    //-------------------------------------------------------------------------
    // ゴール判定
    // 引数 : 現在のプレイヤー番号
    // 戻値 : 全員ゴールした場合true
    //-------------------------------------------------------------------------
    public static boolean judge(int player){
        if(positions.get(player) == 9){
            System.out.println(names.get(player) + "さん到着です！");
            positions.set(player, -1);
            goal.add(player);
        }
        
        if(goal.size() == names.size()){
            return true;
        }
        return false;
    }
}