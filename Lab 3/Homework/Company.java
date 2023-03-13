import java.util.HashMap;
import java.util.Map;

public class Company implements Node, java.lang.Comparable<Company> {

    private String name;
    private int noEmployees;
    Map<Person, EmployeeType> employee = new HashMap<>();

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

    public Map<Person, EmployeeType> getRelationship() {
        return employee;
    }

    public void setRelationship(Map<Person, EmployeeType> employee) {
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

    public void addRelationship(Person p, EmployeeType e) {
        employee.put(p, e);
        p.getJobs().put(this, e);
    }
}
