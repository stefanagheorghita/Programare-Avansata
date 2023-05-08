import entity.Album;
import entity.Artist;
import entity.Genre;
import org.junit.Test;
import repository.AlbumRepository;
import repository.ArtistRepository;
import repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {

    public static void main(String[] args) {
        Tests test = new Tests();
        test.testJPA();
    }

    @Test
    public void testJPA() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Artist artist = new Artist("Beatles");
        em.persist(artist);

        Artist a = (Artist) em.createQuery(
                        "select e from Artist e where e.name='Beatles'")
                .getSingleResult();
        a.setName("The Beatles");
        em.getTransaction().commit();
        assertEquals(a.getName(), "The Beatles");
        em.close();
        emf.close();
    }


    @Test
    public void artistQueries() {
        long startTime = System.currentTimeMillis();
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();
        ArtistRepository artistRepository = new ArtistRepository(em);
        List<Artist> artists = artistRepository.findByNamePattern("%Artist100%");
        System.out.println(artists);
        List<Artist> expectedArtists = new ArrayList<>();
        expectedArtists.add(new Artist("Artist100"));
        for (int i = 0; i < 10; i++)
            expectedArtists.add(new Artist("Artist100" + i));
        assertEquals(expectedArtists.size(), artists.size());
        for (int i = 0; i < expectedArtists.size(); i++) {
            Artist expectedArtist = expectedArtists.get(i);
            Artist actualArtist = artists.get(i);
            assertEquals(expectedArtist.getName(), actualArtist.getName());
        }
        em.close();
        emf.close();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " ms");

    }

    @Test
    public void albumQueries()
    {
        long startTime = System.currentTimeMillis();
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();
        AlbumRepository albumRepository=new AlbumRepository(em);
        List<Album> albums=albumRepository.findByReleaseYearInterval(1970,2000);
        System.out.println(albums);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " ms");
    }

    @Test
    public void genreQueries()
    {
        long startTime = System.currentTimeMillis();
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();
        GenreRepository genreRepository=new GenreRepository(em);
        Genre genre=genreRepository.findByName("Rock");
        assertEquals(genre.getName(),"Rock");
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " ms");
    }

}


