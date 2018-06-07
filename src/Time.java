import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Time {

    private Timer timer;
    private Model model;
    private Fenetre fenetre;

    public Time(Model model, Fenetre fenetre) {
        this.model = model;
        this.fenetre = fenetre;
        timer = new Timer(1000, new TraitementTimer());
    }

    private class TraitementTimer implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setScore(model.getScore()+1);
            fenetre.actualiser();
        }
    }

    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}
