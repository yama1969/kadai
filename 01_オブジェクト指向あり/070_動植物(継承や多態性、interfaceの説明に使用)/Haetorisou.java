class Haetorisou extends Plant implements Eatable{
    String getPlantName(){
        return "ハエトリソウ";
    }
    
    @Override
    public void eat(){
        System.out.println("虫を食べます。");
    }
}