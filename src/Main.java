import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    private GameBoard gameBoard;
    private ScoreBoard scoreBoard;
    private StartPanel startPanel;

    public Main() {
        setPreferredSize(new Dimension(800, 750));
        setLayout(null);

        gameBoard = new GameBoard();
        gameBoard.setVisible(false);
        add(gameBoard);

        scoreBoard = new ScoreBoard();
        scoreBoard.setVisible(false);
        add(scoreBoard);

        startPanel = new StartPanel();
        startPanel.setVisible(true);
        add(startPanel);

        StartController startController = new StartController(startPanel, gameBoard, scoreBoard);

    }
}
