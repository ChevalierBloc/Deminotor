import java.awt.image.BufferedImage;

public class Model {

    // 1 = mines

    private int nbcaseligne;
    private int nbcasecolonne;
    private int[][] tabGrille;
    int nbMines ;

    public Model() {

    }
    protected void setNbmineligne(int nb){
        this.nbcaseligne=nb;
    }

    protected void setNbminecolonne(int nb){
        this.nbcasecolonne=nb;
    }
    protected  void setTabGrille(){
        tabGrille = new int[nbcaseligne][nbcasecolonne];
    }

    public int getNbcaseligne() {
        return nbcaseligne;
    }

    public int getNbcasecolonne() {
        return nbcasecolonne;
    }

    public int[][] getTabGrille() {
        return tabGrille;
    }

    public int getNbMines() {
        return nbMines;
    }

    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }

    public void placeMine(){
        while(nbMines != 0){
            for (int i = 0; i <nbcaseligne; i++) {
                for (int j=0; i<nbcasecolonne ; j++){
                    int alea = (int)(Math.random()*100) ;
                    if (alea == 1 && nbMines > 0 && tabGrille[i][j] == 1){
                        tabGrille[i][j] = 1 ;
                        nbMines -=1 ;
                    }
                }

            }
        }
    }
    public void placeVoisins(){

    }
}
