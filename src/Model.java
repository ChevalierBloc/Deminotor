import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;

public class Model {
    private Clip clip;
    private final File fileMusique = new File("musique/musiqueFond.wav");

    private int[][][] tabScore;

    private final ImageIcon[] imageNombres = {new ImageIcon("images/nombre0.png"), new ImageIcon("images/nombre1.png"), new ImageIcon("images/nombre2.png"), new ImageIcon("images/nombre3.png"), new ImageIcon("images/nombre4.png"), new ImageIcon("images/nombre5.png"), new ImageIcon("images/nombre6.png"), new ImageIcon("images/nombre7.png"), new ImageIcon("images/nombre8.png")};
    private final ImageIcon imagesMines = new ImageIcon("images/minotaur.png");
    private final ImageIcon imageDrapeau = new ImageIcon("images/drapeau.png");
    private final ImageIcon imageClique = new ImageIcon("images/clique.png") ;

    //LES TABLEAUX UTILISES

    //tableau a 0 si pas de mines, a 1 si une mine
    //tableau contenant les informations sur les mines
    private int[][] tabMines;

    //tableau contenant le nombre de mines voisines
    private int[][] tabVoisins ;

    // 0 = bouton non active,
    // 1 = bouton active affichage du nombre,
    // 2 = bouton active affichage drapeau,
    // 3 = bouton non active avec aide
    //tableau contenant les information sur la selection d'un bouton
    private int[][] tabJeu ;

    //LES VARIABLES

    //le score
    private int score;

    //la difficulte
    private int difficulte;

    //le nombre de case par ligne
    private int nbcaseligne;

    //le nombre de case par colonne
    private int nbcasecolonne;

    //le nombre de mine sur une grille
    private int nbMines ;

    //le nombre de mine non trouvee, donc le nombre de drapeau restant dispo
    private int nbMinesRestant;

    //coordonnees
    private int x;
    private int y;

    //LES BOOLEENS

    //booleen permettant de savoir si on est en mode deminage ou drapeau
    private boolean poseDrapeau;

    //booleen permettant de savoir si la musique est active ou non
    private boolean music ;

    //booleen permettant de savoir si on est en mode tor ou non
    private boolean tor;

    public Model() {
        tabScore = new int[2][3][3]; // 2 : tor/Normal, 3: Difficulte et 3: les 3 meilleurs temps
        setTabScore();
        initMusique();
        startMusique();
    }

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

    // GETTERS

    public Clip getClip() {
        return clip;
    }

    public File getFileMusique() {
        return fileMusique;
    }

    public int[][][] getTabScore() {
        return tabScore;
    }

    /*
     * retourne le tableau tabMines
     **/
    public int[][] getTabMines() {
        return tabMines;
    }
    /*
     * retourne l'indice x
     * */
    public int getX() {
        return x;
    }

    /*
     * retourne l'indice y
     * */
    public int getY() {
        return y;
    }

    /*
     * retourne le nombre de case sur une ligne
     * */
    public int getNbcaseligne() {
        return nbcaseligne;
    }

    /*
     * retourne le nombre de cases sur une colonne
     * */
    public int getNbcasecolonne() {
        return nbcasecolonne;
    }

    /*
     * retourne le nombre de mine d'une case donnee
     * */
    public int getNbMines() {
        return nbMines;
    }

    /*
     * retourne le nombre de mines restantes et donc le nombre de drapeau restant
     * */
    public int getNbMinesRestant() {
        return nbMinesRestant;
    }

    /*
     * retourne le niveau de difficulte
     * */
    public int getDifficulte() {
        return difficulte;
    }

    /*
     * retourne si le jeu est tor ou non
     * */
    public boolean isTor() {
        return tor;
    }

    public void setEstTor(boolean estTor){
        tor = estTor ;
    }

    /*
     * retourne le tableau tabVoisine
     * */
    public int[][] getTabVoisins() {
        return tabVoisins;
    }

    /*
     * retourne le tableau tabJeu
     * */
    public int[][] getTabJeu() {
        return tabJeu;
    }

    /*
     * retourne le score
     * */
    public int getScore() {
        return score;
    }

    /*
     * retourne un booleen qui permet de savoir si on est en mode posage de drapeau ou deminage
     * */
    public boolean isPoseDrapeau() {
        return poseDrapeau;
    }

    /*
     * retourne un booleen pour savoir si la musqiue est activee
     * */
    public boolean isMusic() {
        return music;
    }

    /*
     * retourne l'indice d'un bouton par rapport a son nom
     * */
    public int getIndice(String indice) {
        int n = 0;
        for (int i=0; i < indice.length();i++){
            char digit = indice.charAt(i);
            n = n*10+digit-'0';
        }
        return n;
    }

    //SETTERS

