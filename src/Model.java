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

    public void setNbcaseligne(int nbcaseligne) {
        this.nbcaseligne = nbcaseligne;
    }

    public void setNbcasecolonne(int nbcasecolonne) {
        this.nbcasecolonne = nbcasecolonne;
    }

    protected int[][] getTabMines() {
        return tabMines;
    }

    protected int getNbMines() {
        return nbMines;
    }

    public int getNbMinesRestant() {
        return nbMinesRestant;
    }

    public void setNbMinesRestant(int nbMinesRestant) {
        this.nbMinesRestant = nbMinesRestant;
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

    protected void placeMine(){
        while(nbMines != 0){
            for (int i = 0; i <nbcaseligne; i++) {
                for (int j=0; i<nbcasecolonne ; j++){
                    int alea = (int)(Math.random()*100) ;
                    if (alea == 1 && nbMines > 0 && tabMines[i][j] != 1){
                        tabMines[i][j] = 1 ;
                        nbMines -=1 ;
                    }
                }

            }
        }
    }
    protected void placeVoisins(){

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected boolean estGagnant() {
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

//    protected boolean estPerdu(){
//        for (int i = 0 ; i<nbcaseligne;i++){
//            for (int j = 0 ; j<nbcasecolonne;j++){
//        if (tabJeu[i][j] == 10){
//            return false;
//        }
//
//    }

    protected void decouvreCases(){

    }

}
