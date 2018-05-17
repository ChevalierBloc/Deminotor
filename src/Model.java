import javax.swing.*;

public class Model {

    /*
    NULL = cases vides ;
    1 = mines
    2 = Mines explosé
     */

    private int nbcaseligne;
    private int nbcasecolonne;
    private int[][] tabMines;
    private int[][] tabVoisins ;
    private int[][] tabJeu ;
    private int nbMines ;
    private ImageIcon imagesMines ;

    public Model() {
        imagesMines = new ImageIcon("images/minotaur.png") ;
    }
    protected void setNbmineligne(int nb){
        this.nbcaseligne=nb;
    }

    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }

    protected void setNbminecolonne(int nb){
        this.nbcasecolonne=nb;
    }
    protected  void initTab(){
        tabMines = new int[nbcaseligne][nbcasecolonne];
        tabVoisins = new int[nbcaseligne][nbcasecolonne];
        tabJeu = new int[nbcaseligne][nbcasecolonne];
    }

    public int getNbcaseligne() {
        return nbcaseligne;
    }

    public int getNbcasecolonne() {
        return nbcasecolonne;
    }

    public int[][] getTabGrille() {
        return tabMines;
    }

    public int getNbMines() {
        return nbMines;
    }

    public void placeMine(){
        while(nbMines != 0){
            for (int i = 0; i <nbcaseligne; i++) {
                for (int j=0; i<nbcasecolonne ; j++){
                    int alea = (int)(Math.random()*100) ;
                    if (alea == 1 && nbMines > 0 && tabMines[i][j] == 1){
                        tabMines[i][j] = 1 ;
                        nbMines -=1 ;
                    }
                }

            }
        }
    }
    public void placeVoisins(){

    }
    public void estGagnant(){

    }
    public void estPerdu(){

    }
    public void decouvreCases(){

    }

}
