package entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll",
                query = "select e from Album e order by e.title"),
        @NamedQuery(name = "Album.findByTitle",
                query = "select e from Album e where e.title = ?1"),
        @NamedQuery(name = "Album.findByReleaseYear",
                query = "select e from Album e where e.releaseYear = ?1"),
        @NamedQuery(name = "Album.findByArtist",
                query = "select e from Album e where e.artist = :artist")

})
public class Album extends AbstractEntity implements Serializable {

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name="genre")
    private String genreNames;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "album_genre",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;



    public Album(String title, Integer releaseYear, Artist artist, List<Genre> genres) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genres = genres;
    }

    public Album() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Genre> getGenres() {
        List<Genre> genres = new ArrayList<>();
        if (genreNames != null && !genreNames.isEmpty()) {
            String[] genreArray = genreNames.split(",");
            for (String genreName : genreArray) {
                Genre genre = new Genre();
                genre.setName(genreName);
                genres.add(genre);
            }
        }
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres=genres;
        StringBuilder sb = new StringBuilder();
        for (Genre genre : genres) {
            sb.append(genre.getName()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        genreNames = sb.toString();
    }

    public String getGenreNames() {
        return genreNames;
    }

    @Override
    public String toString() {
        return "Album{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
