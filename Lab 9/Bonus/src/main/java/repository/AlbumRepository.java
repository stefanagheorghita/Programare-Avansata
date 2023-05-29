package repository;

import entity.Album;
import entity.Artist;
import entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AlbumRepository extends DataRepository<Album, Integer> {
    private EntityManager em;

    public AlbumRepository(EntityManager em) {
        this.em = em;
    }

//    public void create(Album album) {
//        em.persist(album);
//    }

    public Album findById(Integer id) {
        return em.find(Album.class, id);
    }

    public List<Album> findAll() {
        return em.createNamedQuery("Album.findAll")
                .getResultList();
    }

    public List<Album> findByReleaseYear(int releaseYear) {
        return em.createNamedQuery("Album.findByReleaseYear")
                .setParameter("releaseYear", releaseYear)
                .getResultList();
    }

    public List<Album> findByReleaseYearInterval(int firstYear, int lastYear) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Album> query = cb.createQuery(Album.class);
        Root<Album> albumRoot = query.from(Album.class);
        query.select(albumRoot);
        query.where(cb.between(albumRoot.get("releaseYear"), firstYear, lastYear));
        return em.createQuery(query).getResultList();

    }


    public List<Album> findByGenre(Genre genre) {
        return em.createNamedQuery("Album.findByGenre")
                .setParameter("genre", genre)
                .getResultList();
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

    public List<Album> findAlbumsByCriteria(Artist artist, List<Genre> genres, int releaseYear) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Album> query = cb.createQuery(Album.class);

        Root<Album> albumRoot = query.from(Album.class);
        query.select(albumRoot);
        query.where(
                cb.equal(albumRoot.get("artist"), artist),
                albumRoot.get("genres").in(genres),
                cb.equal(albumRoot.get("releaseYear"), releaseYear)
        );

        return em.createQuery(query).getResultList();
    }

    public Class<Album> getEntityClass() {
        return Album.class;
    }


}
