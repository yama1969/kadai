class FoodShopTester{
    public static void main(String[] args){
        FoodShop fs = new FoodShop();
        
        //最初に買えないことの確認
        System.out.print("最初に購入：");
        try{
            fs.selFood();
            error("購入異常。");
        }catch(NotSellException e){
            System.out.println("正常。");
        }
        
        //注文5回
        System.out.print("5回注文：");
        for(int i = 0; i < 5; i++){
            try{
                fs.makeFood();
            }catch(NotMakeException e){
                error("注文異常。");
            }
        }
        System.out.println("正常。");
        
        //注文6回目
        System.out.print("6回目注文：");
        try{
            fs.makeFood();
            error("注文異常。");
        }catch(NotMakeException e){
            System.out.println("正常。");
        }
        
        //5回購入
        System.out.print("5回購入");
        for(int i = 0; i < 5; i++){
            try{
                fs.selFood();
            }catch(NotSellException e){
                error("購入異常。");
            }
        }
        System.out.println("正常。");
        
        //6回目購入
        System.out.print("6回目購入：");
        try{
            fs.selFood();
            error("購入異常。");
        }catch(NotSellException e){
            System.out.println("正常。");
        }
        
        //注文3回
        System.out.print("3回注文：");
        for(int i = 0; i < 3; i++){
            try{
                fs.makeFood();
            }catch(NotMakeException e){
                error("注文異常。");
            }
        }
        System.out.println("正常。");
        
        //3回購入
        System.out.print("3回購入：");
        for(int i = 0; i < 3; i++){
            try{
                fs.selFood();
            }catch(NotSellException e){
                error("購入異常。");
            }
        }
        System.out.println("正常。");
        
        //4回目購入
        System.out.print("4回目購入：");
        try{
            fs.selFood();
            error("購入異常。");
        }catch(NotSellException e){
            System.out.println("正常。");
        }
        
    }
    
    public static void error(String mess){
        System.out.println("■■■■■" + mess + "テスト停止。");
        System.exit(1);
    }
}

//スタブ：FoodShopが利用するクラス
class NotSellException extends Exception{}
class NotMakeException extends Exception{}
interface FoodStockable{void putStock(Eatable food);}
interface Eatable{int getPrice();}
class FoodMaker{
    FoodMaker(FoodStockable stocker, int price){
        System.out.println("FoodMakerコンストラクタ実行。食品インスタンス生成。");
        Eatable e = new Eatable(){public int getPrice(){return 1;}};
        stocker.putStock(e);
    }
}
