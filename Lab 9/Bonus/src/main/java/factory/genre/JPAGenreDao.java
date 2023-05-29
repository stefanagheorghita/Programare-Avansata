package factory.genre;

import entity.Genre;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class JPAGenreDao implements GenreDao {

    private EntityManager em;

    @Override
    public List<Genre> findAll() throws SQLException {
        return em.createNamedQuery("Genre.findAll")
                .getResultList();
    }

    public JPAGenreDao(EntityManager em) {
        this.em = em;
    }

    public void create(String name) {
        entity.Genre genre = new entity.Genre();
        genre.setName(name);
        em.persist(genre);
    }

    public entity.Genre findById(int id) {
        return em.find(entity.Genre.class, id);
    }

    public entity.Genre findByName(String name) {
        return (entity.Genre) em.createNamedQuery("Genre.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }

    public Class<entity.Genre> getEntityClass() {
        return entity.Genre.class;
    }

}
