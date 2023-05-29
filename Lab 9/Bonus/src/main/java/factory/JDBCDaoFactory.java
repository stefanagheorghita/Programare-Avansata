package factory;

import factory.album.AlbumDao;
import factory.album.JDBCAlbumDao;
import factory.artist.ArtistDao;
import factory.artist.JDBCArtistDao;
import factory.genre.GenreDao;
import factory.genre.JDBCGenreDao;

public class JDBCDaoFactory implements DaoFactory {

        @Override
        public ArtistDao createArtistDao() {
            return new JDBCArtistDao();
        }

        @Override
        public GenreDao createGenreDao() {
            return new JDBCGenreDao();
        }

        @Override
        public AlbumDao createAlbumDao() {
            return new JDBCAlbumDao();
        }
}
