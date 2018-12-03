package Property;

import javax.swing.*;
import java.awt.*;

public class Place extends JPanel {
    private City city;
    private JLabel cityNameLabel;

    public Place(String cityName){
        setLayout(null);
        city = new City();
        city.setName(cityName);

        cityNameLabel = new JLabel(cityName);
        cityNameLabel.setBounds(0, 0, 800/7, 550/7);
        cityNameLabel.setFont(new Font("RixVideoGame3D",Font.PLAIN, 20));
        cityNameLabel.setHorizontalAlignment((SwingConstants.CENTER));
        cityNameLabel.setVerticalTextPosition(SwingConstants.CENTER);

        add(cityNameLabel);
    }

    public void setColor(Color color){
        this.cityNameLabel.setForeground(color);
    }

}
