package org.example.DAO;

import org.example.ConnectionPool;
import org.example.Database;
import org.example.model.Artist;
import org.example.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    public void create(String name) throws SQLException {
//        Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
        con.close();
    }

    public List<Genre> findAll() throws SQLException {
//        Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres")) {
            List<Genre> genres = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Genre genre = new Genre(id, name);
                genres.add(genre);
            }
            con.close();
            return genres;
        }
    }

    public Genre findByName(String name) throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from genres where name=?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (rs.next())
                return new Genre(rs.getInt(1), name);
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

    public Genre findById(int id) throws SQLException {
        //  Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres where id=" + id)) {
            if (rs.next())
                return new Genre(id, rs.getString(2));
            else
                return null;
        }
    }

}
