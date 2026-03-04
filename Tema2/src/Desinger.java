public class Desinger extends Person{
    String prefferedTool;
    public Desinger(String name , String dateofbirth ,String prefferedTool, String favouriteHobby){
        super(name, dateofbirth , favouriteHobby);
        this.prefferedTool = prefferedTool;
    }
    @Override
    public String toString() {
        return super.toString() + ", Tool: " + prefferedTool;
    }
}
