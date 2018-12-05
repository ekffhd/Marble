package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WelfareFacilityPanel extends JPanel { // ATM
    private Color          moneyColor, borderColor;
    private Phase          phase;
    private int            price;

    private JLabel         nameLabel, noticeLabel, priceLabel;
    private JButton        recieveButton;

    private ButtonListener buttonListener;

    public WelfareFacilityPanel(Phase phase) {
        moneyColor = new Color(59, 142, 61);
        borderColor = new Color(28, 58, 28);
        buttonListener = new ButtonListener();

        this.phase = phase;

        setBackground(moneyColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(borderColor, 6));
        setLayout(null);

        nameLabel = new JLabel(PlaceConstants.PLACE_LINE_NAME[12]); // ATM
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 50));
        //nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(nameLabel);

        noticeLabel = new JLabel("누가 돈을..?");
        noticeLabel.setBounds(0 , 80, 800/7*5-80, 30);
        noticeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //noticeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noticeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(noticeLabel);

        priceLabel = new JLabel();
        priceLabel.setBounds(0, 110, 800/7*5-80, 120);
        priceLabel.setFont(new Font("Drid Herder Solid", Font.PLAIN, 35));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setVerticalAlignment(SwingConstants.CENTER);
        priceLabel.setForeground(Color.black);
        add(priceLabel);

        recieveButton = new JButton("GET");
        recieveButton.setBounds((800/7*5-160)/2, 250, 80, 40);
        recieveButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        recieveButton.setBackground(Color.white);
        recieveButton.setForeground(borderColor);
        recieveButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        recieveButton.setVerticalAlignment(SwingConstants.CENTER);
        recieveButton.setHorizontalAlignment(SwingConstants.CENTER);
        recieveButton.addMouseListener(buttonListener);
        add(recieveButton);
    } // WelfareFacilityPanel()

    public void set_price_info() {
        price = Main.gameBoard.place[12].get_price();
        price = price * 10000;

        priceLabel.setText(price + " won");
    }

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();
            if (object == recieveButton) {
                setVisible(false);
                phase.next();
            }
        } // mouseClicked()

        public void mousePressed(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        public void mouseEntered(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(borderColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        } // mouseEntered()

        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(borderColor);
        } // mouseExited()
    } // ButtonListener class
} // WelfareFacilityPanel class
