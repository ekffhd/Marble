package Util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Phase {
    protected PropertyChangeSupport propertyChangeSupport;
    private String phase, previousPhase;
    public Phase(){
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void start(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.START;
        propertyChangeSupport.firePropertyChange("START", previousPhase, phase);
    }
    public void roll(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.ROLLING;
        propertyChangeSupport.firePropertyChange("ROLL", previousPhase, phase);
    }
    public void move(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.MOVING;
        propertyChangeSupport.firePropertyChange("MOVE", previousPhase, phase);
    }
    public void gold_card(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.GOLD_CARD;
        propertyChangeSupport.firePropertyChange("GOLD_CARD", previousPhase, phase);
    }
    public void purchase(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.PURCHASE;
        propertyChangeSupport.firePropertyChange("PURCHASE",previousPhase, phase);
    }
    public void bill(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.BILLING;
        propertyChangeSupport.firePropertyChange("BILL", previousPhase, phase);
    }
    public void acquire(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.ACQUIRING;
        propertyChangeSupport.firePropertyChange("ACQUIRE", previousPhase, phase);
    }
    public void next(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.ACQUIRING;
        propertyChangeSupport.firePropertyChange("NEXT", previousPhase, phase);
    }
    public void end(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.END;
        propertyChangeSupport.firePropertyChange("END", previousPhase, phase);
    }
    public void restart(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.RESTART;
        propertyChangeSupport.firePropertyChange("RESTART", previousPhase, phase);
    }
    public void before_start(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.BEFORE_START_PHASE;
        propertyChangeSupport.firePropertyChange("BEFORE_START", previousPhase, phase);
    }
    public void lap(){
        this.previousPhase = this.phase;
        this.phase = PhaseConstants.BEFORE_START_PHASE;
        propertyChangeSupport.firePropertyChange("LAP", previousPhase, phase);
    }

    public String getPreviousPhase() {
        return previousPhase;
    }

}
