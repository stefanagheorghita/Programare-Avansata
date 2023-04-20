package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Student implements Comparable<Student>, Node {
    private String name;

    private List<Project> projects = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Project> admissibleProjects) {
        this.name = name;
        this.projects = admissibleProjects;
    }

    public List<Project> getAdmissibleProjects() {
        return projects;
    }

    public void setAdmissibleProjects(List<Project> admissibleProjects) {
        this.projects = admissibleProjects;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Student s) {
        if (this == null || s == null) {
            System.out.println("One of the objects is null.");
            return 0;
        }

        return this.name.compareTo(s.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}