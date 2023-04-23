package org.example.DAO;

import org.example.model.Album;
import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    public void create(int year, String title, String artist, String genre) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (release_year,title,artist,genre) values (?,?,?,?)")) {
            pstmt.setInt(1, year);
            pstmt.setString(2, title);
            pstmt.setString(3, artist);
            pstmt.setString(4, genre);
            pstmt.executeUpdate();
        }
    }

    public List<Album> findAll() throws SQLException {
        Connection con = Database.getConnection();
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
                Album album = new Album(id, title, artist, releaseYear, genres);
                albums.add(album);
            }
            return albums;
        }
    }

    public List<Album> findByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id,release_year,artist,genre from albums where title='" + title + "'")) {
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String artist = rs.getString("artist");
                List<String> genres = List.of(rs.getString("genre").split(","));
                Album album = new Album(id, title, artist, releaseYear, genres);
                albums.add(album);
                return albums;
            }
        }
        return null;
    }

    public Integer findByArtist(String artist) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from albums where artist='" + artist + "'")) {
            return rs.next() ? rs.getInt(1) : null;
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
}
