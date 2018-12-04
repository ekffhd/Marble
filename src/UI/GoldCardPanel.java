package UI;

import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GoldCardPanel extends JPanel {

    private JLabel nameLabel;
    private JButton confirmButton;
    private Color goldColor;
    private ButtonListener buttonListener;
    private Phase phase;

    public GoldCardPanel(Phase phase){
        goldColor = new Color(248, 206, 88);
        this.phase = phase;

        setBackground(goldColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(new Color(248, 206, 88), 10));

        buttonListener = new ButtonListener();

        nameLabel = new JLabel("G O L D    C A R D");
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        //nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.white);
        add(nameLabel);

        confirmButton = new JButton("CONFIRM");
        confirmButton.setBounds(184 , 232, 122, 50);
        confirmButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        confirmButton.setBackground(goldColor);
        confirmButton.setOpaque(true);
        confirmButton.setForeground(Color.white);
        confirmButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        confirmButton.setVerticalAlignment(SwingConstants.CENTER);
        confirmButton.setHorizontalAlignment(SwingConstants.CENTER);
        confirmButton.addMouseListener(buttonListener);
        add(confirmButton);
    }//GoldCardPanel()


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

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(goldColor);
        }
        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(goldColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
    }//ButtonListener class

}
