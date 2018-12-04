package UI;

import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartCardPanel extends JPanel{

    private Color startColor;

    private JLabel nameLabel, cashLabel;
    private JButton getButton;
    private ButtonListener buttonListener;
    private Phase phase;

    public StartCardPanel(Phase phase){

        startColor = new Color(14, 46,64);
        this.phase = phase;
        setBackground(startColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setLayout(null);
        buttonListener = new ButtonListener();

        nameLabel = new JLabel("S  A  L  A  R  Y");
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        //nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.white);
        add(nameLabel);

        cashLabel = new JLabel("+ 200000 won");
        cashLabel.setBounds(0,120, 800/7*5-80, 50);
        cashLabel.setFont(new Font("drid herder solid", Font.PLAIN, 45));
        cashLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cashLabel.setVerticalAlignment(SwingConstants.CENTER);
        cashLabel.setForeground(Color.white);
        add(cashLabel);

        getButton = new JButton("GET");
        getButton.setBounds(184 , 210, 122, 50);
        getButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        getButton.setBackground(startColor);
        getButton.setOpaque(true);
        getButton.setForeground(Color.white);
        getButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        getButton.setVerticalAlignment(SwingConstants.CENTER);
        getButton.setHorizontalAlignment(SwingConstants.CENTER);
        getButton.addMouseListener(buttonListener);
        add(getButton);
    }//GoldCardPanel()


    private class ButtonListener implements MouseListener {

        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();

            if(object == getButton) {
                setVisible(false);
                phase.lap();

            }
        }
        public void mousePressed(MouseEvent event){ }
        public void mouseReleased(MouseEvent event){ }
        public void mouseEntered(MouseEvent event) {
            JButton object = (JButton)event.getSource();

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(startColor);
        }
        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(startColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
    }//ButtonListener class

}
