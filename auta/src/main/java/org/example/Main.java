package org.example;


import static spark.Spark.*;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import org.example.Car;
import org.example.Airbag;
import java.util.ArrayList;

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

    }

    static String handleAdd(Request req, Response res) {
        String responsetext = req.body();
        System.out.println("RESPONSETEXT: " + responsetext);

        Gson gson = new Gson();
        Car car = gson.fromJson(responsetext, Car.class);

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
}