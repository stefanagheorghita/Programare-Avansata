package factory.album;

import entity.Album;
import entity.Artist;
import entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JPAAlbumDao implements AlbumDao {

    private EntityManager em;

    public JPAAlbumDao(EntityManager em) {
        this.em = em;
    }

    public Album findById(int id) {
        return em.find(Album.class, id);
    }

    public List<Album> findByName(String name) {
        return em.createNamedQuery("Album.findByName")
                .setParameter("name", name)
                .getResultList();
    }

    public void create(int year, String title, String artist, String genre) {
        Album album = new Album();
        album.setReleaseYear(year);
        album.setTitle(title);
        Artist artist1 = null;
        try {
            artist1 = em.createNamedQuery("Artist.findByName", Artist.class)
                    .setParameter("name", artist)
                    .getSingleResult();
        } catch (Exception e) {
            artist1 = new Artist();
            artist1.setName(artist);
            em.persist(artist1);
        }
        album.setArtist(artist1);
        List<String> genres = List.of(genre.split(","));
        List<Genre> genreList = new ArrayList<>();
        for (int i = 0; i < genres.size(); i++) {
            try{
            genreList.add(em.createNamedQuery("Genre.findByName", Genre.class)
                    .setParameter("name", genres.get(i))
                    .getSingleResult());}
            catch (Exception e){
                Genre genre1 = new Genre();
                genre1.setName(genres.get(i));
                em.persist(genre1);
                genreList.add(genre1);
            }
        }
        album.setGenres(genreList);
        em.persist(album);
    }

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
