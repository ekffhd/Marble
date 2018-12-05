package UI;

import Player.Player;
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

    private Dice dice1, dice2;
    protected static Player[] player;
    private Phase phase;
    private PhaseListener phaseListener;
    protected static int player_turn;
    protected static int origin_position;
    protected static int next_position;


    public Main() {
        setPreferredSize(new Dimension(800, 750));
        setLayout(null);

        phase = new Phase();
        player_turn = 0;
        origin_position = 0;

        dice1 = new Dice();
        dice2 = new Dice();

        player = new Player[4];
        for(int i=0; i<4; i++){
            player[i] = new Player(i);
            player[i].set_positon(0);
            player[i].add_cash(2000000);
        }

        gameBoard = new GameBoard(phase, this);
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
        this.player_turn++;
        scoreBoard.setBorder(this.player_turn);
        gameBoard.gameControllerPanel.rollButton.setVisible(true);
        scoreBoard.playerCashLabel[player_turn%4].setText(""+player[player_turn%4].get_cash()+" won");
    }

    public void do_a_lap(){
        player[player_turn%4].add_cash(200000);
        scoreBoard.playerCashLabel[player_turn%4].setText(player[player_turn%4].get_cash()+"  won");

        if(next_position == 3 || next_position == 9 || next_position == 15 || next_position == 21){
            gameBoard.gameControllerPanel.eggButton.setVisible(true);
        }
        else if(next_position == 0 || next_position == 6 || next_position == 12 || next_position == 18){
            next();
        }
        else{
            gameBoard.gameControllerPanel.purchaseButton.setVisible(true);
        }

    }

    public void move_player(int position){
        origin_position = player[player_turn%4].get_position();
        next_position = (origin_position + position)%24;
        gameBoard.show_hide_player(player_turn%4, origin_position, next_position);
        player[player_turn%4].set_positon(next_position);
        System.out.println("main"+position);
    }// next()
}
