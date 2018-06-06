import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlMenu implements ActionListener {
    Fenetre f;
    Model model;

    public ControlMenu(Model model,Fenetre f) {
        this.f = f;
        this.model = model;
        f.setControlMenu(this);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
