import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButton implements ActionListener {

    Fenetre f;

    ModelIntermediaire model;

    public ControlButton(ModelIntermediaire model, Fenetre f) {
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
            model.estTor = false;
            f.changerVue(4);
        }

        else if(source == f.getbTor()){
            model.estTor = true;
            f.changerVue(4);
        }

        else if(source == f.getbPerso()){
            f.changerVue(6);
        }

        else if(source == f.getbFacile()){
            model.diffeculte = 1;
            f.actualiser();
            f.changerVue(5);
        }

        else if(source == f.getbMoyen()){
            model.diffeculte = 2;
            f.actualiser();
            f.changerVue(5);
        }

        else if(source == f.getbDiffile()){
            model.diffeculte = 3;
            f.actualiser();
            f.changerVue(5);
        }

        else if(source == f.getbRetourDifficulte()){
            model.estTor = false;
            f.changerVue(2);
        }

        else if(source == f.getbRetourGrille()){
            model.diffeculte = 0;
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
