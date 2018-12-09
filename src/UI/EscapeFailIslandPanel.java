package UI;

import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EscapeFailIslandPanel extends JPanel {

    private JLabel messageLbl, imgLbl ;
    private JButton confirmBtn;
    private ImageIcon imgIcon;
    private Phase phase;

    private ButtonListener buttonListener;

    public EscapeFailIslandPanel(Phase phase){
        setBackground(Color.white);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(Color.black, 6));
        setLayout(null);

        this.phase = phase;
        buttonListener = new ButtonListener();

        imgIcon = new ImageIcon("./image/warning.png");
        imgLbl = new JLabel("", imgIcon, SwingConstants.CENTER);
        imgLbl.setBounds(0,30,800/7*5-80,80);
        imgLbl.setVerticalAlignment(SwingConstants.CENTER);
        imgLbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(imgLbl);

        messageLbl = new JLabel();
        messageLbl.setText("탈출에 실패했습니다.");
        messageLbl.setFont(new Font("RIXVideoGame3D", Font.PLAIN, 30));
        messageLbl.setBounds(0,100,800/7*5-80,100);
        messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
        messageLbl.setVerticalAlignment(SwingConstants.CENTER);
        add(messageLbl);

        confirmBtn = new JButton("CONFIRM");
        confirmBtn.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        confirmBtn.setBackground(Color.white);
        confirmBtn.setForeground(new Color(52, 81, 138));
        confirmBtn.setBorder(BorderFactory.createLineBorder(new Color(52, 81, 138), 2));
        confirmBtn.setBounds(184 , 210, 122, 50);
        confirmBtn.setHorizontalAlignment(SwingConstants.CENTER);
        confirmBtn.setVerticalAlignment(SwingConstants.CENTER);
        confirmBtn.addMouseListener(buttonListener);
        add(confirmBtn);

    } // EscapeFailIslandPanel()

    private class ButtonListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if(obj == confirmBtn){
                setVisible(false);
            }
        }
        public void mouseEntered(MouseEvent e) {
            JButton object = (JButton)e.getSource();
            object.setBackground(new Color(52, 81, 138));
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
        public void mouseExited(MouseEvent e) {
            JButton object = (JButton)e.getSource();
            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(new Color(52, 81, 138));
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    } // ButtonListener class

} // EscapeFailIslandPanel class
