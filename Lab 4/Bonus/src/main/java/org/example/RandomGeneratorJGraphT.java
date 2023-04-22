package org.example;

import com.github.javafaker.Faker;
import org.graph4j.Edge;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnpRandomBipartiteGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class RandomGeneratorJGraphT {

    public Problem pb= new Problem();
    public int numStudents = 800;
    public int numProjects = 200;
    private Graph<Node, DefaultEdge> graph= new SimpleGraph<>(DefaultEdge.class);

    Faker faker = new Faker();


    RandomGeneratorJGraphT(){

        double edgeProbability = 0.3;

        // Create the two sets of vertices
        Set<Student> students = new HashSet<>();
        Set<Project> projects = new HashSet<>();
        for (int i = 0; i < numStudents; i++) {
            Student s = new Student(faker.name().fullName());
            students.add(s);
            graph.addVertex(s);
        }
        for (int i = 0; i < numProjects; i++) {
            Project p = new Project(faker.commerce().productName());
            projects.add(p);
            graph.addVertex(p);
        }

        // Generate the bipartite graph
        graph = new DefaultUndirectedGraph<>(new NodeSupplier(students,projects), SupplierUtil.createDefaultEdgeSupplier(), false);
        GnpRandomBipartiteGraphGenerator<Node, DefaultEdge> generator = new GnpRandomBipartiteGraphGenerator<>(numStudents, numProjects, edgeProbability);
        generator.generateGraph(graph);

        for(Student s : students){
            List<Project> pref = new ArrayList<>();
            for(Project p : projects){
                if(graph.containsEdge(s, p)){
                    pref.add(p);
                }
            }
            pb.newKey(s, pref);
        }

    }
    private class NodeSupplier implements Supplier<Node> {
        private final Iterator<Student> studentIterator;
        private final Iterator<Project> projectIterator;

        NodeSupplier(Set<Student> students, Set<Project> projects) {
            this.studentIterator = students.iterator();
            this.projectIterator = projects.iterator();
        }

        @Override
        public Node get() {
            if (studentIterator.hasNext()) {
                return studentIterator.next();
            } else {
                return projectIterator.next();
            }
        }
    }

    public void createProblem() {




    }
}
