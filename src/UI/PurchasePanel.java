package UI;

import Property.PlaceConstants;

import javax.swing.*;
import java.awt.*;

public class PurchasePanel extends JPanel {

    private Color  mainColor;
    private JLabel placeLabel;
    private JLabel testLabel;
    private JPanel checkboxPanel;

    private Checkbox landCheckbox;
    private Checkbox houseCheckbox;
    private Checkbox buildingCheckbox;
    private Checkbox hotelCheckbox;
    private Checkbox landMarkCheckbox;

    public PurchasePanel(){
        setBackground(new Color(226,226,226));
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setLayout(null);

        placeLabel = new JLabel(PlaceConstants.PLACE_NAME[17]); // placeNameConstants.placeName_NAME[1]
        placeLabel.setBounds(0,0,800/7*5-80, 80);
        placeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setVerticalAlignment(SwingConstants.CENTER);
        placeLabel.setForeground(Color.black);
        add(placeLabel);

        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(0, 100, 800/7*5-80, 50);
        checkboxPanel.setBackground(Color.PINK);
        //checkboxPanel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 10));
        add(checkboxPanel);

        landCheckbox = new Checkbox("land", true);
        landCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        checkboxPanel.add(landCheckbox);
        houseCheckbox = new Checkbox("house", false);
        checkboxPanel.add(houseCheckbox);
        buildingCheckbox = new Checkbox("building", false);
        checkboxPanel.add(buildingCheckbox);
        hotelCheckbox = new Checkbox("hotel", false);
        checkboxPanel.add(hotelCheckbox);
        landMarkCheckbox = new Checkbox("랜드마크", false);
        checkboxPanel.add(landMarkCheckbox);

    } // PurchasePanel()

} // PurchasePanel class
