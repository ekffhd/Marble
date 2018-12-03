package Util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PhaseListener implements PropertyChangeListener {
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