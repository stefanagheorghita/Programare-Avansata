import java.time.LocalDate;
import java.util.Map;

public class Designer extends Person {
    private int salary;
    private DesignerType field;
    private int maxClients;


    public Designer() {
        super();
    }

    public Designer(String name, LocalDate birthDate, Map<Person, relationshipType> relationship, Map<Company, employeeType> jobs) {
        super(name, birthDate, relationship, jobs);
    }


    public Designer(String name, LocalDate birthDate, Map<Person, relationshipType> relationship, Map<Company, employeeType> jobs, int salary, DesignerType field, int maxClients) {
        super(name, birthDate, relationship, jobs);
        this.salary = salary;
        this.field = field;
        this.maxClients = maxClients;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }

    public DesignerType getField() {
        return field;
    }

    public void setField(DesignerType field) {
        this.field = field;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}

