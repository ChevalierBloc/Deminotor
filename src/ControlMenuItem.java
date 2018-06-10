import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ControlMenuItem implements ItemListener {
    Fenetre f;
    Model model;

    public ControlMenuItem(Model model,Fenetre f) {
        this.f = f;
        this.model = model;
        f.setControlMenuItem(this);
    }

    public void itemStateChanged(ItemEvent e) {
        if(f.getJMIOptionsSon().isSelected()){
            model.startMusique();
        }
        else{
            model.stopMusique();
        }
    }
}
