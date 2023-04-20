package org.example;

import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.DenseEdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve {
    private Problem pb;
    private Map<Student, Project> solution = new HashMap<>();
    private Map<Project, Boolean> availableProject = new HashMap<>();
    private HopcroftKarpMaximumMatching matching;


    public Solve(Problem pb) {
        this.pb = pb;

        for (List<Project> p : pb.getPrefMap().values())
            for (Project pr : p) {
                availableProject.put(pr, false);
            }

    }

    public void greedySolve() {
        for (Student s : pb.getPrefMap().keySet()) {
            int i = 0;
            while (i < pb.getPrefMap().get(s).size() && availableProject.get(pb.getPrefMap().get(s).get(i))) {
                i++;
            }
            if (i < pb.getPrefMap().get(s).size()) {
                solution.put(s, pb.getPrefMap().get(s).get(i));
                availableProject.put(pb.getPrefMap().get(s).get(i), true);
            } else {
                System.out.println("The algorithm didn't give a valid solution.");
                return;
            }

        }
        System.out.println("Greedy- Solution:" + solution.size());
    }

    public List<DefaultEdge> maximumMatchingSolve() {
        Graph<Node, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        for (Student s : pb.getPrefMap().keySet()) {
            graph.addVertex(s);
        }
        for (Project p : availableProject.keySet()) {
            graph.addVertex(p);
        }
        for (Student s : pb.getPrefMap().keySet()) {
            for (Project p : pb.getPrefMap().get(s)) {
                graph.addEdge(s, p);
            }
        }
        MatchingAlgorithm<Node, DefaultEdge> denseEdmondsMaximumCardinalityMatching = new DenseEdmondsMaximumCardinalityMatching<>(graph);
        List<DefaultEdge> matching = new ArrayList<>(denseEdmondsMaximumCardinalityMatching.getMatching().getEdges());
        System.out.println("JGraphT- Matching:" + matching.size());
        return matching;
    }


    public void maximumMatchingSolve2() {
        org.graph4j.Graph graph = org.graph4j.GraphBuilder.empty()
                .estimatedNumVertices(pb.getPrefMap().keySet().size() + availableProject.keySet().size())
                .buildGraph();
        //org.graph4j.Graph gr = new org.graph4j.GraphBuilder().empty();

        for (Student s : pb.getPrefMap().keySet()) {
            graph.addVertex(s);
        }
        for (Project p : availableProject.keySet()) {
            graph.addVertex(p);
        }
        for (Student s : pb.getPrefMap().keySet()) {
            for (Project p : pb.getPrefMap().get(s)) {
                graph.addEdge(s, p);
            }
        }
        this.matching = new HopcroftKarpMaximumMatching(graph);
        HopcroftKarpMaximumMatching matching = new HopcroftKarpMaximumMatching(graph);
        solution = new HashMap<>();
        for (int[] e : matching.getMatching().edges()) {

            solution.put(new ArrayList<>(pb.getPrefMap().keySet()).get(e[0]), new ArrayList<>(availableProject.keySet()).get(e[1] - pb.getPrefMap().keySet().size()));
        }

        System.out.println("Graph4J- Matching:" + matching.getMatching().edges().length);
        matching.getMinimumVertexCover();
    }

    public void minimumVertexCover() {
        System.out.println("Graph4J- Minimum Vertex Cover:" + matching.getMinimumVertexCover().size());
        System.out.print("Minimum Vertex Cover: ");
        for (int n : matching.getMinimumVertexCover()) {
            if (n < pb.getPrefMap().keySet().size())
                System.out.print(new ArrayList<>(pb.getPrefMap().keySet()).get(n));
            else
                System.out.print(new ArrayList<>(availableProject.keySet()).get(n - pb.getPrefMap().keySet().size()));
            System.out.print(",");
        }


    }
    public void maximumStableSet(){
        System.out.println("Graph4J- Maximum Stable Set:" + matching.getMaximumStableSet().size());
        System.out.print("Maximum Stable Set: ");
        for (int n : matching.getMaximumStableSet()) {
            if (n < pb.getPrefMap().keySet().size())
                System.out.print(new ArrayList<>(pb.getPrefMap().keySet()).get(n));
            else
                System.out.print(new ArrayList<>(availableProject.keySet()).get(n - pb.getPrefMap().keySet().size()));
            System.out.print(",");
        }
    }

    public Map<Student, Project> getSolution() {
        return solution;
    }

    public void setSolution(Map<Student, Project> solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Solve{" +
                "solution=" + solution +
                '}';
    }

}
