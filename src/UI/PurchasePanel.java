package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PurchasePanel extends JPanel {

    private Color  mainColor;
    private JLabel placeLabel;
    private JLabel testLabel;
    private JPanel checkboxPanel;
    private JButton confirmButton;

    private Checkbox landCheckbox;
    private Checkbox houseCheckbox;
    private Checkbox buildingCheckbox;
    private Checkbox hotelCheckbox;
    private Checkbox landMarkCheckbox;

    private Phase phase;

    private ButtonListener buttonListener;

    public PurchasePanel(Phase phase){
        setBackground(new Color(226,226,226));
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setLayout(null);
        this.phase = phase;
        buttonListener = new ButtonListener();

        placeLabel = new JLabel(PlaceConstants.PLACE_NAME[17]); // placeNameConstants.placeName_NAME[1]
        placeLabel.setBounds(0,0,800/7*5-80, 80);
        placeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        //placeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
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
        //landCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        landCheckbox.setFont(new Font("RixVideoGame 3D", Font.PLAIN, 20));
        checkboxPanel.add(landCheckbox);
        houseCheckbox = new Checkbox("house", false);
        checkboxPanel.add(houseCheckbox);
        buildingCheckbox = new Checkbox("building", false);
        checkboxPanel.add(buildingCheckbox);
        hotelCheckbox = new Checkbox("hotel", false);
        checkboxPanel.add(hotelCheckbox);
        landMarkCheckbox = new Checkbox("랜드마크", false);
        checkboxPanel.add(landMarkCheckbox);

        confirmButton = new JButton("CONFIRM");
        confirmButton.setBounds(122, 200, 122, 50);
        confirmButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        confirmButton.setBackground(Color.white);
        confirmButton.setForeground(mainColor);
        confirmButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        confirmButton.setVerticalAlignment(SwingConstants.CENTER);
        confirmButton.setHorizontalAlignment(SwingConstants.CENTER);
        confirmButton.addMouseListener(buttonListener);
        confirmButton.setVisible(false);
        add(confirmButton);

    } // PurchasePanel()


    public void setMainColor(){

    }


    public void set_panel_info(){
        placeLabel.setText(PlaceConstants.PLACE_LINE_NAME[Main.next_position]);
        confirmButton.setVisible(true);
    }

    private class ButtonListener implements MouseListener {

        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();

            if(object == confirmButton) {
                setVisible(false);
                phase.next();
            }
        }
        public void mousePressed(MouseEvent event){ }
        public void mouseReleased(MouseEvent event){ }
        public void mouseEntered(MouseEvent event) {
            JButton object = (JButton)event.getSource();

            object.setBackground(mainColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(mainColor);
        }
    }//ButtonListener class

} // PurchasePanel class
