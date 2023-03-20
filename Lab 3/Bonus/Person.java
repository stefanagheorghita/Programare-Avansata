import java.time.LocalDate;
import java.util.*;

public class Person implements Node, java.lang.Comparable<Person> {

    private String name;

    private LocalDate birthDate;

    private Map<Person, RelationshipType> relationship = new HashMap<>();
    private Map<Company, EmployeeType> jobs = new HashMap<>();

    public Person(String name, LocalDate birthDate, Map<Person, RelationshipType> relationship, Map<Company, EmployeeType> jobs) {
        this.name = name;
        this.birthDate = birthDate;
        this.relationship = relationship;
        this.jobs = jobs;
    }


    public Person(String name) {
        this.name = name;

    }

    public Person() {
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\''+ ", number of relationships=" + this.getNoRelationships()  +
                '}';
    }

    public Map<Person, RelationshipType> getRelationship() {
        return relationship;
    }

    public void setRelationship(Map<Person, RelationshipType> relationship) {
        this.relationship = relationship;
    }

    public Map<Company, EmployeeType> getJobs() {
        return jobs;
    }

    public void setJobs(Map<Company, EmployeeType> jobs) {
        this.jobs = jobs;
    }

    public int getNoRelationships() {
        return jobs.size() + relationship.size();
    }

    public void addRelationship(Person node, RelationshipType r) {
        relationship.put(node, r);
        if (Objects.equals(r, RelationshipType.BOSS))
            node.getRelationship().put(this, RelationshipType.EMPLOYEE);
        else
            node.getRelationship().put(this, r);
    }

    public void addCompany(Company node, EmployeeType e) {
        jobs.put(node, e);
        node.getRelationship().put(this, e);
    }

    public List<Node> getAdj(){
        List<Node> adj=new ArrayList<>();
        for(Company c : jobs.keySet())
            adj.add(c);
        for(Person p : relationship.keySet())
            adj.add(p);
        return adj;
    }

}
