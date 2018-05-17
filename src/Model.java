import javax.swing.*;

public class Model {
    /*
    NULL = cases vides ;
    1 = mines
    2 = Mines explosé
     */
    private int difficulte;
    private boolean estTor;
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

    protected void setNbMines(int nbMines) {
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

    protected int getNbcaseligne() {
        return nbcaseligne;
    }

    protected int getNbcasecolonne() {
        return nbcasecolonne;
    }

    protected int[][] getTabGrille() {
        return tabMines;
    }

    protected int getNbMines() {
        return nbMines;
    }
    protected void setDifficulte(int difficulte){
        this.difficulte = difficulte;
    }
    public int getDifficulte() {
        return difficulte;
    }
    public void setEstTor(boolean estTor) {
        this.estTor = estTor;
    }
    public boolean isEstTor() {
        return estTor;
    }



    protected void placeMine(){
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
    protected void placeVoisins(){

    }
    protected void estGagnant(){

    }
    protected void estPerdu(){

    }
    protected void decouvreCases(){

    }

}
