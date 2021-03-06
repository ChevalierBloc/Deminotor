

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.random;

public class ControlButtonJeuGrille implements ActionListener {
    Fenetre f;
    Model model;
    boolean enPause;
    ArrayList<Point> listVoisinDevoile;

    public ControlButtonJeuGrille(Model model, Fenetre f) {
        this.f = f;
        this.model = model;
        enPause = false;
        listVoisinDevoile = new ArrayList<>();
        f.setControlBoutonGrille(this);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String bouton;
        if(source == f.getbClique()){
            if(model.isPoseDrapeau()){
                model.setPoseDrapeau(false);
                f.getbClique().setIcon(new ImageIcon(model.getImageClique().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
            }
            else{
                model.setPoseDrapeau(true);
                f.getbClique().setIcon(new ImageIcon(model.getImageDrapeau().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
            }
        }
        else if (source == f.getbPause()){
            if(enPause){
                f.getTime().start();
                f.reprendre();
                enPause = false;
                f.getbPause().setText("Pause");
            }
            else{
                f.getTime().stop();
                f.pause();
                enPause = true;
                f.getbPause().setText("Reprendre");
            }
        }
        else if(source == f.getbAide()){
            ArrayList<Point> listBouton0 = new ArrayList<>();
            for (int i = 0; i < model.getTabMines().length; i++) {
                for (int j=0; j<model.getTabMines()[i].length ; j++){
                    if(model.getTabVoisins()[i][j] == 0 && model.getTabMines()[i][j]==0 && model.getTabJeu()[i][j]==0)
                        listBouton0.add(new Point(i, j));
                }
            }
            if(listBouton0.size()<=0){
                f.genererErreur("aide");
            }
            else {
                int max = listBouton0.size() - 1;
                int random = (int) (random() * listBouton0.size() - 1);
                f.aide((int) listBouton0.get(random).getX(), (int) listBouton0.get(random).getY());
                model.getTabJeu()[(int) listBouton0.get(random).getX()][(int) listBouton0.get(random).getY()] = 3;
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
                                    if (model.estGagnant()) {
                                        f.getTime().stop();
                                        f.gagner();
                                    }
                                }
                            }
                        } else {
                            if (model.getTabMines()[i][j] == 1) {
                                f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                                f.getTime().stop();
                                f.perdu();
                            } else {
                                if(model.getTabJeu()[i][j] == 2){

                                }else {
                                    if(model.getTabJeu()[i][j] == 3)
                                        f.getTabButton()[i][j].setBackground(null);
                                    model.getTabJeu()[i][j] = 1;
                                    if (model.getTabVoisins()[i][j]==0){
                                        verifVoisins(i,j); //faut enlever pour tester voisin
                                    }
                                    f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j]].getImage()));
                                    f.actualiser();
                                    if(model.estGagnant()) {
                                        f.getTime().stop();
                                        model.actualiserTab();
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

    private void verifVoisins(int i, int j){
        int x;
        int y;
        if ((i>0 && i < model.getNbcaseligne() - 1) && (j > 0 && j < model.getNbcasecolonne() -1)){
            f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
            if (model.getTabVoisins()[i-1][j] == 0 && model.getTabJeu()[i-1][j] == 0){
                listVoisinDevoile.add(new Point(i-1, j));
            }
            model.getTabJeu()[i-1][j]=1;

            f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
            if (model.getTabVoisins()[i-1][j+1] == 0 && model.getTabJeu()[i-1][j+1] == 0){
                listVoisinDevoile.add(new Point(i-1, j+1));
            }
            model.getTabJeu()[i-1][j+1]=1;

            f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
            if (model.getTabVoisins()[i-1][j-1] == 0 && model.getTabJeu()[i-1][j-1] == 0){
                listVoisinDevoile.add(new Point(i-1, j-1));
            }
            model.getTabJeu()[i-1][j-1]=1;

            f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
            if (model.getTabVoisins()[i+1][j] == 0 && model.getTabJeu()[i+1][j] == 0){
                listVoisinDevoile.add(new Point(i+1, j));
            }
            model.getTabJeu()[i+1][j]=1;

            f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
            if (model.getTabVoisins()[i+1][j+1] == 0 && model.getTabJeu()[i+1][j+1] == 0){
                listVoisinDevoile.add(new Point(i+1, j+1));
            }
            model.getTabJeu()[i+1][j+1]=1;

            f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
            if (model.getTabVoisins()[i+1][j-1] == 0 && model.getTabJeu()[i+1][j-1] == 0){
                listVoisinDevoile.add(new Point(i+1, j-1));
            }
            model.getTabJeu()[i+1][j-1]=1;

            f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
            if (model.getTabVoisins()[i][j-1] == 0 && model.getTabJeu()[i][j-1] == 0){
                listVoisinDevoile.add(new Point(i, j-1));
            }
            model.getTabJeu()[i][j-1]=1;

            f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
            if (model.getTabVoisins()[i][j+1] == 0 && model.getTabJeu()[i][j+1] == 0){
                listVoisinDevoile.add(new Point(i, j+1));
            }
            model.getTabJeu()[i][j+1]=1;
        }else if (model.isTor()){
            if (j == 0 && i != 0 && i != model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j+1));
                }
                model.getTabJeu()[i-1][j+1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j+1));
                }
                model.getTabJeu()[i+1][j+1]=1;

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[i][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(i, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[i][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[i-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[i-1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i-1][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[i-1][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[i+1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[i+1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i+1][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[i+1][model.getNbcasecolonne()-1]=1;

            } else if (i == 0 && j != 0 && j != model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j+1));
                }
                model.getTabJeu()[i+1][j+1]=1;

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j-1));
                }
                model.getTabJeu()[i+1][j-1]=1;

                f.getTabButton()[model.getNbcaseligne()-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, j));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][j]=1;

                f.getTabButton()[model.getNbcaseligne()-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j-1]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j-1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, j-1));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][j-1]=1;

                f.getTabButton()[model.getNbcaseligne()-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j+1]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j+1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, j+1));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][j+1]=1;

            } else if (i == model.getNbcaseligne()-1 && j != 0 && j != model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j+1));
                }
                model.getTabJeu()[i-1][j+1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j-1));
                }
                model.getTabJeu()[i-1][j-1]=1;

                f.getTabButton()[0][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j]].getImage()));
                if (model.getTabVoisins()[0][j] == 0 && model.getTabJeu()[0][j] == 0) {
                    listVoisinDevoile.add(new Point(0, j));
                }
                model.getTabJeu()[0][j]=1;

                f.getTabButton()[0][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j-1]].getImage()));
                if (model.getTabVoisins()[0][j-1] == 0 && model.getTabJeu()[0][j-1] == 0) {
                    listVoisinDevoile.add(new Point(0, j-1));
                }
                model.getTabJeu()[0][j-1]=1;

                f.getTabButton()[0][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j+1]].getImage()));
                if (model.getTabVoisins()[0][j+1] == 0 && model.getTabJeu()[0][j+1] == 0) {
                    listVoisinDevoile.add(new Point(0, j+1));
                }
                model.getTabJeu()[0][j+1]=1;
            } else if (j == model.getNbcasecolonne()-1 && i != 0 && i!=model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j-1));
                }
                model.getTabJeu()[i+1][j-1]=1;

                f.getTabButton()[i+1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][0]].getImage()));
                if (model.getTabVoisins()[i + 1][0] == 0 && model.getTabJeu()[i+1][0] == 0) {
                    listVoisinDevoile.add(new Point(i+1, 0));
                }
                model.getTabJeu()[i+1][0]=1;

                f.getTabButton()[i][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][0]].getImage()));
                if (model.getTabVoisins()[i][0] == 0 && model.getTabJeu()[i][0] == 0) {
                    listVoisinDevoile.add(new Point(i, 0));
                }
                model.getTabJeu()[i][0]=1;

                f.getTabButton()[i-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][0]].getImage()));
                if (model.getTabVoisins()[i-1][0] == 0 && model.getTabJeu()[i-1][0] == 0) {
                    listVoisinDevoile.add(new Point(i-1, 0));
                }
                model.getTabJeu()[i-1][0]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j-1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;
            } else if (i == model.getNbcaseligne()-1 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j-1));
                }
                model.getTabJeu()[i-1][j-1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][0]].getImage()));
                if (model.getTabVoisins()[i - 1][0] == 0 && model.getTabJeu()[i-1][0] == 0) {
                    listVoisinDevoile.add(new Point(i-1, 0));
                }
                model.getTabJeu()[i-1][0]=1;

                f.getTabButton()[i][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][0]].getImage()));
                if (model.getTabVoisins()[i][0] == 0 && model.getTabJeu()[i][0] == 0) {
                    listVoisinDevoile.add(new Point(i, 0));
                }
                model.getTabJeu()[i][0]=1;

                f.getTabButton()[0][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][0]].getImage()));
                if (model.getTabVoisins()[0][0] == 0 && model.getTabJeu()[0][0] == 0) {
                    listVoisinDevoile.add(new Point(0, 0));
                }
                model.getTabJeu()[0][0]=1;

                f.getTabButton()[0][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j]].getImage()));
                if (model.getTabVoisins()[0][j] == 0 && model.getTabJeu()[0][j] == 0) {
                    listVoisinDevoile.add(new Point(0, j));
                }
                model.getTabJeu()[0][j]=1;

                f.getTabButton()[0][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j-1]].getImage()));
                if (model.getTabVoisins()[0][j-1] == 0 && model.getTabJeu()[0][j-1] == 0) {
                    listVoisinDevoile.add(new Point(0, j-1));
                }
                model.getTabJeu()[0][j-1]=1;
            } else if (i == 0 && j == 0) {
                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j+1));
                }
                model.getTabJeu()[i+1][j+1]=1;

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[model.getNbcaseligne()-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][0]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][0] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][0] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, 0));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][0]=1;

                f.getTabButton()[model.getNbcaseligne()-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][j+1]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][j+1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, j+1));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][j+1]=1;

                f.getTabButton()[0][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[0][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[0][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(0, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[0][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[i+1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[i+1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[i+1][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[i+1][model.getNbcasecolonne()-1]=1;
            } else if (i == model.getNbcaseligne()-1 && j == 0) {
                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j+1));
                }
                model.getTabJeu()[i-1][j+1]=1;

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[0][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[0][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[0][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(0, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[0][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[0][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][0]].getImage()));
                if (model.getTabVoisins()[0][0] == 0 && model.getTabJeu()[0][0] == 0) {
                    listVoisinDevoile.add(new Point(0, 0));
                }
                model.getTabJeu()[0][0]=1;

                f.getTabButton()[0][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][j+1]].getImage()));
                if (model.getTabVoisins()[0][j+1] == 0 && model.getTabJeu()[0][j+1] == 0) {
                    listVoisinDevoile.add(new Point(0, j+1));
                }
                model.getTabJeu()[0][j+1]=1;
            } else if (i == 0 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j-1));
                }
                model.getTabJeu()[i+1][j-1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, model.getNbcasecolonne()-1));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][model.getNbcasecolonne()-1]=1;

                f.getTabButton()[model.getNbcaseligne()-1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[model.getNbcaseligne()-1][0]].getImage()));
                if (model.getTabVoisins()[model.getNbcaseligne()-1][0] == 0 && model.getTabJeu()[model.getNbcaseligne()-1][0] == 0) {
                    listVoisinDevoile.add(new Point(model.getNbcaseligne()-1, 0));
                }
                model.getTabJeu()[model.getNbcaseligne()-1][0]=1;

                f.getTabButton()[0][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[0][0]].getImage()));
                if (model.getTabVoisins()[0][0] == 0 && model.getTabJeu()[0][0] == 0) {
                    listVoisinDevoile.add(new Point(0, 0));
                }
                model.getTabJeu()[0][0]=1;

                f.getTabButton()[i+1][0].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][0]].getImage()));
                if (model.getTabVoisins()[i+1][0] == 0 && model.getTabJeu()[i+1][0] == 0) {
                    listVoisinDevoile.add(new Point(i+1, 0));
                }
                model.getTabJeu()[i+1][0]=1;
            }
        }else {
            if (j == 0 && i!=0 && i!=model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j+1));
                }
                model.getTabJeu()[i-1][j+1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j+1));
                }
                model.getTabJeu()[i+1][j+1]=1;

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;
            } else if (i == 0 && j!=0 && j!=model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j+1));
                }
                model.getTabJeu()[i+1][j+1]=1;

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j-1));
                }
                model.getTabJeu()[i+1][j-1]=1;
            } else if (i == model.getNbcaseligne()-1 && j!=0 && j!= model.getNbcasecolonne()-1) {
                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j+1));
                }
                model.getTabJeu()[i-1][j+1]=1;

                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j-1));
                }
                model.getTabJeu()[i-1][j-1]=1;
            } else if (j == model.getNbcasecolonne()-1 && i!=0 && i!=model.getNbcaseligne()-1) {
                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;

                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j-1));
                }
                model.getTabJeu()[i+1][j-1]=1;

                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j-1));
                }
                model.getTabJeu()[i-1][j-1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;
            } else if (i == model.getNbcaseligne()-1 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i-1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j-1]].getImage()));
                if (model.getTabVoisins()[i - 1][j - 1] == 0 && model.getTabJeu()[i-1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j-1));
                }
                model.getTabJeu()[i-1][j-1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;
            } else if (i == 0 && j == 0) {
                f.getTabButton()[i+1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j+1]].getImage()));
                if (model.getTabVoisins()[i + 1][j + 1] == 0 && model.getTabJeu()[i+1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j+1));
                }
                model.getTabJeu()[i+1][j+1]=1;

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;
            } else if (i == model.getNbcaseligne()-1 && j == 0) {
                f.getTabButton()[i-1][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j+1]].getImage()));
                if (model.getTabVoisins()[i - 1][j + 1] == 0 && model.getTabJeu()[i-1][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j+1));
                }
                model.getTabJeu()[i-1][j+1]=1;

                f.getTabButton()[i-1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i-1][j]].getImage()));
                if (model.getTabVoisins()[i - 1][j] == 0 && model.getTabJeu()[i-1][j] == 0) {
                    listVoisinDevoile.add(new Point(i-1, j));
                }
                model.getTabJeu()[i-1][j]=1;

                f.getTabButton()[i][j+1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j+1]].getImage()));
                if (model.getTabVoisins()[i][j + 1] == 0 && model.getTabJeu()[i][j+1] == 0) {
                    listVoisinDevoile.add(new Point(i, j+1));
                }
                model.getTabJeu()[i][j+1]=1;
            } else if (i == 0 && j == model.getNbcasecolonne()-1) {
                f.getTabButton()[i+1][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j-1]].getImage()));
                if (model.getTabVoisins()[i + 1][j - 1] == 0 && model.getTabJeu()[i+1][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j-1));
                }
                model.getTabJeu()[i+1][j-1]=1;

                f.getTabButton()[i][j-1].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i][j-1]].getImage()));
                if (model.getTabVoisins()[i][j - 1] == 0 && model.getTabJeu()[i][j-1] == 0) {
                    listVoisinDevoile.add(new Point(i, j-1));
                }
                model.getTabJeu()[i][j-1]=1;

                f.getTabButton()[i+1][j].setIcon(new ImageIcon(model.getImageNombres()[model.getTabVoisins()[i+1][j]].getImage()));
                if (model.getTabVoisins()[i + 1][j] == 0 && model.getTabJeu()[i+1][j] == 0) {
                    listVoisinDevoile.add(new Point(i+1, j));
                }
                model.getTabJeu()[i+1][j]=1;
            }
        }
        if(listVoisinDevoile.size()>0){
            x = (int) listVoisinDevoile.get(listVoisinDevoile.size()-1).getX();
            y = (int) listVoisinDevoile.get(listVoisinDevoile.size()-1).getY();
            listVoisinDevoile.remove(listVoisinDevoile.size()-1);
            verifVoisins(x, y);
        }
    }
}

