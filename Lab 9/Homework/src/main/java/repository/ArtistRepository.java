package repository;

import entity.Artist;

import javax.persistence.EntityManager;

public class ArtistRepository {
    private EntityManager em;

    public ArtistRepository(EntityManager em) {
        this.em = em;
    }

    public void create(Artist artist) {
        em.persist(artist);
    }

    public Artist findById(Integer id) {
        return em.find(Artist.class, id);
    }

    public Artist findByName(String name) {
        return (Artist) em.createNamedQuery("Artist.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }
}
