package Property;

public class Building {
    protected int land; // 소유한 플레이어의 숫자 저장 ( -1은 소유한 사람 X)
    protected int house ; // 0 : 소유 X, 1 : 소유 O
    protected int building;
    protected int hotel;
    protected int landmark;

    public Building (){
        land = -1; // 소유한 플레이어의 숫자 저장 ( -1은 소유한 사람 X)
        house = 0; // 0 : 소유 X, 1 : 소유 O
        building = 0;
        hotel = 0;
        landmark = 0;
    }

    public int get_land_owner() { return land; }
    public int get_house_ownership() { return house; }
    public int get_building_ownership() { return building; }
    public int get_hotel_ownership() { return hotel; }
    public int get_landmark_ownership() { return landmark; }

    public void set_land_owner(int owner) { land = owner; }
    public void purchase_house() { house = 1; }
    public void purchase_building() { building = 1; }
    public void purchase_hotel() { hotel = 1; }
    public void purchase_landmark() { landmark = 1; }

    public void sell_property() {
        land = -1;
        house = 0;
        building = 0;
        hotel = 0;
        landmark = 0;
    }
}