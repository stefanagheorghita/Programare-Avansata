package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findByName",
                query = "SELECT g FROM Genre g WHERE g.name = :name")
})
public class Genre extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "album_genre", joinColumns = {@JoinColumn(name = "genre_id")}, inverseJoinColumns = {@JoinColumn(name = "album_id")})
    List<Album> albums = new ArrayList<>();


    public Genre() {
        this.id = null;
        this.name = null;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                '}';
    }
}