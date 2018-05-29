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
    private JCheckBox son;
    private JButton bRetourOption;

    private JLabel nomDemineurPerso;
    private JLabel lNbLigne;
    private JTextField bNbLigne;
    private JLabel lNbColonne;
    private JTextField bNbColonne;
    private JLabel lNbMine;
    private JTextField bNbMine;
    private JButton bValider;
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

    public JTextField getbNbLigne() {
        return bNbLigne;
    }
    public JTextField getbNbColonne() {
        return bNbColonne;
    }
    public JTextField getbNbMine() {
        return bNbMine;
    }
    public JButton getbValider() {
        return bValider;
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

    public JButton[][] getTabButton() {
        return tabButton;
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

        bValider.addActionListener(actionListener);
        bRetourPerso.addActionListener(actionListener);

        bFacile.addActionListener(actionListener);
        bMoyen.addActionListener(actionListener);
        bDiffile.addActionListener(actionListener);
        bRetourDifficulte.addActionListener(actionListener);

        bRetourGrille.addActionListener(actionListener);
    }

    public void setControlBoutonGrille(ActionListener actionListener){
        for(int i=0; i<tabButton.length; i++){
            for( int j=0; j<tabButton[i].length; j++){
                tabButton[i][j].addActionListener(actionListener);
            }
        }
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
        son = new JCheckBox("Activer le son");
        bRetourOption = new JButton("Retour");

        nomDemineurPerso = new JLabel("Personnaliser");
        lNbLigne = new JLabel("Nombre de ligne :");
        bNbLigne = new JTextField();
        lNbColonne = new JLabel("Nombre de colonne :");;
        bNbColonne = new JTextField();
        lNbMine = new JLabel("Nombre de mine :");;
        bNbMine = new JTextField();
        bValider = new JButton("Valider");
        bRetourPerso = new JButton("Retour");

        nomDemineurDifficulte = new JLabel("Difficulté");
        bFacile = new JButton("Facile");
        bMoyen = new JButton("Moyen");
        bDiffile = new JButton("Difficile");
        bRetourDifficulte = new JButton("Retour");

        lScore = new JLabel("Score :"+model.getScore());
        lMine = new JLabel("Nombres de mines :"+model.getNbMines());
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
        gl = new GridLayout(3,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(son);
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
        panGrille.setLayout(g);
        tabButton = new JButton[model.getNbcaseligne()][model.getNbcasecolonne()];
        for(int i=0; i<tabButton.length; i++){
            for( int j=0; j<tabButton[i].length; j++){
                tabButton[i][j] = new JButton();
                tabButton[i][j].setPreferredSize(new Dimension(50,50));
                tabButton[i][j].setActionCommand(""+i+j);
                System.out.println(model.getTabMines()[i][j]);
                panGrille.add(new JPanel().add(tabButton[i][j]));
            }
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

        JPanel panNbLigne = new JPanel();
        panNbLigne.setLayout(gl);
        panDemineur.add(lNbLigne);
        panDemineur.add(bNbLigne);

        JPanel panNbColonne = new JPanel();
        panNbColonne.setLayout(gl);
        panDemineur.add(lNbColonne);
        panDemineur.add(bNbColonne);

        JPanel panNbMine = new JPanel();
        panNbMine.setLayout(gl);
        panDemineur.add(lNbMine);
        panDemineur.add(bNbMine);

        JPanel panel = new JPanel();
        gl = new GridLayout(6,1);
        gl.setVgap(20);
        panel.setLayout(gl);
        panel.add(panDemineur);
        panel.add(panNbLigne);
        panel.add(panNbColonne);
        panel.add(panNbMine);
        panel.add(bValider);
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
        lMine.setText("Nombres de mines :"+model.getNbMines());
    }

    public void genererErreur(String erreur){
        JOptionPane optionPane = new JOptionPane();
        switch(erreur){
            case "ligne":
                optionPane.showMessageDialog(null, "saississez un nombre entre 2 et 16", "Nombre de ligne", JOptionPane.ERROR_MESSAGE);
                break;
            case "colonne":
                optionPane.showMessageDialog(null, "saississez un nombre entre 2 et 30", "Nombre de colonne", JOptionPane.ERROR_MESSAGE);
                break;
            case "nbMines":
                optionPane.showMessageDialog(null, "saississez un nombre", "Nombre de mine", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}