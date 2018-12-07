package Property;
import Player.Player;

public class City implements IPlace {
    private String name;
    protected Building buildings;
    protected int price;
    protected Player owner;
    protected Place place;

    public City(Place place){
        buildings = new Building();
        owner = new Player(-1);
        this.place = place;
        price = 0;
    }

    public void setName(String name){ //도시 이름 설정
        this.name = name;
    }

    public void set_city_price(){
        price = 0;

        price += BuildingConstants.LAND_TOLL[place.placeId];

        if(buildings.house == 1){
            price += BuildingConstants.LAND_TOLL[place.placeId]+3;
        }
        if(buildings.building == 1){
            price += BuildingConstants.LAND_TOLL[place.placeId]+7;
        }
        if(buildings.hotel == 1){
            price += BuildingConstants.LAND_TOLL[place.placeId]+12;
        }
        if(buildings.landmark == 1){
            price = BuildingConstants.LANDMARK_TOLL[place.placeId];
        }
    }


    public void onEnter(){
        if (owner == null){ // 주인이 없을 떄

        }
        else { // 주인이 있을 때
        }
    }

}
