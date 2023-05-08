package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album_genres")
public class AlbumToGenres extends AbstractEntity implements Serializable {

    @JoinColumn(name = "album_id", nullable = false)
    private Integer albumId;

    @JoinColumn(name = "genre_id", nullable = false)
    private Integer genreId;

}