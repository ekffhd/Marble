package UI;

import Player.Player;
import Property.GoldCard;
import Property.Building;
import Property.Place;
import UI.GameBoard;
import UI.ScoreBoard;
import UI.StartController;
import UI.StartPanel;
import Util.Dice;
import Util.Phase;
import Util.PhaseListener;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    protected static GameBoard gameBoard;
    private ScoreBoard scoreBoard;
    private StartPanel startPanel;
    private StartController startController;
    private GoldCard goldCard;
    private GameOverPanel gameoverPanel;

    private Dice dice1, dice2;
    private int dice1Num, dice2Num;
    protected static Player[] player;
    private Phase phase;
    private PhaseListener phaseListener;
    protected static int playerTurn;
    protected static int originPosition;
    protected static int nextPosition;
    protected static int cardId;
    protected int afterPosition;
    private int destination, originalOwner;


    private  int expense, land, house, building, hotel, landmark; // 선택 여부
    protected int price;

    public Main() {
        setPreferredSize(new Dimension(800, 750));
        setLayout(null);

        //Phase
        phase = new Phase();
        playerTurn = 0;
        originPosition = 0;

        //주사위
        dice1 = new Dice();
        dice2 = new Dice();
        goldCard = new GoldCard();

        //플레이어
        player = new Player[4];
        for(int i=0; i<4; i++){
            player[i] = new Player(i);
            player[i].set_position(0);
            player[i].add_cash(2000000);
        }

        //게임 판
        gameBoard = new GameBoard(phase);
        gameBoard.setVisible(false);
        add(gameBoard);

        //점수 판
        scoreBoard = new ScoreBoard(player);
        scoreBoard.setVisible(false);
        add(scoreBoard);

        //시작 화면 패널
        startPanel = new StartPanel();
        startPanel.setVisible(true);
        add(startPanel);

        startController = new StartController(startPanel, gameBoard, scoreBoard, phase);

        phaseListener = new PhaseListener(dice1, dice2, scoreBoard, gameBoard, this, phase);
        phase.addPropertyChangeListener(phaseListener);
        phase.before_start();

        //게임 오버 패널
        gameoverPanel = new GameOverPanel(phase);
        gameoverPanel.setVisible(false);
        add(gameoverPanel);
    }

    public int get_origin_position(){
        return originPosition;
    }
    public Player get_active_player(){
        return player[playerTurn%4];
    }
    public void next(){
        int isLandCount = player[playerTurn%4].get_island_count();
        if(dice1Num == dice2Num && isLandCount == 0){
            gameBoard.gameControllerPanel.doubleButton.setVisible(true);
        }
        else{
            player[playerTurn%4].init_dice_count();
            this.playerTurn++;
            scoreBoard.setBorder(this.playerTurn);
            gameBoard.gameControllerPanel.rollButton.setVisible(true);
        }

    }// next()


    public void get_salary(){
        player[playerTurn%4].add_cash(200000);
        scoreBoard.set_player_cash_label(playerTurn%4);
        gameBoard.gameControllerPanel.startCardPanel.setVisible(true);
    }

    public void roll_dice(){
        gameBoard.gameControllerPanel.rollButton.setVisible(false);
        dice1.roll_dice();
        dice2.roll_dice();
        dice1Num = dice1.get_dice();
        dice2Num = dice2.get_dice();
        player[playerTurn%4].add_dice_count();
        gameBoard.show_dice(dice1Num, dice2Num);
        int isLandCount = player[playerTurn%4].get_island_count();
        if(isLandCount!=0){ // 무인도
            if(dice1Num == dice2Num){ // 더블
                gameBoard.gameControllerPanel.escapeSuccessIslandPanel.setVisible(true);
                scoreBoard.escape_island_icon(playerTurn%4);
                player[playerTurn%4].escape_island();
            }
            else{
                player[playerTurn%4].sub_island_count();
                gameBoard.gameControllerPanel.escapeFailIslandPanel.setVisible(true);
                scoreBoard.sub_island_icon_count(player[playerTurn%4].get_player_id(),player[playerTurn%4].get_island_count());
            }
        }
        else{
            if((dice1.get_dice() == dice2.get_dice())&&player[playerTurn%4].get_dice_count() > 2){
                player[playerTurn%4].init_dice_count();
                gameBoard.gameControllerPanel.doubleButton.setVisible(false);
                player[playerTurn%4].add_island_count();
                move_player(6);
            }
            else{
                gameBoard.gameControllerPanel.moveButton.setVisible(true);
            }

        }
    }

    public void purchase_property() {
        expense = gameBoard.gameControllerPanel.purchasePanel.get_expense();
        land = gameBoard.gameControllerPanel.purchasePanel.get_land();
        house = gameBoard.gameControllerPanel.purchasePanel.get_house();
        building = gameBoard.gameControllerPanel.purchasePanel.get_building();
        hotel = gameBoard.gameControllerPanel.purchasePanel.get_hotel();
        landmark = gameBoard.gameControllerPanel.purchasePanel.get_landmark();

        this.expense = expense*10000;
        player[playerTurn%4].sub_cash(expense);
        scoreBoard.set_player_cash_label(playerTurn%4);

        if (land == 1) {gameBoard.place[nextPosition].purchase_land(player[playerTurn%4]);}
        if (house == 1) { gameBoard.place[nextPosition].purchase_house(); }
        if (building == 1) {gameBoard.place[nextPosition].purchase_building(); }
        if (hotel == 1) { gameBoard.place[nextPosition].purchase_hotel(); }
        if (landmark == 1) { gameBoard.place[nextPosition].purchase_landmark(); }

        gameBoard.place[nextPosition].update_building_status(playerTurn%4);
        gameBoard.place[nextPosition].set_city_price();
        phase.next();
    }

    public void move_player(int afterPosition){
        originPosition = player[playerTurn%4].get_position();
        nextPosition = (afterPosition)%24;
        gameBoard.show_hide_player(playerTurn%4, originPosition, nextPosition);
        player[playerTurn%4].set_position(nextPosition);

        if(nextPosition < originPosition) { // 한 바퀴 돌았을 때
            get_salary();
        }
        else{
            phase.gap();
            phase.show_panel();
        }
    }// move_player()

    public void fire_gold_card_effect(){
        switch(cardId){
            case 0:
                goldCard.donate(30000, gameBoard.place[12], player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 1:
                goldCard.donate(50000, gameBoard.place[12], player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 2:
                goldCard.donate(80000, gameBoard.place[12], player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 3:
                player[playerTurn%4].set_exemption();
                phase.next();
                break;
            case 4:
                phase.next();
                break;
            case 5:
                phase.next();
                break;
            case 6:
                goldCard.lotto(30000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 7:
                goldCard.lotto(50000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 8:
                goldCard.lotto(80000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 9:
                goldCard.lotto(100000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 10:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[23],23, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = 23;
                show_panel();
                break;
            case 11:
                goldCard.pay_taxes(30000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 12:
                goldCard.pay_taxes(50000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 13:
                goldCard.pay_taxes(100000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                phase.next();
                break;
            case 14:
                gameBoard.gameControllerPanel.rollButton.setVisible(true);
                break;
            case 15:
                gameBoard.gameControllerPanel.rollButton.setVisible(true);
                break;
            case 16:
                gameBoard.gameControllerPanel.rollButton.setVisible(true);
                break;
            case 17:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[6],6, player[playerTurn%4]);
                player[playerTurn%4].set_position(6);
                originPosition = nextPosition;
                nextPosition = 6;
                show_panel();
                break;
            case 18:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[6],6, player[playerTurn%4]);
                player[playerTurn%4].set_position(6);
                originPosition = nextPosition;
                nextPosition = 6;
                show_panel();
                break;
            case 19:
                afterPosition = nextPosition-1;
                if(afterPosition<0){
                    afterPosition+=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = afterPosition;
                show_panel();
                break;
            case 20:
                afterPosition = nextPosition-3;
                if(afterPosition<0){
                    afterPosition+=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = afterPosition;
                show_panel();
                break;
            case 21:
                afterPosition = nextPosition+2;
                if(afterPosition>23){
                    afterPosition%=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = afterPosition;
                show_panel();
                break;
            case 22:
                afterPosition = nextPosition+3;
                if(afterPosition>23){
                    afterPosition%=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = afterPosition;
                show_panel();
                break;
            case 23:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[18],18, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = 18;
                show_panel();
                break;
            case 24:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[18],18, player[playerTurn%4]);
                originPosition = nextPosition;
                nextPosition = 18;
                show_panel();
                break;
        }
    }// fire_gold_card_effect()

    public void special_event(){

        if(nextPosition == 3 || nextPosition == 9 || nextPosition == 15 || nextPosition == 21){ //황금오리
            System.out.println("황금오리");
            this.cardId = gameBoard.gameControllerPanel.goldCardPanel.cardId;
            fire_gold_card_effect();
        }
        else if(nextPosition == 6){ // 직잭
            System.out.println("직잭");
            if(player[playerTurn%4].get_island_count() == 0){
                player[playerTurn%4].add_island_count();
                scoreBoard.set_island_icon_count(playerTurn%4);
            }
            else{
                player[playerTurn%4].sub_island_count();
                scoreBoard.sub_island_icon_count(playerTurn%4,player[playerTurn%4].get_island_count());
            }
            phase.next();
        }
        else if (nextPosition == 12) { // ATM
            System.out.println("광개토");

            price = gameBoard.place[12].get_price();
            player[playerTurn%4].add_cash(price);
            gameBoard.place[12].set_price(0);
            scoreBoard.set_player_cash_label(playerTurn%4);
            phase.next();
        } else if (nextPosition == 18) { //  헬기
            System.out.println("헬기");

            destination = gameBoard.gameControllerPanel.helicopterPanel.get_destination();
            move_player(destination);
        } // if ~ else if
    }

    public void bill(int bill){
        player[gameBoard.place[nextPosition].get_land_owner()].add_cash(bill);
        scoreBoard.set_player_cash_label(gameBoard.place[nextPosition].get_land_owner());

        player[playerTurn%4].sub_cash(bill);
        scoreBoard.set_player_cash_label(playerTurn%4);

        if(gameBoard.place[nextPosition].get_landmark_ownership() == 0) { // 매입 가능
            gameBoard.gameControllerPanel.takeOverButton.setVisible(true);
            //매입버튼.setVisible(true);
        }
        else{
            phase.next();
            //매입버튼.setVisible(false);
        }
        //phase.takeOver();
        //phase.next();
    }

    public void take_over() {
        originalOwner = gameBoard.place[nextPosition].get_land_owner();
        expense = gameBoard.gameControllerPanel.takeOverPanel.get_expense();
        house = gameBoard.gameControllerPanel.takeOverPanel.get_house();
        building = gameBoard.gameControllerPanel.takeOverPanel.get_building();
        hotel = gameBoard.gameControllerPanel.takeOverPanel.get_hotel();

        this.expense = expense * 10000;
        player[playerTurn%4].sub_cash(expense);
        scoreBoard.set_player_cash_label(playerTurn%4);

        player[originalOwner].add_cash(expense);
        scoreBoard.set_player_cash_label(originalOwner);

        gameBoard.place[nextPosition].purchase_land(player[playerTurn%4]);
        if (house == 1) { gameBoard.place[nextPosition].purchase_house(); }
        if (building == 1) {gameBoard.place[nextPosition].purchase_building(); }
        if (hotel == 1) { gameBoard.place[nextPosition].purchase_hotel(); }

        gameBoard.place[nextPosition].update_building_status(playerTurn%4);
        gameBoard.place[nextPosition].set_city_price();

        phase.next();
    }

    public void show_panel(){
        if(nextPosition == 3 || nextPosition == 9 || nextPosition == 15 || nextPosition == 21){// 황금오리 패널
            gameBoard.gameControllerPanel.eggButton.setVisible(true);
        }
        else if(nextPosition == 0){
            gameBoard.gameControllerPanel.startCardPanel.setVisible(true);
        }
        else if(nextPosition == 6){// 지그재그
            gameBoard.gameControllerPanel.islandPanel.setVisible(true);
        }
        else if(nextPosition == 12){// ATM
            gameBoard.gameControllerPanel.welfareFacilityPanel.setVisible(true);
        }
        else if(nextPosition == 18){// 헬리콥터
            gameBoard.gameControllerPanel.helicopterPanel.reset_helicopter_panel();
            gameBoard.gameControllerPanel.helicopterPanel.setVisible(true);
            gameBoard.show_city_number();
        }
        else{
            if(gameBoard.place[nextPosition].get_land_owner() == -1){//소유자 x
                gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
            }
            else{ // 소유자 o
                if(gameBoard.place[nextPosition].get_land_owner() == playerTurn%4){// 자신의 땅
                    if(gameBoard.place[nextPosition].get_landmark_ownership() == 1){// 더 이상 구매할 수 없음
                        phase.next();
                    }
                    else{
                        gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
                    }

                }
                else{//타인의 땅
                    if(player[playerTurn%4].get_exemption() == 1){ //면제권
                        //TODO 면제 패널
                        player[playerTurn%4].init_exemption();
                        phase.next();
                    }
                    else{
                        /*
                        if(gameBoard.place[nextPosition].get_landmark_ownership() == -1) { // 매입 가능
                            //매입버튼.setVisible(true);
                        }
                        else{
                            //매입버튼.setVisible(false);
                        }
                        */
                        gameBoard.gameControllerPanel.payButton.setVisible(true);
                    }
                }
            }
        }
    }
}
