package UI;

import Player.Player;
import Property.GoldCard;
import Property.Building;
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
    private GameBoard gameBoard;
    private ScoreBoard scoreBoard;
    private StartPanel startPanel;
    private StartController startController;
    private GoldCard goldCard;

    private Dice dice1, dice2;
    protected static Player[] player;
    private Phase phase;
    private PhaseListener phaseListener;
    protected static int playerTurn;
    protected static int originPosition;
    protected static int nextPosition;
    protected static int cardId;
    protected int afterPosition;


    private  int expense, land, house, building, hotel, landmark; // 선택 여부


    public Main() {
        setPreferredSize(new Dimension(800, 750));
        setLayout(null);

        phase = new Phase();
        playerTurn = 0;
        originPosition = 0;

        dice1 = new Dice();
        dice2 = new Dice();
        goldCard = new GoldCard();

        player = new Player[4];
        for(int i=0; i<4; i++){
            player[i] = new Player(i);
            player[i].set_position(0);
            player[i].add_cash(2000000);
        }

        gameBoard = new GameBoard(phase);
        gameBoard.setVisible(false);
        add(gameBoard);

        scoreBoard = new ScoreBoard(player);
        scoreBoard.setVisible(false);
        add(scoreBoard);

        startPanel = new StartPanel();
        startPanel.setVisible(true);
        add(startPanel);

        startController = new StartController(startPanel, gameBoard, scoreBoard, phase);

        phaseListener = new PhaseListener(dice1, dice2, scoreBoard, gameBoard, this, phase);
        phase.addPropertyChangeListener(phaseListener);
        phase.before_start();
    }

    public void next(){
        this.playerTurn++;
        scoreBoard.setBorder(this.playerTurn);
        gameBoard.gameControllerPanel.rollButton.setVisible(true);
        scoreBoard.playerCashLabel[playerTurn%4].setText(""+player[playerTurn%4].get_cash()+" won");
    }// next()

    public void do_a_lap(){
        player[playerTurn%4].add_cash(200000);
        scoreBoard.set_player_cash_label(playerTurn%4);

        if(nextPosition == 3 || nextPosition == 9 || nextPosition == 15 || nextPosition == 21){
            gameBoard.gameControllerPanel.eggButton.setVisible(true);
        }
        else if(nextPosition == 0 || nextPosition == 12 || nextPosition == 18){
            next();
        }
        else if(nextPosition == 6){
            player[playerTurn%4].set_island_count();
            gameBoard.gameControllerPanel.islandPanel.setVisible(true);
        }
        else{
            gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
        }
    }// do_a_lap()

    public void purchase_property() {
        player[playerTurn%4].sub_cash(expense);
        scoreBoard.playerCashLabel[playerTurn%4].setText(player[playerTurn%4].get_cash()+"  won"); // 돈
        expense = gameBoard.gameControllerPanel.purchasePanel.get_expense();
        land = gameBoard.gameControllerPanel.purchasePanel.get_land();
        house = gameBoard.gameControllerPanel.purchasePanel.get_house();
        building = gameBoard.gameControllerPanel.purchasePanel.get_building();
        hotel = gameBoard.gameControllerPanel.purchasePanel.get_hotel();
        landmark = gameBoard.gameControllerPanel.purchasePanel.get_landmark();
        this.expense = expense*10000;
        if (land == 1) {gameBoard.place[nextPosition].set_building_status(player[playerTurn%4], house, building, hotel, landmark);}
        /*if (house == 1) { buildings[nextPosition].purchase_house(); }
        if (building == 1) { buildings[nextPosition].purchase_building(); }
        if (hotel == 1) { buildings[nextPosition].purchase_hotel(); }
        if (landmark == 1) { buildings[nextPosition].purchase_landmark(); }*/
        phase.next();
    }

    public void move_player(int position){
        originPosition = player[playerTurn%4].get_position();
        nextPosition = (originPosition + position)%24;
        gameBoard.show_hide_player(playerTurn%4, originPosition, nextPosition);
        player[playerTurn%4].set_position(nextPosition);

        if(nextPosition > originPosition){
            if(nextPosition == 3 || nextPosition == 9 || nextPosition == 15 || nextPosition == 21){
                cardId = goldCard.set_card_id();
                gameBoard.gameControllerPanel.goldCardPanel.set_card_information(cardId);
            }
            else if(nextPosition == 0){}
            else if(nextPosition == 6){}
            else if(nextPosition == 12){
                phase.next();
            }
            else if(nextPosition == 18){
                phase.next();
            }
            else {
                if (gameBoard.place[nextPosition].get_land_owner() == -1) { // 소유자 X
                    gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
                }
                else if (gameBoard.place[nextPosition].get_land_owner() == (Main.playerTurn)%4) { // 소유자 = 현재 턴
                    if (gameBoard.place[nextPosition].get_landmark_ownership() == 0) { // 살 건물이 남아있음
                        gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
                    } else { // 모든 건물을 삼
                        phase.next();
                    }
                } else { // 소유자 != 현재 턴
                    gameBoard.gameControllerPanel.payButton.setVisible(true);
                }
            }

        }

        System.out.println("main"+position);
    }// move_player()

    public void fire_gold_card_effect(){
        switch(cardId){
            case 0:
                System.out.println("fire");
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
                break;
            case 11:
                goldCard.pay_taxes(30000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 12:
                goldCard.pay_taxes(50000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 13:
                goldCard.pay_taxes(100000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 17:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[6],6, player[playerTurn%4]);
                break;
            case 18:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[6],6, player[playerTurn%4]);
                break;
            case 19:
                afterPosition = nextPosition-1;
                if(afterPosition<0){
                    afterPosition+=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                break;
            case 20:
                afterPosition = nextPosition-3;
                if(afterPosition<0){
                    afterPosition+=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                break;
            case 21:
                afterPosition = nextPosition+2;
                if(afterPosition>23){
                    afterPosition%=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                break;
            case 22:
                afterPosition = nextPosition+3;
                if(afterPosition>23){
                    afterPosition%=24;
                }
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[afterPosition],afterPosition, player[playerTurn%4]);
                break;
            case 23:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[18],18, player[playerTurn%4]);
                break;
            case 24:
                goldCard.move_player(gameBoard.place[nextPosition], gameBoard.place[18],18, player[playerTurn%4]);
                break;
        }
        phase.next();
    }// fire_gold_card_effect()

    public void special_event(){
        if(nextPosition == 6){
            player[playerTurn%4].set_island_count();
            System.out.println("Island");
        }
    }


    public void bill(int bill){
        player[gameBoard.place[nextPosition].get_land_owner()].add_cash(bill);
        scoreBoard.set_player_cash_label(gameBoard.place[nextPosition].get_land_owner());

        player[playerTurn%4].sub_cash(bill);
        scoreBoard.set_player_cash_label(playerTurn%4);
    }
}
