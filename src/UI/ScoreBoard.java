package UI;
//주석 완료
import Player.Player;
import Player.PlayerConstants;
import Player.PlayerData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class ScoreBoard extends JPanel {
    private Player[]        player;
    private JPanel[]        playerInformationPanel;
    protected JLabel[]      playerNameLabel, playerCashLabel, playerIconLabel;
    protected JLabel[][]    islandCountIconLabel;
    protected  JLabel       tollFreeIconLabel;
    private ImageIcon[]     playerIcon;
    private ImageIcon[][]   islandCountIcon;
    private ImageIcon       tollFreeIcon;

    //점수판 패널
    public ScoreBoard(Player player[]) {
        //패널 설정
        setBounds(0,550,800,200);
        setLayout(null);
        setBackground(Color.white);
        setLayout(new GridLayout(1,4));

        //플레이어 정보 배열 4개 선언
        this.player = new Player[4];
        playerInformationPanel = new JPanel[4];
        playerNameLabel = new JLabel[4];
        playerCashLabel = new JLabel[4];
        playerIconLabel = new JLabel[4];
        playerIcon = new ImageIcon[4];
        islandCountIconLabel = new JLabel[4][3];
        islandCountIcon = new ImageIcon[4][3];


        for(int i=0;i<4;i++){
            //플레이어 개인 패널 생성
            playerInformationPanel[i] = new JPanel();
            playerInformationPanel[i].setBackground(PlayerConstants.PLAYER_COLOR[i]);
            playerInformationPanel[i].setBounds(0,0,200,200);
            playerInformationPanel[i].setLayout(null);
            add(playerInformationPanel[i]);

            this.player[i] = player[i];

            //플레이어 이름 라벨
            playerNameLabel[i] = new JLabel("PLAYER " + (i + 1));
            playerNameLabel[i].setFont(new Font("boxy bold", Font.BOLD, 25));
            playerNameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerNameLabel[i].setBounds(0,150,200,50);
            playerInformationPanel[i].add(playerNameLabel[i]);

            //플레이어 아이콘
            playerIcon[i] = new ImageIcon(PlayerConstants.PLAYER_ICON[i]);
            playerIconLabel[i] = new JLabel(playerIcon[i]);
            playerIconLabel[i].setBounds(0,0,200,120);
            playerInformationPanel[i].add(playerIconLabel[i]);

            //플레이어 소지금 라벨
            playerCashLabel[i] = new JLabel(""+player[i].get_cash()+" won");
            playerCashLabel[i].setFont(new Font("drid herder solid",Font.PLAIN, 18));
            playerCashLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            playerCashLabel[i].setBounds(0,120, 200, 30);
            playerInformationPanel[i].add(playerCashLabel[i]);


            //지그재그 탈출 카운트 아이콘
            for (int j=0; j<3; j++) {
                islandCountIcon[i][j] = new ImageIcon("./image/island_count"+(j+1)+".png");
                islandCountIconLabel[i][j] = new JLabel(islandCountIcon[i][j]);
                islandCountIconLabel[i][j].setBounds(8, 25*j+5, 27, 25);
                islandCountIconLabel[i][j].setVisible(false);
                playerInformationPanel[i].add(islandCountIconLabel[i][j]);
            }

            //면제권 아이콘
            tollFreeIcon = new ImageIcon("./image/free.png");
            tollFreeIconLabel = new JLabel(tollFreeIcon);
            tollFreeIconLabel.setBounds(162, 10, 27,25);
            tollFreeIconLabel.setVisible(false);
            playerInformationPanel[i].add(tollFreeIconLabel);
        }
    }//ScoreBoard()

    //턴을 나타내는 (분홍색)테두리 설정
    public void setBorder(int player_turn){
        if(player_turn != 0){
            playerInformationPanel[(player_turn-1)%4].setBorder(null);
        }
        playerInformationPanel[player_turn%4].setBorder(BorderFactory.createLineBorder(Color.magenta, 10));
    }

    //소지금 라벨 재설정
    public void set_player_cash_label(int playerId){
        playerCashLabel[playerId].setText(player[playerId].get_cash()+" won");
    }

    //지그재그 탈출 카운트 설정
    public void set_island_icon_count(int playerId){
        System.out.println(playerId+"가 지그재그로");
        islandCountIconLabel[playerId][2].setVisible(true);
    }

    //지그재그 탈출 카운트 1 감소
    public void sub_island_icon_count(int playerId, int count){
        System.out.println(playerId+" "+count);
        islandCountIconLabel[playerId][count].setVisible(false);
        islandCountIconLabel[playerId][count-1].setVisible(true);
    }

    //지그재그 탈출 성공
    public void escape_island_icon(int playerId){
        islandCountIconLabel[playerId][0].setVisible(false);
        islandCountIconLabel[playerId][1].setVisible(false);
        islandCountIconLabel[playerId][2].setVisible(false);
    }
}//ScoreBoard class
