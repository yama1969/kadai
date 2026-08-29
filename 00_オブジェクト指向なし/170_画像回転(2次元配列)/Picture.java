import java.io.*;

class Picture{
    //画像データ定義
    static char[][] picdat;

    static{
    	picdat = new char[][]{
    		"□■■■□".toCharArray(),
    		"□■□□□".toCharArray(),
    		"□■■■□".toCharArray(),
    		"□■□□□".toCharArray(),
    		"□■□□□".toCharArray()
    	};
    }
/*
    static{
        picdat = new char[][]{
            "□□□□■□□□□□.toCharArray(),
            "□□□□■■□□□□.toCharArray(),
            "□□□□■■■□□□.toCharArray(),
            "□□□□■■■□□□.toCharArray(),
            "□□□□■■■□□□.toCharArray(),
            "■■■■■■■■■□.toCharArray(),
            "□■■■■■■■■□.toCharArray(),
            "□□■■■■■■□□.toCharArray(),
            "□□□■■■■■□□.toCharArray(),
            "□□□□□□□□□□.toCharArray()
        };
    }
*/
    
    //メニュー表示とコマンド受付
    public static void main(String[] args) throws IOException{
        //入力されたコマンド値
        int cmd_no = 0;
        //入力オブジェクト
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        do{
            //画像表示
            dispPic();
            
            //メニュー表示
            System.out.println("0:終了");
            System.out.println("1.上下反転");
            System.out.println("2.左右反転");
            System.out.println("3.右上－左下軸反転");
            System.out.println("4.右下－左上軸反転");
            System.out.println("5.右90°回転");
            System.out.println("6.左90°回転");
            System.out.println("7.180°回転");
            System.out.println("8.2倍拡大");
            System.out.println("9.1/2縮小");
            System.out.print("上記の数値を入力：");
            
            //コマンド入力
            cmd_no = Integer.parseInt(br.readLine());
            
            //コマンド処理
            switch(cmd_no){
                case 1:            //上下反転
                    turnPicUD();
                    break;
                case 2:            //左右反転
                    turnPicLR();
                    break;
                case 3:            //右上－左下軸反転
                    turnPicLU_RD();
                    break;
                case 4:
                    turnPicLD_RU();
                    break;
                case 5:
                    turnPicLD_RU();
                    turnPicLR();
                    break;
                case 6:
                    turnPicLU_RD();
                    turnPicLR();
                    break;
                case 7:
                    turnPicLR();
                    turnPicUD();
                    break;
                case 8:
                    magnify();
                    break;
                case 9:
                    reduce();
                    break;
            }
            System.out.println();
        }while(cmd_no != 0);
    }
    
    static void dispPic(){
        for(int y = 0; y < picdat.length; y++){
            System.out.println(picdat[y]);
        }
    }
    
    static void turnPicUD(){
        for(int y = 0; y < picdat.length / 2; y++){
            for(int x = 0; x < picdat[y].length; x++){
                char work = picdat[y][x];
                picdat[y][x] = picdat[picdat.length - y - 1][x];
                picdat[picdat.length - y - 1][x] = work;
            }
        }
    }
    
    static void turnPicLR(){
        for(int y = 0; y < picdat.length; y++){
            for(int x = 0; x < picdat[y].length / 2; x++){
                char work = picdat[y][x];
                picdat[y][x] = picdat[y][picdat[y].length - x - 1];
                picdat[y][picdat[y].length - x - 1] = work;
            }
        }
    }
    
    static void turnPicLU_RD(){
        for(int y = 0; y < picdat.length; y++){
            for(int x = 0; x < picdat[y].length - y; x++){
                char work = picdat[y][x];
                picdat[y][x] = picdat[picdat[y].length - x - 1][picdat.length - y - 1];
                picdat[picdat[y].length - x - 1][picdat.length - y - 1] = work;
            }
        }
    }
    
    static void turnPicLD_RU(){
        for(int y = 0; y < picdat.length; y++){
            for(int x = y + 1; x < picdat[y].length; x++){
                char work = picdat[y][x];
                picdat[y][x] = picdat[x][y];
                picdat[x][y] = work;
            }
        }
    }
    
    static void magnify(){
    	char[][] temp = new char[picdat.length * 2][picdat.length * 2];
    	for(int y = 0; y < picdat.length; y++){
    		for(int x = 0; x < picdat[y].length; x++){
    			temp[y * 2    ][x * 2    ] = picdat[y][x];
    			temp[y * 2 + 1][x * 2    ] = picdat[y][x];
    			temp[y * 2    ][x * 2 + 1] = picdat[y][x];
    			temp[y * 2 + 1][x * 2 + 1] = picdat[y][x];
    		}
    	}
    	picdat = temp;
    }
    
    static void reduce(){
    	char[][] temp = new char[picdat.length / 2][picdat.length / 2];
    	for(int y = 0; y < picdat.length - 1; y = y + 2){
    		for(int x = 0; x < picdat[y].length - 1; x = x + 2){
    			temp[y / 2][x / 2] = picdat[y][x];
    		}
    	}
    	picdat = temp;
    }
}
