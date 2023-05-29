package factory.artist;

import entity.Artist;

import java.sql.SQLException;
import java.util.List;
public interface ArtistDao {
        void create(String name) throws SQLException;
        Artist findById(int id) throws SQLException;
        Artist findByName(String name) throws SQLException;
        List<Artist> findAll() throws SQLException;

}
