import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButton implements ActionListener {

    Fenetre f;

    Model model;

    public ControlButton(Model model, Fenetre f) {
        this.f = f;
        this.model = model;
        f.setControlBouton(this);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source == f.getbJeu()) {
            f.changerVue(2);
        }

        else if (source == f.getbOption()) {
            f.changerVue(3);
        }

        else if (source == f.getbQuitter()) {
            System.exit(0);
        }

        else if(source == f.getbRetourJouer()){
            f.changerVue(1);
        }

        else if(source == f.getbNorma()){
            model.setEstTor(false);
            f.changerVue(4);
        }

        else if(source == f.getbTor()){
            model.setEstTor(true);
            f.changerVue(4);
        }

        else if(source == f.getbPerso()){
            f.changerVue(6);
        }

        else if(source == f.getbFacile()){
            model.setDifficulte(1);
            f.changerVue(5);
        }

        else if(source == f.getbMoyen()){
            model.setDifficulte(2);
            f.changerVue(5);
        }

        else if(source == f.getbDiffile()){
            model.setDifficulte(3);
            f.changerVue(5);
        }

        else if(source == f.getbRetourDifficulte()){
            model.setEstTor(false);
            f.changerVue(2);
        }

        else if(source == f.getbRetourGrille()){
            model.setDifficulte(0);
            f.changerVue(4);
        }

        else if(source == f.getbRetourPerso()){
            f.changerVue(2);
        }

        else if(source == f.getbRetourOption()){
            f.changerVue(1);
        }

    }
}
