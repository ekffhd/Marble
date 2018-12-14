package UI;
//주석 완료
import Player.PlayerConstants;
import Util.Phase;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//게임 종료 패널
public class GameOverPanel extends JPanel {

    private JLabel 	        title;
    private Color  	        backGroundColor;
    private JPanel	        rankingPanel;
    protected JButton[]     rankingArray;
    private JButton	        replayBtn;
    private int		        k=0;
    private Phase           phase;

    private ButtonListener buttonListener;

    public GameOverPanel(Phase phase){
        //게임 종료 패널
        backGroundColor = new Color(9,26,99);
        this.phase = phase;
        setBackground(backGroundColor);
        setLayout(null);
        buttonListener = new ButtonListener();

        //제목 라벨
        title = new JLabel("GAME OVER");
        title.setBounds(0, 100, 800, 100);
        title.setFont(new Font("RIXVideoGame3D", Font.PLAIN, 100));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        //랭킹 패널
        rankingPanel = new JPanel();
        rankingPanel.setBounds(50, 250, 700, 260);
        rankingPanel.setBackground(backGroundColor);
        rankingPanel.setForeground(Color.white);
        rankingPanel.setBorder(BorderFactory.createLineBorder(Color.white, 6));
        rankingPanel.setLayout(new GridLayout(5,3));
        add(rankingPanel);

        //재시작 버튼
        replayBtn = new JButton("REPLAY");
        replayBtn.setFont(new Font("RIXVideoGame3D", Font.PLAIN, 30));
        replayBtn.setBounds(310, 580, 180, 60);
        replayBtn.setBackground(backGroundColor);
        replayBtn.setBorder(BorderFactory.createLineBorder(Color.white, 3));
        replayBtn.setForeground(Color.white);
        replayBtn.addMouseListener(buttonListener);
        add(replayBtn);

        //랭킹 배열 선언
        rankingArray = new JButton[15];

        //랭킹 패널 grid 에 랭크 설정
        while(k<15) {
            for(int i = 0 ; i< 5; i++) {
                for(int j=0; j<3 ; j++) {
                    rankingArray[k] = new JButton(PlayerConstants.RANKING[i][j]);
                    rankingArray[k].setBackground(backGroundColor);
                    rankingArray[k].setOpaque(true);
                    rankingArray[k].setBorderPainted(false);
                    rankingArray[k].setForeground(Color.white);
                    rankingArray[k].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
                    rankingPanel.add(rankingArray[k]);
                    k++;
                } // for j
            } // for i
        } // while k
    } // GameOverPanel()

    private class ButtonListener implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if(obj == replayBtn){ // 재시작 버튼을 누를 경우
                // restart phase
                phase.restart();
            }
        }
        //button hovering
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
} // GameOverPanel class
