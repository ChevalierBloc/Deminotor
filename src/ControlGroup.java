public class ControlGroup {

    private Model model ;
    private Fenetre vue ;
    public ControlButtonJeuMenu cb ;

    public ControlGroup(Model model){
        this.model = model ;
        vue = new Fenetre(model);
        cb = new ControlButtonJeuMenu(model,vue);
    }
}

