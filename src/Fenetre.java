import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Fenetre extends JFrame {
    private Model model;
    private Time time;
    private ActionListener actionListener;

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
    private JCheckBox cbSon;
    private JButton bRetourOption;

    private JLabel nomDemineurPerso;
    private JLabel lNbLigne;
    private JTextField tfNbLigne;
    private JLabel lNbColonne;
    private JTextField tfNbColonne;
    private JLabel lNbMine;
    private JTextField tfNbMine;
    private JButton bValider;
    private JButton bRetourPerso;

    private JButton bFacile;
    private JButton bMoyen;
    private JButton bDiffile;

    private JLabel  nomDemineurDifficulte;
    private JButton bRetourDifficulte;

    private JLabel  lScore;
    private JLabel  lMine;
    private JButton bPause;
    private JButton bAide;

    private JButton bClique;
    private JButton[][] tabButton;

    private JMenuBar jMenuBar;
    private JMenu JMMOptions;
    private JMenuItem JMINouvellePartie;
    private JMenuItem JMIRetourMenuPrincipal;
    private JCheckBoxMenuItem JMIOptionsSon ;

    public Fenetre(Model model) {
        this.model = model;
        time = new Time(model, this);
        initAttribut();
        ajouterWidgetVuePrincipal();
        this.setIconImage(model.getImagesMines().getImage());
        setSize(700,500);
        setTitle("Déminotor");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
    }

    public Time getTime() {
        return time;
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

    public JCheckBox getCbSon() {
        return cbSon;
    }

    public JButton getbRetourOption() {
        return bRetourOption;
    }

    public JTextField getbNbLigne() {
        return tfNbLigne;
    }
    public JTextField getbNbColonne() {
        return tfNbColonne;
    }
    public JTextField getbNbMine() {
        return tfNbMine;
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


    public JButton getbPause() {
        return bPause;
    }

    public JButton getbAide() {
        return bAide;
    }

    public JButton getbClique() {
        return bClique;
    }
    public JButton[][] getTabButton() {
        return tabButton;
    }

    public JMenuItem getJMINouvellePartie() {
        return JMINouvellePartie;
    }

    public JMenuItem getJMIRetourMenuPrincipal() {
        return JMIRetourMenuPrincipal;
    }
    public JMenuItem getJMIOptionsSon() {
        return JMIOptionsSon;
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
    }

    public void setControlMenuItem(ItemListener itemListener){
        JMIOptionsSon.addItemListener(itemListener);
    }

    public void setControlBoutonGrille(ActionListener actionListener){
        this.actionListener = actionListener;
        for(int i=0; i<tabButton.length; i++){
            for( int j=0; j<tabButton[i].length; j++){
                tabButton[i][j].addActionListener(actionListener);
            }
        }
        bClique.addActionListener(actionListener);
        bPause.addActionListener(actionListener);
        bAide.addActionListener(actionListener);
    }

    public void suppControlBoutonGrille(){
        for(int i=0; i<tabButton.length; i++){
            for( int j=0; j<tabButton[i].length; j++){
                tabButton[i][j].removeActionListener(actionListener);
            }
        }
        bClique.removeActionListener(actionListener);
        bPause.removeActionListener(actionListener);
        bAide.removeActionListener(actionListener);
    }

    public void setControlMenu(ActionListener al){
        JMIRetourMenuPrincipal.addActionListener(al);
        JMINouvellePartie.addActionListener(al);
        JMIOptionsSon.addActionListener(al);
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
        cbSon = new JCheckBox("Activer le son",true);
        bRetourOption = new JButton("Retour");

        nomDemineurPerso = new JLabel("Personnaliser");
        lNbLigne = new JLabel("Nombre de ligne :");
        tfNbLigne = new JTextField();
        tfNbLigne.setPreferredSize(new Dimension(60,25));
        lNbColonne = new JLabel("Nombre de colonne :");;
        tfNbColonne = new JTextField();
        tfNbColonne.setPreferredSize(new Dimension(60,25));
        lNbMine = new JLabel("Nombre de mine :");
        tfNbMine = new JTextField();
        tfNbMine.setPreferredSize(new Dimension(60,25));
        bValider = new JButton("Valider");
        bRetourPerso = new JButton("Retour");

        nomDemineurDifficulte = new JLabel("Difficulté");
        bFacile = new JButton("Facile");
        bMoyen = new JButton("Moyen");
        bDiffile = new JButton("Difficile");
        bRetourDifficulte = new JButton("Retour");

        lScore = new JLabel("Temps : "+model.getScore() + " secondes");
        lMine = new JLabel("Nombres de drapeaux : "+model.getNbMinesRestant());
        bClique = new JButton(new ImageIcon(model.getImageClique().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
        bClique.setPreferredSize(new Dimension(20, 20));
        bPause = new JButton("Pause");
        bAide = new JButton("Aide");

        jMenuBar = new JMenuBar();
        JMMOptions = new JMenu("Options");
        JMINouvellePartie = new JMenuItem("Nouvelle Partie");
        JMIRetourMenuPrincipal = new JMenuItem("Menu Principal");
        JMIOptionsSon = new JCheckBoxMenuItem("Son activé", true);
    }

    public void ajouterWidgetVuePrincipal(){
        jMenuBar.setVisible(false);
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
        panel.add(cbSon);
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
                tabButton[i][j].setPreferredSize(new Dimension(25,25));
                tabButton[i][j].setActionCommand(i+"/"+j);
                panGrille.add(new JPanel().add(tabButton[i][j]));
            }
        }

        JPanel panDemineur = new JPanel();
        panDemineur.add(lScore);
        panDemineur.add(imageDemineur);
        panDemineur.add(lMine);

        JPanel panButton = new JPanel();
        panButton.add(bClique);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panDemineur);
        panel.add(panGrille);
        panel.add(panButton);
        JPanel panelTemp = new JPanel();
        panelTemp.setLayout(new BoxLayout(panelTemp, BoxLayout.X_AXIS));
        panelTemp.add(bPause);
        panelTemp.add(bAide);

        panel.add(panelTemp);

        panGeneral.add(panel);

        setContentPane(panGeneral);

        JMMOptions.add(JMIRetourMenuPrincipal);
        JMMOptions.add(JMIOptionsSon);
        jMenuBar.add(JMMOptions);
        jMenuBar.add(JMINouvellePartie);

        setJMenuBar(jMenuBar);
        jMenuBar.setVisible(true);
    }


    public void ajouterWidgetVuePerso(){

        JPanel panDemineur = new JPanel();
        panDemineur.setLayout(new BoxLayout(panDemineur, BoxLayout.X_AXIS));

        panDemineur.add(imageDemineur);
        panDemineur.add(nomDemineurPerso);

        JPanel panNbLigne = new JPanel();
        JPanel panNbLigne2 = new JPanel();
        panNbLigne.setLayout(new BoxLayout(panNbLigne, BoxLayout.X_AXIS));
        panNbLigne2.add(panNbLigne);

        panNbLigne.add(lNbLigne);
        panNbLigne.add(tfNbLigne);

        JPanel panNbColonne = new JPanel();
        JPanel panNbColonne2 = new JPanel();
        panNbColonne.setLayout(new BoxLayout(panNbColonne, BoxLayout.X_AXIS));
        panNbColonne2.add(panNbColonne);

        panNbColonne.add(lNbColonne);
        panNbColonne.add(tfNbColonne);

        JPanel panNbMine = new JPanel();
        JPanel panNbMine2 = new JPanel();
        panNbMine.setLayout(new BoxLayout(panNbMine, BoxLayout.X_AXIS));
        panNbMine2.add(panNbMine);

        panNbMine.add(lNbMine);
        panNbMine.add(tfNbMine);

        JPanel panButon = new JPanel();
        panButon.setLayout(new BoxLayout(panButon,BoxLayout.X_AXIS));
        panButon.add(bValider);
        panButon.add(bRetourPerso);

        JPanel panel = new JPanel(new GridLayout(6,1));

        panel.add(panDemineur);
        panel.add(panNbLigne2);
        panel.add(panNbColonne2);
        panel.add(panNbMine2);
        panel.add(panButon);

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
        lScore.setText("Temps : "+model.getScore() + " secondes");
        lMine.setText("Nombres de mines :"+model.getNbMinesRestant());
    }

    public void gagner(){
        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, "Vous avez Gagner votre temps est de "+ model.getScore() + " secondes", "Victoire !!!!!", JOptionPane.INFORMATION_MESSAGE);
    }

    public void perdu(){
        JOptionPane perdu = new JOptionPane();
        perdu.showMessageDialog(null, "Perdu !!!", "Vous avez perdu", JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0 ; i < tabButton.length ; i++){
            for (int j = 0 ; j < tabButton[i].length ; j++){
                tabButton[i][j].setEnabled(false);
                if(model.getTabMines()[i][j] == 1)
                    tabButton[i][j].setIcon(new ImageIcon(model.getImagesMines().getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
            }
        }
    }

    public void aide(int x, int y){
        tabButton[x][y].setBackground(Color.red);
    }

    public void pause(){
        for (int i = 0 ; i < tabButton.length ; i++){
            for (int j = 0 ; j < tabButton[i].length ; j++){
                tabButton[i][j].setVisible(false);
            }
        }
    }

    public void reprendre(){
        for (int i = 0 ; i < tabButton.length ; i++){
            for (int j = 0 ; j < tabButton[i].length ; j++){
                tabButton[i][j].setVisible(true);
            }
        }
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
            case "drapeau":
                optionPane.showMessageDialog(null, "Ils n'y a plus de drapeau a posse", "Nombre de drapeau", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "aide":
                optionPane.showMessageDialog(null, "Ils n'y a plus d'aide", "Aide", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}