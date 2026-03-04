public class Programmer extends  Person{
    private String prefferedLanguage;
    public Programmer(String name,String dateofbirth , String prefferedLanguage ,String favouriteHobby) {
        super(name , dateofbirth , favouriteHobby);
        this.prefferedLanguage = prefferedLanguage ;
    }
    @Override
    public String toString() {
        return super.toString() + ", Limbaj: " + prefferedLanguage;
    }
}