    /*
     * l'indice x prend la valeur donnee en parametre
     * */
    public void setX(int x) {
        this.x = x;
    }

    /*
     * l'indice y prend la valeur donnee en parametre
     * */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * Le nombre de case par ligne prend la valeur donnee en parametre
     * */
    public void setNbCaseLigne(int nbcaseligne) {
        this.nbcaseligne = nbcaseligne;
    }

    /*
     * le nombre de case par colonne prend la valeur donnee en parametre
     * */
    public void setNbCaseColonne(int nbcasecolonne) {
        this.nbcasecolonne = nbcasecolonne;
    }

    /*
     * le nombre de mines prend la valeur donnee en parametre
     * */
    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }

    /*
     * le nombre de mines/drapeaux restant prend la valeur donnee en parametre
     * */
    public void setNbMinesRestant(int nbMinesRestant) {
        this.nbMinesRestant = nbMinesRestant;
    }

    /*
     * la difficulte prend la valeur donnee en parametre
     * */
    public void setDifficulte(int difficulte){
        this.difficulte = difficulte;
    }

    /*
     * le booleen estTor prend la valeur donnee en parametre
     * */
    public void setTor(boolean tor) {
        this.tor = tor;
    }

    /*
     * le boolean poseDrapeau prend la valeur donnee en parametre
     * */
    public void setPoseDrapeau(boolean poseDrapeau) {
        this.poseDrapeau = poseDrapeau;
    }

    /*
     * le boolean music prend la valeur donnee en parametre
     * */
    public void setMusic(boolean music) {
        this.music = music;
    }

    /*
     * le score prend la valeur donnee en parametre
     * */
    public void setScore(int score) {
        this.score = score;
    }

    //FIN DES SETTERS

    private void initMusique(){
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(fileMusique);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusique(){
        clip.stop();
    }

    public void startMusique(){
        clip.loop(70);
    }

    /**
     * place les mines dans le tableau d'une maniere aleatoire
     * tant que le nombre de mines max n'est pas atteint
     */
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

    /**
     * initialise les tableaux
     */
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
                }else if (tor){
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

    private void setTabScore(){
        setTabScoreNormal();
        setTabScoreTor();
    }

    private void setTabScoreNormal(){
        BufferedReader br;
        String nombre;
        int cptIndice = 0;

        try {
            br = new BufferedReader(new FileReader("Score/Normal/ScoreFacile.txt"));
            while ((nombre = br.readLine() ) != null && cptIndice< 3){
                tabScore[0][0][cptIndice] = Integer.parseInt(nombre);
                cptIndice++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cptIndice = 0;
        try {
            br = new BufferedReader(new FileReader("Score/Normal/ScoreMoyen.txt"));
            while ((nombre = br.readLine() ) != null && cptIndice< 3){
                tabScore[0][1][cptIndice] = Integer.parseInt(nombre);
                cptIndice++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cptIndice = 0;
        try {
            br = new BufferedReader(new FileReader("Score/Normal/ScoreDifficile.txt"));
            while ((nombre = br.readLine() ) != null && cptIndice< 3){
                tabScore[0][2][cptIndice] = Integer.parseInt(nombre);
                cptIndice++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTabScoreTor(){
        BufferedReader br;
        String nombre;
        int cptIndice = 0;

        try {
            br = new BufferedReader(new FileReader("Score/Tor/ScoreFacile.txt"));
            while ((nombre = br.readLine() ) != null && cptIndice< 3){
                tabScore[1][0][cptIndice] = Integer.parseInt(nombre);
                cptIndice++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cptIndice = 0;
        try {
            br = new BufferedReader(new FileReader("Score/Tor/ScoreMoyen.txt"));
            while ((nombre = br.readLine() ) != null && cptIndice< 3){
                tabScore[1][1][cptIndice] = Integer.parseInt(nombre);
                cptIndice++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        cptIndice = 0;
        try {
            br = new BufferedReader(new FileReader("Score/Tor/ScoreDifficile.txt"));
            while ((nombre = br.readLine() ) != null && cptIndice< 3){
                tabScore[1][2][cptIndice] = Integer.parseInt(nombre);
                cptIndice++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualiserTab(){
        switch(difficulte){
            case 1:
                actualiserTabScoreFacile();
                break;
            case 2:
                actualiserTabScoreMoyen();
                break;
            case 3:
                actualiserTabScoreDifficile();
                break;
        }
        actualiserFichier();
    }

    private void actualiserTabScoreFacile(){
        if(tor){
            if( score < tabScore[1][0][0]){
                int tmp = tabScore[1][0][0];
                int tmp2 = tabScore[1][0][1];
                tabScore[1][0][0] = score;
                tabScore[1][0][1] = tmp;
                tabScore[1][0][2] = tmp2;
            }
            else if ( score > tabScore[1][0][0] && score < tabScore[1][0][1] ){
                int tmp = tabScore[1][0][1];
                tabScore[1][0][1] = score;
                tabScore[1][0][2] = tmp;
            }
            else if ( score > tabScore[1][0][1] && score < tabScore[1][0][2] ){
                tabScore[1][0][2] = score;
            }
        }else{
            if( score < tabScore[0][0][0]){
                int tmp = tabScore[0][0][0];
                int tmp2 = tabScore[0][0][1];
                tabScore[0][0][0] = score;
                tabScore[0][0][1] = tmp;
                tabScore[0][0][2] = tmp2;
            }
            else if ( score > tabScore[0][0][0] && score < tabScore[0][0][1] ){
                int tmp = tabScore[0][0][1];
                tabScore[0][0][1] = score;
                tabScore[0][0][2] = tmp;
            }
            else if ( score > tabScore[0][0][1] && score < tabScore[0][0][2] ){
                tabScore[0][0][2] = score;
            }
        }
    }

    private void actualiserTabScoreMoyen(){
        if(tor){
            if( score < tabScore[1][1][0]){
                int tmp = tabScore[1][1][0];
                int tmp2 = tabScore[1][1][1];
                tabScore[1][1][0] = score;
                tabScore[1][1][1] = tmp;
                tabScore[1][1][2] = tmp2;
            }
            else if ( score > tabScore[1][1][0] && score < tabScore[1][1][1] ){
                int tmp = tabScore[1][1][1];
                tabScore[1][1][1] = score;
                tabScore[1][1][2] = tmp;
            }
            else if ( score > tabScore[1][1][1] && score < tabScore[1][1][2] ){
                tabScore[1][1][2] = score;
            }
        }else{
            if( score < tabScore[0][1][0]){
                int tmp = tabScore[0][1][0];
                int tmp2 = tabScore[0][1][1];
                tabScore[0][1][0] = score;
                tabScore[0][1][1] = tmp;
                tabScore[0][1][2] = tmp2;
            }
            else if ( score > tabScore[0][1][0] && score < tabScore[0][1][1] ){
                int tmp = tabScore[0][1][1];
                tabScore[0][1][1] = score;
                tabScore[0][1][2] = tmp;
            }
            else if ( score > tabScore[0][1][1] && score < tabScore[0][1][2] ){
                tabScore[0][1][2] = score;
            }
        }
    }

    private void actualiserTabScoreDifficile(){
        if(tor){
            if( score < tabScore[1][2][0]){
                int tmp = tabScore[1][2][0];
                int tmp2 = tabScore[1][2][1];
                tabScore[1][2][0] = score;
                tabScore[1][2][1] = tmp;
                tabScore[1][2][2] = tmp2;
            }
            else if ( score > tabScore[1][2][0] && score < tabScore[1][2][1] ){
                int tmp = tabScore[1][2][1];
                tabScore[1][2][1] = score;
                tabScore[1][2][2] = tmp;
            }
            else if ( score > tabScore[1][2][1] && score < tabScore[1][2][2] ){
                tabScore[1][2][2] = score;
            }
        }else{
            if( score < tabScore[0][2][0]){
                int tmp = tabScore[0][2][0];
                int tmp2 = tabScore[0][2][1];
                tabScore[0][2][0] = score;
                tabScore[0][2][1] = tmp;
                tabScore[0][2][2] = tmp2;
            }
            else if ( score > tabScore[0][2][0] && score < tabScore[0][2][1] ){
                int tmp = tabScore[0][2][1];
                tabScore[0][2][1] = score;
                tabScore[0][2][2] = tmp;
            }
            else if ( score > tabScore[0][2][1] && score < tabScore[0][2][2] ){
                tabScore[0][2][2] = score;
            }
        }
    }

    private void actualiserFichier(){
        actualiserFichierNormal();
        actualiserFichierTor();
    }

    private void actualiserFichierTor(){
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter("Score/Tor/ScoreFacile.txt"));
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(tabScore[1][0][i]));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw = new BufferedWriter(new FileWriter("Score/Tor/ScoreMoyen.txt"));
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(tabScore[1][1][i]));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw = new BufferedWriter(new FileWriter("Score/Tor/ScoreDifficile.txt"));
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(tabScore[1][2][i]));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualiserFichierNormal() {
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter("Score/Normal/ScoreFacile.txt"));
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(tabScore[0][0][i]));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw = new BufferedWriter(new FileWriter("Score/Normal/ScoreMoyen.txt"));
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(tabScore[0][1][i]));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw = new BufferedWriter(new FileWriter("Score/Normal/ScoreDifficile.txt"));
            for(int i=0; i<3; i++){
                bw.write(String.valueOf(tabScore[0][2][i]));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
