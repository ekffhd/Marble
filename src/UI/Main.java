package UI;

import Player.Player;
import Property.GoldCard;
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
    protected static int next_position;
    protected static int cardId;

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
            player[i].set_positon(0);
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

        if(next_position == 3 || next_position == 9 || next_position == 15 || next_position == 21){
            gameBoard.gameControllerPanel.eggButton.setVisible(true);
        }
        else if(next_position == 0 || next_position == 6 || next_position == 12 || next_position == 18){
            next();
        }
        else{
            gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
        }
    }// do_a_lap()

    public void move_player(int position){
        originPosition = player[playerTurn%4].get_position();
        next_position = (originPosition + position)%24;
        gameBoard.show_hide_player(playerTurn%4, originPosition, next_position);
        player[playerTurn%4].set_positon(next_position);

        if(next_position == 3 || next_position == 9 || next_position == 15 || next_position == 21){
            cardId = goldCard.set_card_id();
            gameBoard.gameControllerPanel.goldCardPanel.set_card_information(cardId);
        }
        System.out.println("main"+position);
    }// move_player()

    public void fire_gold_card_effect(){
        switch(cardId){
            case 0:
                System.out.println("fire");
                goldCard.donate(30000, gameBoard.place[12], player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 1:
                goldCard.donate(50000, gameBoard.place[12], player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 2:
                goldCard.donate(80000, gameBoard.place[12], player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                goldCard.lotto(30000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 7:
                goldCard.lotto(50000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 8:
                goldCard.lotto(80000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;
            case 9:
                goldCard.lotto(100000, player[playerTurn%4]);
                scoreBoard.set_player_cash_label(playerTurn%4);
                break;



        }
        phase.next();
    }
}
