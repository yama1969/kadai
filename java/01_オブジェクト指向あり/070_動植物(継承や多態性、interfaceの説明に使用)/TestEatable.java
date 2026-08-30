class TestEatable{
    public static void main(String[] args){
        Eatable e = getEatable();
        e.eat();
    }
    
    static Eatable getEatable(){
        int num = (int)(Math.random() * 4);
        switch(num){
        case 0:
            return new Cat();
        case 1:
            return new Dog();
        case 2:
            return new Cow();
        case 3:
            return new Haetorisou();
        }
        return null;
    }
}
