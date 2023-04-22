package org.example.graph;

import org.example.entries.Document;
import org.graph4j.Edge;

import java.util.ArrayList;
import java.util.List;

public class ColoringGraph4J {
    private org.graph4j.alg.coloring.VertexColoring c2;
    private int chromaticNumber;
    org.graph4j.Graph<Document, Edge> G;

    public ColoringGraph4J(org.graph4j.Graph<Document, Edge> G) {
        this.G = G;
    }

    public void graph4JGreedyColoring() {
        org.graph4j.alg.coloring.GreedyColoring greedyColoring = new org.graph4j.alg.coloring.GreedyColoring(G);
        c2 = greedyColoring.findColoring();
        chromaticNumber = c2.numUsedColors();
    }

    public void graph4JBrownColoring() {
        BrownColoringGraph4J brownBacktrackColoring = new BrownColoringGraph4J(G);
        c2 = brownBacktrackColoring.findColoring(G);
        chromaticNumber = c2.numUsedColors();
    }

    @Override
    public String toString() {
        List<Document> vertices = new ArrayList<>();
        for (int v = 0; v < c2.numColoredVertices(); v++) {
            vertices.add(G.getVertexLabel(v));
        }
        String coloring = "Coloring{";
        for (int v = 0; v < c2.numColoredVertices(); v++) {
            coloring += vertices.get(v) + " = " + c2.getColor(v) + ", ";
        }
        return coloring;
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

}
