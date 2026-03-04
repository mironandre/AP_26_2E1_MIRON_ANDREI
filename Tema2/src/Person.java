import java.util.HashMap;
import java.util.Map;

public class Person implements Node{
    private String name;
    private String dateofbirth;
    private String favouriteHobby;
    private Map<Object, String> relationships = new HashMap<>();
    public Person(String name , String dateofbirth , String favouriteHobby) {
        this.name = name;
        this.dateofbirth = dateofbirth ;
        this.favouriteHobby = favouriteHobby ;
    }
    public void addRelationship(Object target, String type) {
        relationships.put(target, type);
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getImportance() {
        return relationships.size();
    }
    @Override
    public String toString() {
        return "Născut la: " + dateofbirth + ", Hobby: " + favouriteHobby;
    }
}
