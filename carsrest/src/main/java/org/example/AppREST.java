package org.example;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.controller.PhotoserviceImpl;
import org.example.response.ResponseEntity;
import spark.Request;
import spark.Response;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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

            String data = photoserviceImpl.getPhotos();

            return gson.toJson(new ResponseEntity("photos found", "SUCCESS", data));
        });
        get("/api/photos/:id", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            res.header("Access-Control-Allow-Origin", "*");
            res.type("application/json");

            String id = req.params("id");
            String data = photoserviceImpl.getPhotoById(id);

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
            // Set content type to image/jpeg
            res.type("image/jpeg");

            // Provide the path to your image file
            String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\public\\honda_fit.jpg";

            // Return the image file
            return new java.io.FileInputStream(imagePath);
        });
    }
}