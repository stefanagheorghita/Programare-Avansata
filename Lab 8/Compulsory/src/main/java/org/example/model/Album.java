package org.example.model;

import java.util.List;

public class Album  extends Superclass{


    private String title;
    private String artist;
    private int releaseYear;
    private List<String> genres;

    public Album(int id, String title, String artist, int releaseYear, List<String> genres) {
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


}
