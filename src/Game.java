import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Game {
    private Dice dice1, dice2;
    private Player[] player;
    private Phase phase;
    private int player_turn;
    private PhaseListener phaseListener;

    public Game(){
        phase = new Phase();
        player_turn = 0;
        phaseListener = new PhaseListener();
        phase.addPropertyChangeListener(phaseListener);
        phase.before_start();

        dice1 = new Dice();
        dice2 = new Dice();

        player = new Player[4];
        for(int i=0; i<4; i++){
            player[i] = new Player(i);
        }
    }

    public void start(){
        phase.start();
    }

    public int rollDice(){
        return dice1.roll_dice() + dice2.roll_dice();
    }

    public class PhaseListener implements PropertyChangeListener{
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            if (event.getPropertyName().equals("START")){
                System.out.println("start");
            }
            else if (event.getPropertyName().equals("BEFORE_START")){
                System.out.println("before start");
            }
            else if (event.getPropertyName().equals("ROLL")){
                System.out.println("roll");
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
}
