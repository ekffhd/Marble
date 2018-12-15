package UI;

import Property.Place;
import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//인수 패널
public class TakeOverPanel extends JPanel {
    private Color           mainBackgroundColor, mainColor;
    private JLabel          titleLabel, placeLabel, expenseLabel;
    private JPanel          checkboxPanel;
    private JPanel[]        menuPanel;
    private JLabel[]        menuLabel, priceLabel;
    private JCheckBox[]     menuCheckBox;
    private JButton         takeOverButton, cancelButton;
    private Phase           phase;
    private int             expense;
    private int             house, building, hotel;
    private Place               place;

    private CheckBoxListener checkBoxListener;
    private ButtonListener  buttonListener;

    public TakeOverPanel(Phase phase) {
        mainBackgroundColor = Color.white;
        mainColor = new Color(52, 81, 138);;
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

        titleLabel = new JLabel("인수를 하시겠습니까?");
        titleLabel.setBounds(0,80, 800/7*5-80, 30);
        titleLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //titleLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.black);
        add(titleLabel);

        expenseLabel = new JLabel(expense+"");
        expenseLabel.setBounds(10,10,100,70);
        expenseLabel.setFont(new Font("Drid Herder Solid", Font.PLAIN, 20));
        expenseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        expenseLabel.setVerticalAlignment((SwingConstants.CENTER));
        expenseLabel.setForeground((Color.black));
        add(expenseLabel);

        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(6, 110, 800/7*5-80-12, 120);
        checkboxPanel.setBackground(mainBackgroundColor);
        checkboxPanel.setLayout(null);
        add(checkboxPanel);

        menuPanel = new JPanel[4];
        for (int i=0; i<4; i++){
            menuPanel[i] = new JPanel();
            menuPanel[i].setBackground(mainBackgroundColor);
            menuPanel[i].setBounds((800/7*5-567)/2+95*i, 0, 95, 120);
            menuPanel[i].setLayout(null);
            checkboxPanel.add(menuPanel[i]);
        }

        menuLabel = new JLabel[4];
        menuLabel[0] = new JLabel("부지");
        menuLabel[1] = new JLabel("집");
        menuLabel[2] = new JLabel("빌딩");
        menuLabel[3] = new JLabel("호텔");
        for (int i=0; i<4; i++){
            menuLabel[i].setBounds(0,0,95,50);
            menuLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            menuLabel[i].setVerticalAlignment(SwingConstants.CENTER);
            menuLabel[i].setFont(new Font("Rix전자오락 Bold", Font.PLAIN, 30));
            //menuLabel[i].setFont(new Font("RixVideoGameB", Font.PLAIN, 25));
            menuPanel[i].add(menuLabel[i]);
        }

        priceLabel = new JLabel[4];
        for (int i=0; i<4; i++){
            priceLabel[i] = new JLabel();
            priceLabel[i].setBounds(0,50,95,40);
            priceLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            priceLabel[i].setVerticalAlignment(SwingConstants.CENTER);
            priceLabel[i].setFont(new Font("Drid Herder Solid", Font.PLAIN, 20));
            menuPanel[i].add(priceLabel[i]);
        }

        menuCheckBox = new JCheckBox[4];
        for (int i=0; i<4; i++){
            menuCheckBox[i] = new JCheckBox();
            menuCheckBox[i].setSelected(false);
            menuCheckBox[i].setBounds(0, 90, 95, 30);
            menuCheckBox[i].setHorizontalAlignment(SwingConstants.CENTER);
            menuCheckBox[i].setVerticalAlignment(SwingConstants.CENTER);
            menuCheckBox[i].setBackground(mainBackgroundColor);
            menuCheckBox[i].addActionListener(checkBoxListener);
            menuPanel[i].add(menuCheckBox[i]);
        }
        menuCheckBox[0].setSelected(true);
        menuCheckBox[0].setEnabled(false);

        takeOverButton = new JButton("BUY"); // 구매 버튼
        takeOverButton.setBounds(40, 250, (800/7*5-210)/2, 40);
        takeOverButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        takeOverButton.setBackground(Color.white);
        takeOverButton.setForeground(mainColor);
        takeOverButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        takeOverButton.setVerticalAlignment(SwingConstants.CENTER);
        takeOverButton.setHorizontalAlignment(SwingConstants.CENTER);
        takeOverButton.addMouseListener(buttonListener);
        add(takeOverButton);

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
    }

    void set_take_over_panel_info(Place place){
        reset_checkbox();

        this.place = place;

        expense = place.get_land_price();

        house = 0; building = 0; hotel = 0;

        placeLabel.setText(PlaceConstants.PLACE_LINE_NAME[Main.nextPosition]);

        priceLabel[0].setText(place.get_land_price()+""); // 부지
        priceLabel[1].setText(place.get_house_price()+""); // 집
        priceLabel[2].setText(place.get_building_price()+""); // 빌딩
        priceLabel[3].setText(place.get_hotel_price()+""); // 호텔

        if (place.get_house_ownership()) { // 집 소유
            expense += place.get_house_price();
            menuCheckBox[1].setSelected(true);
            menuCheckBox[1].setEnabled(false);
            house = 1;
        }
        if (place.get_building_ownership()) { // 빌딩 소유
            expense += place.get_building_price();
            menuCheckBox[2].setSelected(true);
            menuCheckBox[2].setEnabled(false);
            building = 1;
        }
        if (place.get_hotel_ownership()) { // 호텔 소유
            expense += place.get_hotel_price();
            menuCheckBox[3].setSelected(true);
            menuCheckBox[3].setEnabled(false);
            hotel = 1;
        }

        expenseLabel.setText(expense+"");

    }

    private void reset_checkbox() {
        for (int i=0; i<4; i++) {
            menuCheckBox[i].setEnabled(true);
            menuCheckBox[i].setSelected(false);
        }
        menuCheckBox[0].setSelected(true);
        menuCheckBox[0].setEnabled(false);
    }

    private class CheckBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();

            if(checkbox == menuCheckBox[1]&&menuCheckBox[1].isEnabled()){
                if (menuCheckBox[1].isSelected()){ // 집
                    expense += place.get_house_price();
                    house = 1;
                }
                else{
                    expense -= place.get_house_price();
                    house = 0;
                }
            }

            if(checkbox == menuCheckBox[2]&&menuCheckBox[2].isEnabled()){
                if (menuCheckBox[2].isSelected()) { // 빌딩
                    System.out.println("빌딩 구입"+expense);
                    expense += place.get_building_price();
                    building = 1;
                }
                else{
                    expense -= place.get_building_price();
                    building = 0;
                }
            }

            if(checkbox == menuCheckBox[3]&&menuCheckBox[3].isEnabled()){
                if (menuCheckBox[3].isSelected()){ // 호텔
                    expense += place.get_hotel_price();
                    hotel = 1;
                }
                else{
                    expense -= place.get_hotel_price();
                    hotel = 0;
                }
            }

            expenseLabel.setText(expense+"");

            if (expense * 10000 >= Main.activePlayer.get_cash()){
                takeOverButton.setEnabled(false);
                //System.out.println("false "+expense);
            } else {
                takeOverButton.setEnabled(true);
                //System.out.println("true");
            }
        } // actionPerformed()
    } // CheckBoxListener class

    public int get_expense() { return expense; }
    public int get_house() { return house; }
    public int get_building() { return building; }
    public int get_hotel() { return hotel; }

    private class ButtonListener implements MouseListener {

        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();
            setVisible(false);

            if (object == takeOverButton){
                System.out.println("purchasePanel "+expense);
                phase.takeOver();
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
}
