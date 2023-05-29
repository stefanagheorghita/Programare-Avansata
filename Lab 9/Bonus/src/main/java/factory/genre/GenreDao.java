package factory.genre;

import entity.Genre;

import java.sql.SQLException;
import java.util.List;
public interface GenreDao {

    Genre findById(int id) throws SQLException;
    Genre findByName(String name) throws SQLException;

    List<Genre> findAll() throws SQLException;
    void create(String name) throws SQLException;
}
