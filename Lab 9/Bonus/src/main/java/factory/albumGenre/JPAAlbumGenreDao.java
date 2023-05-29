package factory.albumGenre;

import entity.AlbumToGenres;

import javax.persistence.EntityManager;

public class JPAAlbumGenreDao implements AlbumGenreDao{

    private EntityManager em;

    public JPAAlbumGenreDao(EntityManager em) {
        this.em = em;
    }


    public void create(int id1, int id2) throws Exception {
        AlbumToGenres albumToGenres = new AlbumToGenres();
        albumToGenres.setAlbumId(id1);
        albumToGenres.setGenreId(id2);
        em.persist(albumToGenres);
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
