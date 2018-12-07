package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelicopterPanel extends JPanel {
    private Color mainColor, borderColor;
    private Phase phase;
    private String inputStr;
    private int destination;

    private JLabel nameLabel, noticeLabel, confirmLabel;
    private JLabel errorLabel;
    private JButton chooseButton, goButton;
    private JTextField txtInput;

    private HoverListener hoverListener;
    private ButtonListener buttonListener;

    public HelicopterPanel(Phase phase) {
        mainColor = new Color(198, 141, 141);
        borderColor = new Color(63, 10, 10);
        hoverListener = new HoverListener();
        buttonListener = new ButtonListener();

        this.phase = phase;

        setBackground(mainColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(borderColor, 6));
        setLayout(null);

        nameLabel = new JLabel(PlaceConstants.PLACE_LINE_NAME[18]); // 헬기
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        //nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 50));
        nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(nameLabel);

        noticeLabel = new JLabel("목적지를 입력해주세요");
        noticeLabel.setBounds(0 , 80, 800/7*5-80, 30);
        //noticeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        noticeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noticeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(noticeLabel);

        txtInput = new JTextField();
        txtInput.setBounds((800/7*5-225)/2, 120, 80, 30);
        txtInput.setFont(new Font("Drid Herder Solid", Font.PLAIN, 20));
        txtInput.setHorizontalAlignment(SwingConstants.CENTER);
        txtInput.addActionListener(buttonListener);
        add(txtInput);

        chooseButton = new JButton("CHOOSE");
        chooseButton.setBounds((800/7*5-225)/2+85, 120, 60, 30);
        chooseButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        chooseButton.setBackground(Color.white);
        chooseButton.setForeground(borderColor);
        chooseButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        chooseButton.setVerticalAlignment(SwingConstants.CENTER);
        chooseButton.setHorizontalAlignment(SwingConstants.CENTER);
        chooseButton.addMouseListener(hoverListener);
        chooseButton.addActionListener(buttonListener);
        add(chooseButton);

        errorLabel = new JLabel("0~23사이의 숫자를 입력해주세요!!");
        errorLabel.setBounds(0, 150, 800/7*5-80, 30);
        //errorLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        errorLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
        errorLabel.setForeground(Color.red);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(errorLabel);

        confirmLabel = new JLabel(); // ~ 이동 합니다
        confirmLabel.setBounds(0 , 120, 800/7*5-80, 60);
        //confirmLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 60));
        confirmLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(confirmLabel);

        goButton = new JButton("GO");
        goButton.setBounds((800/7*5-160)/2, 250, 80, 40);
        goButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        goButton.setBackground(Color.white);
        goButton.setForeground(borderColor);
        goButton.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        goButton.setVerticalAlignment(SwingConstants.CENTER);
        goButton.setHorizontalAlignment(SwingConstants.CENTER);
        goButton.addMouseListener(hoverListener);
        goButton.addActionListener(buttonListener);
        add(goButton);
    }

    public void reset_helicopter_panel() {
        noticeLabel.setVisible(true);
        txtInput.setVisible(true);
        chooseButton.setVisible(true);
        errorLabel.setVisible(false);
        confirmLabel.setVisible(false);
        goButton.setVisible(false);
    }

    public int get_destination() { return destination; }

    public static boolean is_number (String str) {
        boolean result = true;
        if (str.equals("")) {
            result = false;
        } else {
            for (int i=0; i<str.length(); i++) {
                char c = str.charAt(i);
                if (c<49 || c>59) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private class HoverListener implements MouseListener {
        public void mouseClicked(MouseEvent event){}
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
    } // HoverListener class

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == txtInput || object == chooseButton) {
                inputStr = txtInput.getText();
                if (is_number(inputStr)) { // 숫자 일때
                    destination = Integer.parseInt(inputStr);
                    if (destination < 0 || destination > 23) {
                        errorLabel.setVisible(true);
                    } else {
                        confirmLabel.setText("목적지: "+PlaceConstants.PLACE_LINE_NAME[destination]);
                        txtInput.setText("");
                        noticeLabel.setVisible(false);
                        txtInput.setVisible(false);
                        chooseButton.setVisible(false);
                        errorLabel.setVisible(false);
                        confirmLabel.setVisible(true);
                        goButton.setVisible(true);
                    }
                } else { // 숫자가 아닐 때
                    errorLabel.setVisible(true);
                } // if ~ else
            } else if (object == goButton) {
                setVisible(false);
                Main.gameBoard.hide_city_number();
                phase.special();
            }
        }
    }
}
