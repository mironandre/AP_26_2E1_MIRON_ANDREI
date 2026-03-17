import java.util.Set;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Instanțiem rețeaua
        SocialNetwork socialNetwork = new SocialNetwork();
        //Creăm profilurile (folosind clasele tale)
        Company google = new Company("Google", "Mountain View");
        Programmer alice = new Programmer("Alice", "1990-05-12", "Java", "Gaming");
        Desinger bob = new Desinger("Bob", "1992-08-20", "Figma", "Photography");

        Company microsoft = new Company("Microsoft", "Redmond");
        Programmer charlie = new Programmer("Charlie", "1988-11-03", "C#", "Reading");

        //Stabilim relațiile pentru Grupul 1 (Triunghi: Google - Alice - Bob)
        google.addRelationship(alice, "Angajator");
        alice.addRelationship(bob, "Prieten");
        bob.addRelationship(google, "Collaborator");

        //Stabilim relațiile pentru Grupul 2 (Triunghi: Alice - Charlie - Microsoft)
        // Observă că Alice este singura care face legătura cu acest grup!
        alice.addRelationship(charlie, "Cunoștința");
        charlie.addRelationship(microsoft, "Angajat");
        microsoft.addRelationship(alice, "Fost angajat");

        //Adăugăm toti membrii in reteaua sociala
        socialNetwork.addProfile(google);
        socialNetwork.addProfile(alice);
        socialNetwork.addProfile(bob);
        socialNetwork.addProfile(microsoft);
        socialNetwork.addProfile(charlie);

        // Afișăm rețeaua ordonată după importanță
        System.out.println("Afisare retea dupa importanta");
        socialNetwork.printNetwork();

        //Analiza punctelor critice și a componentelor
        System.out.println("\n Analiza conectivitate");
        NetworkAnalyzer analyzer = new NetworkAnalyzer(socialNetwork.getProfiles());
        analyzer.analyze();

        // Afișam punctele de articulație (Cut Vertices)
        Set<Node> cutVertices = analyzer.getArticulationPoints();
        System.out.println("Noduri critice care asigura conectivitatea:");
        if (cutVertices.isEmpty()) {
            System.out.println("Nu exista puncte critice. Reteaua este robusta.");
        } else {
            for (Node n : cutVertices) {
                System.out.println("critic -> " + n.getName() + "Daca dispare, reteaua se rupe");
            }
        }
        // Afișam componentele biconexe (părțile maximale rezistente)
        System.out.println("\nPartile maximale ale retelei (Componente Biconexe):");
        List<Set<Node>> components = analyzer.getBiconnectedComponents();
        int i = 1;
        for (Set<Node> comp : components) {
            System.out.print("Grupul " + i + " rezistent la deconectare: ");
            for (Node n : comp) {
                System.out.print(n.getName() + " ");
            }
            System.out.println();
            i++;
        }
    }
}