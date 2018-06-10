import javax.swing.*;
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
        JMenuItem source = (JMenuItem) e.getSource();
        if (source == f.getJMINouvellePartie()){
            f.suppControlBoutonGrille();
            model.setNbMinesRestant(model.getNbMines());
            model.initTab();
            model.placeMine();
            model.setNbVoisin();
            model.setScore(0);
            f.changerVue(5);
            f.actualiser();
            f.getbPause().setEnabled(true);
            f.getbAide().setEnabled(true);
            new ControlButtonJeuGrille(model, f);
            f.getTime().start();
        }else if (source == f.getJMIRetourMenuPrincipal()) {
            f.suppControlBoutonGrille();
            f.getTime().stop();
            model.setScore(0);
            f.changerVue(1);
            f.setSize(700,500);
        }
    }
}
