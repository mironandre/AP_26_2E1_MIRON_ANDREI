public class Main {
    public static void main(String[] args) {
        Programmer p1 = new Programmer("Andrei", "1995-05-12", "Java", "Gaming");
        Programmer p2 = new Programmer("Elena", "1998-11-23", "Python", "Photography");
        Desinger d1 = new Desinger("Marius", "1992-03-15", "Adobe Illustrator", "Hiking");
        Company google = new Company("Google", "Mountain View");
        Company adobe = new Company("Adobe", "San Jose");
        google.addRelationship(p1, "employee");
        google.addRelationship(p2, "intern");
        p1.addRelationship(google, "boss");
        p1.addRelationship(d1, "friend");
        d1.addRelationship(p1, "friend");
        adobe.addRelationship(d1, "lead_designer");
        SocialNetwork network = new SocialNetwork();
        network.addProfile(p1);
        network.addProfile(p2);
        network.addProfile(d1);
        network.addProfile(google);
        network.addProfile(adobe);
        network.printNetwork();
    }
}