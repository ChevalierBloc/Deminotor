import javax.swing.*;

public class Model {
    /*
    NULL = cases vides ;
    1 = mines
    2 = Mines explosé
     */
    private final ImageIcon[] imageNombres = {new ImageIcon("images/nombre0.png"), new ImageIcon("images/nombre1.png"), new ImageIcon("images/nombre2.png"), new ImageIcon("images/nombre3.png"), new ImageIcon("images/nombre4.png"), new ImageIcon("images/nombre5.png")};
    private final ImageIcon imagesMines = new ImageIcon("images/minotaur.png");
    private final ImageIcon imageDrapeau = new ImageIcon("images/drapeau.png");
    private final ImageIcon imageClique = new ImageIcon("images/clique.png") ;
    private int score;
    private int difficulte;
    private boolean estTor;
    private int nbcaseligne;
    private int nbcasecolonne;
    private int[][] tabMines;
    private int[][] tabVoisins ;
    private int[][] tabJeu ;  // 0 = bouton non active, 1 = bouton activer nombre , 2 = bouton activer drapeau
    private int nbMines ;
    private int nbMinesRestant;
    private int x;
    private int y;
    private boolean isMouseRightClic;
    private boolean music ;

    public Model() {}

    public ImageIcon[] getImageNombres() {
        return imageNombres;
    }
    public ImageIcon getImagesMines() {
        return imagesMines;
    }
    public ImageIcon getImageDrapeau() {
        return imageDrapeau;
    }
    public ImageIcon getImageClique() {
        return imageClique;
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

    public int[][] getTabVoisins() {
        return tabVoisins;
    }
    public int[][] getTabJeu() {
        return tabJeu;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public boolean isMouseRightClic() {
        return isMouseRightClic;
    }
    public void setMouseRightClic(boolean MouseRightClic) {
        isMouseRightClic = MouseRightClic;
    }

    public boolean isMusic() {
        return music;
    }
    public void setMusic(boolean music) {
        this.music = music;
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
        int nbMineTmp = nbMines;
        while(nbMineTmp > 0){
            for (int i = 0; i <tabMines.length; i++) {
                for (int j=0; j<tabMines[i].length ; j++){
                    alea = (int)(Math.random()*100) ;
                    if (alea == 1 && nbMineTmp > 0 && tabMines[i][j] != 1){
                        tabMines[i][j] = 1 ;
                        nbMineTmp -=1 ;
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


    public void setNbVoisin(){
        for (int i = 0; i < nbcaseligne; i++) {
            for (int j = 0; j < nbcasecolonne; j++) {
                if ((i>0 && i < nbcaseligne - 1) && (j > 0 && j < nbcasecolonne -1)){
                    if (tabMines[i-1][j] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i-1][j+1] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i-1][j-1] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i+1][j] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i+1][j+1] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i+1][j-1] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i][j-1] == 1){
                        tabVoisins[i][j]++;
                    }
                    if (tabMines[i][j+1] == 1){
                        tabVoisins[i][j]++;
                    }
                }else if (estTor){
                    if (j == 0 && i != 0 && i != nbcaseligne-1) {
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i-1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i+1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == 0 && j != 0 && j != nbcasecolonne-1) {
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][j-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][j+1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == nbcaseligne-1 && j != 0 && j != nbcasecolonne-1) {
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][j-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][j+1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (j == nbcasecolonne-1 && i != 0 && i!=nbcaseligne-1) {
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i-1][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == nbcaseligne-1 && j == nbcasecolonne-1) {
                        if (tabMines[i - 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }

                        if (tabMines[i - 1][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][j-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == 0 && j == 0) {
                        if (tabMines[i + 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == nbcaseligne-1 && j == 0) {
                        if (tabMines[i - 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == 0 && j == nbcasecolonne-1) {
                        if (tabMines[i + 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][nbcasecolonne-1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[nbcaseligne-1][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[0][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[1][0] == 1) {
                            tabVoisins[i][j]++;
                        }
                    }
                }else {
                    if (j == 0 && i!=0 && i!=nbcaseligne-1) {
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == 0 && j!=0 && j!=nbcasecolonne-1) {
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == nbcaseligne-1 && j!=0 && j!= nbcasecolonne-1) {
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (j == nbcasecolonne-1 && i!=0 && i!=nbcaseligne-1) {
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == nbcaseligne-1 && j == nbcasecolonne-1) {
                        if (tabMines[i - 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == 0 && j == 0) {
                        if (tabMines[i + 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == nbcaseligne-1 && j == 0) {
                        if (tabMines[i - 1][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i - 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j + 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                    } else if (i == 0 && j == nbcasecolonne-1) {
                        if (tabMines[i + 1][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i][j - 1] == 1) {
                            tabVoisins[i][j]++;
                        }
                        if (tabMines[i + 1][j] == 1) {
                            tabVoisins[i][j]++;
                        }
                    }
                }
            }
        }
    }

    public boolean drapeauPosse(){
        return nbMinesRestant<=0;
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
}
