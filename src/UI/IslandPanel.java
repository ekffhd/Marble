package UI;
//주석 완료
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//지그재그 패널
public class IslandPanel extends JPanel {

    private Color           islandColor;

    private JLabel          nameLabel, textLabel1, textLabel2;
    private JButton         confirmButton;
    private ButtonListener  buttonListener;
    private Phase           phase;

    public IslandPanel(Phase phase){
        // 패널 설정
        islandColor = new Color(101,35,42);
        this.phase = phase;
        setBackground(islandColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setLayout(null);
        buttonListener = new ButtonListener();

        //카드 안내 라벨1
        textLabel1 = new JLabel("당신은 지그재그에 갇혔습니다.");
        textLabel1.setBounds(0,80, 800/7*5-80, 30);
        textLabel1.setFont(new Font("RixVideoGameB", Font.PLAIN, 25));
        //textLabel1.setFont(new Font("Rix전자오락 Bold", Font.PLAIN, 25));
        textLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel1.setVerticalAlignment(SwingConstants.CENTER);
        textLabel1.setForeground(Color.white);
        add(textLabel1);

        //카드 안내 라벨2
        textLabel2 = new JLabel("숙취로 인해 3턴 동안 움직이지 못합니다.");
        textLabel2.setBounds(0,120, 800/7*5-80, 30);
        textLabel2.setFont(new Font("RixVideoGameB", Font.PLAIN, 25));
        //textLabel2.setFont(new Font("Rix전자오락 Bold", Font.PLAIN, 25));
        textLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel2.setVerticalAlignment(SwingConstants.CENTER);
        textLabel2.setForeground(Color.white);
        add(textLabel2);

        //카드 제목 라벨
        nameLabel = new JLabel("Z I G --- Z A G");
        nameLabel.setBounds(0,0,800/7*5-80, 80);
        nameLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        //nameLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.white);
        add(nameLabel);

        //확인 버튼
        confirmButton = new JButton("CONFIRM");
        confirmButton.setBounds(184 , 210, 122, 50);
        confirmButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        confirmButton.setBackground(islandColor);
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
                //확인 버튼을 누를 경우 special phase로 넘어간다.
                setVisible(false);
                phase.gap();
                phase.special();
            }
        }
        public void mousePressed(MouseEvent event){ }
        public void mouseReleased(MouseEvent event){ }
        //button hovering
        public void mouseEntered(MouseEvent event) {
            JButton object = (JButton)event.getSource();

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(islandColor);
        }
        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(islandColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
    }//ButtonListener class

}
