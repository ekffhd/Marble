package Util;

import Player.Player;
import UI.GameBoard;
import UI.Main;
import UI.ScoreBoard;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class PhaseListener implements PropertyChangeListener {

    private Dice      dice1, dice2;
    private GameBoard gameBoard;
    private Main      main;
    private Player    player;
    private int       bill;

    public PhaseListener(Dice dice1, Dice dice2, GameBoard gameBoard, Main main){
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.gameBoard = gameBoard;
        this.main = main;
        this.player = new Player(-1);
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("START")){
            //시작
            main.start();
            gameBoard.show_hide_player(0,0,0);
            gameBoard.show_hide_player(1,0,0);
            gameBoard.show_hide_player(2,0,0);
            gameBoard.show_hide_player(3,0,0);
        }
        else if (event.getPropertyName().equals("BEFORE_START")){
            //없어도 될듯
        }
        else if (event.getPropertyName().equals("ROLL")){
            //주사위 굴리기
            main.roll_dice();
        }
        else if (event.getPropertyName().equals("MOVE")){
            //플레이어 이동
            player = main.get_active_player();
            main.move_player( player.get_position()+ dice1.get_dice() + dice2.get_dice());
        }
        else if (event.getPropertyName().equals("GAP")){
            //GAP <-- 연속으로 같은 phase가 발생하면 잘 작동이 되지 않는 것 같아 추가하였습니다.
        }
        else if (event.getPropertyName().equals("PURCHASE")){
            //구매
            main.purchase_property();
        }
        else if (event.getPropertyName().equals("BILL")){
            //통행료 지불
            this.bill = gameBoard.gameControllerPanel.tollPanel.get_bill();
            bill = bill*10000;
            main.bill(bill);
        }
        else if (event.getPropertyName().equals("TAKEOVER")){
            //인수
            main.take_over();
        }
        else if (event.getPropertyName().equals("SPECIAL")){
            //특수 이벤트
            main.special_event();
        }
        else if (event.getPropertyName().equals("NEXT")){
            //다음 턴으로 넘기기
            main.next();
        }
        else if (event.getPropertyName().equals("END")){
            //종료
            main.end();
        }
        else if (event.getPropertyName().equals("SHOW_PANEL")){
            //위치에 해당하는 패널 띄우기
            main.show_panel();
        }
        else if (event.getPropertyName().equals("RESTART")){
            //재시작
        }
    }
}