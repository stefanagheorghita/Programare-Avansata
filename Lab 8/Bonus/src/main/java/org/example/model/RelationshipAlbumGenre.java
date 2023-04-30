package org.example.model;

public class RelationshipAlbumGenre {
    private int id;
    private Album album;
    private Genre genre;

    public RelationshipAlbumGenre(int id, Album album, Genre genre) {
        this.id = id;
        this.album = album;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "RelationshipAlbumGenre{" +
                "id=" + id +
                ", album=" + album +
                ", genre=" + genre +
                '}';
    }
}
