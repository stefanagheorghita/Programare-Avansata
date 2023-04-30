package org.example.graph;

import org.example.DAO.AlbumDAO;
import org.example.model.Album;
import org.example.model.Genre;
import org.graph4j.Edge;
import org.graph4j.alg.clique.BronKerboschCliqueIterator;

import java.util.List;

public class Graph {
    private org.graph4j.Graph<Album, Edge> graph;

    public void constructGraph(List<Album> albumList){
        graph =  org.graph4j.GraphBuilder.empty()
                .estimatedNumVertices(albumList.size())
                .buildGraph();
        for (Album album : albumList) {
            graph.addVertex(album);
        }
        for (Album album : albumList) {
            for (Album album2 : albumList) {
                if (album != album2) {
                   if(areRelated(album, album2)) {
                        graph.addEdge(album, album2);
                    }
                }
            }
        }
    }


    public void algorithm() {
        if(graph==null) {
            System.out.println("Graph is null");
            System.exit(0);
        }

        BronKerboschCliqueIterator solving = new BronKerboschCliqueIterator(graph);
        while (solving.hasNext()) {
            System.out.println("Next playlist: "  );
            System.out.println(solving.next());
            System.out.println("--------------------------------------------------");
        }
    }

    public boolean areRelated(Album album1, Album album2) {
        if(album1.getArtist().equals(album2.getArtist())) {
            return true;
        }
        if(album1.getReleaseYear()==album2.getReleaseYear()) {
            return true;
        }
        for(Genre genre : album1.getGenres()) {
            if(album2.getGenres().contains(genre)) {
                return true;
            }
        }
        return false;
    }


}
