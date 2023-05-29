package factory.artist;

import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class JPAArtistDao implements ArtistDao {

    private EntityManager em;

    public void create(String name) {
        Artist artist = new Artist();
        artist.setName(name);
        em.persist(artist);

    }

    public JPAArtistDao(EntityManager em) {
        this.em = em;
    }

    public Artist findById(int id) throws SQLException {
        return em.find(Artist.class, id);
    }

    public Artist findByName(String name) {
        return (Artist) em.createNamedQuery("Artist.findByName")
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Artist> findByNamePattern(String pattern){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Artist> criteriaQuery = criteriaBuilder.createQuery(Artist.class);
        Root<Artist> artistRoot = criteriaQuery.from(Artist.class);
        criteriaQuery.select(artistRoot);

        Predicate predicate = criteriaBuilder.like(artistRoot.get("name"), pattern);
        criteriaQuery.where(predicate);

        List<Artist> artists = em.createQuery(criteriaQuery).getResultList();
        return artists;

    }

    public List<Artist> findAll() throws SQLException {
        return em.createNamedQuery("Artist.findAll")
                .getResultList();
    }

    public Class<Artist> getEntityClass() {
        return Artist.class;
    }


}
