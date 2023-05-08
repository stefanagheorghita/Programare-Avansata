package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findAll",
                query = "select e from Artist e order by e.name"),
        @NamedQuery(name = "Artist.findByName",
                query = "select e from Artist e where e.name = :name")
})

public class Artist extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String name;

    public Artist() {
        this.id = null;
        this.name = null;
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                '}';
    }
}