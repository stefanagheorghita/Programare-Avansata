package org.example.model;

public class Artist extends Superclass {

    private String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
