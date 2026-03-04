import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    // Lista care conține toate profilurile (Person, Programmer, Designer, Company)
    // Folosim tipul Node pentru a le putea amesteca în aceeași listă
    private List<Node> profiles;

    // Constructor
    public SocialNetwork() {
        this.profiles = new ArrayList<>();
    }

    // Metodă pentru a adăuga un profil (orice obiect care implementează Node)
    public void addProfile(Node profile) {
        if (profile != null) {
            profiles.add(profile);
        }
    }

    /**
     * Metoda principală cerută: printează rețeaua ordonată după importanță.
     */
    public void printNetwork() {
        // 1. Sortarea: Folosim metoda sort din List.
        // (b, a) asigură ordinea descrescătoare (cel mai important primul).
        profiles.sort((a, b) -> Integer.compare(b.getImportance(), a.getImportance()));

        System.out.println("\n REȚEAUA SOCIALĂ (Ordonată după Importanță) ");

        if (profiles.isEmpty()) {
            System.out.println("Rețeaua este goală.");
            return;
        }

        // 2. Parcurgerea și afișarea fiecărui profil
        for (Node n : profiles) {
            System.out.println("\n");
            System.out.println("NUME: " + n.getName());
            System.out.println("IMPORTANTA: " + n.getImportance() + " conexiuni");

            // Afișăm detaliile specifice (bazat pe metoda toString din fiecare clasă)
            System.out.println("DETALII: " + n.toString());
        }

        System.out.println("\n");
    }
}