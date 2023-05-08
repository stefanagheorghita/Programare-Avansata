package entity;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

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

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}