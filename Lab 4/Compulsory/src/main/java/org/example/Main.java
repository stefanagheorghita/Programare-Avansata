package main.java.org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Random random= new Random();
        //List<Student> students = Stream.of(new Student("S0", List.of(new Project("P0"), new Project("P1"), new Project("P2"))), new Student("S2", List.of(new Project("P0"))), new Student("S1", List.of(new Project("P0"), new Project("P1")))).collect(Collectors.toList());
       // List<Project> projects = Stream.of(new Project("P0"), new Project("P1"), new Project("P2")).collect(Collectors.toList());
        var students= IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + random.nextInt(26)) )
                .toList();

        LinkedList<Student> studentList = new LinkedList<>(students);
        Collections.sort(studentList);
        System.out.println(studentList);
        var projects=IntStream.rangeClosed(0,2).mapToObj(i->new Project("P"+i)).toList();
        TreeSet<Project> projectTreeSet = new TreeSet<>(projects);
        System.out.println(projectTreeSet);
    }
}