import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        LocalDate dt = LocalDate.parse("2000-01-12");
        Person p1 = new Designer("P1");
        Person p2 = new Person("P2");
        Person p3 = new Programmer("P3");
        Person p4 = new Person("P4");
        List<Node> nodesList = new ArrayList<>();
        p1.setBirthDate(dt);

        nodesList.add(p1);
        nodesList.add(p2);
        nodesList.add(p3);
        nodesList.add(p4);

        Company c1 = new Company("C1");
        Company c2 = new Company("C2");
        Company c3 = new Company("C3");


        nodesList.add(c1);
        nodesList.add(c2);
        nodesList.add(c3);
        // System.out.println(nodesList);

        RelationshipType r1 = RelationshipType.FRIEND;
        RelationshipType r2 = RelationshipType.FAMILY;
        EmployeeType e1 = EmployeeType.DIRECTOR;
        EmployeeType e2 = EmployeeType.INTERN;
        EmployeeType e3 = EmployeeType.MANAGER;

        p1.addRelationship(p4, r1);

        c2.addRelationship(p4, e1);
        p1.addCompany(c1, e1);
        c1.addRelationship(p4, e3);
        c1.addRelationship(p2, e2);
        c2.addRelationship(p1, e2);
        c2.addRelationship(p4, e1);
        p2.addCompany(c3, e2);
        Collections.sort(nodesList, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(nodesList);
        Network n = new Network(nodesList);
        System.out.println("The importance of node p1: " + n.importance(p1));

        Collections.sort(n.getNodes(), new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getNoRelationships() - o2.getNoRelationships() != 0)
                    return o1.getNoRelationships() - o2.getNoRelationships();
                else
                    return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(n);
        System.out.println(n.find_cutpoints());
        System.out.println(n.findMaximally2ConnectedComponents());
    }
}