package org.example.entries;

import java.util.Map;

public class Book extends Document {
    static int x=1;
    public Book() {
        this.id= String.valueOf("book"+ x);
        x++;
    }

    public Book(String name, String location) {
        super(name, location);
        this.id= String.valueOf("book"+ x);
        x++;
    }

    public Book(String name, String location, Map<String, Object> tags) {
        super(name, location, tags);
        this.id= String.valueOf("book"+ x);
        x++;
    }


}
