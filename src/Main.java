import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private GameBoard gameBoard;
    private ScoreBoard scoreBoard;

    public Main() {
        setPreferredSize(new Dimension(800, 750));
        setLayout(null);

        gameBoard = new GameBoard();
        add(gameBoard);

        scoreBoard = new ScoreBoard();
        add(scoreBoard);
    }
}
