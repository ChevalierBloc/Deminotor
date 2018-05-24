import javax.swing.*;

public class Model {
    /*
    NULL = cases vides ;
    1 = mines
    2 = Mines explosé
     */
    private int score;
    private int difficulte;
    private boolean estTor;
    private int nbcaseligne;
    private int nbcasecolonne;
    private int[][] tabMines;
    private int[][] tabVoisins ;
    private int[][] tabJeu ;
    private int nbMines ;
    private int nbMinesRestant;
    private ImageIcon imagesMines ;
    private int x;
    private int y;

    public Model() {
        imagesMines = new ImageIcon("images/minotaur.png") ;
    }

    public ImageIcon getImagesMines() {
        return imagesMines;
    }
    public int[][] getTabMines() {
        return tabMines;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


    public int getNbcaseligne() {
        return nbcaseligne;
    }
    public int getNbcasecolonne() {
        return nbcasecolonne;
    }
    public int getNbMines() {
        return nbMines;
    }

    public void setNbCaseLigne(int nbcaseligne) {
        this.nbcaseligne = nbcaseligne;
    }
    public void setNbCaseColonne(int nbcasecolonne) {
        this.nbcasecolonne = nbcasecolonne;
    }
    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }


    public int getNbMinesRestant() {
        return nbMinesRestant;
    }
    public void setNbMinesRestant(int nbMinesRestant) {
        this.nbMinesRestant = nbMinesRestant;
    }

    public void setDifficulte(int difficulte){
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

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getIndice(String indice) {
        int n = 0;
        for (int i=0; i < indice.length();i++){
            char digit = indice.charAt(i);
            n = n*10+digit-'0';
        }
        return n;
    }

    public void placeMine(){
        int alea;
        while(nbMines > 0){
            for (int i = 0; i <tabMines.length; i++) {
                for (int j=0; j<tabMines[i].length ; j++){
                    System.out.println(j);
                    alea = (int)(Math.random()*100) ;
                    if (alea == 1 && nbMines > 0 && tabMines[i][j] != 1){
                        tabMines[i][j] = 1 ;
                        nbMines -=1 ;
                    }
                }

            }
        }
    }

    public void initTab(){
        tabMines = new int[nbcaseligne][nbcasecolonne];
        tabVoisins = new int[nbcaseligne][nbcasecolonne];
        tabJeu = new int[nbcaseligne][nbcasecolonne];
    }

    protected void placeVoisins(){

    }

    public boolean estGagnant() {
        for (int i = 0 ; i<nbcaseligne;i++){
            for (int j = 0 ; j<nbcasecolonne;j++){
                //Verifie si la cases n'est pas découvert
                if (tabJeu[i][j] == 0){
                    return false;
                }
            }
        }
        return true ;
    }

//    public boolean estPerdu(){
//        for (int i = 0 ; i<nbcaseligne;i++){
//            for (int j = 0 ; j<nbcasecolonne;j++){
//        if (tabJeu[i][j] == 10){
//            return false;
//        }
//
//    }

    public void decouvreCases(){

    }

}
