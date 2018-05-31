

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
        for(int i=0; i<f.getTabButton().length; i++){
            for( int j=0; j<f.getTabButton()[i].length; j++){
                bouton = i+"/"+j;
                if (bouton.equals(e.getActionCommand())) {
                    System.out.println("testAction");
                    model.setX(i);
                    model.setY(j);
                }
            }
        }
    }
}
