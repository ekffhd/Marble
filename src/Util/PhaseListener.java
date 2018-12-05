package Util;

import UI.GameBoard;
import UI.Main;
import UI.ScoreBoard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class PhaseListener implements PropertyChangeListener {

    private Dice dice1, dice2;
    private ScoreBoard scoreBoard;
    private GameBoard gameBoard;
    private Main main;
    private Phase phase;
    private int expense, bill;
    private  int land, house, building, hotel, landmark; // 선택 여부

    public PhaseListener(Dice dice1, Dice dice2, ScoreBoard scoreBoard, GameBoard gameBoard, Main main, Phase phase){
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.scoreBoard = scoreBoard;
        this.gameBoard = gameBoard;
        this.main = main;
        this.phase = phase;

    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("START")){
            System.out.println("start");
            gameBoard.show_hide_player(0,0,0);
            gameBoard.show_hide_player(1,0,0);
            gameBoard.show_hide_player(2,0,0);
            gameBoard.show_hide_player(3,0,0);
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
        else if (event.getPropertyName().equals("MOVE")){
            System.out.println("move");
            main.move_player(dice1.get_dice()+dice2.get_dice());
        }
        else if (event.getPropertyName().equals("GOLD_CARD")){
            System.out.println("gold card");
            main.fire_gold_card_effect();
        }
        else if (event.getPropertyName().equals("PURCHASE")){
            System.out.println("purchase");
            expense = gameBoard.gameControllerPanel.purchasePanel.get_expense();
            land = gameBoard.gameControllerPanel.purchasePanel.get_land();
            house = gameBoard.gameControllerPanel.purchasePanel.get_house();
            building = gameBoard.gameControllerPanel.purchasePanel.get_building();
            hotel = gameBoard.gameControllerPanel.purchasePanel.get_hotel();
            landmark = gameBoard.gameControllerPanel.purchasePanel.get_landmark();
            expense = expense*10000;

            main.purchase_property(expense, land, house, building, hotel, landmark);
            phase.next();
        }
        else if (event.getPropertyName().equals("ACQUIRE")){
            System.out.println("acquire");
        }
        else if (event.getPropertyName().equals("BILL")){
            System.out.println("bill");
            this.bill = gameBoard.gameControllerPanel.tollPanel.get_bill();
            bill = bill*10000;
            main.bill(bill);
        }
        else if (event.getPropertyName().equals("NEXT")){
            System.out.println("next");
            main.next();
        }
        else if (event.getPropertyName().equals("END")){
            System.out.println("end");
        }
        else if (event.getPropertyName().equals("LAP")){
            System.out.println("lap");
            main.do_a_lap();
        }
        else if (event.getPropertyName().equals("RESTART")){
            System.out.println("restart");
        }
        else if (event.getPropertyName().equals("SPECIAL")){
            System.out.println("special");
            main.special_event();
        }

    }
}