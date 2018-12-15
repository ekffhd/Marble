package Property;

import Player.Player;

public class Building {
    protected int       land_price;
    protected int       house_price;
    protected int       building_price;
    protected int       hotel_price;
    protected int       landmark_price;

    protected Player    player;
    protected int       land; // 소유한 플레이어의 숫자 저장 ( -1은 소유한 사람 X)
    protected int       house ; // 0 : 소유 X, 1 : 소유 O
    protected int       building;
    protected int       hotel;
    protected int       landmark;

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

    public void set_land_owner(Player player) { this.player = player; }
    public void purchase_house() { this.house = 1; }
    public void purchase_building() { this.building = 1; }
    public void purchase_hotel() { this.hotel = 1; }
    public void purchase_landmark() { this.landmark = 1; }

    public void set_building_price(int land, int house, int hotel, int building, int landmark){
        land_price = land;
        house_price = house;
        hotel_price = hotel;
        building_price = building;
        landmark_price = landmark;
    }

    public int get_land_price(){return land_price;}
    public int get_house_price(){return house_price;}
    public int get_hotel_price(){return hotel_price;}
    public int get_building_price(){return building_price;}
    public int get_landmark_price(){return landmark_price;}


    public void sell_property() {
        land = -1;
        house = 0;
        building = 0;
        hotel = 0;
        landmark = 0;
    }
}