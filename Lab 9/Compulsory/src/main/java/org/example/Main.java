package org.example;

import entity.Artist;
import repository.ArtistRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        testJPA();
    }

    public static void testJPA() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Artist artist = new Artist("Beatles");
            em.persist(artist);

            Artist a = (Artist) em.createQuery(
                            "select e from Artist e where e.name='Beatles'")
                    .getSingleResult();
            a.setName("The Beatles");
            em.getTransaction().commit();
            ArtistRepository artistRepository = new ArtistRepository(em);
            Artist artist1 = artistRepository.findById(1);

            System.out.println(artist1.getName());

        } finally {
            em.close();
            emf.close();
        }
        System.out.println("Done");
    }
}