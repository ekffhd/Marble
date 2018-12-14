package UI;
//주석 완료

import Property.Place;
import Util.DiceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControllerPanel extends JPanel {
    private JLabel                      projectName;

    //주사위
    private JLabel                      dice1Label, dice2Label;
    private ImageIcon                   dice1Image, dice2Image, eggImage;

    //버튼
    protected JButton                   rollButton, doubleButton, moveButton, purchaseButton, eggButton, payButton, takeOverButton;
    private Color                       mainColor;
    protected PurchasePanel             purchasePanel;              //구매 패널
    public TollPanel                    tollPanel;                  //통행료 지불 패널
    protected GoldCardPanel             goldCardPanel;              //황금카드 패널
    protected IslandPanel               islandPanel;                //지그재그 패널
    protected EscapeSuccessIslandPanel  escapeSuccessIslandPanel;   //지그재그 탈출 성공 패널
    protected EscapeFailIslandPanel     escapeFailIslandPanel;      //지그재그 탈출 실패 패널
    protected WelfareFacilityPanel      welfareFacilityPanel;       //광개토 ATM 패널
    protected HelicopterPanel           helicopterPanel;            //헬리콥터 패널
    protected StartCardPanel            startCardPanel;             //월급 패널
    protected TakeOverPanel             takeOverPanel;              //인수 패널
    private ButtonListener              buttonListener;
    private Phase                       phase;
    protected Place[]                   place;

    public GameControllerPanel(Phase phase, Place place[]){
        //패널 설정
        setBackground(Color.white);
        setLayout(null);
        buttonListener = new ButtonListener();
        mainColor = new Color(52, 81, 138);
        this.phase = phase;
        this.place = place;

        //월급 패널
        startCardPanel = new StartCardPanel(phase);
        startCardPanel.setVisible(false);
        add(startCardPanel);

        //구입 패널
        purchasePanel = new PurchasePanel(phase);
        purchasePanel.setVisible(false);
        add(purchasePanel);

        //통행료 지불 패
        tollPanel = new TollPanel(phase);
        tollPanel.setVisible(false);
        add(tollPanel);

        //황금 카드 패널
        goldCardPanel = new GoldCardPanel(phase);
        goldCardPanel.setVisible(false);
        add(goldCardPanel);

        //지그재그 도착 패널
        islandPanel = new IslandPanel(phase);
        islandPanel.setVisible(false);
        add(islandPanel);

        //탈출 성공 패널
        escapeSuccessIslandPanel = new EscapeSuccessIslandPanel(this);
        escapeSuccessIslandPanel.setVisible(false);
        add(escapeSuccessIslandPanel);

        //탈출 실패 패널
        escapeFailIslandPanel = new EscapeFailIslandPanel(phase);
        escapeFailIslandPanel.setVisible(false);
        add(escapeFailIslandPanel);

        //광개토 ATM 패널
        welfareFacilityPanel = new WelfareFacilityPanel(phase);
        welfareFacilityPanel.setVisible(false);
        add(welfareFacilityPanel);

        //헬리콥터 패널
        helicopterPanel = new HelicopterPanel(phase);
        helicopterPanel.setVisible(false);
        add(helicopterPanel);

        //인수 패널
        takeOverPanel = new TakeOverPanel(phase);
        takeOverPanel.setVisible(false);
        add(takeOverPanel);

        //주사위 이미지
        dice1Image = new ImageIcon();
        dice2Image = new ImageIcon();

        dice1Label = new JLabel();
        dice1Label.setBounds(210, 50, 60, 60);
        dice1Label.setVisible(false);
        add(dice1Label);

        dice2Label = new JLabel();
        dice2Label.setBounds(300, 50, 60, 60);
        dice2Label.setVisible(false);
        add(dice2Label);

        //황금알 이미지
        eggImage = new ImageIcon("./image/egg.gif");
        eggButton = new JButton(eggImage);
        eggButton.setBounds(135,0,300,300);
        eggButton.setBackground(null);
        eggButton.setBorder(null);
        eggButton.setOpaque(true);
        eggButton.setVisible(false);
        eggButton.addMouseListener(buttonListener);
        add(eggButton);

        //프로젝트 이름
        projectName = new JLabel("<html><div style='text-align: center;'>파란구슬<BR>놀이</div></html>");
        projectName.setBounds(175,100,230,200);
        projectName.setVerticalAlignment(SwingConstants.CENTER);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        projectName.setFont(new Font("RixVideoGame3D", Font.ITALIC, 70));
        //projectName.setFont(new Font("Rix전자오락 3D", Font.ITALIC, 70));
        projectName.setForeground(mainColor);
        add(projectName);

        //주사위 굴리기 버튼
        rollButton = new JButton("ROLL");
        rollButton.setBounds(228, 300, 114, 50);
        rollButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        rollButton.setBackground(Color.white);
        rollButton.setForeground(mainColor);
        rollButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        rollButton.setVerticalAlignment(SwingConstants.CENTER);
        rollButton.setHorizontalAlignment(SwingConstants.CENTER);
        rollButton.addMouseListener(buttonListener);
        add(rollButton);

        //주사위 다시 굴리기 버튼
        doubleButton = new JButton("AGAIN");
        doubleButton.setBounds(228, 300, 114, 50);
        doubleButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        doubleButton.setBackground(Color.white);
        doubleButton.setForeground(mainColor);
        doubleButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        doubleButton.setVerticalAlignment(SwingConstants.CENTER);
        doubleButton.setHorizontalAlignment(SwingConstants.CENTER);
        doubleButton.addMouseListener(buttonListener);
        doubleButton.setVisible(false);
        add(doubleButton);

        //움직이기 버튼
        moveButton = new JButton("MOVE");
        moveButton.setBounds(228, 300, 114, 50);
        moveButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        moveButton.setBackground(Color.white);
        moveButton.setForeground(mainColor);
        moveButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        moveButton.setVerticalAlignment(SwingConstants.CENTER);
        moveButton.setHorizontalAlignment(SwingConstants.CENTER);
        moveButton.addMouseListener(buttonListener);
        moveButton.setVisible(false);
        add(moveButton);

        //구매 버튼
        purchaseButton = new JButton("PURCHASE");
        purchaseButton.setBounds(228, 300, 114, 50);
        purchaseButton.setFont(new Font("drid herder solid", Font.PLAIN, 17));
        purchaseButton.setBackground(Color.white);
        purchaseButton.setForeground(mainColor);
        purchaseButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        purchaseButton.setVerticalAlignment(SwingConstants.CENTER);
        purchaseButton.setHorizontalAlignment(SwingConstants.CENTER);
        purchaseButton.addMouseListener(buttonListener);
        purchaseButton.setVisible(false);
        add(purchaseButton);

        //통행료 지불 버튼
        payButton = new JButton("PAY");
        payButton.setBounds(228, 300, 114, 50);
        payButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        payButton.setBackground(Color.white);
        payButton.setForeground(mainColor);
        payButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        payButton.setVerticalAlignment(SwingConstants.CENTER);
        payButton.setHorizontalAlignment(SwingConstants.CENTER);
        payButton.addMouseListener(buttonListener);
        payButton.setVisible(false);
        add(payButton);

        //인수 버튼
        takeOverButton = new JButton("TAKE");
        takeOverButton.setBounds(228, 300, 114, 50);
        takeOverButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        takeOverButton.setBackground(Color.white);
        takeOverButton.setForeground(mainColor);
        takeOverButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        takeOverButton.setVerticalAlignment(SwingConstants.CENTER);
        takeOverButton.setHorizontalAlignment(SwingConstants.CENTER);
        takeOverButton.addMouseListener(buttonListener);
        takeOverButton.setVisible(false);
        add(takeOverButton);
    }//GameControllerPanel class

    //주사위 값 설정
    public void show_dice(int dice1, int dice2){
        dice1Image = new ImageIcon(DiceConstants.DICE_IMAGE[dice1-1]);
        dice1Label.setIcon(dice1Image);
        dice2Image = new ImageIcon(DiceConstants.DICE_IMAGE[dice2-1]);
        dice2Label.setIcon(dice2Image);
    }

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();

            if(object == rollButton || object == doubleButton){ //주사위 굴리기 버튼을 누를 경우
                //roll phase로 넘어간다.
                phase.roll();
                dice1Label.setVisible(true);
                dice2Label.setVisible(true);
                doubleButton.setVisible(false);
            }
            else if(object == moveButton){         //움직이기 버튼을 누를 경우
                //move phase로 넘어간다.
                phase.move();
                moveButton.setVisible(false);
                dice1Label.setVisible(false);
                dice2Label.setVisible(false);
            }
            else if(object == purchaseButton){     //구매 버튼을 누를 경우
                //부지 구매 패널을 나타낸다.
                purchaseButton.setVisible(false);
                purchasePanel.set_purchase_panel_info(place[Main.nextPosition]);
                purchasePanel.setVisible(true);
            }
            else if(object == payButton) {         //통행료 지불 버튼을 누를 경우
                //통행료 지불 패널을 나타낸다.
                payButton.setVisible(false);
                tollPanel.set_toll_panel_info(place[Main.nextPosition]);
                tollPanel.setVisible(true);
            }
            else if(object == eggButton){          //황금알 버튼을 누를 경우
                //황금 카드 패널 정보를 설정한 후, 황금 카드 패널을 나타낸다.
                eggButton.setVisible(false);
                goldCardPanel.set_card_panel_info();
                goldCardPanel.setVisible(true);
            }
            else if(object == takeOverButton) {    //인수 버튼을 누를 경우
                //인수 패널 정보를 설정한 후, 인수 패널을 나타낸다.
                takeOverButton.setVisible(false);
                takeOverPanel.set_take_over_panel_info(place[Main.nextPosition]);
                takeOverPanel.setVisible(true);
            }
        }//mouseClicked
        public void mousePressed(MouseEvent event){}
        public void mouseReleased(MouseEvent event){}
        //button hovering
        public void mouseEntered(MouseEvent event) {
            JButton object = (JButton)event.getSource();

            if(object != eggButton){
                object.setBackground(mainColor);
                object.setOpaque(true);
                object.setForeground(Color.white);
            }
        } // mouseEntered ()

        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            if(object != eggButton){
                object.setBackground(Color.white);
                object.setOpaque(true);
                object.setForeground(mainColor);
            }
        }//mouseExited()
    }//ButtonListener class
} // GameControllerPanel class
