package org.example.model;

import java.util.Date;
import java.util.List;

public class Playlist {

    private Date creationDate;
    private int id;
    private String name;

    private List<Album> albums;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
