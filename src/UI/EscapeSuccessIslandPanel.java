package UI;
//주석 완료
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//지그재그 탈출 성공 패널 (주사위 더블이 나왔을 경우)
public class EscapeSuccessIslandPanel extends JPanel {

    private JLabel              messageLbl, imgLbl ;
    private JButton             confirmBtn;
    private ImageIcon           imgIcon;
    private GameControllerPanel gameControllerPanel;

    private ButtonListener buttonListener;

    public EscapeSuccessIslandPanel(GameControllerPanel gameControllerPanel){

        //패널 설정
        setBackground(Color.white);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(Color.black, 6));
        setLayout(null);
        this.gameControllerPanel = gameControllerPanel;
        buttonListener = new ButtonListener();

        //탈출 성공 아이콘
        imgIcon = new ImageIcon("./image/congrats.png");
        imgLbl = new JLabel("", imgIcon, SwingConstants.CENTER);
        imgLbl.setBounds(0,30,800/7*5-80,80);
        imgLbl.setVerticalAlignment(SwingConstants.CENTER);
        imgLbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(imgLbl);

        //탈출 성공 메세지
        messageLbl = new JLabel();
        messageLbl.setText("<html><div style='text-align: center;'>축하합니다!<BR>지그재그 탈출에 성공하셨습니다!</div></html>");
        //messageLbl.setFont(new Font("RIXVideoGame3D", Font.PLAIN, 30));
        messageLbl.setFont(new Font("RIX전자오락 3D", Font.PLAIN, 30));
        messageLbl.setBounds(0,100,800/7*5-80,100);
        messageLbl.setHorizontalAlignment(SwingConstants.CENTER);
        messageLbl.setVerticalAlignment(SwingConstants.CENTER);
        add(messageLbl);

        //확인 버튼
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

    } // EscapeSuccessIslandPanel()

    private class ButtonListener implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            //확인 버튼을 누를 경우, 현재 패널은 사라지고 move 버튼이 나타난다.
            Object obj = e.getSource();
            if(obj == confirmBtn){
                setVisible(false);
                gameControllerPanel.moveButton.setVisible(true);
            }
        }
        //확인 버튼 hovering
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

} // EscapeSuccessIslandPanel class
