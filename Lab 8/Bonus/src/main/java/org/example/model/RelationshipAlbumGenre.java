package org.example.model;

public class RelationshipAlbumGenre {
    private int id;
    private int albumId;
    private int genreId;

    public RelationshipAlbumGenre(int id, int albumId, int genreId) {
        this.id = id;
        this.albumId = albumId;
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "RelationshipAlbumGenre{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", genreId=" + genreId +
                '}';
    }
}
