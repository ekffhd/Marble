package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PurchasePanel extends JPanel {

    private Color            mainBackgroundColor, mainColor;
    private JLabel           placeLabel;
    private JPanel           checkboxPanel;
    private JCheckBox        landCheckbox, houseCheckbox, buildingCheckbox, hotelCheckbox, landMarkCheckbox;
    private JButton          purchaseButton, cancelButton;
    private Phase            phase;
    private Main             main;

    private CheckBoxListener checkBoxListener;
    private ButtonListener   buttonListener;

    public PurchasePanel(Phase phase, Main main){
        mainBackgroundColor = Color.white;
        mainColor = new Color(52, 81, 138);
        checkBoxListener = new CheckBoxListener();
        buttonListener = new ButtonListener();

        this.phase = phase;
        this.main = main;

        setBackground(mainBackgroundColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(mainColor, 6));
        setLayout(null);

        placeLabel = new JLabel(PlaceConstants.PLACE_NAME[17]); // placeNameConstants.placeName_NAME[1]
        placeLabel.setBounds(0,0,800/7*5-80, 80);
        placeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setVerticalAlignment(SwingConstants.CENTER);
        placeLabel.setForeground(Color.black);
        add(placeLabel);

        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(6, 80, 800/7*5-80-12, 150);
        checkboxPanel.setBackground(mainBackgroundColor);
        checkboxPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        add(checkboxPanel);

        landCheckbox = new JCheckBox("부지");
        landCheckbox.setSelected(true);
        landCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
        landCheckbox.setBackground(mainBackgroundColor);
        landCheckbox.addActionListener(checkBoxListener);
        checkboxPanel.add(landCheckbox);

        houseCheckbox = new JCheckBox("집", false);
        houseCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
        houseCheckbox.setBackground(mainBackgroundColor);
        houseCheckbox.addActionListener(checkBoxListener);
        checkboxPanel.add(houseCheckbox);

        buildingCheckbox = new JCheckBox("빌딩", false);
        buildingCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
        buildingCheckbox.setBackground(mainBackgroundColor);
        buildingCheckbox.addActionListener(checkBoxListener);
        checkboxPanel.add(buildingCheckbox);

        hotelCheckbox = new JCheckBox("호텔", false);
        hotelCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
        hotelCheckbox.setBackground(mainBackgroundColor);
        hotelCheckbox.addActionListener(checkBoxListener);
        checkboxPanel.add(hotelCheckbox);

        landMarkCheckbox = new JCheckBox("랜드마크", false);
        landMarkCheckbox.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
        landMarkCheckbox.setBackground(mainBackgroundColor);
        landMarkCheckbox.setEnabled(false);
        landMarkCheckbox.addActionListener(checkBoxListener);
        checkboxPanel.add(landMarkCheckbox);

        purchaseButton = new JButton("BUY"); // 구매 버튼
        purchaseButton.setBounds(40, 250, (800/7*5-210)/2, 40);
        purchaseButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        purchaseButton.setBackground(Color.white);
        purchaseButton.setForeground(mainColor);
        purchaseButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        purchaseButton.setVerticalAlignment(SwingConstants.CENTER);
        purchaseButton.setHorizontalAlignment(SwingConstants.CENTER);
        purchaseButton.addMouseListener(buttonListener);
        add(purchaseButton);

        cancelButton = new JButton("CANCEL"); // 구매 취소 버튼
        cancelButton.setBounds(90+(800/7*5-210)/2, 250, (800/7*5-210)/2, 40);
        cancelButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        cancelButton.setBackground(Color.white);
        cancelButton.setForeground(mainColor);
        cancelButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        cancelButton.setVerticalAlignment(SwingConstants.CENTER);
        cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
        cancelButton.addMouseListener(buttonListener);
        add(cancelButton);
    } // PurchasePanel()

    private class CheckBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            if (checkbox == landCheckbox){
                landCheckbox.setSelected(true);
            } else if (checkbox == houseCheckbox) {

            } else if (checkbox == buildingCheckbox) {

            } else if (checkbox == hotelCheckbox) {

            } else if (checkbox == landMarkCheckbox) {

            } // if ~ else if
        } // actionPerformed()
    } // CheckBoxListener class

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){}

        public void mousePressed(MouseEvent event){
            main.next();
        } // mousePressed()

        public void mouseReleased(MouseEvent event){}

        public void mouseEntered(MouseEvent event){
            JButton object = (JButton)event.getSource();
            object.setBackground(mainColor);
            object.setForeground(Color.white);
        } // mouseEntered()

        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();
            object.setBackground(Color.white);
            object.setForeground(mainColor);
        } // mouseExited()
    } // ButtonListener class

} // PurchasePanel class
