import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Ana");
        Person p2 = new Person("Ioan");
        Person p3 = new Person("Ivo");
        Person p4 = new Person("Zana");
        List<Person> p = new ArrayList<>();
        p.add(p2);
        p.add(p1);
        p.add(p4);
        p.add(p3);
        Collections.sort(p);
        System.out.println(p);
        Company c1 = new Company("Coco");
        Company c2 = new Company("Aro");
        Company c3 = new Company("Vivo");
        List<Company> c = new ArrayList<>();
        c.add(c1);
        c.add(c2);
        c.add(c3);
        Collections.sort(c);
        System.out.println(c);
    }
}