package org.example.DAO;

import org.example.ConnectionPool;
import org.example.model.Album;
import org.example.Database;
import org.example.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AlbumDAO {

    public void create(int year, String title, String artist, String genre) throws SQLException {
        //  Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (release_year,title,artist,genre) values (?,?,?,?)")) {
            pstmt.setInt(1, year);
            pstmt.setString(2, title);
            pstmt.setString(3, artist);
            pstmt.setString(4, genre);
            pstmt.executeUpdate();
        }
        ArtistDAO artistDAO = new ArtistDAO();
        if (artistDAO.findByName(artist) == null) {
            artistDAO.create(artist);
        }
        GenreDAO genreDAO = new GenreDAO();
        AlbumToGenreDAO albumToGenreDAO = new AlbumToGenreDAO();
        List<String> genres = List.of(genre.split(","));
        for (String genreName : genres) {
            if (genreDAO.findByName(genreName) == null) {
                genreDAO.create(genre);
                albumToGenreDAO.create(findByTitleArtistYear(title, artist, year), genreDAO.findByName(genreName));
            }
        }
    }

    public List<Album> findAll() throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums")) {
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                GenreDAO genreDAO = new GenreDAO();
                for (String genre : genres) {
                    int genreId = genreDAO.findByName(genre);
                    genresList.add(new Genre(genreId, genre));
                }
                Album album = new Album(id, title, artist, releaseYear, genresList);
                albums.add(album);
            }
            return albums;
        }
    }

    public List<Album> findByTitle(String title) throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id,release_year,artist,genre from albums where title='" + title + "'")) {
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String artist = rs.getString("artist");
                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                GenreDAO genreDAO = new GenreDAO();
                for (String genre : genres) {
                    int genreId = genreDAO.findByName(genre);
                    genresList.add(new Genre(genreId, genre));
                }
                Album album = new Album(id, title, artist, releaseYear, genresList);
                albums.add(album);
                return albums;
            }
        }
        return null;
    }

    public List<Album> findByArtist(String artist) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums where artist='" + artist + "'")) {
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                GenreDAO genreDAO = new GenreDAO();
                for (String genre : genres) {
                    int genreId = genreDAO.findByName(genre);
                    genresList.add(new Genre(genreId, genre));
                }
                Album album = new Album(id, title, artist, releaseYear, genresList);
                albums.add(album);
            }
            return albums;
        }
    }


    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select title from albums where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public Integer findByTitleArtistYear(String title, String artist, int year) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select id from albums where title=? and artist=? and release_year=?");
            stmt.setString(1, title);
            stmt.setString(2, artist);
            stmt.setInt(3, year);
            rs = stmt.executeQuery();
            return rs.next() ? rs.getInt(1) : null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
