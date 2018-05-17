import javax.swing.*;
import java.awt.event.ActionListener;

public class VuesDifficulte extends JFrame {
    private Model model;

    private JButton bFacile;
    private JButton bMoyen;
    private JButton bDiffile;
    private JButton bRetour;

    private JLabel nomDemineur;

    public VuesDifficulte() {}

    public VuesDifficulte(Model model) {
        this.model = model;
        initAttribut();
        ajouterWidget();
        setSize(500, 700);
        setTitle("Démineur");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Gestion de la fermeture
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

    public JButton getbRetour() {
        return bRetour;
    }

    public void setControlBouton(ActionListener actionListener) {
        bFacile.addActionListener(actionListener);
        bMoyen.addActionListener(actionListener);
        bDiffile.addActionListener(actionListener);
        bRetour.addActionListener(actionListener);
    }

    public void initAttribut() {
        nomDemineur = new JLabel("Choississez la Difficulté");
        bFacile = new JButton("Facile");
        bMoyen = new JButton("Moyen");
        bDiffile = new JButton("Difficile");
        bRetour = new JButton("Retour");
    }

    public void ajouterWidget() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nomDemineur);
        panel.add(bFacile);
        panel.add(bMoyen);
        panel.add(bDiffile);
        panel.add(bRetour);

        JPanel panelGeneral = new JPanel();
        panelGeneral.add(panel);

        setContentPane(panelGeneral);
    }
}

