package Property;
//주석 완료
import Player.Player;
import Player.PlayerConstants;

import javax.swing.*;
import java.awt.*;

public class Place extends JPanel {
    protected CityNew       city; //도시 정보 클래스
    protected JLabel        cityNameLabel;
    protected JLabel        cityNumberLabel;

    protected ImageIcon[]   playerIcon; //플레이어 말 아이콘
    protected JLabel[]      playerIconLabel;

    protected ImageIcon[]   buildingIcon;//건물 아이콘
    protected JLabel[]      buildingIconLabel;

    protected int cityNumber; //city ID



    public Place(String cityName, int cityNumber) {
        setLayout(null);

        //도시 정보 설정
        city = new CityNew(this);
        city.setName(cityName);
        this.cityNumber = cityNumber;

        //도시 이름 라벨
        cityNameLabel = new JLabel(cityName);
        cityNameLabel.setBounds(0, 0, 800 / 7, 550 / 7);
        //cityNameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
        cityNameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        cityNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        add(cityNameLabel);

        //도시 번호 라벨
        cityNumberLabel = new JLabel(cityNumber+".");
        cityNumberLabel.setBounds(800/7-22, 550/7-20, 22, 20);
        cityNumberLabel.setFont(new Font("Drid Herder Solid", Font.PLAIN, 11));
        cityNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cityNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(cityNumberLabel);

        //플레이어 말 아이콘
        playerIcon = new ImageIcon[4];
        playerIconLabel = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            playerIcon[i] = new ImageIcon(PlayerConstants.PLAYER_UNIT[i]);
            playerIconLabel[i] = new JLabel(playerIcon[i]);
            playerIconLabel[i].setBounds(27 * i+3, 3, 27, 26);
            add(playerIconLabel[i]);
            playerIconLabel[i].setVisible(false);
        }

        //건물 아이콘
        buildingIcon = new ImageIcon[3];
        buildingIconLabel = new JLabel[3];
        for(int i=0;i<3;i++){
            buildingIcon[i] = new ImageIcon();
            buildingIconLabel[i] = new JLabel(buildingIcon[i]);
            buildingIconLabel[i].setBounds(27*i+3, 52, 27, 26);
            add(buildingIconLabel[i]);
            buildingIconLabel[i].setVisible(false);
        }
    }//Place

    //지역 색 설정
    public void setColor(Color color){
        this.cityNameLabel.setForeground(color);
    }
    public void set_city_number_color(Color color) { this.cityNumberLabel.setForeground(color); }

    //부지&건물 가격 초기화
    public void set_building_price(){
        city.set_building_price();
    }

    //현재 지역 가격 반환
    public int get_city_price(){
        return city.price;
    }

    //해당 지역의 모금액 설정&반환 (광개토 ATM 전용 함수)
    public void add_price(int cash){
        this.city.price += cash;
    }
    public void init_price(int cash){
        this.city.price = cash;
    }

    public String get_city_name(){
        return city.get_name();
    }

    //부지&건물 가격 반환
    public int get_land_price(){
        return city.buildings[0].get_price();
    }
    public int get_house_price(){
        return city.buildings[1].get_price();
    }
    public int get_building_price(){
        return city.buildings[2].get_price();
    }
    public int get_hotel_price(){
        return city.buildings[3].get_price();
    }
    public int get_landmark_price(){
        return city.buildings[4].get_price();
    }

    //부지&건물 소유 상황 반환
    public int get_land_owner_id(){
        return city.player.get_player_id();
    }
    public boolean get_house_ownership(){
        return city.buildings[1].get_existence();
    }
    public boolean get_building_ownership(){
        return city.buildings[2].get_existence();
    }
    public boolean get_hotel_ownership(){
        return city.buildings[3].get_existence();
    }
    public boolean get_landmark_ownership(){
        return city.buildings[4].get_existence();
    }

    //지역 색 설정
    public void set_owner(Player player){
        cityNameLabel.setForeground(PlayerConstants.PLAYER_COLOR[player.get_player_id()]);
        buildingIconLabel[0].setIcon(new ImageIcon(PlaceConstants.BUILDING_URL[0]+player.get_player_id()+".png"));
        buildingIconLabel[1].setIcon(new ImageIcon(PlaceConstants.BUILDING_URL[1]+player.get_player_id()+".png"));
        buildingIconLabel[2].setIcon(new ImageIcon(PlaceConstants.BUILDING_URL[2]+player.get_player_id()+".png"));
    }

    //부지&건물 구입
    public void purchase_land(Player player){
        city.set_owner(player);
        city.add_city_price(0);
        city.buildings[0].set_existence();
        set_owner(player);
    }
    public void purchase_house(){
        city.add_city_price(1);
        city.buildings[1].set_existence();
    }
    public void purchase_building(){
        city.add_city_price(2);
        city.buildings[2].set_existence();
    }
    public void purchase_hotel(){
        city.add_city_price(3);
        city.buildings[3].set_existence();
    }
    public void purchase_landmark(){
        city.add_city_price(4);
        city.buildings[4].set_existence();

    }

    //city ID 보이기/숨기기
    public void show_city_number() { this.cityNumberLabel.setVisible(true); }
    public void hide_city_number() { this.cityNumberLabel.setVisible(false);}

    //플레이어 아이콘 보이기/숨기기
    public void show_player(int playerId) {
        this.playerIconLabel[playerId].setVisible(true);
    }
    public void hide_player(int playerId){
        this.playerIconLabel[playerId].setVisible(false);
    }

    //건물 아이콘 보이기/숨기기
    public void update_building_status(int playerId){
        if(city.buildings[1].get_existence()){
            buildingIconLabel[0].setVisible(true);
        }
        else{
            buildingIconLabel[0].setVisible(false);
        }
        if(city.buildings[2].get_existence()){
            buildingIconLabel[1].setVisible(true);
        }
        else{
            buildingIconLabel[1].setVisible(false);
        }
        if(city.buildings[3].get_existence()){
            buildingIconLabel[2].setVisible(true);
        }
        else{
            buildingIconLabel[2].setVisible(false);
        }
        if(city.buildings[4].get_existence()){
            buildingIconLabel[0].setVisible(false);
            buildingIconLabel[1].setVisible(false);
            buildingIconLabel[2].setVisible(false);
            setBackground(PlayerConstants.PLAYER_COLOR[playerId]);
            cityNameLabel.setForeground(Color.black);
        }
        else{
            setBackground(Color.white);
        }
    }//update_building_status()

    public void init_place_info(Player player){
        this.cityNameLabel.setForeground(Color.black);
        this.cityNumberLabel.setForeground(Color.black);

        city.init_city();

        update_building_status(player.get_player_id());

    }
}
