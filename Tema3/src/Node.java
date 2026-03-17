import java.util.Map;

public interface Node {
    String getName();
    int getImportance();
    Map<Object, String> getRelationships();
}