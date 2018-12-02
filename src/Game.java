public class Game {
    private Dice dice1, dice2;
    private Player[] player;
    private Phase phase;
    private PhaseListener phaseListener;
    private int player_turn;

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

    public void next(){
        while (this.player[this.player_turn].getStatus()){
            this.player_turn++;
        }
    }

    public int rollDice(){
        return dice1.roll_dice() + dice2.roll_dice();
    }
}
