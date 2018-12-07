package Property;

import Player.Player;
import Player.PlayerConstants;

import javax.swing.*;
import java.awt.*;

public class Place extends JPanel {
    protected City        city;
    private JLabel        cityNameLabel;
    protected JLabel      cityNumberLabel;

    protected ImageIcon[] playerIcon;
    protected JLabel[]    playerIconLabel;

    protected ImageIcon[] buildingIcon;
    protected JLabel[]    buildingIconLabel;

    public Place(String cityName, int cityNumber) {
        setLayout(null);
        city = new City();
        city.setName(cityName);

        cityNameLabel = new JLabel(cityName);
        cityNameLabel.setBounds(0, 0, 800 / 7, 550 / 7);
        //cityNameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
        cityNameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));

        cityNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameLabel.setVerticalTextPosition(SwingConstants.CENTER);

        add(cityNameLabel);

        cityNumberLabel = new JLabel(cityNumber+".");
        cityNumberLabel.setBounds(800/7-22, 550/7-20, 22, 20);
        cityNumberLabel.setFont(new Font("Drid Herder Solid", Font.PLAIN, 11));
        cityNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cityNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(cityNumberLabel);

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
    public void set_city_number_color(Color color) { this.cityNumberLabel.setForeground(color); }

    public int get_price() { return this.city.price; }
    public int set_price(int cash) {
        this.city.price = cash;
        return this.city.price;
    }

    public void show_city_number() { this.cityNumberLabel.setVisible(true); }
    public void hide_city_number() { this.cityNumberLabel.setVisible(false);}
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
    public void set_building_status(Player player, int house, int building,int hotel,  int landmark){
        set_owner(player);
        city.buildings.set_land_owner(player.get_player_id());
        city.buildings.purchase_house(house);
        city.buildings.purchase_building(building);
        city.buildings.purchase_hotel(hotel);
        city.buildings.purchase_landmark(landmark);
        update_building_status(player.get_player_id());
    }
    public void update_building_status(int playerId){
        if(city.buildings.house== 1){
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
