package factory.album;

import entity.Album;
import entity.Artist;
import entity.Genre;
import factory.ConnectionPool;
import factory.albumGenre.AlbumGenreDao;
import factory.albumGenre.JDBCAlbumGenreDao;
import factory.artist.JDBCArtistDao;
import factory.genre.JDBCGenreDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAlbumDao implements AlbumDao {
    public void create(int year, String title, String artist, String genre) throws Exception {
        //  Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        JDBCArtistDao artistDAO = new JDBCArtistDao();
        int id;
        if (artistDAO.findByName(artist) == null) {
            artistDAO.create(artist);
        }
        id = artistDAO.findByName(artist).getId();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (release_year,title,artist_id,genre) values (?,?,?,?)")) {
            pstmt.setInt(1, year);
            pstmt.setString(2, title);
            pstmt.setInt(3, id);
            pstmt.setString(4, genre);
            pstmt.executeUpdate();
        }
        con.commit();
        JDBCGenreDao genreDAO = new JDBCGenreDao();
        AlbumGenreDao albumToGenreDAO = new JDBCAlbumGenreDao();
        List<String> genres = List.of(genre.split(","));
        for (String genreName : genres) {
            if (genreDAO.findByName(genreName) == null) {
                genreDAO.create(genreName);
                con.commit();
                albumToGenreDAO.create(findByTitleArtistYear(title, artist, year), genreDAO.findByName(genreName).getId());
            }
        }
        con.commit();
        con.close();
    }


    public List<Album> findByName(String name)throws SQLException{
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from albums where title = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                //String artist = rs.getString("artist")
                int artistId = rs.getInt("artist_id");
                Artist artist1 = new JDBCArtistDao().findById(artistId);

                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                JDBCGenreDao genreDAO = new JDBCGenreDao();
                for (String genre : genres) {
                    genresList.add(genreDAO.findByName(genre));
                }

                Album album = new Album( title,releaseYear, artist1, genresList);
                albums.add(album);
            }
            return albums;

        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            con.close();
        }
    }
    public List<Album> findAll() throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select * from albums");
            rs = stmt.executeQuery();
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
               // String artist = rs.getString("artist");
                int artistId = rs.getInt("artist_id");
                Artist artist1 = new JDBCArtistDao().findById(artistId);
                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                JDBCGenreDao genreDAO = new JDBCGenreDao();
                for (String genre : genres) {
                    genresList.add(genreDAO.findByName(genre));
                }
                Album album = new Album( title,releaseYear, artist1, genresList);
                albums.add(album);
            }
            return albums;
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

    public List<Album> findByTitle(String title) throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("select id,release_year,artist_id,genre from albums where title=?");
            stmt.setString(1, title);
            rs = stmt.executeQuery();
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                //String artist = rs.getString("artist");
                int artistId = rs.getInt("artist_id");
                Artist artist1 = new JDBCArtistDao().findById(artistId);
                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                JDBCGenreDao genreDAO = new JDBCGenreDao();
                for (String genre : genres) {
                    genresList.add(genreDAO.findByName(genre));
                }
               // Artist artist1 = new JDBCArtistDao().findByName(artist);
                Album album = new Album(title,releaseYear, artist1,  genresList);
                albums.add(album);
                return albums;
            }
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
//
    }

    public List<Album> findByArtist(String artist) throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        Artist artist1 = new JDBCArtistDao().findByName(artist);
        if(artist1==null){
         System.out.println("Artist not found");
            return null;
        }
        int artistId = artist1.getId();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums where artist_id='" + artistId + "'")) {
            List<Album> albums = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                List<String> genres = List.of(rs.getString("genre").split(","));
                List<Genre> genresList = new ArrayList<>();
                JDBCGenreDao genreDAO = new JDBCGenreDao();
                for (String genre : genres) {
                    ;
                    genresList.add(genreDAO.findByName(genre));
                }
               // Artist artist1 = new JDBCArtistDao().findByName(artist);
                Album album = new Album(title,  releaseYear,artist1, genresList);
                albums.add(album);
            }
            con.close();
            return albums;
        }
    }


    public Album findById(int id) throws SQLException {
        // Connection con = Database.getConnection();
        Connection con = ConnectionPool.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums where id=" + id)) {
            con.close();
            if (rs.next()) {
                List<Genre> genres = new ArrayList<>();
                String[] genresString = rs.getString("genre").split(",");
                for (String genre : genresString) {
                    genres.add(new JDBCGenreDao().findByName(genre));
                }
                return new Album(rs.getString("title"),  rs.getInt("release_year"),new JDBCArtistDao().findById(rs.getInt("artist_id")), genres);
            } else
                return null;

        }
    }

    public Integer findByTitleArtistYear(String title, String artist, int year) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Artist artist1 = new JDBCArtistDao().findByName(artist);
        if(artist1==null)
        {
            System.out.println("Artist not found");
            return null;
        }
        int art = artist1.getId();
        try {
            stmt = con.prepareStatement("select id from albums where title=? and artist_id=? and release_year=?");
            stmt.setString(1, title);
            stmt.setInt(2, art);
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
