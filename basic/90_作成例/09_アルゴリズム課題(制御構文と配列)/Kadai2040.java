/******************************************************************************
 * 各ソート法の比較回数の比較
 *****************************************************************************/
public class Kadai2040{
    public static void main(String[] args){
        new Buble().measurement();    //バブルソート
        new Ex_buble().measurement(); //改良バブルソート
        new Select().measurement();   //選択ソート
        new Insert().measurement();   //挿入ソート
        new Shell().measurement();    //シェルソート
        new Quick().measurement();    //クイックソート
    }
    
    /** ソート時間計測 *******************************************************/
    private static abstract class Sort{
        private static final int NUM = 50000;           //データ数
        private static final int[] DAT = new int[NUM];  //並べ替え対象配列
        
        private String name = null;                     //ソート法の名称
        
        /** static初期化 *****************************************************/
        static{
            //配列データ格納
            int dat_num = - NUM / 2;
            for(int i = 0; i < NUM; i++){
                DAT[i] = dat_num;
                dat_num++;
            }
            //配列シャッフル
            for(int i = 0; i < NUM; i++){
                int j = (int)(Math.random() * (double)NUM);
                int w = DAT[j];
                DAT[j] = DAT[i];
                DAT[i] = w;
            }
            
            System.out.println("データ数 " + NUM);
        }
        
        /** コンストラクタ ***************************************************/
        protected Sort(String name){
            if(name == null){
                name = "名無し";
            }
            this.name = name;
        }
        
        /** 計測実行 *********************************************************/
        public void measurement(){
            // 並べ替え用配列準備
            int[] sort_dat = new int[NUM];
            for(int i = 0; i < NUM; i++){
                sort_dat[i] = DAT[i];
            }
            
            //ソート実行
            System.out.print(name + "\t\t");
            long start = System.currentTimeMillis();
            long cnt = sort(sort_dat);
            long end = System.currentTimeMillis();
            System.out.print(cnt + "回\t");
            System.out.print((end - start) + "ms\t");
            
            // 並べ替え結果チェック
            for(int i = 0; i < sort_dat.length - 1; i++){
                if(sort_dat[i] <= sort_dat[i + 1]){
                    System.out.println("NG");
                }
            }
            System.out.println("OK");
        }
        
        /** ソート方法の実装 *************************************************/
        public abstract long sort(int dat[]);
    }
    
    /** バブルソート *********************************************************/
    private static class Buble extends Sort{
        public Buble(){
            super("バブルソート");
        }
        
        @Override
        public long sort(int[] dat){
            long n = 0L;  //比較回数
            
            //比較終了位置のループ
            for(int end = 0; end < dat.length - 1; end++){
                //比較位置ループ
                for(int comp = dat.length - 1; comp > end; comp--){
                    if(dat[comp] > dat[comp - 1]){
                        int w = dat[comp];
                        dat[comp] = dat[comp - 1];
                        dat[comp - 1] = w;
                    }
                    n++;
                }
            }
            return n;
        }
    }
    
    /** 改良バブルソート *****************************************************/
    private static class Ex_buble extends Sort{
        public Ex_buble(){
            super("改良バブル");
        }
        
        @Override
        public long sort(int[] dat){
            //配列並べ替え(改良バブルソート)
            long n = 0L;                        //比較回数カウント
            
            //比較終了位置ループ
            for(int end = 0; end < dat.length - 1; end++){
                int last = dat.length - 1;      //交換位置記録用
                //比較位置ループ
                for(int comp = dat.length - 1; comp > end; comp--){
                    if(dat[comp] > dat[comp - 1]){
                        int w = dat[comp];
                        dat[comp] = dat[comp - 1];
                        dat[comp - 1] = w;
                        last = comp;
                    }
                    n++;                        //比較回数加算
                }
                end = last - 1;
            }
            return n;
        }
    }
    
    /** 選択ソート ***********************************************************/
    private static class Select extends Sort{
        public Select(){
            super("選択ソート");
        }
        
        @Override
        public long sort(int[] dat){
            long n = 0L;                         //比較回数カウント
            
            //配列並べ替え(選択ソート)
            for(int l = 0; l < dat.length - 1; l++){
                int max_index = l;
                for(int r = l + 1; r < dat.length; r++){
                    if(dat[max_index] < dat[r]){
                        max_index = r;
                    }
                    n++;
                }
                int w = dat[l];
                dat[l] = dat[max_index];
                dat[max_index] = w;
            }
            return n;
        }
    }
    
    /** 挿入ソート ***********************************************************/
    private static class Insert extends Sort{
        public Insert(){
            super("挿入ソート");
        }
        
        @Override
        public long sort(int[] dat){
            long n = 0L;                        //比較回数カウント
            
            //挿入ソート(添字ins_pos-1までがソート済み、添字ins_posが挿入値)
            for(int ins_pos = 1; ins_pos < dat.length; ins_pos++){
                int ins_dat = dat[ins_pos];    //挿入値
                int comp_pos = ins_pos - 1;    //比較添字初期値
                while(comp_pos > -1 && dat[comp_pos] < ins_dat){
                    dat[comp_pos + 1] = dat[comp_pos];
                    comp_pos--;
                    n++;
                }
                dat[comp_pos + 1] = ins_dat;
            }
            return n;
        }
    }
    
    /** シェルソート *********************************************************/
    private static class Shell extends Sort{
        public Shell(){
            super("シェルソート");
        }
        
        @Override
        public long sort(int[] dat){
            long n = 0L;                        //比較回数カウント
            
            //飛び幅を変えるループ
            for(int width = dat.length / 2; width > 0; width /= 2){
                //挿入ソートの先頭位置を変えるループ
                for(int top = 0; top < width; top++){
                    //挿入ソートのループ(挿入データ位置を変える)
                    for(int inspos = top + width; inspos < dat.length; inspos += width){
                        int insdat = dat[inspos];
                        int comp = inspos;
                        //値比較ループ(比較位置を変える)
                        while(comp > top && dat[comp - width] < insdat){
                            dat[comp] = dat[comp - width];
                            comp -= width;
                            n++;
                        }
                        if(comp <= top){
                            comp = top;
                        }
                        dat[comp] = insdat;
                    }
                }
            }
            return n;
        }
    }
    
    /** クイックソート *******************************************************/
    private static class Quick extends Sort{
        public Quick(){
            super("クイックソート");
        }
        
        @Override
        public long sort(int[] dat){
            return quick(dat, 0, dat.length - 1);
        }
        
        private long quick(int[] dat, int start, int end){
            
            if(start >= end){
                return 0L;
            }
            
            long n = 0L;
            
            int pivot = dat[start];
            int left = start;
            int right = end;
            
            for(; dat[left] > pivot; left++){
            }
            for(; right > start && dat[right] <= pivot; right--){
            }
            
            if(right == left){
                left++;
            }
            
            while(left < right){
                int w = dat[left];
                dat[left] = dat[right];
                dat[right] = w;
                n++;
                
                for(left++; dat[left] > pivot; left++){
                }
                for(right--; dat[right] <= pivot; right--){
                }
            }
            
            n += quick(dat, start, right);
            n += quick(dat, left, end);
            
            return n;
        }
    }
}
