package org.example;

import org.example.DAO.AlbumDAO;
import org.example.graph.Graph;
import org.example.model.Album;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreatePlaylists {

    public static void main(String[] args) {
        ConnectionPool con = new ConnectionPool();
        List<Album> albums = new ArrayList<>();
        try {
           // ConnectionPool.getConnection().commit();

            var albumDAO = new AlbumDAO();
            albums.addAll(albumDAO.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Graph graph = new Graph();
        graph.constructGraph(albums);
        graph.algorithm();

    }
}
