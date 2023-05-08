package repository;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import java.util.List;

public class AlbumRepository {
    private EntityManager em;

    public void create(Album album) {
        em.persist(album);
    }

    public Album findById(Integer id) {
        return em.find(Album.class, id);
    }

    public List<Album> findByArtist(Artist artist) {
        return em.createNamedQuery("Album.findByArtist")
                .setParameter("artist", artist)
                .getResultList();
    }

    public List<Album> findByTitle(String title) {
        return em.createNamedQuery("Album.findByTitle")
                .setParameter("title", title)
                .getResultList();
    }

}
