package UI;

import Property.PlaceConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PurchasePanel extends JPanel {

    private Color            mainBackgroundColor, mainColor;
    private JLabel           placeLabel;
    private JPanel           checkboxPanel;

    private JButton          purchaseButton, cancelButton;
    private Phase            phase;
    private int              position, expense;

    private JPanel[]         menuPanel;
    private JLabel[]         menuLabel, priceLabel;
    private JCheckBox[]      menuCheckBox;

    private CheckBoxListener checkBoxListener;
    private ButtonListener   buttonListener;


    public PurchasePanel(Phase phase){
        mainBackgroundColor = Color.white;
        mainColor = new Color(52, 81, 138);
        checkBoxListener = new CheckBoxListener();
        buttonListener = new ButtonListener();

        this.phase = phase;
        expense = 0;

        setBackground(mainBackgroundColor);
        setBounds(40, 40, 800/7*5-80, 550/7*5-80);
        setBorder(BorderFactory.createLineBorder(mainColor, 6));
        setLayout(null);

        placeLabel = new JLabel(); // placeNameConstants.placeName_NAME[1]
        placeLabel.setBounds(0,0,800/7*5-80, 80);
        placeLabel.setFont(new Font("Rix전자오락 3D", Font.PLAIN, 40));
        //placeLabel.setFont(new Font("RixVideoGame3D", Font.PLAIN, 40));
        placeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeLabel.setVerticalAlignment(SwingConstants.CENTER);
        placeLabel.setForeground(Color.black);
        add(placeLabel);

        checkboxPanel = new JPanel();
        checkboxPanel.setBounds(6, 80, 800/7*5-80-12, 150);
        checkboxPanel.setBackground(mainBackgroundColor);
        checkboxPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        checkboxPanel.setLayout(null);
        add(checkboxPanel);

        menuPanel = new JPanel[5];
        for (int i=0; i<5; i++){
            menuPanel[i] = new JPanel();
            menuPanel[i].setBackground(mainBackgroundColor);
            menuPanel[i].setBounds((800/7*5-567)/2+95*i, 0, 95, 150);
            menuPanel[i].setBorder(BorderFactory.createLineBorder(Color.blue, 1));
            menuPanel[i].setLayout(null);
            checkboxPanel.add(menuPanel[i]);
        }

        menuLabel = new JLabel[5];
        menuLabel[0] = new JLabel("부지");
        menuLabel[1] = new JLabel("집");
        menuLabel[2] = new JLabel("빌딜");
        menuLabel[3] = new JLabel("호텔");
        menuLabel[4] = new JLabel("랜드마크");
        for (int i=0; i<5; i++){
            menuLabel[i].setBounds(0,0,95,60);
            menuLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            menuLabel[i].setVerticalAlignment(SwingConstants.CENTER);
            menuLabel[i].setFont(new Font("Rix전자오락 3D", Font.PLAIN, 30));
            menuPanel[i].add(menuLabel[i]);
        }

        priceLabel = new JLabel[5];
        for (int i=0; i<5; i++){
            priceLabel[i] = new JLabel();
            priceLabel[i].setBounds(0,60,95,50);
            priceLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            priceLabel[i].setVerticalAlignment(SwingConstants.CENTER);
            priceLabel[i].setFont(new Font("Drid Herder Solid", Font.PLAIN, 20));
            menuPanel[i].add(priceLabel[i]);
        }

        menuCheckBox = new JCheckBox[5];
        for (int i=0; i<5; i++){
            menuCheckBox[i] = new JCheckBox();
            menuCheckBox[i].setSelected(false);
            menuCheckBox[i].setBounds(0, 110, 95, 40);
            menuCheckBox[i].setHorizontalAlignment(SwingConstants.CENTER);
            menuCheckBox[i].setVerticalAlignment(SwingConstants.CENTER);
            menuCheckBox[i].setBackground(mainBackgroundColor);
            menuCheckBox[i].addActionListener(checkBoxListener);
            menuPanel[i].add(menuCheckBox[i]);
        }
        menuCheckBox[0].setSelected(true);
        menuCheckBox[4].setEnabled(false);

        purchaseButton = new JButton("BUY"); // 구매 버튼
        purchaseButton.setBounds(40, 250, (800/7*5-210)/2, 40);
        purchaseButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        purchaseButton.setBackground(Color.white);
        purchaseButton.setForeground(mainColor);
        purchaseButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        purchaseButton.setVerticalAlignment(SwingConstants.CENTER);
        purchaseButton.setHorizontalAlignment(SwingConstants.CENTER);
        purchaseButton.addMouseListener(buttonListener);
        add(purchaseButton);

        cancelButton = new JButton("CANCEL"); // 구매 취소 버튼
        cancelButton.setBounds(90+(800/7*5-210)/2, 250, (800/7*5-210)/2, 40);
        cancelButton.setFont(new Font("drid herder solid", Font.PLAIN, 20));
        cancelButton.setBackground(Color.white);
        cancelButton.setForeground(mainColor);
        cancelButton.setBorder(BorderFactory.createLineBorder(mainColor, 2));
        cancelButton.setVerticalAlignment(SwingConstants.CENTER);
        cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
        cancelButton.addMouseListener(buttonListener);
        add(cancelButton);
    } // PurchasePanel()

    public void set_panel_info(){
        position = Main.next_position;
        expense = 0;
        placeLabel.setText(PlaceConstants.PLACE_LINE_NAME[Main.next_position]);
        priceLabel[0].setText(PlaceConstants.LAND_PRICE[Main.next_position]+" $"); // 부지
        priceLabel[1].setText(PlaceConstants.HOUSE_PRICE[Main.next_position]+" $"); // 집
        priceLabel[2].setText(PlaceConstants.BUIDING_PRICE[Main.next_position]+" $"); // 빌딩
        priceLabel[3].setText(PlaceConstants.HOTEL_PRICE[Main.next_position]+" $"); // 호텔
        priceLabel[4] .setText(PlaceConstants.LANDMARK_PRICE[Main.next_position]+" $"); // 랜드마크
    }

    public int get_expense(){
        return expense;
    }

    private class CheckBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JCheckBox checkbox = (JCheckBox) event.getSource();
            if (checkbox == menuCheckBox[0]){
                menuCheckBox[0].setSelected(true);
            } else if (checkbox == menuCheckBox[1]) {

            } else if (checkbox == menuCheckBox[2]) {

            } else if (checkbox == menuCheckBox[3]) {

            } else if (checkbox == menuCheckBox[4]) {

            } // if ~ else if
        } // actionPerformed()
    } // CheckBoxListener class

    private class ButtonListener implements MouseListener {

        public void mouseClicked(MouseEvent event){
            Object object = event.getSource();
            setVisible(false);
            if (object == purchaseButton){
                System.out.println(position);
                System.out.print(expense+" >> ");
                if (menuCheckBox[0].isSelected()){ // 부지
                    expense += PlaceConstants.LAND_PRICE[Main.next_position];
                }
                if (menuCheckBox[1].isSelected()){ // 집
                    expense += PlaceConstants.HOUSE_PRICE[Main.next_position];
                }
                if (menuCheckBox[2].isSelected()){ // 빌딩
                    expense += PlaceConstants.BUIDING_PRICE[Main.next_position];
                }
                if (menuCheckBox[3].isSelected()){ // 호텔
                    expense += PlaceConstants.HOTEL_PRICE[Main.next_position];
                }
                if (menuCheckBox[4].isSelected()){ // 랜드마크
                    expense += PlaceConstants.LANDMARK_PRICE[Main.next_position];
                }
                System.out.println(expense);
                phase.purchase();
            }else if (object == cancelButton){
                phase.next();
            } // if ~ else if
        }
        public void mousePressed(MouseEvent event){ }
        public void mouseReleased(MouseEvent event){ }
        public void mouseEntered(MouseEvent event) {
            JButton object = (JButton)event.getSource();

            object.setBackground(mainColor);
            object.setOpaque(true);
            object.setForeground(Color.white);
        }
        public void mouseExited(MouseEvent event){
            JButton object = (JButton)event.getSource();

            object.setBackground(Color.white);
            object.setOpaque(true);
            object.setForeground(mainColor);
        }
    }//ButtonListener class

} // PurchasePanel class
