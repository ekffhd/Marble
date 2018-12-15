package UI;
//주석 완료
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//시작 화면
public class StartPanel extends JPanel {

    private Phase           phase;
    private JLabel          projectTitle;
    private Color           mainColor;
    public JButton          startButton;
    public HoveringListener hoveringListener;


    public StartPanel(Phase phase){
        //패널 설정
        mainColor = new Color(9,26,99);
        hoveringListener = new HoveringListener();
        setBounds(0,0,800, 750);
        setBackground(mainColor);
        setLayout(null);
        this.phase = phase;

        //제목 라벨
        projectTitle = new JLabel("<html><div style='text-align: center;'>파란구슬<BR>놀이</div></html>");
        projectTitle.setBounds(200,200, 400, 200);
        //projectTitle.setFont(new Font("RixVideoGame3D", Font.ITALIC, 100));
        projectTitle.setFont(new Font("Rix전자오락 3D", Font.ITALIC, 100));
        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setForeground(Color.white);
        add(projectTitle);

        //시작 버튼
        startButton = new JButton("S  T  A  R  T");
        startButton.setBounds(250,500, 300, 50);
        startButton.setBackground(mainColor);
        startButton.setBorder(BorderFactory.createLineBorder(Color.white, 3));
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Rix전자오락 3D", Font.ITALIC, 30));
        //startButton.setFont(new Font("RixVideoGame3D", Font.ITALIC, 30));
        startButton.addMouseListener(hoveringListener);

        add(startButton);

    }//startPanel();

    private class HoveringListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            JButton object = (JButton)event.getSource();

            if(object == startButton){ //시작 버튼을 누를 경우
                //start phase로 넘어간다.
                phase.start();
            }
        }
        public void mousePressed(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        //button hovering
        public void mouseEntered(MouseEvent event) {
            JButton object = (JButton)event.getSource();

            startButton.setBackground(Color.white);
            startButton.setOpaque(true);
            startButton.setForeground(mainColor);
        }
        public void mouseExited(MouseEvent event){
            startButton.setBackground(mainColor);
            startButton.setForeground(Color.white);
        }

    }
}
