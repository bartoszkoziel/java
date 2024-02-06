package org.example.model;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    public String id;
    @SerializedName("fileName")
    public String name;
    @SerializedName("filePath")
    public String path;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Photo(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
}
