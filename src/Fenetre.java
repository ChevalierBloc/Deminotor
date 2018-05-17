import javax.swing.*;
import java.awt.image.BufferedImage;

public class Fenetre extends JFrame {

    private Model model;

    // Menu principal

    JPanel panGeneral;

    private JButton bJeu;
    private JButton bOption;
    private JButton bQuitter;

    private JLabel imageDemineur;
    private JLabel nomDemineur;

    private JButton bNorma;
    private JButton bTor;
    private JButton bPerso;
    private JButton bRetourJouer;

    private JLabel nomDemineurOption;
    private JButton bRetourOption;

    private JLabel nomDemineurPerso;
    private JButton bRetourPerso;

    // Menu difficulté

    private JButton bFacile;
    private JButton bMoyen;
    private JButton bDiffile;

    private JLabel nomDemineurDifficulte;
    private JButton bRetourDifficulte;



    public Fenetre(Model model){
        this.model = model;
        initAttribut();
        ajouterWidgetPrincipal();
        setSize(500, 700);
        setTitle("Démineur");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
    }

    public void initAttribut() {
        panGeneral = new JPanel();

        imageDemineur = new JLabel(new ImageIcon(new ImageIcon("images/demineur.jpg").getImage().getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH)));
        nomDemineur = new JLabel("Démineur :)");
        bJeu = new JButton("Jouer");
        bOption = new JButton("Option");
        bQuitter = new JButton("Quitter");

        nomDemineur = new JLabel("Jouer ");
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
    }
    public void ajouterWidgetPrincipal() {
        JPanel panDemineur = new JPanel();
        panDemineur.setLayout(new BoxLayout(panDemineur, BoxLayout.X_AXIS));
        panDemineur.add(imageDemineur);
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


    public JButton getbJeu() {
        return bJeu;
    }

    public JButton getbOption() {
        return bOption;
    }

    public JButton getbQuitter() {
        return bQuitter;
    }

    public JLabel getImageDemineur() {
        return imageDemineur;
    }

    public JLabel getNomDemineur() {
        return nomDemineur;
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

    public JLabel getNomDemineurOption() {
        return nomDemineurOption;
    }

    public JButton getbRetourOption() {
        return bRetourOption;
    }

    public JLabel getNomDemineurPerso() {
        return nomDemineurPerso;
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

    public JLabel getNomDemineurDifficulte() {
        return nomDemineurDifficulte;
    }

    public JButton getbRetourDifficulte() {
        return bRetourDifficulte;
    }

    public
}
