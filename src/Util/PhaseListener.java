package Util;

import UI.GameBoard;
import UI.ScoreBoard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



public class PhaseListener implements PropertyChangeListener {

    private Dice dice1, dice2;
    private ScoreBoard scoreBoard;
    private GameBoard gameBoard;

    public PhaseListener(Dice dice1, Dice dice2, ScoreBoard scoreBoard, GameBoard gameBoard){
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.scoreBoard = scoreBoard;
        this.gameBoard = gameBoard;
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("START")){
            System.out.println("start");
        }
        else if (event.getPropertyName().equals("BEFORE_START")){
            System.out.println("before start");
        }
        else if (event.getPropertyName().equals("ROLL")){
            System.out.println("roll");
            dice1.roll_dice();
            dice2.roll_dice();
            gameBoard.show_dice(dice1.get_dice(), dice2.get_dice());
        }
        else if (event.getPropertyName().equals("ACQUIRE")){
            System.out.println("acquire");
        }
        else if (event.getPropertyName().equals("BILL")){
            System.out.println("bill");
        }
        else if (event.getPropertyName().equals("END")){
            System.out.println("end");
        }
        else if (event.getPropertyName().equals("RESTART")){
            System.out.println("restart");
        }
    }
}