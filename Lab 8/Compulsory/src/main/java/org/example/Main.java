package org.example;

import org.example.DAO.AlbumDAO;
import org.example.DAO.ArtistDAO;
import org.example.DAO.GenreDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();
            System.out.println("Albums:");
            System.out.println("Finding the albums with the name-The Wall: " + albums.findByTitle("The Wall"));
            System.out.println("Finding the albums with the name-Thriller: " + albums.findByTitle("Thriller"));
            System.out.println("All the albums: " + albums.findAll());
            System.out.println("Artists:");
            System.out.println("Finding the artists with the id-1: " + artists.findById(1));
            System.out.println("Finding the artists with the name-Michael Jackson: " + artists.findByName("Michael Jackson"));
            System.out.println("All the artists: " + artists.findAll());
            System.out.println("All the genres:" + genres.findAll());
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}