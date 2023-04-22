package org.example.graph;

import org.example.Catalog;
import org.example.CatalogUtil;
import org.example.entries.Document;
import org.graph4j.Edge;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Graph {

    private Catalog catalog;

    private org.jgrapht.Graph<Document, DefaultEdge> graph1;
    private org.graph4j.Graph<Document, Edge> graph2;

    public Graph(Catalog catalog) {
        this.catalog = catalog;
    }

    public void constructGraph1() {
        CatalogUtil catalogUtil = new CatalogUtil();
        graph1 = new SimpleGraph<>(DefaultEdge.class);
        for (Document d : catalog.getDocumentList()) {
            graph1.addVertex(d);
        }
        for (Document d : catalog.getDocumentList()) {
            for (Document d2 : catalog.getDocumentList()) {
                if (d != d2) {
                    if (catalogUtil.areConnected(d, d2)) {
                        graph1.addEdge(d, d2);
                    }
                }
            }
        }
    }

    public void constructGraph2() {
        CatalogUtil catalogUtil = new CatalogUtil();
        graph2 = org.graph4j.GraphBuilder.empty()
                .estimatedNumVertices(catalog.getDocumentList().size())
                .buildGraph();
        for (Document d : catalog.getDocumentList()) {
            graph2.addVertex(d);
        }
        for (Document d : catalog.getDocumentList()) {
            for (Document d2 : catalog.getDocumentList()) {
                if (d != d2) {
                    if (catalogUtil.areConnected(d, d2)) {
                        graph2.addEdge(d, d2);
                    }
                }
            }
        }
    }


    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public org.jgrapht.Graph<Document, DefaultEdge> getGraph1() {
        return graph1;
    }

    public void setGraph1(org.jgrapht.Graph<Document, DefaultEdge> graph1) {
        this.graph1 = graph1;
    }

    public org.graph4j.Graph<Document, Edge> getGraph2() {
        return graph2;
    }

    public void setGraph2(org.graph4j.Graph<Document, Edge> graph2) {
        this.graph2 = graph2;
    }
}
