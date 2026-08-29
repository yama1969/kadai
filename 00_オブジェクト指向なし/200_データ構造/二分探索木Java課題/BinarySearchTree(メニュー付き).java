import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BinarySearchTree{
    
    private static final int DATNUM = 15;
    private static int[] dat = null;
    
    public static void main(String[] args){
        makeTree();
        
        char com = 0;
        do{
            System.out.println("探索 : s");
            System.out.println("挿入 : i");
            System.out.println("削除 : d");
            System.out.println("表示 : v");
            System.out.println("木　 : t");
            System.out.println("終了 : e");
            System.out.println();
            com = inputChar("コマンド");
            
            switch(com){
            case 's':
                search(inputInt("探索値"));
                break;
            case 'i':
                insert(inputInt("挿入値"));
                break;
            case 'd':
                delete(inputInt("削除値"));
                break;
            case 'v':
                System.out.println();
                show();
                break;
            case 't':
                System.out.println();
                showTree();
                break;
            case 'e':
                break;
            default:
                com = 0;
            }
            System.out.println();
        }while(com != 'e');
    }
    
    //---キーボード入力--------------------------------------------------------
    private static char inputChar(String msg){
        char c = 0;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg + " => ");
            String line = reader.readLine();
            if(line != null){
                if(line.length() > 0){
                    c = line.charAt(0);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return c;
    }
    
    private static int inputInt(String msg){
        int num = 0;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg + " => ");
            String line = reader.readLine();
            if(line != null){
                num = Integer.parseInt(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
        }
        return num;
    }
    
    //---二分探索木作成--------------------------------------------------------
    public static void makeTree(){
        dat = new int[DATNUM];
        int cnt = (int)(Math.random() * DATNUM);
        for(int i = 0; i < cnt; i++){
            int num = (int)(Math.random() * 90) + 10;
            insert(num);
        }
    }
    
    //---探索------------------------------------------------------------------
    public static int search(int num){
        int i = 0;
        while(i < dat.length && dat[i] != num && dat[i] !=0){
            if(dat[i] > num){
                i = (i + 1) * 2 - 1;
            }else{
                i = (i + 1) * 2;
            }
        }
        if(i < dat.length && dat[i] != 0){
            System.out.println(num + "は添字" + i + "にあります。");
        }else{
            System.out.println(num + "は見つかりませんでした。");
        }
        return i;
    }
    
    //---挿入------------------------------------------------------------------
    public static void insert(int num){
        int i = 0;
        boolean ins = false;
        while(i < dat.length && dat[i] != num){
            if(dat[i] == 0){
                dat[i] = num;
                ins = true;
            }else{
                if(dat[i] > num){
                    i = (i + 1) * 2 - 1;
                }else{
                    i = (i + 1) * 2;
                }
            }
        }
        if(ins){
            System.out.println("値を添字" + i + "に挿入しました。");
        }else{
            if(i < dat.length){
                System.out.println("既に挿入済みの値です。");
            }else{
                System.out.println("空きがありませんでした。");
            }
        }
    }
    
    //---削除------------------------------------------------------------------
    public static void delete(int num){
        boolean del = false;
        int i = search(num);
        if(i < dat.length && dat[i] != 0){
            dat[i] = 0;
            del = true;
            int l = (i + 1) * 2 - 1;
            int r = (i + 1) * 2;
            while((l < dat.length && dat[l] != 0) || (r < dat.length && dat[r] != 0)){
                int j = 0;
                if(r < dat.length && dat[r] != 0){
                    j = r;
                    l = (j + 1) * 2 - 1;
                    while(l < dat.length && dat[l] != 0){
                        j = l;
                        l = (j + 1) * 2 - 1;
                    }
                }else{
                    j = l;
                    r = (j + 1) * 2;
                    while(r < dat.length && dat[r] != 0){
                        j = r;
                        r = (j + 1) * 2;
                    }
                }
                dat[i] = dat[j];
                i = j;
                dat[i] = 0;
                l = (i + 1) * 2 - 1;
                r = (i + 1) * 2;
            }
        }
        if(del){
            System.out.println("値" + num + "を削除しました。");
        }else{
            System.out.println("削除する値" + num + "が見つかりませんでした。");
        }
    }
    
    //---表示------------------------------------------------------------------
    public static void show(){
        int i = 0;
        int stat = 2;
        while(i != 0 || stat != 0){
            if(i >= dat.length || dat[i] == 0 || stat == 0){
                stat = i % 2;
                i = (i - 1) / 2;
            }else{
                if(stat == 1){
                    System.out.print(dat[i] + " ");
                    i = (i + 1) * 2;
                }else{
                    i = (i + 1) * 2 - 1;
                }
                stat = 2;
            }
        }
        System.out.println();
    }
    
    //---木表示----------------------------------------------------------------
    public static void showTree(){
        int end = 0;
        int next = 2;
        for(int i = 0; i < dat.length; i++){
            if(dat[i] < 10){
                System.out.print(" ");
            }
            System.out.print(dat[i] + " ");
            if(i == end){
                System.out.println();
                end += next;
                next *= 2;
            }
        }
        System.out.println();
    }
}
