public class Company implements Node, java.lang.Comparable<Company> {


    private String name;

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

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}
