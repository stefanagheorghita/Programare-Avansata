package repository;

import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.GenericDeclaration;
import java.util.List;

public class ArtistRepository extends DataRepository<Artist, Integer> {
    private EntityManager em;

    public ArtistRepository(EntityManager em) {
        this.em = em;
    }

//    public void create(Artist artist) {
//        em.persist(artist);
//    }

    public Artist findById(Integer id) {
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

    public Class<Artist> getEntityClass() {
        return Artist.class;
    }
}
