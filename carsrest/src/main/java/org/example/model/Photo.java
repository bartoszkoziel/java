package org.example.model;

public class Photo {
    private String id;
    private String name;
    private String path;

    public Photo(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
