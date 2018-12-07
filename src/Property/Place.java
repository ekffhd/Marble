package Property;

import Player.Player;
import Player.PlayerConstants;

import javax.swing.*;
import java.awt.*;

public class Place extends JPanel {
    protected City city;
    private JLabel cityNameLabel;

    protected ImageIcon[] playerIcon;
    protected JLabel[] playerIconLabel;

    protected ImageIcon[] buildingIcon;
    protected JLabel[] buildingIconLabel;

    protected int placeId;



    public Place(String cityName, int placeId) {
        setLayout(null);
        city = new City(this);
        city.setName(cityName);
        this.placeId = placeId;

        cityNameLabel = new JLabel(cityName);
        cityNameLabel.setBounds(0, 0, 800 / 7, 550 / 7);
        cityNameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
        //cityNameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));

        cityNameLabel.setHorizontalAlignment((SwingConstants.CENTER));
        cityNameLabel.setVerticalTextPosition(SwingConstants.CENTER);

        add(cityNameLabel);

        playerIcon = new ImageIcon[4];
        playerIconLabel = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            playerIcon[i] = new ImageIcon(PlayerConstants.PLAYER_UNIT[i]);
            playerIconLabel[i] = new JLabel(playerIcon[i]);
            playerIconLabel[i].setBounds(27 * i+3, 3, 27, 26);
            add(playerIconLabel[i]);
            playerIconLabel[i].setVisible(false);
        }

        buildingIcon = new ImageIcon[3];
        buildingIconLabel = new JLabel[3];

        for(int i=0;i<3;i++){
            buildingIcon[i] = new ImageIcon();
            buildingIconLabel[i] = new JLabel(buildingIcon[i]);
            buildingIconLabel[i].setBounds(27*i+3, 52, 27, 26);
            add(buildingIconLabel[i]);
            buildingIconLabel[i].setVisible(false);
        }
    }//Place()

    public void setColor(Color color){
        this.cityNameLabel.setForeground(color);
    }

    public int get_price() { return this.city.price; }
    public int set_price(int cash) {
        this.city.price = cash;
        return this.city.price;
    }
    public void set_building_price(int[] cash){
        city.buildings.set_building_price(cash[0], cash[1], cash[2], cash[3], cash[4]);
    }
    public int get_land_price(){
        return city.buildings.get_land_price();
    }
    public int get_house_price(){
        return city.buildings.get_house_price();
    }
    public int get_building_price(){
        return city.buildings.get_building_price();
    }
    public int get_hotel_price(){
        return city.buildings.get_hotel_price();
    }
    public int get_landmark_price(){
        return city.buildings.get_landmark_price();
    }

    public void purchase_land(Player player){
        city.owner = player;
        city.buildings.set_land_owner(player.get_player_id());
        set_owner(player);
        cityNameLabel.setForeground(PlayerConstants.PLAYER_COLOR[player.get_player_id()]);
    }
    public void purchase_house(){ city.buildings.purchase_house(); }
    public void purchase_building(){ city.buildings.purchase_building(); }
    public void purchase_hotel(){ city.buildings.purchase_hotel(); }
    public void purchase_landmark(){
        city.buildings.purchase_landmark();
        cityNameLabel.setForeground(Color.black);}
    public void set_city_price(){
        city.set_city_price();
    }

    public int get_city_price(){
        return city.price;
    }



    public void show_player(int playerId) {
        this.playerIconLabel[playerId].setVisible(true);
    }
    public void hide_player(int playerId){
        this.playerIconLabel[playerId].setVisible(false);
    }
    public void set_owner(Player player){
        city.owner = player;
        buildingIconLabel[0].setIcon(new ImageIcon(PlaceConstants.BUILDING_URL[0]+player.get_player_id()+".png"));
        buildingIconLabel[1].setIcon(new ImageIcon(PlaceConstants.BUILDING_URL[1]+player.get_player_id()+".png"));
        buildingIconLabel[2].setIcon(new ImageIcon(PlaceConstants.BUILDING_URL[2]+player.get_player_id()+".png"));
    }

    public void update_building_status(int playerId){
        if(city.buildings.house == 1){
            buildingIconLabel[0].setVisible(true);
        }
        else{
            buildingIconLabel[0].setVisible(false);
        }
        if(city.buildings.building == 1){
            buildingIconLabel[1].setVisible(true);
        }
        else{
            buildingIconLabel[1].setVisible(false);
        }
        if(city.buildings.hotel == 1){
            buildingIconLabel[2].setVisible(true);
        }
        else{
            buildingIconLabel[2].setVisible(false);
        }
        if(city.buildings.landmark == 1){
            buildingIconLabel[0].setVisible(false);
            buildingIconLabel[1].setVisible(false);
            buildingIconLabel[2].setVisible(false);
            setBackground(PlayerConstants.PLAYER_COLOR[playerId]);
        }

    }

    public int get_land_owner(){
        return city.owner.get_player_id();
    }
    public int get_house_ownership(){
        return city.buildings.get_house_ownership();
    }
    public int get_building_ownership(){
        return city.buildings.get_building_ownership();
    }
    public int get_hotel_ownership(){
        return city.buildings.get_hotel_ownership();
    }
    public int get_landmark_ownership(){
        return city.buildings.get_landmark_ownership();
    }
}
