package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album_genres")
public class AlbumToGenres extends AbstractEntity implements Serializable {

    public AlbumToGenres() {
    }

    public AlbumToGenres(Album album, Genre genre) {
        this.albumId = album.getId();
        this.genreId = genre.getId();
    }

    @JoinColumn(name = "album_id", nullable = false)
    private Integer albumId;

    @JoinColumn(name = "genre_id", nullable = false)
    private Integer genreId;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }
}