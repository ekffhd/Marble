package UI;

import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControllerPanel extends JPanel {
    private JLabel                  projectName;
    private JButton                 rollButton;
    private Color                   mainColor;
    private ButtonListener          buttonListener;
    private Phase phase;
    public GameControllerPanel(Phase phase){
        setBackground(Color.white);
        setLayout(null);
        buttonListener = new ButtonListener();
        mainColor = new Color(52, 81, 138);
        this.phase = phase;

        projectName = new JLabel("<html><div style='text-align: center;'>파란구슬<BR>놀이</div></html>");
        projectName.setBounds(165,100,220,200);
        projectName.setVerticalAlignment(SwingConstants.CENTER);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        projectName.setFont(new Font("RixVideoGame3D", Font.ITALIC, 50));
        projectName.setForeground(mainColor);
        add(projectName);

        rollButton = new JButton("ROLL");
        rollButton.setBounds(220, 300, 100, 50);
        rollButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        rollButton.setBackground(Color.white);
        rollButton.setForeground(mainColor);
        rollButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        rollButton.setVerticalAlignment(SwingConstants.CENTER);
        rollButton.setHorizontalAlignment(SwingConstants.CENTER);
        rollButton.addMouseListener(buttonListener);
        add(rollButton);
    }//GameControllerPanel class

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){phase.roll();}
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

    }
}
