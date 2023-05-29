package factory;

import factory.album.AlbumDao;
import factory.artist.ArtistDao;
import factory.genre.GenreDao;

public interface DaoFactory {

    ArtistDao createArtistDao();
    GenreDao createGenreDao();
    AlbumDao createAlbumDao();
}
