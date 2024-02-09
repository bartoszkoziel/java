package org.example;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.example.controller.PhotoserviceImpl;
import org.example.model.Photo;
import org.example.response.ResponseEntity;
import spark.Request;
import spark.Response;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;

public class AppREST {

    private static PhotoserviceImpl photoserviceImpl = new PhotoserviceImpl();
    public static void main(String[] args) {

        port(7777);
        get("/test", (req, res) -> "tewwst");
        get("/api/photos", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");

            Type listType = new TypeToken<HashMap<String, Photo>>() {}.getType();

            JsonElement data = gson.fromJson(photoserviceImpl.getPhotos(), listType);
            System.out.print("DATA CHECK: \n" + data);

            return gson.toJson(new ResponseEntity("photos found", "SUCCESS", data));
        });
        get("/api/photos/:id", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");

            String id = req.params("id");
            Type listType = new TypeToken<HashMap<String, Photo>>() {}.getType();
            JsonElement data = gson.fromJson(photoserviceImpl.getPhotoById(id), listType);

            if (Objects.equals(data, "")) {
                return gson.toJson(new ResponseEntity("photo not found", "ERROR"));
            }

            return gson.toJson(new ResponseEntity("photo found", "SUCCESS", data));
        });
        get("/api/photos/photo/:name", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");

            String name = req.params("name");
            String data = photoserviceImpl.getPhotoByName(name);

            if (Objects.equals(data, "")) {
                return gson.toJson(new ResponseEntity("photo not found", "ERROR"));
            }

            return gson.toJson(new ResponseEntity("photo found", "SUCCESS", data));
        });
        delete("/api/photos/:id", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");

            String id = req.params("id");
            Boolean data = photoserviceImpl.deletePhotoById(id);

            if (!data) {
                return gson.toJson(new ResponseEntity("photo not found", "ERROR"));
            }

            return gson.toJson(new ResponseEntity("photo" + id + "deleted", "SUCCESS"));
        });
        put("/api/photos/:id", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");

            System.out.println("REQ.BODY: " + req.body());

            String id = req.params("id");
            Boolean data = photoserviceImpl.renamePhotoById(id, req.body());

            if (!data) {
                return gson.toJson(new ResponseEntity("photo not found", "ERROR"));
            }

            return gson.toJson(new ResponseEntity("photo " + id + " renamed to: " + req.body(), "SUCCESS"));
        });
        get("/api/photos/data/:id", (req, res)-> {
            String filepath = photoserviceImpl.getPathById(req.params("id"));

            System.out.println("FILEPATH ::: " + filepath);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if(filepath == "") {

                res.type("application/json");
                System.out.println("ABORTING, PHOTO DOES NOT EXIST");
                return gson.toJson(new ResponseEntity("photo not found", "ERROR"));
            }

            res.type("image/jpeg");
            System.out.println("RETURNING STREAM");
            return new java.io.FileInputStream(filepath);

        });
    }
}