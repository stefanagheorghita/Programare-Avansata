package org.example.entries;

import java.util.Map;
import java.util.Random;


public class Article extends Document {
    static int x = 0;
    public Article() {


        this.id= String.valueOf("article"+ x);
        x++;

    }

    public Article(String name, String location, Map<String, Object> tags) {
        super(name, location, tags);
        this.id= String.valueOf("article"+ x);
        x++;
    }

    public Article(String name, String location) {

        super(name, location);
        this.id= String.valueOf("article"+ x);
        x++;
    }



}
