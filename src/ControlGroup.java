public class ControlGroup {

    private Model model ;
    private Fenetre vue ;
    private ControlButtonJeuMenu cb ;
    private ControlMenu cm;

    public ControlGroup(Model model){
        this.model = model ;
        vue = new Fenetre(model);
        cb = new ControlButtonJeuMenu(model,vue);
        cm = new ControlMenu(model,vue);

    }
}

