package Property;

// 건물/부지 클래스
public class Building {
    protected boolean       existence;  //건물 소유여부
    protected int           price;      //건물 가격
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

    //소유 여부 설정/초기화/반환
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
