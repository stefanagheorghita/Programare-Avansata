import java.time.LocalDate;
import java.util.Map;

public class Programmer extends Person {
    private ProgrammerType type;
    private int salary;
    private int workHours;

    private String position;

    public Programmer() {
    }

    public Programmer(String name) {
        super(name);
    }

    public Programmer(String name, LocalDate birthDate, Map<Person, RelationshipType> relationship, Map<Company, EmployeeType> jobs) {
        super(name, birthDate, relationship, jobs);
        position = "Programmer";
    }

    public Programmer(String name, LocalDate birthDate, Map<Person, RelationshipType> relationship, Map<Company, EmployeeType> jobs, ProgrammerType type, int salary) {
        super(name, birthDate, relationship, jobs);
        this.type = type;
        this.salary = salary;
        position = "Programmer";
    }


    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ProgrammerType getType() {
        return type;
    }

    public void setType(ProgrammerType type) {
        this.type = type;
    }

    public String getPosition() {
        return "Programmer";
    }

    public void setPosition(String position) {
        this.position = "Programmer";
    }


}
