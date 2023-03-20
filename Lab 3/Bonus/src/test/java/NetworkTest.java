import org.junit.Test;
//import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NetworkTest {
    public NetworkTest() {

    }

    @Test
    public void find_cutpoints() {

        Person p1 = new Designer("P1");
        Person p2 = new Person("P2");
        Person p3 = new Programmer("P3");
        Person p4 = new Person("P4");
        List<Node> nodesList = new ArrayList<>();
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
        Network n = new Network(nodesList);

        n.find_cutpoints();
        List<Node> cutPoints = new ArrayList<>(n.find_cutpoints());
        List<Node> expected = new ArrayList<>();
        expected.add(p2);
        expected.add(c1);

        assertEquals(expected, cutPoints);

    }

    @Test
    public void findMaximally2ConnectedComponents() {
    }
}