package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {

    private String id;
    private String name;
    private String location;
    private Map<String, Object> tags = new HashMap<>();

    public Document() {
    }

    public Document(String id) {
        this.id = id;
    }

    public Document(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public Document(String id, String name, String location, Map<String, Object> tags) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.tags = tags;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }
}
