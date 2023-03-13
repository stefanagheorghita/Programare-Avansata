package main.java.org.example;

import java.util.List;

public class Project implements Comparable<Project> {
    private String name;

    //private List<Student>
    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Project p) {
        if (this == null || p == null) {
            System.out.println("One of the objects is null.");
            return 0;
        }
        return this.name.compareTo(p.name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}