package factory;

import factory.album.AlbumDao;
import factory.album.JPAAlbumDao;
import factory.artist.ArtistDao;
import factory.artist.JPAArtistDao;
import factory.genre.GenreDao;
import factory.genre.JPAGenreDao;

import javax.persistence.EntityManager;

public class JPADaoFactory implements DaoFactory{

            EntityManager em;

            public JPADaoFactory(EntityManager em) {
                this.em = em;
            }

            @Override
            public ArtistDao createArtistDao() {
                return new JPAArtistDao(em);
            }

            @Override
            public GenreDao createGenreDao() {
                return new JPAGenreDao(em);
            }

            @Override
            public AlbumDao createAlbumDao() {
                return new JPAAlbumDao(em);
            }
}
