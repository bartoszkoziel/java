package org.example.controller;

import org.example.response.ResponseEntity;
import spark.Request;
import spark.Response;

public interface Photoservice {
    String getPhotos();
    String getPhotoById(String id);
    String getPhotoByName(String name);
    Boolean deletePhotoById(String id);
    Boolean renamePhotoById(String id, String newname);
//    ResponseEntity getPhotoStreamById(String id);

}
