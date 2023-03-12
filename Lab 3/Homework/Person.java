import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Person implements Node, java.lang.Comparable<Person> {


    private String name;

    private LocalDate birthDate;

    private Map<Person, relationshipType> relationship = new HashMap<>();
    private Map<Company, employeeType> jobs = new HashMap<>();


    public Person(String name, LocalDate birthDate, Map<Person, relationshipType> relationship, Map<Company, employeeType> jobs) {
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

    public Map<Person, relationshipType> getRelationship() {
        return relationship;
    }

    public void setRelationship(Map<Person, relationshipType> relationship) {
        this.relationship = relationship;
    }

    public Map<Company, employeeType> getJobs() {
        return jobs;
    }

    public void setJobs(Map<Company, employeeType> jobs) {
        this.jobs = jobs;
    }

    public int getNoRelationships() {
        return jobs.size() + relationship.size();
    }

    public void addRelationship(Person node, relationshipType r) {
        relationship.put(node, r);
        if (Objects.equals(r, relationshipType.BOSS))
            node.getRelationship().put(this, relationshipType.EMPLOYEE);
        else
            node.getRelationship().put(this, r);
    }

    public void addCompany(Company node, employeeType e) {
        jobs.put(node, e);
        node.getRelationship().put(this, e);
    }

}
