import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartPanel extends JPanel {

    private JLabel projectTitle;
    private Color mainColor;
    public JButton startButton;
    public HoveringListener hoveringListener;



    public StartPanel(){
        mainColor = new Color(9,26,99);
        hoveringListener = new HoveringListener();

        setBounds(0,0,800, 750);
        setBackground(mainColor);
        setLayout(null);




        projectTitle = new JLabel("<html><div style='text-align: center;'>파란구슬<BR>놀이</div></html>");
        projectTitle.setBounds(200,200, 400, 200);
        projectTitle.setFont(new Font("RixVideoGame3D", Font.ITALIC, 100));
        projectTitle.setHorizontalAlignment(SwingConstants.CENTER);
        projectTitle.setForeground(Color.white);
        add(projectTitle);

        startButton = new JButton("S  T  A  R  T");
        startButton.setBounds(250,500, 300, 50);
        startButton.setBackground(mainColor);
        startButton.setBorder(BorderFactory.createLineBorder(Color.white, 3));
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("RixVideoGame3D", Font.ITALIC, 30));
        startButton.addMouseListener(hoveringListener);

        add(startButton);

    }//startPanel();

    /*
    private class HoveringListener implements MouseListener {
        public void mouseClicked(MouseEvent event){}
        public void mousePressed(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        public void mouseEntered(MouseEvent event){
            JButton obj = (JButton)event.getSource();

            obj.setBackground(DrawConstants.MENU_ENTER[0]);
            obj.setForeground(DrawConstants.MENU_ENTER[1]);
        }
        public void mouseExited(MouseEvent event){
            JButton obj = (JButton)event.getSource();

            obj.setBackground(DrawConstants.MENU_EXIT[0]);
            obj.setForeground(DrawConstants.MENU_EXIT[1]);
        }
    }
     */

    private class HoveringListener implements MouseListener {
        public void mouseClicked(MouseEvent event){}
        public void mousePressed(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
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
