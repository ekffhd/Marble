import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Phase {
    protected PropertyChangeSupport propertyChangeSupport;
    private String phase;

    public Phase(){
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void start(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.START;
        propertyChangeSupport.firePropertyChange("START", previous_phase, phase);
    }
    public void roll(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.ROLLING;
        propertyChangeSupport.firePropertyChange("ROLL", previous_phase, phase);
    }
    public void bill(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.BILLING;
        propertyChangeSupport.firePropertyChange("BILL", previous_phase, phase);
    }
    public void acquire(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.ACQUIRING;
        propertyChangeSupport.firePropertyChange("ACQUIRE", previous_phase, phase);
    }
    public void end(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.END;
        propertyChangeSupport.firePropertyChange("END", previous_phase, phase);
    }
    public void restart(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.RESTART;
        propertyChangeSupport.firePropertyChange("RESTART", previous_phase, phase);
    }
    public void before_start(){
        String previous_phase = this.phase;
        this.phase = PhaseConstants.BEFORE_START_PHASE;
        propertyChangeSupport.firePropertyChange("BEFORE_START", previous_phase, phase);
    }
}
