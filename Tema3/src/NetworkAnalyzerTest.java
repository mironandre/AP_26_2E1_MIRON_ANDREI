import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.List;

public class NetworkAnalyzerTest {

    private SocialNetwork network;
    private Programmer alice;
    private Company google;
    private Desinger bob;
    private Company microsoft;
    private Programmer charlie;
    @BeforeEach
    public void setUp() {
        // Metoda setUp() rulează înaintea fiecărui test pentru a pregăti datele proaspete
        network = new SocialNetwork();

        // Initializam profilurile
        google = new Company("Google", "Mountain View");
        alice = new Programmer("Alice", "1990-05-12", "Java", "Gaming");
        bob = new Desinger("Bob", "1992-08-20", "Figma", "Photography");
        microsoft = new Company("Microsoft", "Redmond");
        charlie = new Programmer("Charlie", "1988-11-03", "C#", "Reading");

        // Construim rețeaua (forma de "papion" / bow-tie)
        // Triunghiul 1 Google - Alice - Bob
        google.addRelationship(alice, "employee");
        alice.addRelationship(bob, "friend");
        bob.addRelationship(google, "contractor");

        // Triunghiul 2 Alice - Charlie - Microsoft
        alice.addRelationship(charlie, "friend");
        charlie.addRelationship(microsoft, "employee");
        microsoft.addRelationship(alice, "former employee");

        // 3 Adaugam in retea
        network.addProfile(google);
        network.addProfile(alice);
        network.addProfile(bob);
        network.addProfile(microsoft);
        network.addProfile(charlie);
    }

    @Test
    public void testArticulationPoints() {
        NetworkAnalyzer analyzer = new NetworkAnalyzer(network.getProfiles());
        analyzer.analyze();

        Set<Node> cutVertices = analyzer.getArticulationPoints();

        // Verificam ca Alice este singurul punct de articulație (nod critic)
        assertEquals(1, cutVertices.size(), "Ar trebui să existe un singur punct de articulație (cut vertex).");
        assertTrue(cutVertices.contains(alice), "Alice ar trebui sa fie punctul de articulatie.");
    }
    @Test
    public void testBiconnectedComponents() {
        NetworkAnalyzer analyzer = new NetworkAnalyzer(network.getProfiles());
        analyzer.analyze();
        List<Set<Node>> components = analyzer.getBiconnectedComponents();
        // Verificam că exista exact 2 componente maximale (cele două triunghiuri unite de Alice)
        assertEquals(2, components.size(), "Ar trebui să existe exact 2 componente biconexe (parti maximale)");
    }

    @Test
    public void testDisconnectedNetwork() {
        // Testam caz special fara nicio relatie
        SocialNetwork emptyNet = new SocialNetwork();
        Programmer programator1 = new Programmer("Dan", "2000-01-01", "C", "Sport");
        Company companie1 = new Company("Apple", "USA");

        emptyNet.addProfile(programator1);
        emptyNet.addProfile(companie1);

        NetworkAnalyzer analyzer = new NetworkAnalyzer(emptyNet.getProfiles());
        analyzer.analyze();

        assertTrue(analyzer.getArticulationPoints().isEmpty(), "O retea fara relatii nu ar trebui sa aiba puncte de articulatie.");
    }
}