package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelicopterPanel extends JPanel {
    private Color mainColor, borderColor;
    private Phase phase;
    private Point destination;

    private JLabel nameLabel, noticeLabel, confirmLabel;
    private JButton chooseButton, goButton;

    private ButtonListener buttonListener;

    public HelicopterPanel(Phase phase) {
        mainColor = new Color(198, 141, 141);
        borderColor = new Color(63, 10, 10);
        destination = new Point();
        buttonListener = new ButtonListener();

        setBackground(mainColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(borderColor, 6));
        setLayout(null);

        nameLabel = new JLabel(PlaceConstants.PLACE_LINE_NAME[18]); // 헬기
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 50));
        //nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(nameLabel);

        noticeLabel = new JLabel("목적지를 선택해주세요");
        noticeLabel.setBounds(0 , 80, 800/7*5-80, 30);
        noticeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //noticeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noticeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(noticeLabel);

        confirmLabel = new JLabel("이동하겠습니다");
        confirmLabel.setBounds(0 , 80, 800/7*5-80, 30);
        confirmLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //confirmLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmLabel.setVerticalAlignment(SwingConstants.CENTER);
        confirmLabel.setVisible(false);
        add(confirmLabel);

        chooseButton = new JButton("CHOOSE");
        chooseButton.setBounds((800/7*5-160)/2, 250, 80, 40);
        chooseButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        chooseButton.setBackground(Color.white);
        chooseButton.setForeground(borderColor);
        chooseButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        chooseButton.setVerticalAlignment(SwingConstants.CENTER);
        chooseButton.setHorizontalAlignment(SwingConstants.CENTER);
        //chooseButton.addMouseListener(buttonListener);
        add(chooseButton);

        goButton = new JButton("GO");
        goButton.setBounds((800/7*5-160)/2, 250, 80, 40);
        goButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        goButton.setBackground(Color.white);
        goButton.setForeground(borderColor);
        goButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        goButton.setVerticalAlignment(SwingConstants.CENTER);
        goButton.setHorizontalAlignment(SwingConstants.CENTER);
        goButton.addMouseListener(buttonListener);
        goButton.setVisible(false);
        add(goButton);
    }

    /*
    public void get_destination() {
        while(destination.x == 0 && destination.y == 0) {
            destination = Main.gameBoard.savept;
        }
        System.out.println("x: "+destination.x+"  y: "+destination.y);
    }*/

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();
            if (object == chooseButton) {
                destination = new Point();
                Main.gameBoard.reset_pt();
                while(destination.x == 0 && destination.y == 0) {
                    destination = Main.gameBoard.savept;
                }
                System.out.println("x: "+destination.x+"  y: "+destination.y);
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
}
