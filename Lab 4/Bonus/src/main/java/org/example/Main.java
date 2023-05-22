package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Faker faker = new Faker();
        var students = IntStream.rangeClosed(0, 4)
                .mapToObj(i -> new Student(faker.name().fullName()))
                .toList();

        LinkedList<Student> studentList = new LinkedList<>(students);
        Collections.sort(studentList);
        System.out.println(studentList);
        var projects = IntStream.rangeClosed(0, 5).mapToObj(i -> {
            return new Project(faker.animal().name());

        }).toList();
        TreeSet<Project> projectTreeSet = new TreeSet<>(projects);
        System.out.println(projectTreeSet);


        Problem pb = new Problem();
        pb.newKey(studentList.get(0), projects);
        pb.newKey(studentList.get(1), Arrays.asList(projects.get(0), projects.get(1)));
        pb.newKey(studentList.get(2), Arrays.asList(projects.get(1)));
        pb.newKey(studentList.get(3), Arrays.asList(projects.get(3)));
        pb.newKey(studentList.get(4), Arrays.asList(projects.get(5)));
        pb.getLowPref();
        Solve sv = new Solve(pb);
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();
        sv.greedySolve();
        System.out.println("Greedy solution: " + sv);
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Greedy: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        System.out.println("JGraphT solution: " + sv.maximumMatchingSolve());
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("JGraphT: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        sv.maximumMatchingSolve2();
        System.out.println("Graph4J solution: " + sv);
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Graph4J: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);

        System.out.println("-----------------------------------------------------");
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        RandomGeneratorGraph4J rd4 = new RandomGeneratorGraph4J();
        rd4.createProblem();
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Create instance with Graph4J: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        Solve sv2 = new Solve(rd4.pb);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        sv2.greedySolve();
        System.out.println("Greedy solution: " + sv2);
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Greedy: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        System.out.println("JGraphT solution: " + sv2.maximumMatchingSolve());
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("JGraphT: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        sv2.maximumMatchingSolve2();
        System.out.println("Graph4J solution: " + sv2);
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Graph4J: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        sv2.minimumVertexCover();

        System.out.println("-----------------------------------------------------");

        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();

        RandomGeneratorJGraphT rd = new RandomGeneratorJGraphT();
       // rd.createProblem();
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Create instance with JGraphT: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);

        Solve sv3 = new Solve(rd.pb);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        sv3.greedySolve();
        System.out.println("Greedy solution: " + sv3);
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("JGraphT: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        System.out.println("JGraphT solution: " + sv3.maximumMatchingSolve());
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("JGraphT: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        sv3.maximumMatchingSolve2();
        System.out.println("Graph4J solution: " + sv3);
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("JGraphT: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        sv3.minimumVertexCover();
        sv3.maximumStableSet();

    }


}