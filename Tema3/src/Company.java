
import java.util.HashMap;
import java.util.Map;

public class Company implements Node{
    String name;
    String officeLocation;
    private Map<Object, String> relationships = new HashMap<>();
    public Company( String name , String officeLocation){
        this.name=name ;
        this.officeLocation= officeLocation ;
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
        return "Companie situată în " + officeLocation;
    }
    @Override
    public Map<Object, String> getRelationships() {
        return relationships;
    }
}
