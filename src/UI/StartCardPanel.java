package UI;
//주석 완료
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartCardPanel extends JPanel{

    private Color           startColor;

    private JLabel          nameLabel, cashLabel;
    private JButton         getButton;
    private ButtonListener  buttonListener;
    private Phase           phase;

    //월급 패널
    public StartCardPanel(Phase phase){
        //패널 설정
        startColor = new Color(14, 46,64);
        this.phase = phase;
        setBackground(startColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setLayout(null);
        buttonListener = new ButtonListener();

        //제목 라벨
        nameLabel = new JLabel("S  A  L  A  R  Y");
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        //nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.white);
        add(nameLabel);

        //월급 라벨
        cashLabel = new JLabel("+ 200000 won");
        cashLabel.setBounds(0,120, 800/7*5-80, 50);
        cashLabel.setFont(new Font("drid herder solid", Font.PLAIN, 45));
        cashLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cashLabel.setVerticalAlignment(SwingConstants.CENTER);
        cashLabel.setForeground(Color.white);
        add(cashLabel);

        //확인 버튼
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

            if(object == getButton) { //확인 버튼을 클릭할 경우
                setVisible(false);
                if(Main.nextPosition == 0) { //만약 월급지점에 도착했다면
                    //다음 턴으로 넘어간다.
                    phase.next();
                }
                else{ //월급지점을 지나쳤다면
                    //플레이어가 밟은 place 에 해당하는 패널을 띄운다.
                    phase.show_panel();
                }
            }
        }
        public void mousePressed(MouseEvent event){ }
        public void mouseReleased(MouseEvent event){ }
        //button hovering
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
