package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//헬리콥터 패널
public class HelicopterPanel extends JPanel {
    private Color               mainColor, borderColor;
    private Phase               phase;
    private String              inputStr;
    private int                 destination;

    private JLabel              nameLabel, noticeLabel, confirmLabel;
    private JLabel              errorLabel;
    private JButton             chooseButton, goButton;
    private JTextField          txtInput;

    private HoverListener       hoverListener;
    private ButtonListener      buttonListener;

    public HelicopterPanel(Phase phase) {
        //패널 설정
        mainColor = new Color(198, 141, 141);
        borderColor = new Color(63, 10, 10);
        hoverListener = new HoverListener();
        buttonListener = new ButtonListener();
        this.phase = phase;
        setBackground(mainColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(borderColor, 6));
        setLayout(null);

        //헬리콥터 패널 이름 라벨
        nameLabel = new JLabel(PlaceConstants.PLACE_LINE_NAME[18]);
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 50));
        //nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(nameLabel);

        //안내 메세지 라벨
        noticeLabel = new JLabel("목적지를 입력해주세요");
        noticeLabel.setBounds(0 , 80, 800/7*5-80, 30);
        noticeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //noticeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        noticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        noticeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(noticeLabel);

        //목적지 입력 인풋
        txtInput = new JTextField();
        txtInput.setBounds((800/7*5-225)/2, 120, 80, 30);
        txtInput.setFont(new Font("Drid Herder Solid", Font.PLAIN, 20));
        txtInput.setHorizontalAlignment(SwingConstants.CENTER);
        txtInput.addActionListener(buttonListener);
        add(txtInput);

        //목적지 설정 버튼
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

        //에러 메세지 라벨
        errorLabel = new JLabel("0~23사이의 숫자를 입력해주세요!!");
        errorLabel.setBounds(0, 150, 800/7*5-80, 30);
        errorLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 20));
        //errorLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 20));
        errorLabel.setForeground(Color.red);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(errorLabel);

        //확인 안내 메세지 라벨
        confirmLabel = new JLabel(); // ~ 이동 합니다
        confirmLabel.setBounds(0 , 120, 800/7*5-80, 60);
        confirmLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 60));
        //confirmLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(confirmLabel);

        //이동 버튼
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

    //헬리콥터 패널 내용 초기화
    public void reset_helicopter_panel() {
        noticeLabel.setVisible(true);
        txtInput.setVisible(true);
        chooseButton.setVisible(true);
        errorLabel.setVisible(false);
        confirmLabel.setVisible(false);
        goButton.setVisible(false);
    }

    //목적지 반환
    public int get_destination() { return destination; }

    //목적지가 알맞은 숫자인지 확인
    public static boolean is_number (String str) {
        boolean result = true;
        if (str.equals("")) {       //아무 값도 입력하지 않았을 경우
            result = false;
        }
        else {
            for (int i=0; i<str.length(); i++) {
                char c = str.charAt(i);
                if (c<48 || c>57) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }



    //button hovering
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

            if (object == txtInput || object == chooseButton) { //목적지를 설정했을 경우
                inputStr = txtInput.getText();

                if (is_number(inputStr)) { //목적지가 숫자일 경우
                    //숫자로 변환한 뒤 유효범위 ( 0~23)인지 아닌지 검사한다.
                    destination = Integer.parseInt(inputStr);
                    if (destination < 0 || destination > 23) {
                        //유효범위가 아니라면 에러 메세지를 띄운다.
                        errorLabel.setVisible(true);
                    } else {
                        //유효범위라면 확인 메세지 라벨과 이동 버튼을 띄운다.
                        confirmLabel.setText("목적지: "+PlaceConstants.PLACE_LINE_NAME[destination]);
                        txtInput.setText("");
                        noticeLabel.setVisible(false);
                        txtInput.setVisible(false);
                        chooseButton.setVisible(false);
                        errorLabel.setVisible(false);
                        confirmLabel.setVisible(true);
                        goButton.setVisible(true);
                    }
                } else { // 숫자가 아닐 경우
                    //에러 메세지를 띄운다.
                    errorLabel.setVisible(true);
                } // if ~ else
            } else if (object == goButton) { //이동 버튼을 눌렀을 경우
                //지역 번호를 숨긴 후 special phase 로 넘어간다.
                setVisible(false);
                Main.gameBoard.hide_city_number();
                phase.special();
                phase.gap();
            }
        }
    }//ButtonListener class
}
