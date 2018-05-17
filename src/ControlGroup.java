public class ControlGroup {

    private ModelIntermediaire model ;
    private Fenetre vue ;
    public ControlButton cb ;
    public ControlMenu cm ;

    public ControlGroup(ModelIntermediaire model){
        this.model = model ;
        vue = new Fenetre(model);
        cb = new ControlButton(model,vue);
        //cm = new ControlMenu(model,vue);
    }
}

