package Property;

public class BuildingNew {
    protected boolean existence;
    protected int price;
    public BuildingNew(){
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
