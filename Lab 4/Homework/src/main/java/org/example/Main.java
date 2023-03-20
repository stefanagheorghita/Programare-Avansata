package org.example;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Random random= new Random();
        Faker faker=new Faker();
        var students= IntStream.rangeClosed(0, 4)
                .mapToObj(i -> new Student(faker.name().fullName()) )
                .toList();

        LinkedList<Student> studentList = new LinkedList<>(students);
        Collections.sort(studentList);
        System.out.println(studentList);
        var projects=IntStream.rangeClosed(0,5).mapToObj(i-> {
         return new Project(faker.animal().name());

        }).toList();
        TreeSet<Project> projectTreeSet = new TreeSet<>(projects);
        System.out.println(projectTreeSet);


        Problem pb=new Problem();
        pb.newKey(studentList.get(0),projects);
        pb.newKey(studentList.get(1), Arrays.asList(projects.get(0),projects.get(1)));
        pb.newKey(studentList.get(2), Arrays.asList(projects.get(1)));
        pb.newKey(studentList.get(3), Arrays.asList(projects.get(3)));
        pb.newKey(studentList.get(4), Arrays.asList(projects.get(5)));
        pb.getLowPref();
        Solve sv=new Solve(pb);
        sv.greedySolve();
        System.out.println(sv);
    }

}