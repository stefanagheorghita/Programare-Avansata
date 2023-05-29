package repository;

import entity.AlbumToGenres;
import entity.Artist;

import javax.persistence.EntityManager;

public class AlbumToGenresRepository {

    private EntityManager em;

    public AlbumToGenresRepository(EntityManager em) {
        this.em = em;
    }

    public AlbumToGenres findByGenreId(Integer id) {
        return (AlbumToGenres) em.createNamedQuery("AlbumToGenres.findByGenreId")
                .setParameter("genre_id", id)
                .getSingleResult();
    }

    public AlbumToGenres findByAlbumId(Integer id) {
        return (AlbumToGenres) em.createNamedQuery("AlbumToGenres.findByGenreId")
                .setParameter("album_id", id)
                .getSingleResult();
    }


}
