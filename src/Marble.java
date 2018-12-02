import javax.swing.*;

public class Marble {
    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Main main = new Main();
        frame.getContentPane().add(main);

        frame.pack();
        frame.setVisible(true);
    }
}
