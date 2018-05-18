import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Fenetre extends JFrame {
    private Model model;

    JPanel panGeneral;

    private JButton bJeu;
    private JButton bOption;
    private JButton bQuitter;

    private JLabel imageDemineur;
    private JLabel nomDemineur;

    private JLabel nomDemineurJouer;
    private JButton bNorma;
    private JButton bTor;
    private JButton bPerso;
    private JButton bRetourJouer;

    private JLabel nomDemineurOption;
    private JButton bRetourOption;

    private JLabel nomDemineurPerso;
    private JButton bRetourPerso;

    private JButton bFacile;
    private JButton bMoyen;
    private JButton bDiffile;

    private JLabel  nomDemineurDifficulte;
    private JButton bRetourDifficulte;

    private JLabel  lScore;
    private JLabel  lMine;
    private JButton bRetourGrille;

    private JButton[][] tabButton;

    public Fenetre(Model model) {
        this.model = model;
        initAttribut();
        ajouterWidgetVuePrincipal();
        this.setIconImage(model.getImagesMines().getImage());
        setSize(700,500);
        setTitle("Déminotor");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
    }

    public JButton getbJeu() {
        return bJeu;
    }
    public JButton getbOption() {
        return bOption;
    }
    public JButton getbQuitter() {
        return bQuitter;
    }


    public JButton getbNorma() {
        return bNorma;
    }
    public JButton getbTor() {
        return bTor;
    }
    public JButton getbPerso() {
        return bPerso;
    }
    public JButton getbRetourJouer() {
        return bRetourJouer;
    }


    public JButton getbRetourOption() {
        return bRetourOption;
    }


    public JButton getbRetourPerso() {
        return bRetourPerso;
    }


    public JButton getbFacile() {
        return bFacile;
    }
    public JButton getbMoyen() {
        return bMoyen;
    }
    public JButton getbDiffile() {
        return bDiffile;
    }
    public JButton getbRetourDifficulte() {
        return bRetourDifficulte;
    }


    public JLabel getlScore() {
        return lScore;
    }
    public JLabel getlMine() {
        return lMine;
    }
    public JButton getbRetourGrille() {
        return bRetourGrille;
    }


    public void setControlBouton(ActionListener actionListener){
        bJeu.addActionListener(actionListener);
        bOption.addActionListener(actionListener);
        bQuitter.addActionListener(actionListener);

        bNorma.addActionListener(actionListener);
        bTor.addActionListener(actionListener);
        bPerso.addActionListener(actionListener);
        bRetourJouer.addActionListener(actionListener);

        bRetourOption.addActionListener(actionListener);

        bRetourPerso.addActionListener(actionListener);

        bFacile.addActionListener(actionListener);
        bMoyen.addActionListener(actionListener);
        bDiffile.addActionListener(actionListener);
        bRetourDifficulte.addActionListener(actionListener);

        bRetourGrille.addActionListener(actionListener);
    }

    public void initAttribut(){
        panGeneral = new JPanel();

        imageDemineur = new JLabel(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH)));
        nomDemineur = new JLabel("Déminotor");
        bJeu = new JButton("Jouer");
        bOption = new JButton("Option");
        bQuitter = new JButton("Quitter");

        nomDemineurJouer = new JLabel("Jouer");
        bNorma = new JButton("Jeu Normal");
        bTor = new JButton("Jeu TOR");
        bPerso = new JButton("Jeu Personnaliser");
        bRetourJouer = new JButton("Retour");

        nomDemineurOption = new JLabel("Option");
        bRetourOption = new JButton("Retour");

        nomDemineurPerso = new JLabel("Personnaliser");
        bRetourPerso = new JButton("Retour");

        nomDemineurDifficulte = new JLabel("Difficulté");
        bFacile = new JButton("Facile");
        bMoyen = new JButton("Moyen");
        bDiffile = new JButton("Difficile");
        bRetourDifficulte = new JButton("Retour");

        lScore = new JLabel("Score :"+model.getScore());
        lMine = new JLabel("Mines Restantes :"+model.getNbMines());
        bRetourGrille = new JButton("Retour");
    }

    public void ajouterWidgetVuePrincipal(){
        JPanel panDemineur = new JPanel();
        GridLayout gl = new GridLayout(1,2);
        gl.setHgap(25);
        panDemineur.setLayout(gl);
        panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineur);

        JPanel panel = new JPanel();
        gl = new GridLayout(4,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(bJeu);
        panel.add(bOption);
        panel.add(bQuitter);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueJouer(){
        JPanel panDemineur = new JPanel();
        GridLayout gl = new GridLayout(1,2);
        gl.setHgap(5);
        panDemineur.setLayout(gl);
        panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineurJouer);

        JPanel panel = new JPanel();
        gl = new GridLayout(5,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(bNorma);
        panel.add(bTor);
        panel.add(bPerso);
        panel.add(bRetourJouer);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueOption(){
        JPanel panDemineur = new JPanel();
        GridLayout gl = new GridLayout(1,2);
        gl.setHgap(5);
        panDemineur.setLayout(gl);
        panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineurOption);

        JPanel panel = new JPanel();
        gl = new GridLayout(2,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(bRetourOption);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueDifficulte(){
        JPanel panDemineur = new JPanel();
        GridLayout gl = new GridLayout(1,2);
        gl.setHgap(25);
        panDemineur.setLayout(gl);
        panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineurDifficulte);

        JPanel panel = new JPanel();
        gl = new GridLayout(5,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(bFacile);
        panel.add(bMoyen);
        panel.add(bDiffile);
        panel.add(bRetourDifficulte);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueGrille(){
        JPanel panGrille = new JPanel();
        GridLayout g = new GridLayout(model.getNbcaseligne(),model.getNbcasecolonne(),1,1);
        switch(model.getDifficulte()){
            case 1:
                panGrille.setLayout(g);
                tabButton = new JButton[model.getNbcaseligne()][model.getNbcasecolonne()];
                for(int i=0; i<tabButton.length; i++){
                    for( int j=0; j<tabButton[i].length; j++){
                        tabButton[i][j] = new JButton();
                        tabButton[i][j].setPreferredSize(new Dimension(20,20));
                        tabButton[i][j].setActionCommand(""+i+j);
                        panGrille.add(new JPanel().add(tabButton[i][j]));
                    }
                }
                break;
            case 2:
                panGrille.setLayout(g);
                tabButton = new JButton[model.getNbcaseligne()][model.getNbcasecolonne()];
                for(int i=0; i<tabButton.length; i++){
                    for( int j=0; j<tabButton[i].length; j++){
                        tabButton[i][j] = new JButton();
                        tabButton[i][j].setPreferredSize(new Dimension(20,20));
                        tabButton[i][j].setActionCommand(""+i+j);
                        panGrille.add(new JPanel().add(tabButton[i][j]));
                    }
                }
                break;
            case 3:
                panGrille.setLayout(g);
                tabButton = new JButton[model.getNbcaseligne()][model.getNbcasecolonne()];
                for(int i=0; i<tabButton.length; i++){
                    for( int j=0; j<tabButton[i].length; j++){
                        tabButton[i][j] = new JButton();
                        tabButton[i][j].setPreferredSize(new Dimension(20,20));
                        tabButton[i][j].setActionCommand(""+i+j);
                        panGrille.add(new JPanel().add(tabButton[i][j]));
                    }
                }
                break;
        }

        JPanel panDemineur = new JPanel();
        panDemineur.add(lScore);
        panDemineur.add(imageDemineur);
        panDemineur.add(lMine);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panDemineur);
        panel.add(panGrille);
        panel.add(bRetourGrille);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }


    public void ajouterWidgetVuePerso(){
        JPanel panDemineur = new JPanel();
        GridLayout gl = new GridLayout(1,2);
        gl.setHgap(5);
        panDemineur.setLayout(gl);
        panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineurPerso);

        JPanel panel = new JPanel();
        gl = new GridLayout(2,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(bRetourPerso);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void changerVue(int vue){
        switch(vue){
            case 1:
                panGeneral.remove(0);
                ajouterWidgetVuePrincipal();
                break;
            case 2:
                panGeneral.remove(0);
                ajouterWidgetVueJouer();
                break;
            case 3:
                panGeneral.remove(0);
                ajouterWidgetVueOption();
                break;
            case 4:
                panGeneral.remove(0);
                ajouterWidgetVueDifficulte();
                break;
            case 5:
                panGeneral.remove(0);
                ajouterWidgetVueGrille();
                break;
            case 6:
                panGeneral.remove(0);
                ajouterWidgetVuePerso();
                break;
        }
    }

    public void actualiser(){
        lScore.setText("Score :"+model.getScore());
        lMine.setText("Mines Restantes :"+model.getNbMines());
    }
}