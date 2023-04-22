package org.example.graph;

import org.example.entries.Document;
import org.graph4j.alg.coloring.VertexColoring;
import org.graph4j.alg.coloring.VertexColoringAlgorithm;
import org.graph4j.*;


import java.util.*;

public class BrownColoringGraph4J<V> {
    private final List<V> vertexList; // list of all vertices
    private final int[][] neighbors;


    private int[] partialColorAssignment; // color assigned to a specific vertex
    private int[] colorCount; // Number of colors used up to the ith vertex that has been colored
    private BitSet[] allowedColors;
    private int chi; // chromatic number

    private int[] completeColorAssignment;
    private org.graph4j.alg.coloring.VertexColoring<Document> vertexColoring;

    public BrownColoringGraph4J(org.graph4j.Graph<V, Edge> graph) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        final int numVertices = graph.vertices().length;
        vertexList = new ArrayList<>(numVertices);
        neighbors = new int[numVertices][];

        for (int v : graph.vertices()) {
            neighbors[vertexList.size()] = new int[graph.edgesOf(v).length];
            vertexList.add(graph.getVertexLabel(v));
        }
        for (int i = 0; i < numVertices; i++) {
            int nbIndex = 0;
            final V vertex = vertexList.get(i);
            for (Edge e : graph.edgesOf(i)) {
                if (i == e.source())
                    neighbors[i][nbIndex++] = e.target();
                else if (i == e.target())
                    neighbors[i][nbIndex++] = e.source();
            }
        }
    }

    private void recursiveColor(int pos) {
        colorCount[pos] = colorCount[pos - 1];
        allowedColors[pos].set(0, colorCount[pos] ); // To color the ith vertex, one can use the

        for (int i = 0; i < neighbors[pos].length; i++) {
            final int nb = neighbors[pos][i];
            if (partialColorAssignment[nb] > -1) {
                allowedColors[pos].clear(partialColorAssignment[nb]);
            }
        }

        for (int i = 0; (i < colorCount[pos]) && (colorCount[pos]-1 < chi); i++) {
            if (allowedColors[pos].get(i)) { // Try all available colors for vertex i. A color is
                // available if its not used by its neighbor
                partialColorAssignment[pos] = i;
                if (pos < (neighbors.length - 1)) { // If not all vertices have been colored,
                    // proceed with the next uncolored vertex
                    recursiveColor(pos + 1);
                } else { // Otherwise we have found a feasible coloring
                    chi = colorCount[pos];
                    System
                            .arraycopy(
                                    partialColorAssignment, 0, completeColorAssignment, 0,
                                    partialColorAssignment.length);
                }
            }
        }
        // consider using a new color for vertex i
        if ((colorCount[pos] + 1) < chi) {
            colorCount[pos]++;
            partialColorAssignment[pos] = colorCount[pos]-1;
            if (pos < (neighbors.length - 1)) {
                recursiveColor(pos + 1);
            } else {
                chi = colorCount[pos];
                System
                        .arraycopy(
                                partialColorAssignment, 0, completeColorAssignment, 0,
                                partialColorAssignment.length);
            }
        }
        partialColorAssignment[pos] = 0;
    }

    private void lazyComputeColoring(org.graph4j.Graph<V, Edge> graph) {
        if (vertexColoring != null)
            return;

        chi = neighbors.length + 1;
        partialColorAssignment = new int[neighbors.length];
        completeColorAssignment = new int[neighbors.length];
        partialColorAssignment[0] = 0;
        colorCount = new int[neighbors.length];
        colorCount[0] = 1;
        allowedColors = new BitSet[neighbors.length];
        for (int i = 0; i < neighbors.length; i++) {
            allowedColors[i] = new BitSet(1);
        }
        recursiveColor(1);

        int[] colorMap = new int[neighbors.length];
        for (int i = 0; i < vertexList.size(); i++)
            colorMap[i] = completeColorAssignment[i];
        vertexColoring = new VertexColoring(graph, colorMap);


    }


    public int getChromaticNumber(org.graph4j.Graph<V, Edge> graph) {

        return vertexColoring.numUsedColors();
    }

    public org.graph4j.alg.coloring.VertexColoring findColoring(org.graph4j.Graph<V, Edge> graph) {
        lazyComputeColoring(graph);

        return vertexColoring;
    }


}
