package factory.album;

import entity.Album;
import java.util.List;
public interface AlbumDao {
    void create(int year, String title, String artist, String genre) throws Exception;
    Album findById(int id) throws Exception;
    List<Album> findByName(String name) throws Exception;
    List<Album> findAll() throws Exception;

}
