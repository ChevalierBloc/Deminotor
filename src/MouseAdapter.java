import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MouseAdapter implements MouseListener {
    Model model;
    Fenetre f;
    JButton[][] tabButton;

    public MouseAdapter(Model model, Fenetre fenetre) {
        this.model = model;
        this.f = fenetre;
        tabButton = f.getTabButton();
        f.setControlBoutonGrilleGauche(this);
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3)
            model.setMouseRightClic(true);
        else if (e.getButton() == MouseEvent.BUTTON1)
            model.setMouseRightClic(false);

        String bouton;
        for(int i=0; i<tabButton.length; i++){
            for( int j=0; j<tabButton[i].length; j++){
                if(model.getX() == i && model.getY() == j) {
                    System.out.println("testMouse");
                    if(model.isMouseRightClic()){
                        f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageDrapeau().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                    }
                    else {
                        if (model.getTabMines()[i][j] == 1) {
                            f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                            f.perdu();
                        } else {
                            f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j]].getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                        }
                    }
                }
            }
        }
    }

    // inutile mais garder sinon compilateur ne veut pas
    public void mousePressed(MouseEvent e) {

    }

    // inutile mais garder sinon compilateur ne veut pas
    public void mouseExited(MouseEvent e) {

    }

    // inutile mais garder sinon compilateur ne veut pas
    public void mouseEntered(MouseEvent e) {

    }

    // inutile mais garder sinon compilateur ne veut pas
    public void mouseClicked(MouseEvent e) {

    }
}
