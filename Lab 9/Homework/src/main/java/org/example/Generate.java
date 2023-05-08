package org.example;

import com.github.javafaker.Faker;
import entity.AbstractEntity;
import entity.Album;
import entity.Artist;
import entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {


    public static void main(String[] args) {
        System.out.println("Generating data...");
        EntityManagerFactory emf = EntityManagerFactoryObject.getInstance();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        System.out.println("Created EntityManagerFactory and EntityManager");
        try {
            transaction.begin();
            System.out.println("Transaction started");
            long startTime = System.currentTimeMillis();
            createGenres(em);
            System.out.println("Created genres");
            generate(2000, em);
            System.out.println("Done generating albums and artists");
            transaction.commit();
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " ms");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }


    private static void generate(int numAlbums, EntityManager em) {
        System.out.println("Generating " + numAlbums + " albums and artists...");
        Faker faker = new Faker();
        Random random = new Random();
        List<Genre> listOfGenres = em.createQuery("select e from Genre e").getResultList();
        List<String> allArtistNames = em.createQuery("select e.name from Artist e").getResultList();
        for (int i = 0; i < numAlbums; i++) {
            System.out.println("Generating album " + i);
            Album album = new Album();
            album.setTitle("Album " + i);
            album.setReleaseYear(random.nextInt(1900, 2023));
            Artist artist = new Artist();
            String artistName = faker.artist().name();
            if (allArtistNames.contains(artistName)) {
                artist = (Artist) em.createQuery("select e from Artist e where e.name = :name")
                        .setParameter("name", artistName)
                        .getSingleResult();
            } else {
                artist.setName(artistName);
                allArtistNames.add(artistName);
            }
            album.setArtist(artist);
            int noOfGenres = random.nextInt(1, 5);
            List<String> genresNames = new ArrayList<>();
            List<Genre> genres = new ArrayList<>();
            for (int j = 0; j < noOfGenres; j++) {
                int genreIndex = random.nextInt(0, listOfGenres.size());
                Genre genre = listOfGenres.get(genreIndex);
                if (!genresNames.contains(genre.getName())) {
                    genresNames.add(genre.getName());
                    genres.add(genre);
                   // genre.getAlbums().add(album);
                } else {
                    j--;
                }
            }
            album.setGenres(genres);
            em.persist(artist);
            em.persist(album);
        }
        for(int i=0;i<numAlbums;i++)
        {
            System.out.println("Generating artist " + i);
            Artist artist=new Artist("Artist"+i);
            em.persist(artist);
        }
    }


    public static void createGenres(EntityManager em) {
        Faker faker = new Faker();
        List<String> allGenres = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            System.out.println("Generating genre " + i);
            Genre genre = new Genre();
            do {
                genre.setName(faker.music().genre());
            } while (allGenres.contains(genre.getName()));
            allGenres.add(genre.getName());
            em.persist(genre);
        }

        for(int i=15;i<30;i++)
        {
            System.out.println("Generating genre " + i);
            Genre genre = new Genre();
            genre.setName("Genre"+i);
        }

    }
}

