public class Model {

    private int nbcaseligne;
    private int nbcasecolonne;
    private int[][] tabGrille;

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
}
