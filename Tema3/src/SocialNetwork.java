
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    //lista
    private List<Node> profiles;

    //constructor
    public SocialNetwork() {
        this.profiles = new ArrayList<>();
    }
    public List<Node> getProfiles() {
        return profiles;
    }
    //metoda pentru profile
    public void addProfile(Node profile) {
        if (profile != null) {
            profiles.add(profile);
        }
    }

   //metoda principala pentru print
    public void printNetwork() {
        // sortarea
        profiles.sort((a, b) -> Integer.compare(b.getImportance(), a.getImportance()));

        System.out.println("\n Reteaua sociala ordonata dupa importanta");
        if (profiles.isEmpty()) {
            System.out.println("Rețeaua este goala");
            return;
        }
        //parcurgere si afisare
        for (Node n : profiles) {
            System.out.println("\n");
            System.out.println("NUME: " + n.getName());
            System.out.println("IMPORTANTA: " + n.getImportance() + " conexiuni");
            // Afisam detaliile
            System.out.println("DETALII: " + n.toString());
        }
        System.out.println("\n");
    }
}
