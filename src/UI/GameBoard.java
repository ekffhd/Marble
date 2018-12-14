package UI;

import Property.Place;
import Property.PlaceConstants;
import Property.BuildingConstants;
import Util.Phase;

import javax.swing.*;
import java.awt.*;

//게임 보드
public class GameBoard extends JPanel {

    public GameControllerPanel      gameControllerPanel;    //확인창, 주사위굴리기 등 전반적인 게임 진행이 나타나는 패널
    protected Place[]               place;

    private GridBagConstraints[]    gbc;                    //place 를 나타내기 위한 GBC
    private GridBagConstraints      gameControllerGbc;      //게임 진행 패널을 위한 GBC
    private Color                   goldEggColor;
    private Phase                   phase;

    private ImageIcon               eggImageIcon;
    private JLabel[]                eggLabel;

    public GameBoard(Phase phase) {
        //패널 설정
        setBounds(0,0,800,550);
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        this.phase = phase;

        //Place 생성
        place = new Place[24];
        //Place를 위한 gbc 생성
        gbc = new GridBagConstraints[24];

        //Start Place
        place[0] = new Place(PlaceConstants.PLACE_NAME[0], 0);
        place[0].setPreferredSize(new Dimension(125, 125));
        place[0].setBackground(new Color(14, 46,64));
        place[0].setBorder(BorderFactory.createMatteBorder(3, 3, 0, 0, Color.black));
        place[0].setLayout(null);

        gbc[0] = new GridBagConstraints();
        gbc[0].gridx=6;
        gbc[0].gridy=6;
        gbc[0].weightx = 1;
        gbc[0].weighty = 1;
        gbc[0].fill = GridBagConstraints.BOTH;

        add(place[0], gbc[0]);

        //South Place 1~5
        for(int i=1;i<6;i++){
            place[i] = new Place(PlaceConstants.PLACE_NAME[i], i);
            place[i].setPreferredSize(new Dimension(110, 125));
            place[i].setBackground(Color.white);
            place[i].setBorder(BorderFactory.createMatteBorder(3, 1, 0, 0, Color.black));

            gbc[i] = new GridBagConstraints();
            gbc[i].gridx = 6-i;
            gbc[i].gridy = 6;
            gbc[i].weightx = 1;
            gbc[i].weighty = 1;
            gbc[i].fill = GridBagConstraints.BOTH;
            add(place[i], gbc[i]);
        }
        place[5].setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.black));

        //지그재그 Place 6
        place[6] = new Place(PlaceConstants.PLACE_NAME[6], 6);
        place[6].setPreferredSize(new Dimension(125,125));
        place[6].setBackground(new Color(101,35,42));
        place[6].setBorder(BorderFactory.createMatteBorder(3, 0, 0, 3, Color.black));

        gbc[6] = new GridBagConstraints();
        gbc[6].gridx = 0;
        gbc[6].gridy = 6;
        gbc[6].weightx = 1;
        gbc[6].weighty = 1;
        gbc[6].fill = GridBagConstraints.BOTH;
        add(place[6], gbc[6]);

        //West Place 7~11
        for(int i=7;i<12;i++){
            place[i] = new Place(PlaceConstants.PLACE_NAME[i], i);
            place[i].setPreferredSize(new Dimension(125, 100));
            place[i].setBackground(Color.white);
            place[i].setBorder(BorderFactory.createLineBorder(Color.black));
            place[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 3, Color.black));

            gbc[i] = new GridBagConstraints();
            gbc[i].gridx = 0;
            gbc[i].gridy = 12-i;
            gbc[i].weightx = 1;
            gbc[i].weighty = 1;
            gbc[i].fill = GridBagConstraints.BOTH;
            add(place[i], gbc[i]);
        }
        place[11].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.black));

        //사회복지기금 Place 12
        place[12] = new Place(PlaceConstants.PLACE_NAME[12], 12);
        place[12].setPreferredSize(new Dimension(125,125));
        place[12].setBackground(new Color(14, 46,64));
        place[12].setBorder(BorderFactory.createLineBorder(Color.black));
        place[12].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.black));

        gbc[12] = new GridBagConstraints();
        gbc[12].gridx = 0;
        gbc[12].gridy = 0;
        gbc[12].weightx = 1;
        gbc[12].weighty = 1;
        gbc[12].fill = GridBagConstraints.BOTH;
        add(place[12], gbc[12]);

        //North Place 13~17
        for(int i=13; i<18;i++){
            place[i] = new Place(PlaceConstants.PLACE_NAME[i], i);
            place[i].setPreferredSize(new Dimension(110,125));
            place[i].setBackground(Color.white);
            place[i].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 1, Color.black));

            gbc[i] = new GridBagConstraints();
            gbc[i].gridx = i-12;
            gbc[i].gridy = 0;
            gbc[i].weightx = 1;
            gbc[i].weighty = 1;
            gbc[i].fill = GridBagConstraints.BOTH;
            add(place[i], gbc[i]);
        }
        place[17].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));

        //헬기장 Place 18
        place[18] = new Place(PlaceConstants.PLACE_NAME[18], 18);
        place[18].setPreferredSize(new Dimension(125,125));
        place[18].setBackground(new Color(101,35,42));
        place[18].setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, Color.black));

        gbc[18] = new GridBagConstraints();
        gbc[18].gridx = 6;
        gbc[18].gridy = 0;
        gbc[18].weightx = 1;
        gbc[18].weighty = 1;
        gbc[18].fill = GridBagConstraints.BOTH;
        add(place[18], gbc[18]);

        //East Place 19~24
        for(int i=19;i<24;i++){
            place[i] = new Place(PlaceConstants.PLACE_NAME[i], i);
            place[i].setPreferredSize(new Dimension(125, 100));
            place[i].setBackground(Color.white);
            place[i].setBorder(BorderFactory.createMatteBorder(1, 3, 0, 0, Color.black));

            gbc[i] = new GridBagConstraints();
            gbc[i].gridx = 6;
            gbc[i].gridy = i-18;
            gbc[i].weightx = 1;
            gbc[i].weighty = 1;
            gbc[i].fill = GridBagConstraints.BOTH;
            add(place[i], gbc[i]);
        }
        place[19].setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.black));

        //황금오리 Place 3, 9, 15, 21
        eggImageIcon = new ImageIcon("./image/egg_small.png");
        eggLabel = new JLabel[4];
        for(int i=0;i<4;i++){
            eggLabel[i] = new JLabel(eggImageIcon);
            eggLabel[i].setBounds(0,0,120,80);
        }
        goldEggColor = new Color(245, 182, 73);
        place[3].setBackground(goldEggColor);
        place[3].add(eggLabel[0]);
        place[9].setBackground(goldEggColor);
        place[9].add(eggLabel[1]);
        place[15].setBackground(goldEggColor);
        place[15].add(eggLabel[2]);
        place[21].setBackground(goldEggColor);
        place[21].add(eggLabel[3]);

        //부지 가격 설정
        for(int i=0;i<24;i++){
            place[i].set_building_price(BuildingConstants.BUILDING_PRICE[i]);
        }

        //특수 지역 배경 색 설정
        place[0].setColor(Color.white);
        place[0].set_city_number_color(Color.white);
        place[6].setColor(Color.white);
        place[6].set_city_number_color(Color.white);
        place[12].setColor(Color.white);
        place[12].set_city_number_color(Color.white);
        place[18].setColor(Color.white);
        place[18].set_city_number_color(Color.white);
        hide_city_number();

        //게임 조작 패널 add
        gameControllerPanel = new GameControllerPanel(phase, place);
        gameControllerGbc = new GridBagConstraints();
        gameControllerGbc.gridx = 1;
        gameControllerGbc.gridy = 1;
        gameControllerGbc.gridheight = 5;
        gameControllerGbc.gridwidth = 5;
        gameControllerGbc.fill = GridBagConstraints.BOTH;
        add(gameControllerPanel, gameControllerGbc);
    }//GameBoard()

    //주사위 결과를 나타낸다.
    public void show_dice(int dice1, int dice2) {
        gameControllerPanel.show_dice(dice1, dice2);
    }

    //place 숫자를 나타낸다. (헬리콥터 용)
    public void show_city_number() {
        for (int i=0; i< 24; i++) {
            place[i].show_city_number();
        }
    }

    //place 숫자를 숨긴다. (헬리콥터 용)
    public void hide_city_number() {
        for (int i=0; i<24; i++) {
            place[i].hide_city_number();
        }
    }

    //플레이어 위치를 옮겨준다. (move phase일때 실행)
   public void show_hide_player(int playerId,int originPosition, int nextPosition){
        place[originPosition].hide_player(playerId);
        place[nextPosition].show_player(playerId);
    }
}
