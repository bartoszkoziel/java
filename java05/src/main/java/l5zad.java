import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import java.util.ArrayList;

import static spark.Spark.*;

public class l5zad {
    private static ArrayList<Car> list1 = new ArrayList<Car>();

    public static void main(String[] args) {

        staticFiles.location("/public");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);


        get("/add", (req, res) -> {

            String model = req.queryParams("model");
            int doors = Integer.parseInt(req.queryParams("doors"));
            boolean damaged = false;
            if (req.queryParams("damaged") == "on") {
                damaged = true;
            }

            System.out.println(req.queryParams("damaged"));
            System.out.println("HERE!!!: " + model + doors + damaged);
//            list1.add(new Car())

            Gson gson = new Gson();
            return gson.toJson(list1, ArrayList.class);
        });
    }
}

class Car{
    public String model;
    public int doors;
    public boolean damaged;

    public Car(String model, int doors, boolean damaged) {
        this.model = model;
        this.doors = doors;
        this.damaged = damaged;
    }
}