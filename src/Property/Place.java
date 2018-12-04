package Property;

import Player.PlayerConstants;

import javax.swing.*;
import java.awt.*;

public class Place extends JPanel {
    private City city;
    private JLabel cityNameLabel;

    protected ImageIcon[] playerIcon;
    protected JLabel[] playerIconLabel;

    public Place(String cityName) {
        setLayout(null);
        city = new City();
        city.setName(cityName);

        cityNameLabel = new JLabel(cityName);
        cityNameLabel.setBounds(0, 0, 800 / 7, 550 / 7);
        cityNameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
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
    }//Place()

    public void setColor(Color color){
        this.cityNameLabel.setForeground(color);
    }


    public void show_player(int playerId) {
        this.playerIconLabel[playerId].setVisible(true);
    }
    public void hide_player(int playerId){
        this.playerIconLabel[playerId].setVisible(false);
    }
}
