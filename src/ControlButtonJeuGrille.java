

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ControlButtonJeuGrille implements ActionListener {
    Fenetre f;
    Model model;
    Time time;
    boolean enPause;

    public ControlButtonJeuGrille(Model model, Fenetre f) {
        this.f = f;
        this.model = model;
        enPause = false;
        f.setControlBoutonGrille(this);
        time = new Time(model, f);
        time.start();
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String bouton;
        if(source == f.getbClique()){
            if(model.isPoseDrapeau()){
                model.setPoseDrapeau(false);
                f.getbClique().setIcon(new ImageIcon(model.getImageDrapeau().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
            }
            else{
                model.setPoseDrapeau(true);
                f.getbClique().setIcon(new ImageIcon(model.getImageClique().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
            }
        }
        else if (source == f.getbPause()){
            if(enPause){
                time.start();
                f.reprendre();
                enPause = false;
                f.getbPause().setText("Pause");
            }
            else{
                time.stop();
                f.pause();
                enPause = true;
                f.getbPause().setText("Reprendre");
            }
        }
        else {
            for (int i = 0; i < f.getTabButton().length; i++) {
                for (int j = 0; j < f.getTabButton()[i].length; j++) {
                    bouton = i + "/" + j;
                    if(e.getActionCommand().equals(bouton)) {
                        if (model.isPoseDrapeau()) {
                            if(model.getTabJeu()[i][j] == 2){
                                f.getTabButton()[i][j].setIcon(null);
                                model.setNbMinesRestant(model.getNbMinesRestant() + 1);
                                model.getTabJeu()[i][j] = 0;
                                f.actualiser();
                            }else {
                                if(model.drapeauPosse()) {
                                    f.genererErreur("drapeau");
                                }
                                else if(model.getTabJeu()[i][j] != 1) {
                                    f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageDrapeau().getImage()));
                                    model.setNbMinesRestant(model.getNbMinesRestant() - 1);
                                    model.getTabJeu()[i][j] = 2;
                                    f.actualiser();
                                    if (model.estGagnant())
                                        f.gagner();
                                }
                            }
                        } else {
                            if (model.getTabMines()[i][j] == 1) {
                                f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                time.stop();
                                f.perdu();
                            } else {
                                if(model.getTabJeu()[i][j] == 2){

                                }else {
                                    model.getTabJeu()[i][j] = 1;
                                    if (model.getTabVoisins()[i][j]==0){
                                        verifVoisins(i,j); //faut enlever pour tester voisin
                                    }
                                    f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j]].getImage()));
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
    private void verifVoisins(int i, int j){
        if ((i>0 && i < model.getNbcaseligne() - 1) && (j > 0 && j < model.getNbcasecolonne() -1)){
            f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
            model.getTabJeu()[i-1][j]=1;
            if (model.getTabVoisins()[i-1][j] == 0 && model.getTabJeu()[i-1][j] == 0){
                verifVoisins(i-1,j);
            }

            model.getTabJeu()[i-1][j+1]=1;
            f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
            if (model.getTabVoisins()[i-1][j+1] == 0 && model.getTabJeu()[i-1][j+1] == 0){
                verifVoisins(i-1,j+1);
            }

            model.getTabJeu()[i-1][j-1]=1;
            f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
            if (model.getTabVoisins()[i-1][j-1] == 0 && model.getTabJeu()[i-1][j-1] == 0){
                verifVoisins(i-1,j-1);
            }

            model.getTabJeu()[i+1][j]=1;
            f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
            if (model.getTabVoisins()[i+1][j] == 0 && model.getTabJeu()[i+1][j] == 0){
                verifVoisins(i+1,j);
            }

            model.getTabJeu()[i+1][j+1]=1;
            f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
            if (model.getTabVoisins()[i+1][j+1] == 0 && model.getTabJeu()[i+1][j+1] == 0){
                verifVoisins(i+1,j+1);
            }

            model.getTabJeu()[i+1][j-1]=1;
            f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
            if (model.getTabVoisins()[i+1][j-1] == 0 && model.getTabJeu()[i+1][j-1] == 0){
                verifVoisins(i+1,j-1);
            }

            model.getTabJeu()[i][j-1]=1;
            f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
            if (model.getTabVoisins()[i][j-1] == 0 && model.getTabJeu()[i][j-1] == 0){
                verifVoisins(i,j-1);
            }

            model.getTabJeu()[i][j+1]=1;
            f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
            if (model.getTabVoisins()[i][j+1] == 0 && model.getTabJeu()[i][j+1] == 0){
                verifVoisins(i,j+1);
            }
        }else if (model.isTor()){
            if (j == 0 && i != 0 && i != model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1,j);
                }

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                model.getTabJeu()[i-1][j+1]=1;
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    verifVoisins(i-1,j+1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1,j);
                }

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                model.getTabJeu()[i+1][j+1]=1;
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    verifVoisins(i+1,j+1);
                }

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i,j+1);
                }

                f.getTabButton()[i][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[i][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[i][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(i,model.getNbcasecolonne()-1);
                }

                f.getTabButton()[i-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[i-1][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[i-1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i-1][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(i-1,model.getNbcasecolonne()-1);
                }

                f.getTabButton()[i+1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[i+1][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[i+1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i+1][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(i+1,model.getNbcasecolonne()-1);
                }

            } else if (i == 0 && j != 0 && j != model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i,j+1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i,j-1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1,j);
                }

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i+1][j+1]=1;
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    verifVoisins(i+1,j+1);
                }

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                model.getTabJeu()[i+1][j-1]=1;
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    verifVoisins(i+1,j-1);
                }

                f.getTabButton()[model.getNbcaseligne()-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][j]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j] == 0) {
                    verifVoisins(model.getNbcaseligne()-1,j);
                }

                f.getTabButton()[model.getNbcaseligne()-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j-1]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][j-1]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j-1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j-1] == 0) {
                    verifVoisins(model.getNbcaseligne()-1,j-1);
                }

                f.getTabButton()[model.getNbcaseligne()-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j+1]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][j+1]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j+1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j+1] == 0) {
                    verifVoisins(model.getNbcaseligne()-1,j+1);
                }

            } else if (i == model.getNbcaseligne()-1 && j != 0 && j != model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                model.getTabJeu()[i-1][j+1]=1;
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    verifVoisins(i-1, j+1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i-1][j-1]=1;
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    verifVoisins(i-1, j-1);
                }

                f.getTabButton()[0][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j]].getImage()));
                model.getTabJeu()[0][j]=1;
                if (model.getTabVoisins()[0][j] == 0 && model.getTabJeu()[0][j] == 0) {
                    verifVoisins(0, j);
                }

                f.getTabButton()[0][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j-1]].getImage()));
                model.getTabJeu()[0][j-1]=1;
                if (model.getTabVoisins()[0][j-1] == 0 && model.getTabJeu()[0][j-1] == 0) {
                    verifVoisins(0, j-1);
                }

                f.getTabButton()[0][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j+1]].getImage()));
                model.getTabJeu()[0][j+1]=1;
                if (model.getTabVoisins()[0][j+1] == 0 && model.getTabJeu()[0][j+1] == 0) {
                    verifVoisins(0, j+1);
                }
            } else if (j == model.getNbcasecolonne()-1 && i != 0 && i!=model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                model.getTabJeu()[i+1][j-1]=1;
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    verifVoisins(i+1, j-1);
                }

                f.getTabButton()[i+1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][0]].getImage()));
                model.getTabJeu()[i+1][0]=1;
                if (model.getTabVoisins()[i + 1][0] == 0 && model.getTabJeu()[i+1][0] == 0) {
                    verifVoisins(i+1, 0);
                }

                f.getTabButton()[i][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][0]].getImage()));
                model.getTabJeu()[i][0]=1;
                if (model.getTabVoisins()[i][0] == 0 && model.getTabJeu()[i][0] == 0) {
                    verifVoisins(i, 0);
                }

                f.getTabButton()[i-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][0]].getImage()));
                model.getTabJeu()[i-1][0]=1;
                if (model.getTabVoisins()[i-1][0] == 0 && model.getTabJeu()[i-1][0] == 0) {
                    verifVoisins(i-1, 0);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j-1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }
            } else if (i == model.getNbcaseligne()-1 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                model.getTabJeu()[i-1][j-1]=1;
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    verifVoisins(i-1, j-1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][0]].getImage()));
                model.getTabJeu()[i-1][0]=1;
                if (model.getTabVoisins()[i - 1][0] == 0 && model.getTabJeu()[i-1][0] == 0) {
                    verifVoisins(i-1, 0);
                }

                f.getTabButton()[i][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][0]].getImage()));
                model.getTabJeu()[i][0]=1;
                if (model.getTabVoisins()[i][0] == 0 && model.getTabJeu()[i][0] == 0) {
                    verifVoisins(i, 0);
                }

                f.getTabButton()[0][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][0]].getImage()));
                model.getTabJeu()[0][0]=1;
                if (model.getTabVoisins()[0][0] == 0 && model.getTabJeu()[0][0] == 0) {
                    verifVoisins(0, 0);
                }

                f.getTabButton()[0][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j]].getImage()));
                model.getTabJeu()[0][j]=1;
                if (model.getTabVoisins()[0][j] == 0 && model.getTabJeu()[0][j] == 0) {
                    verifVoisins(0, j);
                }

                f.getTabButton()[0][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j-1]].getImage()));
                model.getTabJeu()[0][j-1]=1;
                if (model.getTabVoisins()[0][j-1] == 0 && model.getTabJeu()[0][j-1] == 0) {
                    verifVoisins(0, j-1);
                }
            } else if (i == 0 && j == 0) {
                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                model.getTabJeu()[i+1][j+1]=1;
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    verifVoisins(i+1, j+1);
                }

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }

                f.getTabButton()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(model.getNbcaseligne()-1, model.getNbcasecolonne()-1);
                }

                f.getTabButton()[model.getNbcaseligne()-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][0]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][0]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][0] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][0] == 0) {
                    verifVoisins(model.getNbcaseligne()-1, 0);
                }

                f.getTabButton()[model.getNbcaseligne()-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j+1]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][j+1]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j+1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j+1] == 0) {
                    verifVoisins(model.getNbcaseligne()-1, j+1);
                }

                f.getTabButton()[0][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[0][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[0][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[0][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(0, model.getNbcasecolonne()-1);
                }

                f.getTabButton()[i+1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[i+1][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[i+1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i+1][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(i+1, model.getNbcasecolonne()-1);
                }
            } else if (i == model.getNbcaseligne()-1 && j == 0) {
                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                model.getTabJeu()[i-1][j+1]=1;
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    verifVoisins(i-1, j+1);
                }

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }

                f.getTabButton()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(model.getNbcaseligne()-1, model.getNbcasecolonne()-1);
                }

                f.getTabButton()[0][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[0][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[0][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[0][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(0, model.getNbcasecolonne()-1);
                }

                f.getTabButton()[0][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][0]].getImage()));
                model.getTabJeu()[0][0]=1;
                if (model.getTabVoisins()[0][0] == 0 && model.getTabJeu()[0][0] == 0) {
                    verifVoisins(0, 0);
                }

                f.getTabButton()[0][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j+1]].getImage()));
                model.getTabJeu()[0][j+1]=1;
                if (model.getTabVoisins()[0][1] == 0 && model.getTabJeu()[0][1] == 0) {
                    verifVoisins(0, j+1);
                }
            } else if (i == 0 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                model.getTabJeu()[i+1][j-1]=1;
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    verifVoisins(i+1, j-1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }

                f.getTabButton()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0) {
                    verifVoisins(model.getNbcaseligne()-1, model.getNbcasecolonne()-1);
                }

                f.getTabButton()[model.getNbcaseligne()-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][0]].getImage()));
                model.getTabJeu()[model.getNbcaseligne()-1][0]=1;
                if (model.getTabVoisins()[model.getNbcaseligne()-1][0] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][0] == 0) {
                    verifVoisins(model.getNbcaseligne()-1, 0);
                }

                f.getTabButton()[0][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][0]].getImage()));
                model.getTabJeu()[0][0]=1;
                if (model.getTabVoisins()[0][0] == 0 && model.getTabJeu()[0][0] == 0) {
                    verifVoisins(0, 0);
                }

                f.getTabButton()[i+1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][0]].getImage()));
                model.getTabJeu()[i+1][0]=1;
                if (model.getTabVoisins()[i+1][0] == 0 && model.getTabJeu()[i+1][0] == 0) {
                    verifVoisins(i+1, 0);
                }
            }
        }else {
            if (j == 0 && i!=0 && i!=model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                model.getTabJeu()[i-1][j+1]=1;
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    verifVoisins(i-1, j+1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                model.getTabJeu()[i+1][j+1]=1;
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    verifVoisins(i+1, j+1);
                }

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }
            } else if (i == 0 && j!=0 && j!=model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                model.getTabJeu()[i+1][j+1]=1;
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    verifVoisins(i+1, j+1);
                }

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                model.getTabJeu()[i+1][j-1]=1;
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    verifVoisins(i+1, j-1);
                }
            } else if (i == model.getNbcaseligne()-1 && j!=0 && j!= model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                model.getTabJeu()[i-1][j+1]=1;
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    verifVoisins(i-1, j+1);
                }

                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                model.getTabJeu()[i-1][j-1]=1;
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    verifVoisins(i-1, j-1);
                }
            } else if (j == model.getNbcasecolonne()-1 && i!=0 && i!=model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                model.getTabJeu()[i+1][j-1]=1;
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    verifVoisins(i+1, j-1);
                }

                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                model.getTabJeu()[i-1][j-1]=1;
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    verifVoisins(i-1, j-1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }
            } else if (i == model.getNbcaseligne()-1 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                model.getTabJeu()[i-1][j-1]=1;
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    verifVoisins(i-1, j-1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }
            } else if (i == 0 && j == 0) {
                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                model.getTabJeu()[i+1][j+1]=1;
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    verifVoisins(i+1, j+1);
                }

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }
            } else if (i == model.getNbcaseligne()-1 && j == 0) {
                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                model.getTabJeu()[i-1][j+1]=1;
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    verifVoisins(i-1, j+1);
                }

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                model.getTabJeu()[i-1][j]=1;
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    verifVoisins(i-1, j);
                }

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                model.getTabJeu()[i][j+1]=1;
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    verifVoisins(i, j+1);
                }
            } else if (i == 0 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                model.getTabJeu()[i+1][j-1]=1;
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    verifVoisins(i+1, j-1);
                }

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                model.getTabJeu()[i][j-1]=1;
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    verifVoisins(i, j-1);
                }

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                model.getTabJeu()[i+1][j]=1;
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    verifVoisins(i+1, j);
                }
            }
        }
    }
}

