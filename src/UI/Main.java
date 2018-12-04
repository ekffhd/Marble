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
    private Player[] player;
    private Phase phase;
    private PhaseListener phaseListener;
    private int player_turn;
    private int origin_position;


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
        }

        gameBoard = new GameBoard(phase);
        gameBoard.setVisible(false);
        add(gameBoard);

        scoreBoard = new ScoreBoard();
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
    }

    public void move_player(int position){
        origin_position = player[player_turn%4].get_position();
        gameBoard.show_hide_player(player_turn%4, origin_position, (origin_position+position)%24);
        player[player_turn%4].set_positon((origin_position+position)%24);
        System.out.println("main"+position);
    }
}
