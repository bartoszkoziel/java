package org.example;


import static spark.Spark.*;

import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import org.example.Car;
import org.example.Airbag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Main {
    private static ArrayList<Car> carlist = new ArrayList<Car>();
    public static void setCarlist(ArrayList<Car> carlist) {
        Main.carlist = carlist;
    }
    public static ArrayList<Car> getCarlist() {
        return carlist;
    }

    public static void main(String[] args) {

        staticFiles.location("/public");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);

        System.out.printf("Hello and welcome!");
        post("/add", (req, res) -> handleAdd(req, res));
        get("/json", (req, res) -> handleJson(req, res));
        post("/delete", (req, res) -> handleDelete(req, res));
        post("/update", (req, res) -> handleUpdate(req, res));
    }

    static String handleAdd(Request req, Response res) {
        String responsetext = req.body();
        System.out.println("RESPONSETEXT: " + responsetext);
        Gson gson = new Gson();

        Car car = gson.fromJson(responsetext, Car.class);

        String uuid = Generators.randomBasedGenerator().generate().toString();
        car.setUuid(uuid);

        System.out.println(car);

        ArrayList<Car> temp = getCarlist();
        temp.add(car);
        setCarlist(temp);

        res.type("application/json");
        return responsetext;
    }

    static String handleJson(Request req, Response res) {
        Gson gson = new Gson();
        return gson.toJson(getCarlist(), ArrayList.class );
    }

    static String handleDelete(Request req, Response res) {
        String jsonBody = req.body();
        System.out.println("JSON Body: " + jsonBody);

        // Parse JSON to extract the UUID
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonBody).getAsJsonObject();
        String uuidToDelete = jsonObject.get("uuid").getAsString();
        System.out.println("UUID to delete: " + uuidToDelete);

        ArrayList<Car> carList = getCarlist();

        // Delete the car from the carlist based on its UUID
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getUuid().equals(uuidToDelete)) {
                iterator.remove();
                setCarlist(carList);  // Update the carlist with the modified list
                res.type("application/json");
                return "Car deleted successfully";
            }
        }

        // If the car was not found
        res.status(404);
        return "Car not found";
    }
    static String handleUpdate(Request req, Response res) {
        String jsonBody = req.body();
        System.out.println("JSON Body: " + jsonBody);

        // Parse JSON to extract the UUID and update data
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonBody).getAsJsonObject();

        String uuidToUpdate = jsonObject.get("uuid").getAsString();
        String newModel = jsonObject.get("newModel").getAsString();
        int newRok = jsonObject.get("newRok").getAsInt();

        System.out.println("UUID to update: " + uuidToUpdate);
        System.out.println("New Model: " + newModel);
        System.out.println("New Rok: " + newRok);

        ArrayList<Car> carList = getCarlist();

        // Update the car in the carlist based on its UUID
        for (Car car : carList) {
            if (car.getUuid().equals(uuidToUpdate)) {
                car.setModel(newModel);
                car.setRok(newRok);
                setCarlist(carList);  // Update the carlist with the modified list
                res.type("application/json");
                return "Car updated successfully";
            }
        }

        // If the car was not found
        res.status(404);
        return "Car not found";
    }
}