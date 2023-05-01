package org.example.DAO;

import org.example.ConnectionPool;
import org.example.Database;
import org.example.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    public void create(String name) throws SQLException {
        //Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();

        }
        con.close();
    }

    public List<Artist> findAll() throws SQLException {
        //Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists")) {
            List<Artist> artists = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Artist artist = new Artist(id, name);
                artists.add(artist);
            }
            con.close();
            return artists;
        }
    }

    public Artist findByName(String name) throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from artists where name=?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (rs.next())
                return new Artist(rs.getInt(1), name);
            else
                return null;

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

    public Artist findById(int id) throws SQLException {
        //  Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists where id=" + id)) {
            con.close();
            if (rs.next())
                return new Artist(id, rs.getString(2));
            else
                return null;
        }
    }
}
