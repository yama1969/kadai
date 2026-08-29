class Planter{
    public static void main(String[] args){
        Plant[] p = new Plant[2];
        p[0] = new Kiku();
        p[1] = new Sugi();
        
        for(int i = 0; i < p.length; i++){
            System.out.println(p[i].getPlantName());
        }
    }
}
