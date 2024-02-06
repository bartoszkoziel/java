package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Photo;
import org.example.response.ResponseEntity;
import spark.Request;
import spark.Response;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhotoserviceImpl implements Photoservice {
    private Map<String, Photo> photosMap;
    private static String currPath = System.getProperty("user.dir") + "\\src\\main\\resources\\public";

    @Override
    public String getPhotos() {
        photosMap = createPhotosMap();
        Gson gson = new Gson();
        Type listType = new TypeToken<Map<String, Photo>>() {}.getType();
        String jsonString = gson.toJson(photosMap, listType);

        System.out.println("HERE: " + jsonString);

        return jsonString;
    }

    @Override
    public String getPhotoById(String id) {
        photosMap = createPhotosMap();
        Gson gson = new Gson();

        if (photosMap.get(id) == null) {
            return "";
        }

        String jsonString = gson.toJson(photosMap.get(id), Photo.class);
        System.out.println("HERE: " + jsonString);

        return jsonString;
    }

    @Override
    public String getPhotoByName(String name) {
        photosMap = createPhotosMap();
        Gson gson = new Gson();

        for (Photo value : photosMap.values()) {
            if(Objects.equals(value.getName(), name)){
                String jsonString = gson.toJson(value, Photo.class);
                System.out.println("HERE FOUND: " + jsonString);
                return jsonString;
            }
        }

        return "";
    }

    @Override
    public Boolean deletePhotoById(String id) {
        photosMap = createPhotosMap();
        Gson gson = new Gson();

        if (photosMap.get(id) == null) {
            return false;
        }

        File fileToDelete = new File(photosMap.get(id).getPath());

        if (fileToDelete.delete()) {
            photosMap.remove(id);
            System.out.println("DELETED: " + id);

            return true;
        }
        return false;
    }

    @Override
    public Boolean renamePhotoById(String id, String newName) {
        photosMap = createPhotosMap();
        Gson gson = new Gson();

        System.out.println("NEWNAME: " + newName);

        if (photosMap.get(id) == null) {
            return false;
        }

        File fileToRename = new File(photosMap.get(id).getPath());

        File newFile = new File(currPath + "\\" + newName);
        if(fileToRename.renameTo(newFile)){
            Photo oldPhoto = photosMap.get(id);
            Photo newPhoto = new Photo(oldPhoto.getId(), newName, "upload/" + newName);

            photosMap.replace(id, newPhoto);
            return true;
        }

        return false;
    }

    public static Map<String, Photo> createPhotosMap() {
        String directoryPath = System.getProperty("user.dir") + "/src/main/resources/public";
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null) {
            System.err.println("Error: Unable to read directory " + directoryPath);
            return null;
        }

        Map<String, Photo> photosMap = new HashMap<>();
        int idCounter = 0;

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String filePath = file.getPath();
                Photo photo = new Photo(String.valueOf(idCounter), fileName, filePath);
                photosMap.put(String.valueOf(idCounter), photo);
                idCounter++;
            }
        }

        return photosMap;
    }
}
