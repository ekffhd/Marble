import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Marble {
    public static void main(String[] args){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Music.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception except){
            System.out.println("Error with playing sound.");
            except.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Main main = new Main();
        frame.getContentPane().add(main);


        frame.pack();
        frame.setVisible(true);
    }
}
