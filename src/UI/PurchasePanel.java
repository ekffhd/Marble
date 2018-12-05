package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PurchasePanel extends JPanel {

    private Color            mainBackgroundColor, mainColor;
    private JLabel           placeLabel;
    private JPanel           checkboxPanel;

    private JButton          purchaseButton, cancelButton;
    private Phase            phase;
    private int              position, expense, cash;
    private int              land, house, building, hotel, landmark; // checkbox가 선택되었으면 1

    private JPanel[]         menuPanel;
    private JLabel[]         menuLabel, priceLabel;
    private JCheckBox[]      menuCheckBox;

    private CheckBoxListener checkBoxListener;
    private ButtonListener   buttonListener;


    public PurchasePanel(Phase phase){
        mainBackgroundColor = Color.white;
        mainColor = new Color(52, 81, 138);
        checkBoxListener = new CheckBoxListener();
        buttonListener = new ButtonListener();

        this.phase = phase;
        expense = 0;

        setBackground(mainBackgroundColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(mainColor, 6));
        setLayout(null);

        placeLabel = new JLabel();
        placeLabel.setBounds(0,0,800/7*5-80, 80);
        placeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        //placeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setVerticalAlignment(SwingConstants.CENTER);
        placeLabel.setForeground(Color.black);
        add(placeLabel);

        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(6, 80, 800/7*5-80-12, 150);
        checkboxPanel.setBackground(mainBackgroundColor);
        checkboxPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        checkboxPanel.setLayout(null);
        add(checkboxPanel);

        menuPanel = new JPanel[5];
        for (int i=0; i<5; i++){
            menuPanel[i] = new JPanel();
            menuPanel[i].setBackground(mainBackgroundColor);
            menuPanel[i].setBounds((800/7*5-567)/2+95*i, 0, 95, 150);
            menuPanel[i].setBorder(BorderFactory.createLineBorder(Color.blue, 1));
            menuPanel[i].setLayout(null);
            checkboxPanel.add(menuPanel[i]);
        }

        menuLabel = new JLabel[5];
        menuLabel[0] = new JLabel("부지");
        menuLabel[1] = new JLabel("집");
        menuLabel[2] = new JLabel("빌딜");
        menuLabel[3] = new JLabel("호텔");
        menuLabel[4] = new JLabel("랜드마크");
        for (int i=0; i<5; i++){
            menuLabel[i].setBounds(0,0,95,60);
            menuLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            menuLabel[i].setVerticalAlignment(SwingConstants.CENTER);
            menuLabel[i].setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
            menuPanel[i].add(menuLabel[i]);
        }

        priceLabel = new JLabel[5];
        for (int i=0; i<5; i++){
            priceLabel[i] = new JLabel();
            priceLabel[i].setBounds(0,60,95,50);
            priceLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            priceLabel[i].setVerticalAlignment(SwingConstants.CENTER);
            priceLabel[i].setFont(new Font("Drid Herder Solid", Font.PLAIN, 20));
            menuPanel[i].add(priceLabel[i]);
        }

        menuCheckBox = new JCheckBox[5];
        for (int i=0; i<5; i++){
            menuCheckBox[i] = new JCheckBox();
            menuCheckBox[i].setSelected(false);
            menuCheckBox[i].setBounds(0, 110, 95, 40);
            menuCheckBox[i].setHorizontalAlignment(SwingConstants.CENTER);
            menuCheckBox[i].setVerticalAlignment(SwingConstants.CENTER);
            menuCheckBox[i].setBackground(mainBackgroundColor);
            menuCheckBox[i].addActionListener(checkBoxListener);
            menuPanel[i].add(menuCheckBox[i]);
        }
        menuCheckBox[0].setSelected(true);
        menuCheckBox[4].setEnabled(false);

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

    public void set_purchase_panel_info(){
        position = Main.next_position;
        expense = 0;
        this.cash = Main.player[Main.player_turn%4].get_cash();

        placeLabel.setText(PlaceConstants.PLACE_LINE_NAME[Main.next_position]);
        priceLabel[0].setText(PlaceConstants.LAND_PRICE[Main.next_position]+" $"); // 부지
        priceLabel[1].setText(PlaceConstants.HOUSE_PRICE[Main.next_position]+" $"); // 집
        priceLabel[2].setText(PlaceConstants.BUILDING_PRICE[Main.next_position]+" $"); // 빌딩
        priceLabel[3].setText(PlaceConstants.HOTEL_PRICE[Main.next_position]+" $"); // 호텔
        priceLabel[4] .setText(PlaceConstants.LANDMARK_PRICE[Main.next_position]+" $"); // 랜드마크

        if (Main.buildings[position].get_land_owner() != -1) { // 부지 소유
            menuCheckBox[0].setEnabled(false);
        }
        if (Main.buildings[position].get_house_ownership() == 1) { // 집 소유
            menuCheckBox[1].setEnabled(false);
        }
        if (Main.buildings[position].get_building_ownership() == 1) { // 빌딩 소유
            menuCheckBox[2].setEnabled(false);
        }
        if (Main.buildings[position].get_hotel_ownership() == 1) { // 호텔 소유
            menuCheckBox[3].setEnabled(false);
        }
        if (Main.buildings[position].get_land_owner() != -1  // 랜드마크만 남은 상황
                && Main.buildings[position].get_house_ownership() == 1
                && Main.buildings[position].get_building_ownership() == 1
                && Main.buildings[position].get_hotel_ownership() == 1){
            menuCheckBox[4].setEnabled(true);
        }
    }

    public int get_expense() { return expense; }
    public int get_land() { return land; }
    public int get_house() { return house; }
    public int get_building() { return building; }
    public int get_hotel() { return hotel; }
    public int get_landmark() { return landmark; }

    private class CheckBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            expense = 0; land = 0; house = 0; building = 0; hotel = 0; landmark = 0;
            if (checkbox == menuCheckBox[0]){
                menuCheckBox[0].setSelected(true);
            } // if

            if (menuCheckBox[0].isSelected()){ // 부지
                expense += PlaceConstants.LAND_PRICE[Main.next_position];
                land = 1;
            }
            if (menuCheckBox[1].isSelected()){ // 집
                expense += PlaceConstants.HOUSE_PRICE[Main.next_position];
                house = 1;
            }
            if (menuCheckBox[2].isSelected()){ // 빌딩
                expense += PlaceConstants.BUILDING_PRICE[Main.next_position];
                building = 1;
            }
            if (menuCheckBox[3].isSelected()){ // 호텔
                expense += PlaceConstants.HOTEL_PRICE[Main.next_position];
                hotel = 1;
            }
            if (menuCheckBox[4].isSelected()){ // 랜드마크
                expense += PlaceConstants.LANDMARK_PRICE[Main.next_position];
                landmark = 1;
            }

            if (expense > cash) {
                purchaseButton.setEnabled(false);
            }

        } // actionPerformed()
    } // CheckBoxListener class

    private class ButtonListener implements MouseListener {

        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();
            setVisible(false);
            if (object == purchaseButton){
                if (expense == 0 && menuCheckBox[0].isSelected()){ // 부지
                    expense += PlaceConstants.LAND_PRICE[Main.next_position];
                    land = 1;
                }
                System.out.println(expense);
                phase.purchase();
            }else if (object == cancelButton){
                phase.next();
            } // if ~ else if
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
