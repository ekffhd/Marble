import UI.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Marble {
    public static void main(String[] args){
        System.out.println(System.getProperty("os.name"));
        //BGM
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

        //Frame 추가
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Main Panel추가
        Main main = new Main();
        frame.getContentPane().add(main);

        frame.pack();
        frame.setVisible(true);
    }
}
