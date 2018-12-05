package UI;

import Player.PlayerConstants;
import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TollPanel extends JPanel {

    private Color          mainBackgroundColor, mainColor, placeColor;
    private Phase          phase;
    private int            position, owner;
    private int            bill;

    private JButton        payButton;
    private JLabel         placeLabel, noticeLabel, billLabel;

    private ButtonListener buttonListener;

    public TollPanel (Phase phase) {
        mainBackgroundColor = Color.white;
        mainColor = new Color(52, 81, 138);
        buttonListener = new ButtonListener();

        this.phase = phase;

        setBackground(mainBackgroundColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(mainColor, 6));
        setLayout(null);

        placeLabel = new JLabel();
        placeLabel.setBounds(0,0,800/7*5-80, 80);
        placeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 50));
        //placeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(placeLabel);

        noticeLabel = new JLabel("통행료를 지불하세요!!!");
        noticeLabel.setBounds(0 , 80, 800/7*5-80, 30);
        noticeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //placeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noticeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(noticeLabel);

        billLabel = new JLabel();
        billLabel.setBounds(0, 110, 800/7*5-80, 120);
        billLabel.setFont(new Font("Drid Herder Solid", Font.PLAIN, 35));
        billLabel.setHorizontalAlignment(SwingConstants.CENTER);
        billLabel.setVerticalAlignment(SwingConstants.CENTER);
        billLabel.setForeground(Color.black);
        add(billLabel);

        payButton = new JButton("PAY"); // 통행료 지불 버튼
        payButton.setBounds((800/7*5-160)/2, 250, 80, 40);
        payButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        payButton.setBackground(Color.white);
        payButton.setForeground(mainColor);
        payButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        payButton.setVerticalAlignment(SwingConstants.CENTER);
        payButton.setHorizontalAlignment(SwingConstants.CENTER);
        payButton.addMouseListener(buttonListener);
        add(payButton);
    }

    public void set_toll_panel_info() {
        position = Main.next_position;
        owner = Main.buildings[position].get_land_owner();
        placeColor = PlayerConstants.PLAYER_COLOR[owner];
        bill = 0;

        placeLabel.setText(PlaceConstants.PLACE_LINE_NAME[Main.next_position]);
        placeLabel.setForeground(placeColor);

        if (Main.buildings[position].get_land_owner() != -1) { // 부지 소유
            bill += PlaceConstants.LAND_TOLL[position]; // 부지 통행료
        }
        if (Main.buildings[position].get_house_ownership() == 1) { // 집 소유
            if (position == 23) { bill += 5; }
            else { bill += 3; }
        }
        if (Main.buildings[position].get_building_ownership() == 1) { // 빌딩 소유
            if (position == 23) { bill += 10; }
            else { bill += 4; }
        }
        if (Main.buildings[position].get_hotel_ownership() == 1) { // 호텔 소유
            if (position == 23) { bill += 15; }
            else { bill += 5; }
        }
        if (Main.buildings[position].get_landmark_ownership() == 1) { // 랜드마크 소유
            bill += PlaceConstants.LANDMARK_TOLL[position]; // 랜드마크 통행료
        }
        billLabel.setText("-"+(bill*10000)+" won");

    }

    public int get_bill() { return bill; }

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();
            setVisible(false);
            phase.bill();
        }
        public void mousePressed(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        public void mouseEntered(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(mainColor);
        }
        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(mainColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
    }

}
