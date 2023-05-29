package org.example;

import entity.Album;
import entity.Artist;
import entity.Genre;
import factory.ConnectionPool;
import factory.DaoFactory;
import factory.JDBCDaoFactory;
import factory.JPADaoFactory;
import factory.album.AlbumDao;
import factory.artist.ArtistDao;
import factory.genre.GenreDao;

import java.sql.SQLException;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
;
import java.util.Scanner;

public class Bonus {
    public static void main(String[] args) {
        String implementationType;
        EntityManagerFactory emf = EntityManagerFactoryObject.getInstance();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter implementation type (jdbc/jpa): ");
            implementationType = scanner.nextLine();
            if (!(implementationType.equals("jdbc") || implementationType.equals("jpa"))) {
                System.out.println("Invalid implementation type specified");
                continue;
            }
            System.out.println("Enter table to work with: (artists/albums/genres): ");
            String table = scanner.nextLine();
            if (!(table.equals("artists") || table.equals("albums") || table.equals("genres"))) {
                System.out.println("Invalid table specified");
                continue;
            }
            System.out.println("Enter operation to perform: (create/find by id/find by name/find all): ");
            String operation = scanner.nextLine();
            if (!(operation.equals("create") || operation.equals("find by id") || operation.equals("find by name") || operation.equals("find all"))) {
                System.out.println("Invalid operation specified");
                continue;
            }
            perform(table, operation, implementationType, em, transaction);
        }
    }

    private static void perform(String table, String operation, String implementationType, EntityManager em, EntityTransaction transaction) {
        DaoFactory daoFactory;

        if (implementationType.equals("jdbc")) {
            daoFactory = new JDBCDaoFactory();
        } else if (implementationType.equals("jpa")) {

            daoFactory = new JPADaoFactory(em);
            transaction.begin();
        } else throw new IllegalArgumentException("Invalid implementation type specified");
        ArtistDao artistDao = daoFactory.createArtistDao();
        GenreDao genreDao = daoFactory.createGenreDao();
        AlbumDao albumDao = daoFactory.createAlbumDao();
        switch (operation) {

            case "create":
                System.out.println("Creating new entry in " + table + " table");
                System.out.println("Enter name: ");
                String name = new Scanner(System.in).nextLine();
                try {
                    switch (table) {
                        case "artists":
                            artistDao.create(name);
                            System.out.println("Created artist");
                            break;
                        case "albums":
                            System.out.println("Enter release year: ");
                            int releaseYear = new Scanner(System.in).nextInt();
                            System.out.println("Enter artist: ");
                            String artistName = new Scanner(System.in).nextLine();
                            System.out.println("Enter genre: ");
                            String genreName = new Scanner(System.in).nextLine();
                            albumDao.create(releaseYear, name, artistName, genreName);
                            System.out.println("Created album");
                            break;
                        case "genres":
                            genreDao.create(name);
                            System.out.println("Created genre");
                            break;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error creating entry");
                    e.printStackTrace();
                    break;
                }
            case "find by id":
                System.out.println("Enter id: ");
                int id = new Scanner(System.in).nextInt();
                try {
                    switch (table) {
                        case "artists":
                            Artist artist = artistDao.findById(id);
                            if(artist == null)
                                System.out.println("Artist not found");
                            else
                            System.out.println(artist);
                            break;
                        case "albums":
                            Album album = albumDao.findById(id);
                            if(album == null)
                                System.out.println("Album not found");
                            else
                            System.out.println(album);
                            break;
                        case "genres":
                            Genre genre = genreDao.findById(id);
                            if(genre == null)
                                System.out.println("Genre not found");
                            else
                            System.out.println(genre);
                            break;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error finding entry");
                    break;
                }

            case "find by name":
                System.out.println("Enter name: ");
                String name1 = new Scanner(System.in).nextLine();
                try {
                    switch (table) {
                        case "artists":
                            Artist artist = artistDao.findByName(name1);
                            if(artist == null)
                                System.out.println("Artist not found");
                            else
                            System.out.println(artist);
                            break;
                        case "albums":
                            List<Album> albums = albumDao.findByName(name1);
                            if (albums.isEmpty())
                                System.out.println("Album not found");
                            System.out.println(albums);
                            break;
                        case "genres":
                            Genre genre = genreDao.findByName(name1);
                            if(genre == null)
                                System.out.println("Genre not found");
                            else
                            System.out.println(genre);
                            break;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error finding entry");
                    break;
                }
            case "find all":
                try {
                    switch (table) {
                        case "artists":
                            List<Artist> artists = artistDao.findAll();
                            if (artists.isEmpty())
                                System.out.println("No artists found");
                            System.out.println(artists);
                            break;
                        case "albums":
                            List<Album> albums = albumDao.findAll();
                            if (albums.isEmpty())
                                System.out.println("No albums found");
                            System.out.println(albums);
                            break;
                        case "genres":
                            List<Genre> genres = genreDao.findAll();
                            if (genres.isEmpty())
                                System.out.println("No genres found");
                            System.out.println(genres);
                            break;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error finding entry");
                    break;
                }
        }
        if (implementationType.equals("jpa")) {
            transaction.commit();
        }
        else {
            try {
                ConnectionPool.getConnection().commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
