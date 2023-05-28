package org.example.DAO;

import org.example.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaylistDAO {

    public void create(String name,String albums) throws SQLException {
        //Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into playlists (name,albums,created_at) values (?,?,now())")) {
            pstmt.setString(1, name);
            pstmt.setString(2, albums);
            pstmt.executeUpdate();
            con.commit();
        }
        con.close();
    }

    public void addAlbum(int playlistId, int albumId) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into playlist_albums (playlist_id,album_id) values (?,?)")) {
            pstmt.setInt(1, playlistId);
            pstmt.setInt(2, albumId);
            pstmt.executeUpdate();
            con.commit();
        }
        con.close();
    }
}
