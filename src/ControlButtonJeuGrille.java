

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
            model.setMouseRightClic(false);
        else if(source == f.getbDrapeau())
            model.setMouseRightClic(true);
        else {
            for (int i = 0; i < f.getTabButton().length; i++) {
                for (int j = 0; j < f.getTabButton()[i].length; j++) {
                    bouton = i + "/" + j;
                    if(e.getActionCommand().equals(bouton)) {
                        if (model.isMouseRightClic()) {
                            if(model.drapeauPosse()) {
                                f.genererErreur("drapeau");
                            }else{
                                f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageDrapeau().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                model.setNbMinesRestant(model.getNbMinesRestant()-1);
                                f.actualiser();
                            }
                        } else {
                            if (model.getTabMines()[i][j] == 1) {
                                f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                f.perdu();
                            } else {
                                model.setScore(model.getScore()+1);
                                f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j]].getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                f.actualiser();
                            }
                        }
                    }
                }
            }
        }
    }
}
