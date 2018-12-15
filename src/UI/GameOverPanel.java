package UI;

import Player.Player;
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
    protected JLabel[]      rankingLabel;

    private ButtonListener buttonListener;

    public GameOverPanel(Phase phase){
        //게임 종료 패널
        backGroundColor = new Color(9,26,99);
        this.phase = phase;
        setBackground(backGroundColor);
        setBounds(0,0,800, 750);
        setLayout(null);
        buttonListener = new ButtonListener();

        //제목 라벨
        title = new JLabel("GAME OVER");
        title.setBounds(0, 100, 800, 100);
        //title.setFont(new Font("RIXVideoGame3D", Font.PLAIN, 100));
        title.setFont(new Font("RIX전자오락 3D", Font.PLAIN, 100));
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
        //replayBtn.setFont(new Font("RIXVideoGame3D", Font.PLAIN, 30));
        replayBtn.setFont(new Font("RIX전자오락 3D", Font.PLAIN, 30));
        replayBtn.setBounds(310, 580, 180, 60);
        replayBtn.setBackground(backGroundColor);
        replayBtn.setBorder(BorderFactory.createLineBorder(Color.white, 3));
        replayBtn.setForeground(Color.white);
        replayBtn.addMouseListener(buttonListener);
        add(replayBtn);

        //랭킹 배열 선언
        //rankingArray = new JButton[15];

        //랭킹 패널 grid 에 랭크 설정
        /*while(k<15) {
            for(int i = 0 ; i< 5; i++) {
                for(int j=0; j<3 ; j++) {
                    rankingArray[k] = new JButton(PlayerConstants.RANKING[i][j]);
                    rankingArray[k].setBackground(backGroundColor);
                    rankingArray[k].setOpaque(true);
                    rankingArray[k].setBorderPainted(false);
                    rankingArray[k].setForeground(Color.white);
                    rankingArray[k].setFont(new Font("RIXVideoGame3D",Font.PLAIN,10));
                    rankingPanel.add(rankingArray[k]);
                    k++;
                } // for j
            } // for i
        } // while k*/

        rankingLabel = new JLabel[15];

        rankingLabel[0] = new JLabel("순위");
        //rankingLabel[0].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[0].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[0].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[0].setForeground(Color.white);
        rankingPanel.add(rankingLabel[0]);

        rankingLabel[1] = new JLabel("플레이어");
        //rankingLabel[1].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[1].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[1].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[1].setForeground(Color.white);
        rankingPanel.add(rankingLabel[1]);

        rankingLabel[2] = new JLabel("잔금");
        //rankingLabel[2].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[2].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[2].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[2].setForeground(Color.white);
        rankingPanel.add(rankingLabel[2]);

    } // GameOverPanel()

    public void set_rank(Player[] player){

        //1등
        rankingLabel[3] = new JLabel("1");
        //rankingLabel[3].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[3].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[3].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[3].setForeground(Color.white);
        rankingPanel.add(rankingLabel[3]);

        rankingLabel[4] = new JLabel("PLAYER "+(player[3].get_player_id()+1));
        //rankingLabel[4].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[4].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[4].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[4].setForeground(Color.white);

        rankingPanel.add(rankingLabel[4]);

        rankingLabel[5] = new JLabel(player[3].get_cash() + " won");
        //rankingLabel[5].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[5].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[5].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[5].setForeground(Color.white);

        rankingPanel.add(rankingLabel[5]);

        //2등
        rankingLabel[6] = new JLabel("2");
        //rankingLabel[6].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[6].setFont(new Font("RIX전자오락3D",Font.PLAIN, 30));
        rankingLabel[6].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[6].setForeground(Color.white);

        rankingPanel.add(rankingLabel[6]);

        rankingLabel[7] = new JLabel("PLAYER "+(player[2].get_player_id()+1));
        //rankingLabel[7].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[7].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[7].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[7].setForeground(Color.white);

        rankingPanel.add(rankingLabel[7]);

        rankingLabel[8] = new JLabel("파산");
        //rankingLabel[8].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[8].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[8].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[8].setForeground(Color.white);

        rankingPanel.add(rankingLabel[8]);

        //3등
        rankingLabel[9] = new JLabel("3");
        //rankingLabel[9].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[9].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[9].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[9].setForeground(Color.white);

        rankingPanel.add(rankingLabel[9]);

        rankingLabel[10] = new JLabel("PLAYER "+(player[1].get_player_id()+1));
        //rankingLabel[10].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[10].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[10].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[10].setForeground(Color.white);

        rankingPanel.add(rankingLabel[10]);

        rankingLabel[11] = new JLabel("파산");
        //rankingLabel[11].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[11].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[11].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[11].setForeground(Color.white);

        rankingPanel.add(rankingLabel[11]);

        //4등
        rankingLabel[12] = new JLabel("4");
        //rankingLabel[12].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[12].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[12].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[12].setForeground(Color.white);

        rankingPanel.add(rankingLabel[12]);

        rankingLabel[13] = new JLabel("PLAYER "+(player[0].get_player_id()+1));
        //rankingLabel[13].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[13].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[13].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[13].setForeground(Color.white);

        rankingPanel.add(rankingLabel[13]);


        rankingLabel[14] = new JLabel("파산");
        //rankingLabel[14].setFont(new Font("RIXVideoGame3D",Font.PLAIN, 30));
        rankingLabel[14].setFont(new Font("RIX전자오락 3D",Font.PLAIN, 30));
        rankingLabel[14].setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel[14].setForeground(Color.white);

        rankingPanel.add(rankingLabel[14]);

    }

    private class ButtonListener implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if(obj == replayBtn){ // 재시작 버튼을 누를 경우
                // restart phase
                phase.start();
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
