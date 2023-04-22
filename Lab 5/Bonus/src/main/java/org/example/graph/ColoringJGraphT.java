package org.example.graph;

import org.example.entries.Document;
import org.jgrapht.Graph;
import org.jgrapht.alg.color.BrownBacktrackColoring;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;

public class ColoringJGraphT {
    private VertexColoringAlgorithm.Coloring<Document> c;
    private int chromaticNumber;

    public void JGraphTBrownColoring(Graph<Document, DefaultEdge> G) {
        BrownBacktrackColoring brownBacktrackColoring = new BrownBacktrackColoring(G);
        c = brownBacktrackColoring.getColoring();
        chromaticNumber = brownBacktrackColoring.getChromaticNumber();

    }

    public void JGraphTGreedyColoring(Graph<Document, DefaultEdge> G) {
        c = new GreedyColoring<Document, DefaultEdge>(G).getColoring();
        chromaticNumber = c.getNumberColors();
    }


    @Override
    public String toString() {
        return "Coloring{" +
                "c=" + c +
                '}';
    }

    public int getChromaticNumber() {

        return chromaticNumber;
    }
}

