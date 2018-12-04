package UI;

import Util.Dice;
import Util.DiceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControllerPanel extends JPanel {
    private JLabel                  projectName;
    protected JButton                 rollButton, moveButton;
    private Color                   mainColor;
    private ButtonListener          buttonListener;
    private Phase                   phase;
    private ImageIcon               dice1Image, dice2Image;
    private JLabel                  dice1Label, dice2Label;

    public GameControllerPanel(Phase phase){
        setBackground(Color.white);
        setLayout(null);
        buttonListener = new ButtonListener();
        mainColor = new Color(52, 81, 138);
        this.phase = phase;

        dice1Image = new ImageIcon();
        dice2Image = new ImageIcon();

        dice1Label = new JLabel();
        dice1Label.setBounds(210, 50, 60, 60);
        add(dice1Label);

        dice2Label = new JLabel();
        dice2Label.setBounds(300, 50, 60, 60);
        add(dice2Label);



        projectName = new JLabel("<html><div style='text-align: center;'>파란구슬<BR>놀이</div></html>");
        projectName.setBounds(175,100,230,200);
        projectName.setVerticalAlignment(SwingConstants.CENTER);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        projectName.setFont(new Font("RixVideoGame3D", Font.ITALIC, 70));
        projectName.setForeground(mainColor);
        add(projectName);

        rollButton = new JButton("ROLL");
        rollButton.setBounds(228, 300, 114, 50);
        rollButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        rollButton.setBackground(Color.white);
        rollButton.setForeground(mainColor);
        rollButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        rollButton.setVerticalAlignment(SwingConstants.CENTER);
        rollButton.setHorizontalAlignment(SwingConstants.CENTER);
        rollButton.addMouseListener(buttonListener);
        add(rollButton);

        moveButton = new JButton("MOVE");
        moveButton.setBounds(228, 300, 114, 50);
        moveButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        moveButton.setBackground(Color.white);
        moveButton.setForeground(mainColor);
        moveButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        moveButton.setVerticalAlignment(SwingConstants.CENTER);
        moveButton.setHorizontalAlignment(SwingConstants.CENTER);
        moveButton.addMouseListener(buttonListener);
        moveButton.setVisible(false);
        add(moveButton);

    }//GameControllerPanel class

    public void show_dice(int dice1, int dice2){
        dice1Image = new ImageIcon(DiceConstants.DICE_IMAGE[dice1-1]);
        dice1Label.setIcon(dice1Image);
        dice2Image = new ImageIcon(DiceConstants.DICE_IMAGE[dice2-1]);
        dice2Label.setIcon(dice2Image);
    }

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();

            if(object == rollButton){
                phase.roll();
                rollButton.setVisible(false);
                moveButton.setVisible(true);
            }
            if(object == moveButton){
                phase.move();
                moveButton.setVisible(false);

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
}
