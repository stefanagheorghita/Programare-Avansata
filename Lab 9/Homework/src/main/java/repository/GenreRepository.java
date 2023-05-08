package repository;

import javax.persistence.EntityManager;

public class GenreRepository {
    private EntityManager em;

    public GenreRepository(EntityManager em) {
        this.em = em;
    }

    public void create(entity.Genre genre) {
        em.persist(genre);
    }

    public entity.Genre findById(Integer id) {
        return em.find(entity.Genre.class, id);
    }

    public entity.Genre findByName(String name) {
        return (entity.Genre) em.createNamedQuery("Genre.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }

}
