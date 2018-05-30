

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
                    System.out.println("Bouton : " + bouton);
                    System.out.println("source : " + e.getActionCommand());
                    if(model.getTabMines()[i][j] == 1){
                        f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                        System.out.println("perdu !!!!!!");
                    }else{
                        System.out.println(model.getTabVoisins()[i][j]);
                        f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j]].getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                    }
                }
            }
        }
    }
}
