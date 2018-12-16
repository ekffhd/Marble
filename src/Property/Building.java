package Property;

public class Building {
    protected boolean existence;
    protected int price;
    public Building(){
        existence = false;
        price = 0;
    }

    //가격 설정/반환
    public void set_price(int price){
        this.price = price;
    }
    public int get_price(){
        return price;
    }

    public void set_existence(){
        existence = true;
    }
    public void init_existence(){
        existence = false;
    }
    public boolean get_existence(){
        return existence;
    }


}
