package Property;
import Player.Player;

//지역 데이터
public class City {
    private String          name;
    protected Building[]    buildings;
    protected int           price;
    protected Player        player;
    protected Place         place;

    public City(Place place){
        buildings = new Building[5];

        //배열 순서대로 부지, 집, 빌딩, 호텔, 랜드마크 를 나타낸다.
        for(int i=0;i<5;i++){
            buildings[i] = new Building();
        }

        //소유자 초기화
        //playerId가 -1이면 소유자가 없는 것과 같다.
        player = new Player(-1);

        this.place = place;
        price = 0;
    }

    //부지 이름 설정/반환
    public void setName(String name){ //도시 이름 설정
        this.name = name;
    }
    public String get_name(){
        return name;
    }

    //빌딩 가격 초기화
    public void set_building_price(){
        price = 0;

        buildings[0].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]);      //땅
        buildings[1].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]+3);    //집
        buildings[2].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]+7);    //빌딩
        buildings[3].set_price(BuildingConstants.LAND_TOLL[place.cityNumber]+12);   //호텔
        buildings[4].set_price(BuildingConstants.LANDMARK_TOLL[place.cityNumber]);  //랜드마크
    }

    //건물을 구매함에 따라 도시의 가격이 증가한다.
    public void add_city_price(int building_id){
        price += buildings[building_id].get_price();
    }

    //재시작 또는 소유자가 파산했을 경우 지역의 모든 데이터를 초기화한다.
    public void init_city(){
        price = 0;
        for(int i=0;i<4;i++){
            buildings[i].init_existence();
        }
        player = new Player(-1);
    }

    //도시의 가격을 반환한다.
    public int get_city_price(){
        return price;
    }

    //소유자 설정/반환
    public void set_owner(Player player){
        this.player = player;
    }
    public Player get_owner(){
        return player;
    }




}
