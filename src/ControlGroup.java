public class ControlGroup {

    private Model model ;
    private Fenetre vue ;
    public ControlButtonJeuMenu cb ;
    public ControlMenu cm ;

    public ControlGroup(Model model){
        this.model = model ;
        vue = new Fenetre(model);
        cb = new ControlButtonJeuMenu(model,vue);
        //cm = new ControlMenu(model,vue);
    }
}

