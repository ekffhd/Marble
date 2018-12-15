package Property;
import Player.Player;

public class CityNew implements IPlace {
    private String          name;
    protected BuildingNew[] buildings;
    protected int           price;
    protected Player        player;
    protected Place         place;

    public CityNew(Place place){
        buildings = new BuildingNew[5];

        for(int i=0;i<5;i++){
            buildings[i] = new BuildingNew();
        }

        player = new Player(-1);

        this.place = place;
        price = 0;
    }

    public void setName(String name){ //도시 이름 설정
        this.name = name;
    }
    public String get_name(){
        return name;
    }
    public void set_building_price(){
        price = 0;

        buildings[0].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]);      //땅
        buildings[1].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]+3);    //집
        buildings[2].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]+7);    //빌딩
        buildings[3].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]+12);   //호텔
        buildings[4].set_price(BuildingConstants.LANDMARK_TOLL[place.cityNumber]);  //랜드마크
    }

    public void add_city_price(int building_id){
        price += buildings[building_id].get_price();
    }
    public void init_city(){
        price = 0;
        for(int i=0;i<4;i++){
            buildings[i].init_existence();
        }
        player = new Player(-1);
    }
    public int get_city_price(){
        return price;
    }

    public void set_owner(Player player){
        this.player = player;
    }
    public Player get_owner(){
        return player;
    }


    public void onEnter(){
        if (player == null){ // 주인이 없을 떄

        }
        else { // 주인이 있을 때
        }
    }

}
