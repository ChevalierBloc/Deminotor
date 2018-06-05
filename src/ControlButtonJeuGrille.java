

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ControlButtonJeuGrille implements ActionListener {
    Fenetre f;
    Model model;

    public ControlButtonJeuGrille(Model model, Fenetre f) {
        this.f = f;
        this.model = model;
        f.setControlBoutonGrille(this);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String bouton;
        if(source == f.getbClique())
            model.setPoseDrapeau(false);
        else if(source == f.getbDrapeau())
            model.setPoseDrapeau(true);
        else {
            for (int i = 0; i < f.getTabButton().length; i++) {
                for (int j = 0; j < f.getTabButton()[i].length; j++) {
                    bouton = i + "/" + j;
                    if(e.getActionCommand().equals(bouton)) {
                        if (model.isPoseDrapeau()) {
                            if(model.drapeauPosse()) {
                                f.genererErreur("drapeau");
                            }else{
                                if(model.getTabJeu()[i][j] == 2){
                                    f.getTabButton()[i][j].setIcon(null);
                                    model.setNbMinesRestant(model.getNbMinesRestant() + 1);
                                    model.getTabJeu()[i][j] = 0;
                                    f.actualiser();
                                }else {
                                    if(model.getTabJeu()[i][j] != 1) {
                                        f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageDrapeau().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                        model.setNbMinesRestant(model.getNbMinesRestant() - 1);
                                        model.getTabJeu()[i][j] = 2;
                                        f.actualiser();
                                        if (model.estGagnant())
                                            f.gagner();
                                    }
                                }
                            }
                        } else {
                            if (model.getTabMines()[i][j] == 1) {
                                f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                f.perdu();
                            } else {
                                if(model.getTabJeu()[i][j] == 1){

                                }else {
                                    model.setScore(model.getScore() + 1);
                                    model.getTabJeu()[i][j] = 1;
                                    f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j]].getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                    f.actualiser();
                                    if(model.estGagnant())
                                        f.gagner();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
