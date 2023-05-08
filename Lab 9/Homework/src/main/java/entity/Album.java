package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.title"),
        @NamedQuery(name = "Album.findByArtist",
                query = "select e from Album e where e.artist = ?1"),
        @NamedQuery(name = "Album.findByTitle",
                query = "select e from Album e where e.title = ?1"),
        @NamedQuery(name = "Album.findByReleaseYear",
                query = "select e from Album e where e.releaseYear = ?1")
})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;


    @JoinColumn(name = "artist_id")
    private Artist artist;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    private List<Genre> genres;
}
