package org.example.model;

import java.util.List;

public class Album  extends Superclass{


    private String title;
    private Artist artist;
    private int releaseYear;
    private List<Genre> genres;

    public Album(int id, String title, Artist artist, int releaseYear, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", releaseYear=" + releaseYear +
                ", genres=" + genres +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
