import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Fenetre extends JFrame {
    private ModelIntermediaire model;

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

    private JLabel nomDemineurDifficulte;
    private JButton bRetourDifficulte;

    private JLabel nomDemineurGrille;
    private JButton bRetourGrille;

    public Fenetre(ModelIntermediaire model) {
        this.model = model;
        initAttribut();
        ajouterWidgetVuePrincipal();
        setSize(500,700);
        setTitle("Démineur");
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

        imageDemineur = new JLabel(new ImageIcon(new ImageIcon("images/demineur.jpg").getImage().getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH)));
        nomDemineur = new JLabel("Démineur :)");
        bJeu = new JButton("Jouer");
        bOption = new JButton("Option");
        bQuitter = new JButton("Quitter");

        nomDemineurJouer = new JLabel("Jouer ");
        bNorma = new JButton("Jeu Normal");
        bTor = new JButton("Jeu TOR");
        bPerso = new JButton("Jeu Personnaliser");
        bRetourJouer = new JButton("Retour");

        nomDemineurOption = new JLabel("Option");
        bRetourOption = new JButton("Retour");

        nomDemineurPerso = new JLabel("Personnaliser");
        bRetourPerso = new JButton("Retour");

        nomDemineurDifficulte = new JLabel("Choississez la Difficulté");
        bFacile = new JButton("Facile");
        bMoyen = new JButton("Moyen");
        bDiffile = new JButton("Difficile");
        bRetourDifficulte = new JButton("Retour");

        if(model.estTor)
            nomDemineurGrille = new JLabel("Grille de difficulte " + model.diffeculte + " en TOR");
        else
            nomDemineurGrille = new JLabel("Grille de difficulte " + model.diffeculte + " en normal");
        bRetourGrille = new JButton("Retour");
    }

    public void ajouterWidgetVuePrincipal(){
        JPanel panDemineur = new JPanel();
        panDemineur.setLayout(new BoxLayout(panDemineur, BoxLayout.X_AXIS));
        //panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineur);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panDemineur);
        panel.add(bJeu);
        panel.add(bOption);
        panel.add(bQuitter);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueJouer(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nomDemineurJouer);
        panel.add(bNorma);
        panel.add(bTor);
        panel.add(bPerso);
        panel.add(bRetourJouer);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueOption(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(nomDemineurOption);
        panel.add(bRetourOption);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueDifficulte(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nomDemineurDifficulte);
        panel.add(bFacile);
        panel.add(bMoyen);
        panel.add(bDiffile);
        panel.add(bRetourDifficulte);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }

    public void ajouterWidgetVueGrille(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nomDemineurGrille);
        panel.add(bRetourGrille);

        panGeneral.add(panel);

        setContentPane(panGeneral);
    }


    public void ajouterWidgetVuePerso(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(nomDemineurPerso);
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
        if(model.estTor)
            nomDemineurGrille.setText("Grille de difficulte " + model.diffeculte + " en TOR");
        else
            nomDemineurGrille.setText("Grille de difficulte " + model.diffeculte + " en normal");
    }
}