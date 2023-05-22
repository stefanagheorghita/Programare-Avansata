package org.example;

import com.github.javafaker.Faker;
import org.graph4j.*;
import org.graph4j.generate.RandomGnmBipartiteGenerator;
import org.graph4j.generate.RandomGnpBipartiteGenerator;

import java.util.*;
import java.util.stream.IntStream;


public class RandomGeneratorGraph4J {

    public Graph graph;
    public Problem pb;

    RandomGeneratorGraph4J() {
        RandomGnpBipartiteGenerator gen = new RandomGnpBipartiteGenerator(800, 200, 0.3);
        graph = gen.createGraph();
        System.out.println(graph);
        pb = new Problem();
    }

    public void createProblem() {

        Faker faker = new Faker();
        var students = IntStream.rangeClosed(0, 799)
                .mapToObj(i -> new Student(faker.name().name()))
                .toList();


        LinkedList<Student> studentList = new LinkedList<>(students);
        Collections.sort(studentList);
        var projects = IntStream.rangeClosed(0, 199).mapToObj(i -> {
            return new Project(faker.animal().name());

        }).toList();
        TreeSet<Project> projectTreeSet = new TreeSet<>(projects);

        for (Student s : studentList) {
            List<Project> pref = new ArrayList<>();
            for (Edge e : graph.edges()) {
                if (e.source() == studentList.indexOf(s))
                    pref.add(projects.get(e.target()-800));
            }
            pb.newKey(s, pref);
        }

    }

}
