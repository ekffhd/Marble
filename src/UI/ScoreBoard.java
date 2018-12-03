package UI;

import Player.PlayerConstants;
import Player.PlayerData;

import javax.swing.*;
import java.awt.*;


public class ScoreBoard extends JPanel {
    private JPanel scoreBoardMainPanel;
    private PlayerData[] playerDataArray;
    private JPanel[]  playerInformationPanel;
    private JLabel[] playerNameLabel, playerCashLabel, playerIconLabel;
    private ImageIcon[] playerIcon;
    private JLabel dummy;

    public ScoreBoard() {
        setBounds(0,550,800,200);
        setLayout(null);
        setBackground(Color.white);
        setLayout(new GridLayout(1,4));

        playerInformationPanel = new JPanel[4];
        playerNameLabel = new JLabel[4];
        playerCashLabel = new JLabel[4];
        playerIconLabel = new JLabel[4];
        playerIcon = new ImageIcon[4];
        playerDataArray = new PlayerData[4];


        for(int i=0;i<4;i++){
            playerInformationPanel[i] = new JPanel();
            playerInformationPanel[i].setBackground(PlayerConstants.PLAYER_COLOR[i]);
            playerInformationPanel[i].setBounds(0,0,200,200);
            playerInformationPanel[i].setLayout(null);
            add(playerInformationPanel[i]);

            playerDataArray[i] = new PlayerData(i);

            playerNameLabel[i] = new JLabel("PLAYER " + (i + 1));
            playerNameLabel[i].setFont(new Font("boxy bold", Font.BOLD, 25));
            playerNameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerNameLabel[i].setBounds(0,150,200,50);
            playerInformationPanel[i].add(playerNameLabel[i]);

            playerIcon[i] = new ImageIcon(PlayerConstants.PLAYER_ICON[i]);
            playerIconLabel[i] = new JLabel(playerIcon[i]);
            playerIconLabel[i].setBounds(0,0,200,120);
            playerInformationPanel[i].add(playerIconLabel[i]);

            playerCashLabel[i] = new JLabel("[CASH]  " + playerDataArray[i].getCash()+" $");
            playerCashLabel[i].setFont(new Font("drid herder solid",Font.PLAIN, 18));
            playerCashLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerCashLabel[i].setBounds(0,120, 200, 30);
            playerInformationPanel[i].add(playerCashLabel[i]);
        }
    }
}
