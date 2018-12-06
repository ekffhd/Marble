package Property;
import Player.Player;

public class City implements IPlace {
    private String name;
    protected Building buildings;
    protected int price;
    protected Player owner;

    public City(){
        buildings = new Building();
        owner = new Player(-1);
        price = 0;
    }

    public void setName(String name){ //도시 이름 설정
        this.name = name;
    }

    public void set_city_status(){

    }


    public void onEnter(){
        if (owner == null){ // 주인이 없을 떄

        }
        else { // 주인이 있을 때
        }
    }

}
