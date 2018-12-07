package UI;

import Property.Place;
import Util.Dice;
import Util.DiceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControllerPanel extends JPanel {
    private JLabel                  projectName;
    private JLabel                  dice1Label, dice2Label;
    private ImageIcon               dice1Image, dice2Image, eggImage;
    protected JButton               rollButton, moveButton, purchaseButton, eggButton, payButton;
    private Color                   mainColor;
    protected PurchasePanel         purchasePanel;
    public TollPanel                tollPanel;
    protected GoldCardPanel         goldCardPanel;
    protected IslandPanel           islandPanel;
    protected WelfareFacilityPanel  welfareFacilityPanel;
    protected HelicopterPanel       helicopterPanel;
    private StartCardPanel          startCardPanel;
    private ButtonListener          buttonListener;
    private Phase                   phase;
    protected Place[]               place;

    public GameControllerPanel(Phase phase, Place place[]){
        setBackground(Color.white);
        setLayout(null);
        buttonListener = new ButtonListener();
        mainColor = new Color(52, 81, 138);
        this.phase = phase;
        this.place = place;

        startCardPanel = new StartCardPanel(phase);
        startCardPanel.setVisible(false);
        add(startCardPanel);

        purchasePanel = new PurchasePanel(phase);
        purchasePanel.setVisible(false);
        add(purchasePanel);

        tollPanel = new TollPanel(phase);
        tollPanel.setVisible(false);
        add(tollPanel);

        goldCardPanel = new GoldCardPanel(phase);
        goldCardPanel.setVisible(false);
        add(goldCardPanel);

        islandPanel = new IslandPanel(phase);
        islandPanel.setVisible(false);
        add(islandPanel);

        welfareFacilityPanel = new WelfareFacilityPanel(phase);
        welfareFacilityPanel.setVisible(false);
        add(welfareFacilityPanel);

        helicopterPanel = new HelicopterPanel(phase);
        helicopterPanel.setVisible(false);
        add(helicopterPanel);

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

        eggImage = new ImageIcon("./image/egg.gif");
        eggButton = new JButton(eggImage);
        eggButton.setBounds(135,0,300,300);
        eggButton.setBackground(null);
        eggButton.setBorder(null);
        eggButton.setOpaque(true);
        eggButton.setVisible(false);
        eggButton.addMouseListener(buttonListener);
        add(eggButton);

        projectName = new JLabel("<html><div style='text-align: center;'>파란구슬<BR>놀이</div></html>");
        projectName.setBounds(175,100,230,200);
        projectName.setVerticalAlignment(SwingConstants.CENTER);
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        //projectName.setFont(new Font("RixVideoGame3D", Font.ITALIC, 70));
        projectName.setFont(new Font("Rix전자오락 3D", Font.ITALIC, 70));
        projectName.setForeground(mainColor);
        add(projectName);

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

    }//GameControllerPanel class

    public void show_dice(int dice1, int dice2){
        dice1Image = new ImageIcon(DiceConstants.DICE_IMAGE[dice1-1]);
        dice1Label.setIcon(dice1Image);
        dice2Image = new ImageIcon(DiceConstants.DICE_IMAGE[dice2-1]);
        dice2Label.setIcon(dice2Image);
    }

    private class ButtonListener implements MouseListener {
        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();

            if(object == rollButton){
                phase.roll();
                dice1Label.setVisible(true);
                dice2Label.setVisible(true);
                rollButton.setVisible(false);
                moveButton.setVisible(true);
            }
            if(object == moveButton){
                phase.move();
                moveButton.setVisible(false);
                dice1Label.setVisible(false);
                dice2Label.setVisible(false);
                if(Main.nextPosition < Main.originPosition){
                    startCardPanel.setVisible(true);
                }
                else{
                    if(Main.nextPosition == 3 || Main.nextPosition == 9 || Main.nextPosition == 15 || Main.nextPosition == 21){
                        eggButton.setVisible(true);
                    } else if (Main.nextPosition == 6){ // 직잭
                        phase.special();
                        islandPanel.setVisible(true);
                    } else if (Main.nextPosition == 12) { // ATM
                        welfareFacilityPanel.set_price_info();
                        welfareFacilityPanel.setVisible(true);
                    } else if (Main.nextPosition == 18) { // 헬기
                        Main.gameBoard.show_city_number();
                        helicopterPanel.reset_helicopter_panel();
                        helicopterPanel.setVisible(true);
                        //helicopterPanel.get_destination();
                    } else {
                        //purchaseButton.setVisible(true);
                        if (place[Main.nextPosition].get_land_owner() == -1) { // 소유자 X
                            purchaseButton.setVisible(true);
                        } else if (place[Main.nextPosition].get_land_owner() == (Main.playerTurn)%4) { // 소유자 = 현재 턴
                            if (place[Main.nextPosition].get_landmark_ownership() == 0) { // 살 건물이 남아있음
                                purchaseButton.setVisible(true);
                            } else { // 모든 건물을 삼
                                phase.next();
                            }
                        } else { // 소유자 != 현재 턴
                            payButton.setVisible(true);
                        }
                    }
                }
            }
            if(object == purchaseButton){
                if(Main.nextPosition != 0 || Main.nextPosition != 6 || Main.nextPosition != 12 || Main.nextPosition != 18){
                    purchaseButton.setVisible(false);
                    purchasePanel.set_purchase_panel_info(place[Main.nextPosition]);
                    purchasePanel.setVisible(true);
                }
            }
            if(object == payButton) {
                payButton.setVisible(false);
                tollPanel.set_toll_panel_info(place[Main.nextPosition]);
                tollPanel.setVisible(true);
            }
            if(object == eggButton){
                System.out.println("eggButton");
                eggButton.setVisible(false);
                goldCardPanel.setVisible(true);
            }
        }//mouseClicked
        public void mousePressed(MouseEvent event){ }
        public void mouseReleased(MouseEvent event){ }
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

        }
    }//ButtonListener class
} // GameControllerPanel class
