import java.util.HashMap;
import java.util.Map;


public class Company implements Node, java.lang.Comparable<Company> {

    private String name;
    private int noEmployees;
    Map<Person, employeeType> employee = new HashMap<>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Company o) {
        return name.compareTo(o.name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoEmployees() {
        return noEmployees;
    }

    public Map<Person, employeeType> getRelationship() {
        return employee;
    }

    public void setRelationship(Map<Person, employeeType> employee) {
        this.employee = employee;
    }

    public void setNoEmployees(int noEmployees) {
        this.noEmployees = noEmployees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' + ", number of relationships=" + this.getNoRelationships() +
                '}';
    }

    public int getNoRelationships() {
        return employee.size();
    }

    public void addRelationship(Person p, employeeType e) {
        employee.put(p, e);
        p.getJobs().put(this, e);
    }
}
