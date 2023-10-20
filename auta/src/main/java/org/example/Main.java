package org.example;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import org.example.Car;
import org.example.Airbag;
import java.util.ArrayList;

public class Main {
    private ArrayList<Car> carlist;

    public void setCarlist(ArrayList<Car> carlist) {
        this.carlist = carlist;
    }

    public ArrayList<Car> getCarlist() {
        return carlist;
    }

    public static void main(String[] args) {

        staticFiles.location("/public");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);

        System.out.printf("Hello and welcome!");
        post("/add", (req, res) -> handleAdd(req, res));
    }

    static String handleAdd(Request req, Response res) {
        String responsetext = req.body();
        res.type("application/json");

        return responsetext;
    }
}