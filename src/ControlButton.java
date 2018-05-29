

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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

        else if(source == f.getbValider()){
            if(!estUnEntier(f.getbNbLigne().getText())){
                f.genererErreur("ligne");
            }
            else if(!estUnEntier(f.getbNbColonne().getText())){
                f.genererErreur("colonne");
            }
            else if(!estUnEntier(f.getbNbMine().getText())){
                f.genererErreur("nbMines");
            }
            else {
                int nbLigne = Integer.parseInt(f.getbNbLigne().getText());
                int nbColonne = Integer.parseInt(f.getbNbColonne().getText());
                int nbMine = Integer.parseInt(f.getbNbMine().getText());
                if (nbLigne < 2 || nbLigne > 16) {
                    f.genererErreur("ligne");
                } else if (nbColonne < 2 || nbColonne > 30) {
                    f.genererErreur("colonne");
                } else if (nbMine > (nbLigne * nbColonne) / 2) {
                    f.genererErreur("nbMines");
                } else {
                    model.setNbCaseLigne(nbLigne);
                    model.setNbCaseColonne(nbColonne);
                    model.setNbMines(nbMine);
                    model.placeVoisinsNonTor();
                    f.actualiser();
                    f.changerVue(5);
                    f.setControlBoutonGrille(this);
                }
            }
        }

        else if(source == f.getbFacile()){
            model.setDifficulte(1);
            model.setNbCaseColonne(9);
            model.setNbCaseLigne(9);
            model.setNbMines(16);
            System.out.println(model.getNbMines());
            model.initTab();
            model.placeMine();
            model.placeVoisinsNonTor();
            f.actualiser();
            f.changerVue(5);
            f.setControlBoutonGrille(this);
        }

        else if(source == f.getbMoyen()){
            model.setDifficulte(2);
            model.setNbCaseColonne(16);
            model.setNbCaseLigne(16);
            model.setNbMines(52);
            model.initTab();
            model.placeMine();
            model.placeVoisinsNonTor();
            f.actualiser();
            f.changerVue(5);
            f.setControlBoutonGrille(this);
        }

        else if(source == f.getbDiffile()){
            model.setDifficulte(3);
            model.setNbCaseColonne(30);
            model.setNbCaseLigne(16);
            model.setNbMines(160);
            model.initTab();
            model.placeMine();
            model.placeVoisinsNonTor();
            f.actualiser();
            f.changerVue(5);
            f.setControlBoutonGrille(this);
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
        else{
            String bouton;
            for(int i=0; i<f.getTabButton().length; i++){
                for( int j=0; j<f.getTabButton()[i].length; j++){
                    bouton = ""+i+j;
                    if (bouton.equals(e.getActionCommand())) {
                        if(model.getTabMines()[i][j] == 1){
                            f.getTabButton()[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
                            System.out.println("perdu !!!!!!");
                        }else{
                            System.out.println(model.getTabVoisins()[i][j]);
                            f.getTabButton()[i][j].setText(String.valueOf(model.getTabVoisins()[i][j]));
                        }
                    }
                }
            }
        }
    }

    private boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }
}
