import java.io.IOException;

public class Appli {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = null;
                try {
                    model = new Model();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ControlGroup control = new ControlGroup(model);
            }
        });
    }

}
