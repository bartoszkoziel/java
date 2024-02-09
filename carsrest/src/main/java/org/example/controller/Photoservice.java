package org.example.controller;

import org.example.response.ResponseEntity;
import spark.Request;
import spark.Response;

import java.io.FileInputStream;

public interface Photoservice {
    String getPhotos();
    String getPhotoById(String id);
    String getPhotoByName(String name);
    Boolean deletePhotoById(String id);
    Boolean renamePhotoById(String id, String newname);
    String getPathById(String id);

}
