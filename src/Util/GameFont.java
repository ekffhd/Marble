package Util;


public class GameFont extends java.awt.Font {

    public GameFont(String name, int style, int size) {
        super(name, style, size);
        String OS = System.getProperty("os.name");

        if (name.equals("Rix전자오락 3D"))
        {
            if (OS.equals("Mac OS X")) {
                this.name = "RIXVideoGame3D";
            }
            else {
                this.name = name;
            }
        }
        else if (name.equals("Rix전자오락 Bold")) {
            if (OS.equals("Mac OS X")){
                this.name = "RixVideoGameB";
            }
            else {
                this.name = name;
            }
        }
    }
}
