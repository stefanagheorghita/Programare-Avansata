import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        LocalDate dt = LocalDate.parse("2000-01-12");
        Person p1 = new Designer("Ana");
        Person p2 = new Person("Ioan");
        Person p3 = new Programmer("Ala");
        Person p4 = new Person("Zana");
        List<Node> nodesList = new ArrayList<>();
        p1.setBirthDate(dt);

        nodesList.add(p2);
        nodesList.add(p1);
        nodesList.add(p4);
        nodesList.add(p3);

        Company c1 = new Company("Coco");
        Company c2 = new Company("Aro");
        Company c3 = new Company("Vivo");


        nodesList.add(c1);
        nodesList.add(c2);
        nodesList.add(c3);
        // System.out.println(nodesList);

        RelationshipType r1 = RelationshipType.FRIEND;
        RelationshipType r2 = RelationshipType.FAMILY;
        EmployeeType e1 = EmployeeType.DIRECTOR;
        EmployeeType e2 = EmployeeType.INTERN;
        EmployeeType e3 = EmployeeType.MANAGER;

        p1.addRelationship(p2, r1);
        p2.addRelationship(p3, r2);
        p4.addRelationship(p3, r1);
        p1.addCompany(c1, e1);
        c1.addRelationship(p4, e3);
        c1.addRelationship(p2, e2);

        Collections.sort(nodesList, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(nodesList);
        Network n = new Network(nodesList);
        System.out.println("The importance of node p1: "+n.importance(p1));

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
    }
}